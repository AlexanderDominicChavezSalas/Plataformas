package com.unsapp.medicord.services;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.unsapp.medicord.R;
import com.unsapp.medicord.ui.fragments.ResponseFragment;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Aquí puedes definir las acciones que deseas realizar cuando se active la alarma
        // Por ejemplo, mostrar una notificación

        startServices(context,intent);
        reproducirTonoAlarma(context);

        // También puedes reproducir un sonido, iniciar una actividad, etc.
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
    private void startServices(Context context,Intent intent) {

        // Crear un intent para iniciar el servicio
        Intent serviceIntent_1 = new Intent(context, AlarmNotificationService.class);
        serviceIntent_1.putExtra("nombre", "Paracetamol");
        serviceIntent_1.putExtra("ID", 123);
        serviceIntent_1.putExtra("riesgo", "alto");

        //Intent serviceIntent = new Intent(context, LocationService.class);
        //context.bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
        //context.startService(serviceIntent);

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

