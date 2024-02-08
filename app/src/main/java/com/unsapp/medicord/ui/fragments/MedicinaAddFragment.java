package com.unsapp.medicord.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.unsapp.medicord.R;
import com.unsapp.medicord.data.models.Medicina;
import com.unsapp.medicord.data.models.UnidadMedicina;
import com.unsapp.medicord.data.sqlite.controllers.MedicinaController;
import com.unsapp.medicord.data.sqlite.controllers.UnidadMedicinaController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MedicinaAddFragment extends Fragment {
    private Button btnAgregar, btnCancelar;
    private EditText etNombre, etDosis, etDescripcion;
    private MedicinaController controller;
    private List<UnidadMedicina> listaUnidadMedicina;
    private long uniMedSeleccionadaCod;
    private Spinner spinNivel, spinUnidadMedida;

    public MedicinaAddFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller = new MedicinaController(this.getContext());
        listaUnidadMedicina = new UnidadMedicinaController(this.getContext()).readAll();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_medicina_add, container, false);
        etNombre = root.findViewById(R.id.etNombre);
        etDescripcion = root.findViewById(R.id.etDescripcion);
        etDosis = root.findViewById(R.id.etFrec);

        spinNivel = root.findViewById(R.id.spinNivel);
        spinUnidadMedida = root.findViewById(R.id.spinUnidadMedida);
        HashMap<Long, String> mapUnidadMedicina = generarHashMapUnidad(listaUnidadMedicina);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this.requireContext(),
                android.R.layout.simple_spinner_item,
                new ArrayList<>(mapUnidadMedicina.values())
        );
        Log.e("ERROOOR","Example :"+mapUnidadMedicina);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinUnidadMedida.setAdapter(adapter);
        spinUnidadMedida.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                uniMedSeleccionadaCod = listaUnidadMedicina.get(position).getUniMedCod();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAgregar = root.findViewById(R.id.btnAgregar);
        btnCancelar = root.findViewById(R.id.btnCancelar);

        btnAgregar.setOnClickListener(v -> {
            etNombre.setError(null);
            etDescripcion.setError(null);
            etDosis.setError(null);

            String nombre = etNombre.getText().toString();
            if ("".equals(nombre)) {
                etNombre.setError("Escriba el nombre del articulo");
                etNombre.requestFocus();
                return;
            }
            String descripcion = etDescripcion.getText().toString();

            long unidadMedida = uniMedSeleccionadaCod;

            String dosisString = etDosis.getText().toString();
            double dosis;
            if ("".equals(dosisString)) {
                etDosis.setError("Escribe el precio unitario");
                etDosis.requestFocus();
                return;
            }
            try {
                dosis = Double.parseDouble(dosisString);
            } catch (NumberFormatException e) {
                etDosis.setError("Escribe un número");
                etDosis.requestFocus();
                return;
            }
            int nivel = spinNivel.getSelectedItemPosition()+1;
            Log.d("EL NIVEL ESCOGIDO","asd-"+nivel);
            // Ya pasó la validación
            Medicina nuevoArticulo = new Medicina(nombre,descripcion,unidadMedida,dosis, nivel,0,"A");
            long id = controller.create(nuevoArticulo);
            if (id == -1) {
                // De alguna manera ocurrió un error
                Toast.makeText(getActivity(), "Error al guardar el articulo. Intenta de nuevo", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Se guardo el articulo correctamente", Toast.LENGTH_SHORT).show();
                volverCasa();
            }
        });
        btnCancelar.setOnClickListener(v -> volverCasa());
        return root;
    }
    void volverCasa(){
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, MedicinaFragment.class,null);
        fragmentTransaction.commit();
    }
    private HashMap<Long, String> generarHashMapUnidad(List<UnidadMedicina> listaUnidades) {
        HashMap<Long, String> hashMap = new HashMap<>();
        for (UnidadMedicina unidad : listaUnidades) {
            hashMap.put(unidad.getUniMedCod(), unidad.getUniMedAli());
        }
        return hashMap;
    }
}