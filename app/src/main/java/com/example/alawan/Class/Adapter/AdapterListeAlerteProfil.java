package com.example.alawan.Class.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alawan.Class.Alert;
import com.example.alawan.Class.Animal;
import com.example.alawan.R;

import java.util.List;

public class AdapterListeAlerteProfil extends RecyclerView.Adapter {

    List<Animal> list;

    public AdapterListeAlerteProfil(List<Animal> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.carte_alerte_encours,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.tvNom.setText(list.get(position).getName());
        //Reste du code

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvNom,tvAge, tvRace, tvFin;
        ImageView ivPicture;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFin = itemView.findViewById(R.id.tv_fin_alerte_alerte_encours);
            tvAge = itemView.findViewById(R.id.tv_age_comp_carte);
            tvNom = itemView.findViewById(R.id.tv_nom_comp_carte);
            tvRace = itemView.findViewById(R.id.tv_race_comp_carte);
            ivPicture = itemView.findViewById(R.id.iv_comp_carte);
        }
    }
}
