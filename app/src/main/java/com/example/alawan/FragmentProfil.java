package com.example.alawan;

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

import com.example.alawan.Class.AdapterListeAnimal;
import com.example.alawan.Class.Animal;
import com.example.alawan.Server.RetrofitInstance;
import com.example.alawan.Server.ServerInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentProfil extends Fragment {

    List<Animal> list = new ArrayList<>();
    RecyclerView rv;
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
        ivSetting = view.findViewById(R.id.iv_setting_profile);
        rv = view.findViewById(R.id.rv_compagnons_profile);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        ServerInterface serverInterface = RetrofitInstance.getInstance().create(ServerInterface.class);
        Call<List<Animal>> call = serverInterface.getListAnimal();
        call.enqueue(new Callback<List<Animal>>() {
            @Override
            public void onResponse(Call<List<Animal>> call, Response<List<Animal>> response) {
                list = response.body();
                rv.setAdapter(new AdapterListeAnimal(list));
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