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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MedicinaController controller;
    private MedicinaAdapter adapter;
    private List<Medicina> listaModels;
    private UserViewModel userViewModel;

    public HomeFragment() {
        // Required empty public constructor
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
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false); //--

        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class); //--

        userViewModel.getUser().observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                long userId = user.getId();
            }

        });
        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        controller = new MedicinaController(this.getContext());
        listaModels = new ArrayList<>();
        adapter = new MedicinaAdapter(listaModels);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        return view;
    }
}