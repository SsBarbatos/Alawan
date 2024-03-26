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

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import java.time.ZoneId;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterListeAnimalProfil extends RecyclerView.Adapter {

    List<Animal> list;

    public AdapterListeAnimalProfil(List<Animal> l){
        this.list = l;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.carte_companion,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.tvNom.setText(list.get(position).getName());

        RetrofitInstance.getInstance().create(ServerInterface.class).getRaceAnimal(list.get(position).getIdrace()).enqueue(new Callback<Race>() {
            @Override
            public void onResponse(Call<Race> call, Response<Race> response) {
                if(response.body() != null){
                    myViewHolder.tvRace.setText("Race : " + response.body().getRace());
                }
                else {

                }
            }

            @Override
            public void onFailure(Call<Race> call, Throwable t) {
                Log.v("debug error", t.toString());
            }
        });

        //myViewHolder.tvRace.setText(list.get(position).ge); //Faire un truc global pour les races
        long miliseconds = System.currentTimeMillis();
        LocalDate date1 =list.get(position).getBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate date2 = (new Date(miliseconds)).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int age = Period.between(date1,date2).getYears();
        int ageMois = Period.between(date1,date2).getMonths();
        int ageDay = Period.between(date1,date2).getDays();
        if(age != 0){
            myViewHolder.tvAge.setText(age + " ans");
        }
        else if(ageMois != 0){
            myViewHolder.tvAge.setText(ageMois + " mois");
        }
        else {
            myViewHolder.tvAge.setText(ageDay + " jours");
        }

        myViewHolder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void delete(int position) {
        RetrofitInstance.getInstance().create(ServerInterface.class).deleteAnimal(list.get(position).getId()).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Log.v("debug",response.toString());
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.v("debug error", t.toString());
            }
        });
        list.remove(position);
        notifyItemRemoved(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvRace, tvNom, tvAge;
        ImageView ivPicture, ivDelete;
        public MyViewHolder(@NonNull View view){
            super(view);
            tvNom = view.findViewById(R.id.tv_nom_comp_carte);
            tvRace = view.findViewById(R.id.tv_race_comp_carte);
            tvAge = view.findViewById(R.id.tv_age_comp_carte);
            ivPicture = view.findViewById(R.id.iv_comp_carte);
            ivDelete = view.findViewById(R.id.iv_delete_companion);
        }
    }
}
