package com.example.alawan.Class.Adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alawan.Class.Animal;
import com.example.alawan.R;
import com.example.alawan.Class.Server.RetrofitInstance;
import com.example.alawan.Class.Server.ServerInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.tvNom.setText(list.get(position).getName());
        myViewHolder.tvFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(position,v);
            }
        });
    }

    public void delete(int position,View v){
        ServerInterface serverInterface = RetrofitInstance.getInstance().create(ServerInterface.class);
        serverInterface.finAlerte(list.get(position).getId()).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {

                if(response.body() != null){
                    if (response.body()){
                        Toast.makeText(v.getContext(), "Votre alerte est fini",Toast.LENGTH_LONG);
                    }
                    else{
                        Toast.makeText(v.getContext(), "Erreur",Toast.LENGTH_LONG);
                    }
                }
                else{
                    Toast.makeText(v.getContext(), "Erreur",Toast.LENGTH_LONG);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.d("debug error",t.toString());
            }
        });
        list.remove(position);
        notifyItemRemoved(position);

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
