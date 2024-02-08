package com.unsapp.medicord.data.sqlite.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unsapp.medicord.R;
import com.unsapp.medicord.data.models.Medicina;

import java.util.List;

public class MedicinaAdapter extends RecyclerView.Adapter<MedicinaAdapter.MyViewHolder>{
    private List<Medicina> listModels;

    public MedicinaAdapter(List<Medicina> listModels) {
        this.listModels = listModels;
    }
    public void setList(List<Medicina> articuloList) {
        this.listModels = articuloList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_medicine, parent, false);
        return new MyViewHolder(rootHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Medicina model = listModels.get(position);
        // Obtener los datos de la lista
        long cod = model.getMedCod();
        String nombre = model.getMedNom();

        // Y poner a los TextView los datos con setText
        holder.tvNom.setText(nombre);
        holder.tvCod.setText(String.valueOf(cod));
    }

    @Override
    public int getItemCount() {
        return listModels.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        final TextView tvNom, tvCod;

        MyViewHolder(View itemView) {
            super(itemView);
            this.tvNom = itemView.findViewById(R.id.tvNom);
            this.tvCod = itemView.findViewById(R.id.tvCod);
        }
    }
}
