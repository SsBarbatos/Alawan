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
public class AdapterAlertRecherche extends RecyclerView.Adapter{

    List<Animal> list;

    public AdapterAlertRecherche(List<Animal> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.carte_alerte,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        //rajouter code
    }

    @Override
    public int getItemCount(){return list.size();}

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvAddress, tvRace, tvMedaillon;
        ImageView ivPicture;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAddress = itemView.findViewById(R.id.tv_address_carte_alerte);
            tvMedaillon = itemView.findViewById(R.id.tv_medaillons_carte_alerte);
            tvRace = itemView.findViewById(R.id.tv_race_carte_alerte);

        }
    }
}
