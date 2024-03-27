package com.example.alawan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alawan.Class.Alert;
import com.example.alawan.Class.Animal;
import com.example.alawan.Class.Person;
import com.example.alawan.Class.Server.RetrofitInstance;
import com.example.alawan.Class.Server.ServerInterface;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentZoomAlert extends Fragment {

    TextView tvNom,tvMaster,tvDate,tvDescription;
    Button btAppeler;
    ImageView ivImage;
    Animal animal;
    int idAnimal;

    public FragmentZoomAlert() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_zoom_alert, container, false);
        btAppeler = view.findViewById(R.id.bt_appeler_zoom);
        btAppeler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = "5555555555";
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+phone));
                startActivity(intent);
            }
        });
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ivImage = view.findViewById(R.id.iv_compagnon_zoom);

        tvDate = view.findViewById(R.id.tv_date_perte_zoom);
        tvDescription = view.findViewById(R.id.tv_description_zoom);
        tvMaster = view.findViewById(R.id.tv_nom_maitre_zoom);
        tvNom = view.findViewById(R.id.tv_nom_ajout_zoom);
        GetAnimal();
        GetAlert();

        RetrofitInstance.getInstance().create(ServerInterface.class).getMaster(idAnimal).enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                Log.v("debug",response.toString());
                if(response.body() != null){
                    tvMaster.setText(response.body().getName() + " " + response.body().getLastName());
                }
                else{
                    Log.v("debug",response.toString());
                }
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                Log.v("debug",t.toString());
            }
        });

    }

    private void GetAlert() {
        RetrofitInstance.getInstance().create(ServerInterface.class).getAlert(idAnimal).enqueue(new Callback<Alert>() {
            @Override
            public void onResponse(Call<Alert> call, Response<Alert> response) {
                Log.v("debug",response.toString());
                if(response.body() != null){
                    tvDescription.setText(response.body().getDescription());
                    tvDate.setText((new SimpleDateFormat("yyyy/MM/dd")).format(response.body().getDateLost()));

                }
                else {
                    Log.v("debug",response.toString());
                }
            }
            @Override
            public void onFailure(Call<Alert> call, Throwable t) {
                Log.v("debug error",t.toString());
            }
        });
    }

    private void GetAnimal() {
        SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
        idAnimal = pref.getInt("idZoom",0);
        RetrofitInstance.getInstance().create(ServerInterface.class).getAnimalFromAlert(idAnimal).enqueue(new Callback<Animal>() {
            @Override
            public void onResponse(Call<Animal> call, Response<Animal> response) {
                if(response.body() != null){
                    animal = response.body();
                    tvNom.setText(animal.getName());
                    Picasso.get().load("http://10.0.2.2:8000/img/" + animal.getPicture()).into(ivImage);
                    //Picasso.get().load("http://172.16.86.209:8080/img/" + animal.getPicture()).into(ivImage);
                }
                else {
                    Log.v("debug",response.toString());
                }
            }

            @Override
            public void onFailure(Call<Animal> call, Throwable t) {
                Log.v("debug error",t.toString());
            }
        });
    }
}