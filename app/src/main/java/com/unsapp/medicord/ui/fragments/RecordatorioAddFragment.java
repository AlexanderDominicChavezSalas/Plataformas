package com.unsapp.medicord.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.unsapp.medicord.R;
import com.unsapp.medicord.data.models.Medicina;
import com.unsapp.medicord.data.sqlite.controllers.MedicinaController;
import com.unsapp.medicord.data.sqlite.controllers.RecordatorioController;
import com.unsapp.medicord.ui.helpers.DatePickerFragment;
import com.unsapp.medicord.ui.helpers.TimePickerFragment;

import java.util.ArrayList;

public class RecordatorioAddFragment extends Fragment {

    private RecordatorioController controller;
    private ArrayList<Medicina> listaUnidadMedicina;
    private EditText etDate;
    private EditText etTime;

    public RecordatorioAddFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller = new RecordatorioController(this.getContext());
        listaUnidadMedicina = new MedicinaController(this.getContext()).readAll();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_recordatorio_add, container, false);
        etDate = root.findViewById(R.id.etDate);
        etDate.setOnClickListener(v -> showDatePickerDialog());
        etTime = root.findViewById(R.id.etTime);
        etTime.setOnClickListener(v -> showTimePickerDialog());
        return root;
    }
    private void showTimePickerDialog() {
        TimePickerFragment newFragment = TimePickerFragment.newInstance((datePicker, hour, minute) -> {
            // +1 because January is zero
            final String selectedHour = twoDigits(hour) + " : " + twoDigits(minute);
            etTime.setText(selectedHour);
        });

        newFragment.show(requireActivity().getSupportFragmentManager(), "datePicker");
    }
    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance((datePicker, year, month, day) -> {
            // +1 because January is zero
            final String selectedDate = twoDigits(day) + " / " + twoDigits(month+1) + " / " + year;
            etDate.setText(selectedDate);
        });

        newFragment.show(requireActivity().getSupportFragmentManager(), "datePicker");
    }
    private String twoDigits(int n) {
        return (n<=9) ? ("0"+n) : String.valueOf(n);
    }
}