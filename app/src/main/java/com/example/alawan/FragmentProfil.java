package com.example.alawan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.alawan.Class.Adapter.AdapterListeAlerteProfil;
import com.example.alawan.Class.Adapter.AdapterListeAnimalProfil;
import com.example.alawan.Class.Animal;
import com.example.alawan.Server.RetrofitInstance;
import com.example.alawan.Server.ServerInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentProfil extends Fragment {

    List<Animal> listCompagnon = new ArrayList<>();
    List<Animal> listAlert = new ArrayList<>();
    int idAuth;
    RecyclerView rv1;
    RecyclerView rv2;

    ImageView ivSetting;
    View view;
    public FragmentProfil() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profil, container, false);

        idAuth = getActivity().getPreferences(Context.MODE_PRIVATE).getInt("id",0);

        ivSetting = view.findViewById(R.id.iv_setting_profile);

        rv1 = view.findViewById(R.id.rv_compagnons_profile);
        rv1.setHasFixedSize(true);
        rv1.setLayoutManager(new LinearLayoutManager(getActivity()));

        rv2 = view.findViewById(R.id.rv_alertes_profil);
        rv2.setHasFixedSize(true);
        rv2.setLayoutManager(new LinearLayoutManager(getActivity()));

        ServerInterface serverInterface = RetrofitInstance.getInstance().create(ServerInterface.class);
        serverInterface.getListAnimal().enqueue(new Callback<List<Animal>>() {
            @Override
            public void onResponse(Call<List<Animal>> call, Response<List<Animal>> response) {
                listCompagnon = response.body();
                rv1.setAdapter(new AdapterListeAnimalProfil(listCompagnon));
            }
            @Override
            public void onFailure(Call<List<Animal>> call, Throwable t) {
                Log.v("debug",t.toString());
            }
        });

        serverInterface.getAnimalsAlertProfil(idAuth).enqueue(new Callback<List<Animal>>() {
            @Override
            public void onResponse(Call<List<Animal>> call, Response<List<Animal>> response) {
                listAlert = response.body();
                rv2.setAdapter(new AdapterListeAlerteProfil(listAlert));
            }
            @Override
            public void onFailure(Call<List<Animal>> call, Throwable t) {
                Log.v("debug",t.toString());
            }
        });

        ivSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Rajouter le code de deconnexion
                Intent intent = new Intent(getActivity(), ActivityModifyProfil.class);
                ((ActivityMenu)getActivity()).changePage(intent);
            }
        });
        return view;
    }
}