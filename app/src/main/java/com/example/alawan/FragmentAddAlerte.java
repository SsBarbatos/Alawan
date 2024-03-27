package com.example.alawan;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alawan.Class.Adapter.AdapterListeAnimalAlerte;
import com.example.alawan.Class.Adapter.AdapterListeAnimalProfil;
import com.example.alawan.Class.Animal;
import com.example.alawan.Class.AnimalColor;
import com.example.alawan.Class.Color;
import com.example.alawan.Class.DescriptionDialog;
import com.example.alawan.Class.Person;
import com.example.alawan.Class.Race;
import com.example.alawan.Class.Server.RetrofitInstance;
import com.example.alawan.Class.Server.ServerInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.alawan.Class.Server.RetrofitInstance;
import com.example.alawan.Class.Server.ServerInterface;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class FragmentAddAlerte extends Fragment {

    View view;
    FragmentAddAlerte context;
    ServerInterface serverInterface ;
    NavController navController;

    RecyclerView rvListAnimals;
    List<Animal> listeAnimal;
    Button btAddAnimal;
    Integer userID;
    Date currentDate;
    SimpleDateFormat format;

    public FragmentAddAlerte() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //utiliserCoordonnees();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_add_alerte, container, false);
        context = this;
        userID = getActivity().getPreferences(Context.MODE_PRIVATE).getInt("id",0);

        NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.fv_mainpage);
        navController = navHostFragment.getNavController();
        serverInterface = RetrofitInstance.getInstance().create(ServerInterface.class);

        rvListAnimals = view.findViewById(R.id.rv_liste_animaux);
        rvListAnimals.setHasFixedSize(true);
        rvListAnimals.setLayoutManager(new LinearLayoutManager(getActivity()));

        btAddAnimal = view.findViewById(R.id.bt_nouveaux_animaux);

    // __ SET CURRENT DATE _________________________________________________________________________
        Date date = new Date();
        format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String formattedDate = format.format(date);
        try {
            currentDate = format.parse(formattedDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    // _____________________________________________________________________________________________

        Call<List<Animal>> callGetUserAnimals = serverInterface.getUserAnimal(userID);
        callGetUserAnimals.enqueue(new Callback<List<Animal>>() {
            @Override
            public void onResponse(Call<List<Animal>> call, Response<List<Animal>> response) {
                listeAnimal = response.body();
                rvListAnimals.setAdapter(new AdapterListeAnimalAlerte(listeAnimal, context));
            }
            @Override
            public void onFailure(Call<List<Animal>> call, Throwable t) {
                Log.v("Get Auth ID", t.toString());
            }
        });

        btAddAnimal.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                navController.navigate(R.id.action_fragmentAddAlerte_to_addPet);
            }
        });

        return view;
    }

    public int returnIdAuth(){
        return getActivity().getPreferences(Context.MODE_PRIVATE).getInt("id",0);
    }
}