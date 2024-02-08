package com.unsapp.medicord.data.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class Controller<E> {
    protected final BDHelper ayudanteBaseDeDatos;
    protected final String NOMBRE_TABLA;
    protected final String CODIGO_TABLA;
    protected final String ESTADO_TABLA;
    protected final List<String> params= new ArrayList<>();
    protected boolean isActive = true;
    protected Controller(BDHelper ayudanteBaseDeDatos, Class<?> clase){
        this.ayudanteBaseDeDatos = ayudanteBaseDeDatos;
        NOMBRE_TABLA = clase.getSimpleName();

        Field[] campos = clase.getDeclaredFields();
        String tempCodigo = null;
        String tempEstado = null;
        for (Field campo : campos) {
            if (campo.isAnnotationPresent(Primary.class)) {
                tempCodigo  = campo.getName();
            }
            if (campo.isAnnotationPresent(Estado.class)) {
                tempEstado = campo.getName();
            }
            params.add(campo.getName());
        }
        CODIGO_TABLA = tempCodigo;
        ESTADO_TABLA = tempEstado;
    }
    protected abstract long getCodigo(E model);
    protected abstract ContentValues setFields(E model);
    public int deletePermanently(E model){
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        String[] argumentos = {String.valueOf(getCodigo(model))};
        return baseDeDatos.delete(NOMBRE_TABLA, CODIGO_TABLA+" = ?", argumentos);
    }
    private int changeStatus(long codigo, String status){
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valsActualizar = new ContentValues();
        valsActualizar.put(ESTADO_TABLA,status);
        String[] argumentos = {String.valueOf(codigo)};
        return baseDeDatos.update(NOMBRE_TABLA, valsActualizar, CODIGO_TABLA+" = ?", argumentos);
    }
    public int deleteLogic(E model){
        return changeStatus(getCodigo(model),"*");
    }
    public int deactivate(E model){
        if (isActive){
            isActive = false;
            return changeStatus(getCodigo(model),"I");
        }
        else{
            isActive = true;
            return changeStatus(getCodigo(model),"A");
        }
    }
    public int reactivate(E model){
        return changeStatus(getCodigo(model),"A");
    }
    public long create(E model){
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        return baseDeDatos.insert(NOMBRE_TABLA, null, setFields(model));
    }
    public int update(E model){
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        String[] argumentos = {String.valueOf(getCodigo(model))};
        return baseDeDatos.update(NOMBRE_TABLA, setFields(model), CODIGO_TABLA+" = ?", argumentos);
    }
    public ArrayList<E> readAll(){
        ArrayList<E> listModels = new ArrayList<>();
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getReadableDatabase();

        Cursor cursor = baseDeDatos.query(
                NOMBRE_TABLA,
                null,
                "NOT "+ESTADO_TABLA+" = '*'",
                null,
                null,
                null,
                null
        );
        if (cursor == null) {
            return listModels;
        }

        if (!cursor.moveToFirst()){
            return listModels;
        }
        do {
            listModels.add(parseModel(cursor));
        } while (cursor.moveToNext());
        cursor.close();
        return listModels;
    }

    protected abstract E parseModel(Cursor cursor);
}
