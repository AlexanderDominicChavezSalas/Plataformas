package com.unsapp.medicord.data.sqlite.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.unsapp.medicord.data.models.Medicina;
import com.unsapp.medicord.data.sqlite.BDHelper;
import com.unsapp.medicord.data.sqlite.Controller;

public class MedicinaController extends Controller<Medicina> {
    public MedicinaController(Context context) {
        super(new BDHelper(context,null,1), Medicina.class);
    }

    @Override
    protected long getCodigo(Medicina model) {
        return model.getMedCod();
    }

    @Override
    protected ContentValues setFields(Medicina model) {
        ContentValues valoresModificar = new ContentValues();
        valoresModificar.put("MedNom",model.getMedNom());
        valoresModificar.put("MedDes",model.getMedDes());
        valoresModificar.put("UniMedCod",model.getUniMedCod());
        valoresModificar.put("MedDos",model.getMedDos());
        valoresModificar.put("MedNiv",model.getMedNiv());
        valoresModificar.put("MedCon",model.getMedCon());
        valoresModificar.put("MedEstReg",model.getMedEstReg());
        return valoresModificar;
    }

    @Override
    protected Medicina parseModel(Cursor cursor) {
        long MedCod = cursor.getLong(0);
        String MedNom = cursor.getString(1);
        String MedDes = cursor.getString(2);
        long UniMedCod = cursor.getLong(3);
        double MedDos = cursor.getInt(4);
        int MedNiv = cursor.getInt(5);
        int MedCon = cursor.getInt(6);
        String MedEstReg = cursor.getString(7);
        return new Medicina(MedCod,MedNom,MedDes,UniMedCod,MedDos,MedNiv,MedCon,MedEstReg);
    }
}
