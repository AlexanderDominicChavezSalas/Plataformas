package com.unsapp.medicord.data.sqlite.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.unsapp.medicord.data.models.Recordatorio;
import com.unsapp.medicord.data.sqlite.BDHelper;
import com.unsapp.medicord.data.sqlite.Controller;

public class RecordatorioController extends Controller<Recordatorio> {
    public RecordatorioController(Context context) {
        super(new BDHelper(context,null,1), Recordatorio.class);
    }

    @Override
    protected long getCodigo(Recordatorio model) {
        return model.getRecCod();
    }

    @Override
    protected ContentValues setFields(Recordatorio model) {
        ContentValues valoresModificar = new ContentValues();
        valoresModificar.put("RecFre",model.getRecFre());
        valoresModificar.put("RecFecIni",model.getRecFecIni());
        valoresModificar.put("RecFecFin",model.getRecFecFin());
        valoresModificar.put("RecEstReg",model.getRecEstReg());
        return valoresModificar;
    }

    @Override
    protected Recordatorio parseModel(Cursor cursor) {
        long UniMedCod = cursor.getLong(0);
        long MedCod = cursor.getLong(1);
        int RecFre = cursor.getInt(2);
        String RecFecIni = cursor.getString(3);
        String RecFecFin = cursor.getString(4);
        String RecEstReg = cursor.getString(5);
        return new Recordatorio(UniMedCod, MedCod, RecFre, RecFecIni, RecFecFin, RecEstReg);
    }
}
