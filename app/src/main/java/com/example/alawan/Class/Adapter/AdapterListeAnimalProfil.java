package com.example.alawan.Class.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alawan.Class.Animal;
import com.example.alawan.R;

import java.util.List;

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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.tvNom.setText(list.get(position).getName());
        //myViewHolder.tvRace.setText(list.get(position).ge); //Faire un truc global pour les races

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvRace, tvNom, tvAge;
        ImageView ivPicture;
        public MyViewHolder(@NonNull View view){
            super(view);
            tvNom = view.findViewById(R.id.tv_nom_comp_carte);
            tvRace = view.findViewById(R.id.tv_race_comp_carte);
            tvAge = view.findViewById(R.id.tv_age_comp_carte);
            ivPicture = view.findViewById(R.id.iv_comp_carte);

        }
    }
}
