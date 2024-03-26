package com.example.alawan;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
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

import com.example.alawan.Class.Adapter.AdapterListeAnimalProfil;
import com.example.alawan.Class.Animal;
import com.example.alawan.Class.Server.RetrofitInstance;
import com.example.alawan.Class.Server.ServerInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentAddAlerte extends Fragment {

    View view;
    ServerInterface serverInterface ;
    NavController navController;

    RecyclerView rvListAnimals;
    List<Animal> listeAnimal = new ArrayList<>();
    Button btAddAnimal;
    Integer userID;
    EditText etDescriptionAlerte;

    public FragmentAddAlerte() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_alerte, container, false);

        NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.fv_mainpage);
        navController = navHostFragment.getNavController();
        serverInterface = RetrofitInstance.getInstance().create(ServerInterface.class);

        rvListAnimals = view.findViewById(R.id.rv_liste_animaux);
        rvListAnimals.setHasFixedSize(true);
        rvListAnimals.setLayoutManager(new LinearLayoutManager(getActivity()));

        btAddAnimal = view.findViewById(R.id.bt_nouveaux_animaux);

        etDescriptionAlerte = view.findViewById(R.id.et_description_alerte);

        Call<Integer> callAuthID = serverInterface.getIdAuth();
        callAuthID.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                userID = response.body();

                Call<List<Animal>> callGetUserAnimals = serverInterface.getUserAnimal(userID);
                callGetUserAnimals.enqueue(new Callback<List<Animal>>() {
                    @Override
                    public void onResponse(Call<List<Animal>> call, Response<List<Animal>> response) {
                        listeAnimal = response.body();
                        rvListAnimals.setAdapter(new AdapterListeAnimalProfil(listeAnimal));
                    }
                    @Override
                    public void onFailure(Call<List<Animal>> call, Throwable t) {
                        Log.v("Get Auth ID", t.toString());
                    }
                });
            }
            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.v("Get Auth ID", t.toString());
            }
        });

        btAddAnimal.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                navController.navigate(R.id.action_fragmentAddAlerte_to_map);
            }
        });

        return view;
    }
}