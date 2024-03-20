package com.example.alawan;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.alawan.Class.Alert;
import com.example.alawan.Class.Color;
import com.example.alawan.Class.Person;
import com.example.alawan.Server.RetrofitInstance;
import com.example.alawan.Server.ServerInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentAddAlerte extends Fragment {

    View view;
    EditText etDescription, etDate, etPhone;
    Spinner spRaces, spColors;
    Button btAddAlert;

    public FragmentAddAlerte()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_add_alerte, container, false);

        etDescription = view.findViewById(R.id.et_description_alerte);
        etDate = view.findViewById(R.id.et_date_alerte);
        etPhone = view.findViewById(R.id.et_phone_alerte);

        spColors = view.findViewById(R.id.sp_couleur_alerte);
        spRaces = view.findViewById(R.id.sp_race_alerte);

        btAddAlert = view.findViewById(R.id.bt_lancer_alerte);

        ServerInterface serverInterface = RetrofitInstance.getInstance().create(ServerInterface.class);
        NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.fv_main_page);
        //NavController navController = navHostFragment.getNavController();

        // FILL THE COLORS SPINNER
        ArrayAdapter<CharSequence> adapterColors = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.list_colors,
                android.R.layout.simple_spinner_item
        );
        adapterColors.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spColors.setAdapter(adapterColors);

        // FILL THE RACES SPINNER
        ArrayAdapter<CharSequence> adapterRaces = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.list_colors,
                android.R.layout.simple_spinner_item
        );
        adapterRaces.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spColors.setAdapter(adapterRaces);

        btAddAlert.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                boolean validation = true;

                if (etDate.getText().toString().trim().length() == 0)
                {
                    etDate.setError("Le champ est vide");
                    validation = false;
                }

                if (etDescription.getText().toString().trim().length() == 0)
                {
                    etDescription.setError("Le champ est vide");
                    validation = false;
                }

                if (etPhone.getText().toString().trim().length() == 0)
                {
                    etPhone.setError("Le champ est vide");
                    validation = false;
                }
            }
        });
        return view;
    }
}