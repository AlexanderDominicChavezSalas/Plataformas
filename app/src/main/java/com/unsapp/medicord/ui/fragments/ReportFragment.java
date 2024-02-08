package com.unsapp.medicord.ui.fragments;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.unsapp.medicord.R;
import com.unsapp.medicord.services.AlarmNotificationService;
import com.unsapp.medicord.services.AlarmReceiver;
import com.unsapp.medicord.services.LocationService;
import com.unsapp.medicord.services.NotificationReceiver;

import java.util.Calendar;

public class ReportFragment extends Fragment {
    private Calendar calendar;
    private LocationService foregroundService;
    private boolean serviceBound = false;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    public ReportFragment() {
        // Required empty public constructor
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
        //se reciben datos de fecha y hora
        String fecha="2024-02-07",hora="13:00",riesgo="alto",nombre="Paracetamol";
        int id=123;
        Button startServiceButton = view.findViewById(R.id.start_service_button);

        startServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //metodo programar alarma
                //programarAlarma(fecha,hora);
                programarAlarmaRecurrente(fecha,hora,20000);
            }
        });
        return view;
    }
    private void programarAlarma(String fecha, String hora){
        Context context = getContext();
        calendar= Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 1);
        calendar.set(Calendar.MINUTE, 2);
        calendar.set(Calendar.SECOND, 00);
        calendar.set(Calendar.MILLISECOND, 0);
        //long frecuenciaMilisegundos = frecuenciaHoras * 60 * 60 * 1000;
        long frecuenciaMilisegundos = 10000;
        // Obtener el servicio AlarmManager
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        // Configurar la alarma para que se active en el tiempo específico

        if (alarmManager != null) {
            String tag = "TAG";

            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), tag, new AlarmManager.OnAlarmListener() {
                @Override
                public void onAlarm() {
                    Toast.makeText(context, "AlarmManager.OnAlarmListener", Toast.LENGTH_SHORT).show();
                    //

                    startServices(context);
                    reproducirTonoAlarma(context);
                    //

                }
            },null);
        }

    }
    private void programarAlarma2(String fecha, String hora) {
        Context context = getContext();
        calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 1);
        calendar.set(Calendar.MINUTE, 33);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        // Crear un Intent para el BroadcastReceiver
        Intent intent = new Intent(context, NotificationReceiver.class);

        // Crear un PendingIntent para el BroadcastReceiver
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Guardar la hora de la alarma como extra en el Intent
        intent.putExtra("horaAlarma", calendar.getTimeInMillis());

        // Obtener el servicio AlarmManager
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        // Configurar la alarma
        if (alarmManager != null) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }
    }
    private void programarAlarmaRecurrente(String fecha, String hora, long frecuenciaMilisegundos) {
        Context context = getContext();
        calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 7);
        calendar.set(Calendar.MINUTE, 15);
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

        // Configurar la alarma recurrente
        if (alarmManager != null) {
            String tag = "TAG";
            // Programar la alarma inicial
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            /*alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), tag, new AlarmManager.OnAlarmListener() {
                @Override
                public void onAlarm() {
                    Toast.makeText(context, "AlarmManager.OnAlarmListener", Toast.LENGTH_SHORT).show();
                    //

                    startServices(context);
                    reproducirTonoAlarma(context);
                    //

                }
            },null);*/
            // Programar alarmas recurrentes
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis()+frecuenciaMilisegundos,
                    frecuenciaMilisegundos, pendingIntent);
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