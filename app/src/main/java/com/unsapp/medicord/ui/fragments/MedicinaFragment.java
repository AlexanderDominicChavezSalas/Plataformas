package com.unsapp.medicord.ui.fragments;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.unsapp.medicord.R;
import com.unsapp.medicord.data.models.Medicina;
import com.unsapp.medicord.data.sqlite.adapters.MedicinaAdapter;
import com.unsapp.medicord.data.sqlite.controllers.MedicinaController;
import com.unsapp.medicord.data.viewmodels.UserViewModel;
import com.unsapp.medicord.ui.helpers.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

public class MedicinaFragment extends Fragment {

    private MedicinaController controller;
    private MedicinaAdapter adapter;
    private List<Medicina> listaModels;
    private UserViewModel userViewModel;

    public MedicinaFragment() {
    }
    @Override
    public void onStart() {
        super.onStart();
        refrescarLista();
    }
    public void refrescarLista() {
        if (adapter == null) return;
        listaModels = controller.readAll();
        adapter.setList(listaModels);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false); //--

        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class); //--

        userViewModel.getUser().observe(getViewLifecycleOwner(), user -> {
            long userId = user.getId();
        });

        RecyclerView recyclerView = root.findViewById(R.id.recycler);
        controller = new MedicinaController(this.getContext());
        listaModels = new ArrayList<>();
        adapter = new MedicinaAdapter(listaModels);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this.getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Medicina model = listaModels.get(position);
                if(model.getMedEstReg().equals("A")){

                    Bundle bundle = new Bundle();
                    bundle.putLong("MedCod" ,model.getMedCod());
                    RecordatorioAddFragment fragmentTo = new RecordatorioAddFragment();
                    fragmentTo.setArguments(bundle);

                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, fragmentTo,null);
                    fragmentTransaction.commit();
                }else{
                    Toast.makeText(getActivity(), "Primero necesita activar el item", Toast.LENGTH_SHORT).show();
                }
            }

            @Override // Un toque largo
            public void onLongClick(View view, int position) {
                final Medicina model = listaModels.get(position);
                AlertDialog dialog = new AlertDialog
                        .Builder(getContext())
                        .setPositiveButton("Editar Medicina", (dialog1, which) -> {
                            Bundle bundle = new Bundle();
                            bundle.putLong("MedCod" ,model.getMedCod());
                            bundle.putString("MedNom" ,model.getMedNom());
                            bundle.putString("MedDes" ,model.getMedDes());
                            bundle.putLong("UniMedCod" ,model.getUniMedCod());
                            bundle.putDouble("MedDos" ,model.getMedDos());
                            bundle.putInt("MedNiv" ,model.getMedNiv());

                            MedicinaEditFragment fragmentTo = new MedicinaEditFragment();
                            fragmentTo.setArguments(bundle);

                            FragmentManager fragmentManager = getParentFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.fragment_container, fragmentTo,null);
                            fragmentTransaction.commit();
                        })
                        .setNeutralButton("Desactivar/Activar", (dialog1, which) -> {
                            controller.deactivate(model);
                            refrescarLista();
                        })
                        .setNegativeButton("Eliminar", (dialog1, which) -> {
                            controller.deleteLogic(model);
                            refrescarLista();
                        })
                        .setTitle("Confirmar")
                        .setMessage("¿Eliminar al articulo " + model.getMedNom() + "?")
                        .create();
                dialog.show();
            }
        }));
        return root;
    }
}