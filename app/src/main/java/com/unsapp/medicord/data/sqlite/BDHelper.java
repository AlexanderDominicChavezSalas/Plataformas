package com.unsapp.medicord.data.sqlite;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.unsapp.medicord.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BDHelper extends SQLiteOpenHelper {
    private static final String NOMBRE_BASE_DE_DATOS = "nuevas_plataformas";
    private static boolean HAS_INIT = false;
    private final Context fContext;
    public BDHelper(@Nullable Context context, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, NOMBRE_BASE_DE_DATOS, factory, version);
        this.fContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if (!HAS_INIT){
            InputStream is;
            try {
                is = fContext.getResources().openRawResource(R.raw.test_sql);
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String line;
                while ((line = reader.readLine()) != null) {
                    Log.i("SQL Script", line);
                    if (!line.isEmpty() && !line.trim().startsWith("--"))
                        db.execSQL(line);
                }
                HAS_INIT = true;
            } catch (IOException e) {
                Log.e("ERROR DB", "Error loading init SQL from raw", e);
            } catch (SQLException e) {
                Log.e("ERROR DB", "Error executing init SQL", e);
            } catch (NullPointerException e){
                Log.e("ERROR DB", "Error charging SQL file", e);
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
