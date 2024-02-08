package com.unsapp.medicord.data.sqlite.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unsapp.medicord.R;
import com.unsapp.medicord.data.models.UnidadMedicina;

import java.util.List;

public class UnidadMedicinaAdapter extends RecyclerView.Adapter<UnidadMedicinaAdapter.MyViewHolder>{
    private List<UnidadMedicina> listModels;

    public UnidadMedicinaAdapter(List<UnidadMedicina> listModels) {
        this.listModels = listModels;
    }
    public void setList(List<UnidadMedicina> listModels) {
        this.listModels = listModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_unidad_medicine, parent, false);
        return new MyViewHolder(rootHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        UnidadMedicina model = listModels.get(position);
        // Obtener los datos de la lista
        long cod = model.getUniMedCod();
        String nombre = model.getUniMedNom();

        // Y poner a los TextView los datos con setText
        holder.tvNom.setText(nombre);
        holder.tvCod.setText(String.valueOf(cod));
    }

    @Override
    public int getItemCount() {
        return listModels.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvNom, tvCod;

        MyViewHolder(View itemView) {
            super(itemView);
            this.tvNom = itemView.findViewById(R.id.tvNom);
            this.tvCod = itemView.findViewById(R.id.tvCod);
        }
    }
}
