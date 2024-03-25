package com.example.alawan.Class.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alawan.ActivityMenu;
import com.example.alawan.Class.Alert;
import com.example.alawan.Class.Animal;
import com.example.alawan.Class.Race;
import com.example.alawan.Class.Server.RetrofitInstance;
import com.example.alawan.Class.Server.ServerInterface;
import com.example.alawan.R;

import java.text.SimpleDateFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterAlertRecherche extends RecyclerView.Adapter{

    public interface  InterfaceAlertRecherche{
        public void zoomAlert(int i);
    }
    List<Animal> list;
    InterfaceAlertRecherche interfaceAlertRecherche;

    public AdapterAlertRecherche(List<Animal> list,InterfaceAlertRecherche interfaceAlertRecherche) {
        this.list = list;
        this.interfaceAlertRecherche = interfaceAlertRecherche;
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
        myViewHolder.tvMedaillon.setText(list.get(position).getName());
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
        RetrofitInstance.getInstance().create(ServerInterface.class).getAlert(list.get(position).getId()).enqueue(new Callback<Alert>() {
            @Override
            public void onResponse(Call<Alert> call, Response<Alert> response) {
                if(response.body() != null){
                    myViewHolder.tvAddress.setText(response.body().getPlace());
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    interfaceAlertRecherche.zoomAlert(list.get(getLayoutPosition()).getId());
                }
            });
        }



    }
}
