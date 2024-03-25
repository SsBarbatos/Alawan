package com.example.alawan;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alawan.Class.Adapter.AdapterAlertRecherche;
import com.example.alawan.Class.Animal;
import com.example.alawan.Class.Server.RetrofitInstance;
import com.example.alawan.Class.Server.ServerInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentRecherche extends Fragment {

    RecyclerView rv;
    List<Animal> list = new ArrayList<>();
    View view;
    Context activityMenu;

    public FragmentRecherche(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activityMenu = container.getContext();
        view = inflater.inflate(R.layout.fragment_recherche,container,false);
        rv = view.findViewById(R.id.rv_alerte_recherche);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        ServerInterface serverInterface = RetrofitInstance.getInstance().create(ServerInterface.class);
        serverInterface.getAnimalsAlert().enqueue(new Callback<List<Animal>>() {
            @Override
            public void onResponse(Call<List<Animal>> call, Response<List<Animal>> response) {
                if(response.body() != null){
                    list = response.body();
                    rv.setAdapter(new AdapterAlertRecherche(list,(ActivityMenu) activityMenu));
                }
            }

            @Override
            public void onFailure(Call<List<Animal>> call, Throwable t) {
                Log.v("debug",t.toString());
            }
        });
        return view;
    }


}