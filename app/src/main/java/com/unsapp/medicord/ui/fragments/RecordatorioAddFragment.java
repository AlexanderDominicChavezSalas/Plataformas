package com.unsapp.medicord.ui.fragments;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.unsapp.medicord.R;
import com.unsapp.medicord.data.models.Medicina;
import com.unsapp.medicord.data.sqlite.controllers.MedicinaController;
import com.unsapp.medicord.data.sqlite.controllers.RecordatorioController;
import com.unsapp.medicord.services.AlarmNotificationService;
import com.unsapp.medicord.services.AlarmReceiver;
import com.unsapp.medicord.services.LocationService;
import com.unsapp.medicord.ui.helpers.DatePickerFragment;
import com.unsapp.medicord.ui.helpers.TimePickerFragment;

import java.util.ArrayList;
import java.util.Calendar;

public class RecordatorioAddFragment extends Fragment {
    private Calendar calendar;
    private LocationService foregroundService;
    private boolean serviceBound = false;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
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

        Button btnAgregar = root.findViewById(R.id.btnAgregar);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //metodo programar alarma
                //programarAlarma(fecha,hora);
                String horatemporal=etTime.getText().toString();

                Log.d("ads",horatemporal);
                int hora= Integer.parseInt(horatemporal.substring(0,2));
                int minuto= Integer.parseInt(horatemporal.substring(5));
                String fechatemporal=etDate.getText().toString();
                Log.d("adas",fechatemporal);

                int day= Integer.parseInt(fechatemporal.substring(0,2));
                int month= Integer.parseInt(fechatemporal.substring(5,7));
                int year= Integer.parseInt(fechatemporal.substring(10));
                programarAlarmaRecurrente(year,month,day,hora,minuto,20000);
            }
        });
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
    private void programarAlarmaRecurrente(int year,int month,int day, int hora,int minute, long frecuenciaMilisegundos) {
        Context context = getContext();
        calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hora);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        // Crear un Intent para el BroadcastReceiver
        Intent intent = new Intent(context, AlarmReceiver.class);

        // Crear un PendingIntent para el BroadcastReceiver
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Guardar la hora de la alarma como extra en el Intent
        intent.putExtra("horaAlarma", calendar.getTimeInMillis());

        // Obtener el servicio AlarmManager
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        // Contador para el número de veces que se ha activado la alarma
        int contadorAlarmas = 0;

        // Configurar la alarma recurrente
        if (alarmManager != null) {
            String tag = "TAG";
            // Programar la alarma inicial
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

            // Programar alarmas recurrentes
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() + frecuenciaMilisegundos,
                    frecuenciaMilisegundos, pendingIntent);

            // Incrementar el contador cada vez que se active la alarma
            contadorAlarmas++;

            // Cancelar la alarma después de que se haya activado tres veces
            if (contadorAlarmas >= 3) {
                alarmManager.cancel(pendingIntent);
            }
        }
    }
    private void reproducirTonoAlarma(Context context) {
        try {
            // Crear un MediaPlayer con el tono de alarma
            MediaPlayer mediaPlayer = MediaPlayer.create(context, Settings.System.DEFAULT_ALARM_ALERT_URI);

            // Reproducir el tono
            mediaPlayer.start();

            // Detener la reproducción después de 10 segundos
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                }
            }, 10000); // 20 segundos
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void startServices(Context context) {

        // Crear un intent para iniciar el servicio
        Intent serviceIntent_1 = new Intent(context, AlarmNotificationService.class);
        serviceIntent_1.putExtra("nombre", "Paracetamol");
        serviceIntent_1.putExtra("ID", 123);
        serviceIntent_1.putExtra("riesgo", "alto");


        // Comprobar la versión de Android y empezar el servicio
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Crear y configurar el canal de notificación si no está creado
            createNotificationChannel(context);

            // Iniciar el servicio en primer plano utilizando startForegroundService
            ContextCompat.startForegroundService(context, serviceIntent_1);
        } else {
            // Iniciar el servicio utilizando startService
            context.startService(serviceIntent_1);
        }
    }

    // Método para crear el canal de notificación (solo para Android Oreo y superior)
    private void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (notificationManager != null) {
                NotificationChannel channel = new NotificationChannel(AlarmNotificationService.CHANNEL_ID, "Alarm Channel", NotificationManager.IMPORTANCE_DEFAULT);
                notificationManager.createNotificationChannel(channel);
            }
        }
    }
}