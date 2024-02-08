package com.unsapp.medicord.data.sqlite.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.unsapp.medicord.data.models.UnidadMedicina;
import com.unsapp.medicord.data.sqlite.BDHelper;
import com.unsapp.medicord.data.sqlite.Controller;

public class UnidadMedicinaController extends Controller<UnidadMedicina> {
    public UnidadMedicinaController(Context context) {
        super(new BDHelper(context,null,1), UnidadMedicina.class);
    }

    @Override
    protected long getCodigo(UnidadMedicina model) {
        return model.getUniMedCod();
    }

    @Override
    protected ContentValues setFields(UnidadMedicina model) {
        ContentValues valoresModificar = new ContentValues();
        valoresModificar.put("UniMedNom",model.getUniMedNom());
        valoresModificar.put("UniMedAli",model.getUniMedAli());
        valoresModificar.put("UniMedEstReg",model.getUniMedEstReg());
        return valoresModificar;
    }

    @Override
    protected UnidadMedicina parseModel(Cursor cursor) {
        long UniMedCod = cursor.getLong(0);
        String UniMedNom = cursor.getString(1);
        String UniMedAli = cursor.getString(2);
        String UniMedEstReg = cursor.getString(3);
        return new UnidadMedicina(UniMedCod,UniMedNom,UniMedAli,UniMedEstReg);
    }
}
