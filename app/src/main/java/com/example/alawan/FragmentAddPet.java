package com.example.alawan;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.alawan.Class.Color;
import com.example.alawan.Class.Race;
import com.example.alawan.Class.Server.RetrofitInstance;
import com.example.alawan.Class.Server.ServerInterface;

import java.util.ArrayList;
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

        btAddPet.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                boolean validation = true;

                if (etName.getText().toString().trim().length() == 0)
                {
                    etName.setError("Le champ est vide");
                    validation = false;
                }

                if (etDescription.getText().toString().trim().length() == 0)
                {
                    etDescription.setError("Le champ est vide");
                    validation = false;
                }

                if (etBirth.getText().toString().trim().length() == 0)
                {
                    etBirth.setError("Le champ est vide");
                    validation = false;
                }

            }
        });
        return view;
    }
}