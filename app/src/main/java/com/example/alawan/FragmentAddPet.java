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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.alawan.Class.Color;
import com.example.alawan.Class.Race;
import com.example.alawan.Class.Server.RetrofitInstance;
import com.example.alawan.Class.Server.ServerInterface;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentAddPet extends Fragment
{
    View view;
    EditText etName, etBirth, etDescription;
    Spinner spRace, spColor;
    Button btAddPet;

    List<Color> listColor = new ArrayList<Color>();
    List<Race> listRace = new ArrayList<Race>();

    public FragmentAddPet()
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
        view = inflater.inflate(R.layout.fragment_add_pet, container, false);

        etName = view.findViewById(R.id.et_nom_ajout_compagnon);
        etBirth = view.findViewById(R.id.et_date_ajout_compagnon);
        etDescription = view.findViewById(R.id.et_description_ajout_compagnon);

        spColor = view.findViewById(R.id.sp_couleur_ajout_compagnon);
        spRace = view.findViewById(R.id.sp_race_ajout_compagnon);

        btAddPet = view.findViewById(R.id.bt_ajout_compagnon);

        ServerInterface serverInterface = RetrofitInstance.getInstance().create(ServerInterface.class);
        //NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.fv_main_page);
        //NavController navController = navHostFragment.getNavController();
/*
        // GET COLORS FOR THE SPINNER
        Call<List<Color>> callColor = serverInterface.getListColor();
        callColor.enqueue(new Callback<List<Color>>()
        {
            @Override
            public void onResponse(Call<List<Color>> call, Response<List<Color>> response)
            {
                listColor = response.body();
            }
            @Override
            public void onFailure(Call<List<Color>> call, Throwable t)
            {
                Log.v("debug error",t.toString());
            }
        });

        // GET RACES FOR THE SPINNER
        Call<List<Race>> callRace = serverInterface.getListRace();
        callRace.enqueue(new Callback<List<Race>>()
        {
            @Override
            public void onResponse(Call<List<Race>> call, Response<List<Race>> response)
            {
                listRace = response.body();
            }
            @Override
            public void onFailure(Call<List<Race>> call, Throwable t)
            {
                Log.v("debug error",t.toString());
            }
        });
*/
        // __ FILL THE COLORS SPINNER __________________________________________________________________
        ArrayAdapter<CharSequence> adapterColors = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.list_colors,
                android.R.layout.simple_spinner_item
        );
        adapterColors.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spColor.setAdapter(adapterColors);
        // _____________________________________________________________________________________________
        // __ FILL THE RACES SPINNER ___________________________________________________________________
        ArrayAdapter<CharSequence> adapterRaces = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.list_races,
                android.R.layout.simple_spinner_item
        );
        adapterRaces.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spRace.setAdapter(adapterRaces);
        // _____________________________________________________________________________________________

        btAddPet.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                boolean validation = true;
                if (etName.getText().toString().trim().length() == 0){
                    etName.setError("Le champ est vide");
                    validation = false;
                }
                if (etBirth.getText().toString().trim().length() == 0){
                    etBirth.setError("Le champ est vide");
                    validation = false;
                }
                if (spColor.getSelectedItem().toString().equals("Couleur"))
                {
                    ((TextView)spColor.getSelectedView()).setError("Choisissez une couleur");
                    validation = false;
                }
                if (spRace.getSelectedItem().toString().equals("Race"))
                {
                    ((TextView)spRace.getSelectedView()).setError("Choisissez une race");
                    validation = false;
                }

                if(validation){
                    String dateString = etBirth.getText().toString();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate localDate = LocalDate.parse(dateString, formatter);
                    Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                    RetrofitInstance.getInstance().create(ServerInterface.class).addAnimal(getActivity().getPreferences(Context.MODE_PRIVATE).getInt("id",0),spRace.getSelectedItemPosition() - 1,0,etName.getText().toString(),null,date,false).enqueue(new Callback<Integer>() {
                        @Override
                        public void onResponse(Call<Integer> call, Response<Integer> response) {
                                if (response.body() != null){
                                    NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.fv_mainpage);
                                    NavController navController = navHostFragment.getNavController();
                                    navController.navigate(R.id.action_addPet_to_vav_profil);
                                }
                        }

                        @Override
                        public void onFailure(Call<Integer> call, Throwable t) {
                            Log.v("debug error",t.toString());
                        }
                    });
                }

            }
        });
        return view;
    }
}