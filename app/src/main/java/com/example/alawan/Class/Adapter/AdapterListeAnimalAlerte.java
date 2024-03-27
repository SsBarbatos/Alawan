package com.example.alawan.Class.Adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alawan.Class.Animal;
import com.example.alawan.Class.Race;
import com.example.alawan.Class.Server.RetrofitInstance;
import com.example.alawan.Class.Server.ServerInterface;
import com.example.alawan.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterListeAnimalAlerte extends RecyclerView.Adapter {

    List<Animal> listeAnimal;
    Date currentDate;
    SimpleDateFormat format;

    public AdapterListeAnimalAlerte(List<Animal> l){
        this.listeAnimal = l;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.carte_alerte_dog,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position)
    {
        MyViewHolder myViewHolder = (AdapterListeAnimalAlerte.MyViewHolder) holder;
        myViewHolder.tvNom.setText(listeAnimal.get(position).getName());

        Date date = new Date();
        format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String formattedDate = format.format(date);
        try {
            currentDate = format.parse(formattedDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        RetrofitInstance.getInstance().create(ServerInterface.class).getRaceAnimal(listeAnimal.get(position).getIdrace()).enqueue(new Callback<Race>() {
            @Override
            public void onResponse(Call<Race> call, Response<Race> response) {
                if(response.body() != null){
                    myViewHolder.tvRace.setText("Race : " + response.body().getRace());
                }
                else {
                    Log.v("Reponse get race", response.body().toString());
                }
            }
            @Override
            public void onFailure(Call<Race> call, Throwable t) {
                Log.v("debug error", t.toString());
            }
        });

        ////////////////////////////////////////////////////////////////////////////////////
        ////////////////////// GET LE TELEPHONE EN GETTANT LE USER /////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////

        long miliseconds = System.currentTimeMillis();

        LocalDate date1 = listeAnimal.get(position).getBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate date2 = (new Date(miliseconds)).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        int age = Period.between(date1,date2).getYears();
        int ageMois = Period.between(date1,date2).getMonths();
        int ageDay = Period.between(date1,date2).getDays();

        if(age != 0){
            String strAge = age + " ans";
            myViewHolder.tvAge.setText(strAge);
        }
        else if(ageMois != 0){
            String strAgeMois = ageMois + " mois";
            myViewHolder.tvAge.setText(strAgeMois);
        }
        else {
            String strAgeJours = ageDay + " jours";
            myViewHolder.tvAge.setText(strAgeJours);
        }

        myViewHolder.ivAlerteCarte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {/*
                RetrofitInstance.getInstance().create(ServerInterface.class).addAlert(
                        listeAnimal.get(position).getId(),
                        listeAnimal.get(position).getName() + " a disparu(e) depuis le " + currentDate,
                        listeAnimal.get(position).getIdrace(),
                        null,
                        currentDate
                        //etPhone.getText().toString());*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return listeAnimal.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvNom, tvAge, tvRace;
        ImageView ivPicture, ivAlerteCarte;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAge = itemView.findViewById(R.id.tv_age_comp_carte);
            tvNom = itemView.findViewById(R.id.tv_nom_comp_carte);
            tvRace = itemView.findViewById(R.id.tv_race_comp_carte);
            ivPicture = itemView.findViewById(R.id.iv_comp_carte);
            ivAlerteCarte = itemView.findViewById(R.id.iv_alerte_carte);
        }
    }
}
