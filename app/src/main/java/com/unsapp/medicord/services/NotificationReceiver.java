package com.unsapp.medicord.services;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.unsapp.medicord.R;
import com.unsapp.medicord.ui.fragments.ReportFragment;
import com.unsapp.medicord.ui.fragments.ResponseFragment;

public class NotificationReceiver extends BroadcastReceiver {
    private LocationService foregroundService;
    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LocationService.LocalBinder binder = (LocationService.LocalBinder) iBinder;
            foregroundService = binder.getService();
            serviceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            serviceBound = false;
        }
    };

    private boolean serviceBound = false;
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String riesgo = intent.getStringExtra("riesgo");

        if (action != null) {
            switch (action) {
                case "YES_ACTION":
                    // Acción cuando se presiona "Sí"
                    Toast.makeText(context, "Alarma apagada", Toast.LENGTH_SHORT).show();
                    siAlarma(context);
                    break;
                case "NO_ACTION":
                    // Acción cuando se presiona "No"
                    Toast.makeText(context, "Alarma pospuesta", Toast.LENGTH_SHORT).show();
                    noAlarma(context);
                    break;
                case "HELP_ACTION":
                    // Acción cuando se presiona "Help"
                    Toast.makeText(context, "Alarma ayuda", Toast.LENGTH_SHORT).show();
                    llamadaAlarma(context);
                    break;

            }
        }
        Intent stopServiceIntent = new Intent(context, AlarmNotificationService.class);
        context.stopService(stopServiceIntent);
    }
    private void siAlarma(Context context) {

    }

    private void noAlarma(Context context) {
        // Implementa aquí la lógica para posponer la alarma
        // Por ejemplo, puedes reprogramar la alarma para que suene más tarde
        // o cancelar la alarma actual y establecer una nueva alarma para una hora diferente

    }
    private void llamadaAlarma(Context context) {
        //problema aqui
        /*
        try {
            FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, ResponseFragment.class,null);
            fragmentTransaction.commit();
        } catch (ClassCastException e) {
            Log.e("asd", "Can't get fragment manager");
        }
        *//*
        Intent serviceIntent = new Intent(context, LocationService.class);
        context.bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
        context.startService(serviceIntent);
        if (foregroundService.getLatitude()!=0 && foregroundService.getLongitude()!=0){
            enviarMsgApp(context,"Ayudaaaa","964880690",foregroundService.getLatitude(),foregroundService.getLongitude());
        }else{
            Toast.makeText(context, "Espere a actualizar la ubicacion.", Toast.LENGTH_SHORT).show();
        }*/
        /*
        Intent i = new Intent(context, ResponseFragment.class);
        i.putExtra("updatedString","Hello");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);*/
    }

    public void enviarMsgApp(Context context, String message, String numeroTelefono, double latitude, double longitude) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        message += ". Esta es mi ubicación: http://maps.google.com/maps?saddr="+latitude+","+longitude;
        String uri = "whatsapp://send?phone=" + "+51 " + numeroTelefono + "&text=" + message;
        intent.setData(Uri.parse(uri));
        context.startActivity(intent);
    }


}

