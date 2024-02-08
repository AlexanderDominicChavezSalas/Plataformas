package com.unsapp.medicord.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unsapp.medicord.R;
import com.unsapp.medicord.data.models.Medicina;
import com.unsapp.medicord.data.models.User;
import com.unsapp.medicord.data.sqlite.adapters.MedicinaAdapter;
import com.unsapp.medicord.data.sqlite.controllers.MedicinaController;
import com.unsapp.medicord.data.viewmodels.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private MedicinaController controller;
    private MedicinaAdapter adapter;
    private List<Medicina> listaModels;
    private UserViewModel userViewModel;

    public HomeFragment() {
    }
    @Override
    public void onStart() {
        super.onStart();
        refrescarLista();
    }
    public void refrescarLista() {
        if (adapter == null) return;
        listaModels = controller.readAll();
        adapter.setList(listaModels);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false); //--

        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class); //--

        userViewModel.getUser().observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                long userId = user.getId();
            }

        });

        RecyclerView recyclerView = root.findViewById(R.id.recycler);
        controller = new MedicinaController(this.getContext());
        listaModels = new ArrayList<>();
        adapter = new MedicinaAdapter(listaModels);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        return root;
    }
}