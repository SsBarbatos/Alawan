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
import com.example.alawan.Class.Animal;
import com.example.alawan.Class.Color;
import com.example.alawan.Class.Person;
import com.example.alawan.Class.Race;
import com.example.alawan.Server.RetrofitInstance;
import com.example.alawan.Server.ServerInterface;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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

    ServerInterface serverInterface = RetrofitInstance.getInstance().create(ServerInterface.class);
    NavHostFragment navHostFragment = (NavHostFragment) requireActivity().getSupportFragmentManager().findFragmentById(R.id.fv_main_page);
    NavController navController = navHostFragment.getNavController();

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
        NavController navController = navHostFragment.getNavController();

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
                boolean validation;

                if (etDate.getText().toString().trim().length() == 0) {
                    etDate.setError("Le champ est vide");
                    validation = false;
                }else if (etDescription.getText().toString().trim().length() == 0)
                {
                    etDescription.setError("Le champ est vide");
                    validation = false;
                }else if (etPhone.getText().toString().trim().length() == 0)
                {
                    etPhone.setError("Le champ est vide");
                    validation = false;
                }
                else
                    validation = true;

                if (validation)
                {
                    try {
                        Date date = DateFormat.getDateInstance().parse(etDate.getText().toString());
                        AddAlert(etDescription.getText().toString(), spRaces.getSelectedItem().toString(), spColors.getSelectedItem().toString(), date, etPhone.getText().toString());
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        return view;
    }

    public void AddAlert(String description, String race, String color, Date date, String phone)
    {
        // __ GET USER ID __________________________________________________________________________
        Person user;

        Call<Person> callUser = serverInterface.getUser();
        callUser.enqueue(new Callback<Person>()
        {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response)
            {
                user = response.body();
            }
            @Override
            public void onFailure(Call<Person> call, Throwable t)
            {
                Log.v("debug error",t.toString());
            }
        });


        // __ GET RACE ID __________________________________________________________________________
        Call<List<Race>> callRace = serverInterface.getListRace();
        callRace.enqueue(new Callback<List<Race>>()
        {
            List<Race> listRace;
            Race useRace;
            @Override
            public void onResponse(Call<List<Race>> call, Response<List<Race>> response)
            {
                listRace = response.body();
                for(Race race: listRace)
                {
                    if(race.getRace().toString() == spRaces.getSelectedItem().toString())
                    {
                        useRace = race;
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Race>> call, Throwable t)
            {
                Log.v("debug error",t.toString());
            }
        });
        // _________________________________________________________________________________________

        Animal animal = new Animal(user.getId(), useRace[0].getId(), String picture, True);

        Call<Boolean> callAlert = serverInterface.addAlert(alert.get.getName(),person.getLastName(),person.getEmail(),person.getPassword(),person.getCreationDate());
        callAlert.enqueue(new Callback<Boolean>()
        {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response)
            {
                navController.navigate(R.id.action_nav_signup_to_nav_login);
                Log.v("debug",call.toString());
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t)
            {
                Log.v("debug error",t.toString());
            }
        });

        return true;
    }
        catch (Exception e)
    {
        Log.v("debug catch", e.toString());

        return false;
    }
    }
}