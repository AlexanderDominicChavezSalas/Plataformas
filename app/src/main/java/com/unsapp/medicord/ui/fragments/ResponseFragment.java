package com.unsapp.medicord.ui.fragments;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.unsapp.medicord.R;
import com.unsapp.medicord.services.LocationService;

public class ResponseFragment extends Fragment {
    private LocationService foregroundService;
    private boolean serviceBound = false;
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
        }};
    public ResponseFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent serviceIntent = new Intent(getContext(), LocationService.class);
        this.getActivity().bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (foregroundService.getLatitude()!=0 && foregroundService.getLongitude()!=0){
            enviarMsgApp("Ayudaaaa","964880690",foregroundService.getLatitude(),foregroundService.getLongitude());
        }else{
            Toast.makeText(getActivity(), "Espere a actualizar la ubicacion.", Toast.LENGTH_SHORT).show();
        }
        return inflater.inflate(R.layout.fragment_response, container, false);
    }
    public void enviarMsgApp(String message, String numeroTelefono, double latitude, double longitude) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        message += ". Esta es mi ubicación: http://maps.google.com/maps?saddr="+latitude+","+longitude;
        String uri = "whatsapp://send?phone=" + "+51 " + numeroTelefono + "&text=" + message;
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }
}