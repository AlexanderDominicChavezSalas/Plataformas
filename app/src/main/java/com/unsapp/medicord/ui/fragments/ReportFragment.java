package com.unsapp.medicord.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.unsapp.medicord.R;
import com.unsapp.medicord.data.models.Medicina;
import com.unsapp.medicord.data.models.Recordatorio;
import com.unsapp.medicord.data.sqlite.controllers.MedicinaController;
import com.unsapp.medicord.data.sqlite.controllers.RecordatorioController;
import com.unsapp.medicord.ui.custom.MyGraphView;
import com.unsapp.medicord.ui.custom.PieChartView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReportFragment extends Fragment {
    private List<Medicina> listMedicines;
    private List<Recordatorio> listRecordatorios;
    public ReportFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_report, container, false);
        PieChartView graphTorta = (PieChartView)view.findViewById(R.id.pieChartView);
        listMedicines = new MedicinaController(view.getContext()).readAll();
        int[] data_1 = new int[3];
        int total = 0;
        for (Medicina medicina: listMedicines) {
            data_1[medicina.getMedNiv()-1]++;
            total++;
        }
        graphTorta.updateData(data_1,total);

        listRecordatorios = new RecordatorioController(view.getContext()).readAll();
        MyGraphView graphView = (MyGraphView)view.findViewById(R.id.graphView);
        int[] data_2 = new int[7];
        for (Recordatorio recordatorio: listRecordatorios) {
            String fechaIngresada = recordatorio.getRecFecIni();
            aumentarContadores(data_2, fechaIngresada);
            total++;
        }
        graphView.setDataPoints(data_2);
        return view;
    }
    private void aumentarContadores(int[] contadoresPorDia, String fechaString) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try {
            Calendar calHoy = Calendar.getInstance();
            Date fecha = sdf.parse(fechaString);
            Calendar calFecha = Calendar.getInstance();
            calFecha.setTime(fecha);

            // Iterar para cada día y verificar si la fecha pertenece a cada uno de los 7 días anteriores
            for (int i = 0; i < 7; i++) {
                Calendar calDiaAnterior = (Calendar) calHoy.clone();
                calDiaAnterior.add(Calendar.DAY_OF_YEAR, -i - 1); // Retroceder i+1 días
                if (calFecha.get(Calendar.YEAR) == calDiaAnterior.get(Calendar.YEAR) &&
                        calFecha.get(Calendar.DAY_OF_YEAR) == calDiaAnterior.get(Calendar.DAY_OF_YEAR)) {
                    contadoresPorDia[6-i]++;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}