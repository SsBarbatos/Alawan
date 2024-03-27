package com.example.alawan.Class.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alawan.Class.Animal;
import com.example.alawan.Class.AnimalColor;
import com.example.alawan.Class.Color;
import com.example.alawan.Class.DescriptionDialog;
import com.example.alawan.Class.Person;
import com.example.alawan.Class.Race;
import com.example.alawan.Class.Server.RetrofitInstance;
import com.example.alawan.Class.Server.ServerInterface;
import com.example.alawan.FragmentAddAlerte;
import com.example.alawan.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class AdapterListeAnimalAlerte extends RecyclerView.Adapter {

    NavController navController;

    List<Animal> listeAnimal;
    Date currentDate;
    Animal animal;
    Color color;
    AnimalColor animalColor;
    Race race;
    Person user;
    String description;
    Boolean good;
    ServerInterface serverInterface;
    FragmentAddAlerte fragmentAddAlerte;
    Integer userID;

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
        serverInterface = RetrofitInstance.getInstance().create(ServerInterface.class);


        /*   GET ACTIVITY MARCHE PAS

        NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.fv_mainpage);
        navController = navHostFragment.getNavController();
        serverInterface = RetrofitInstance.getInstance().create(ServerInterface.class);
        */

        MyViewHolder myViewHolder = (AdapterListeAnimalAlerte.MyViewHolder) holder;
        myViewHolder.tvNom.setText(listeAnimal.get(position).getName());

    // __ SET RACE _________________________________________________________________________________
        RetrofitInstance.getInstance().create(ServerInterface.class).getRaceAnimal(listeAnimal.get(position).getIdrace()).enqueue(new Callback<Race>() {
            @Override
            public void onResponse(Call<Race> call, Response<Race> response) {
                if(response.body() != null){
                    myViewHolder.tvRace.setText("Race : " + response.body().getRace());
                    race = response.body();
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
    // _____________________________________________________________________________________________
    // __ SET AGE __________________________________________________________________________________
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
    // _____________________________________________________________________________________________

        Call<Animal> callGetAnimal = serverInterface.getAnimal(listeAnimal.get(position).getId());
        callGetAnimal.enqueue(new Callback<Animal>() {
            @Override
            public void onResponse(Call<Animal> call, Response<Animal> response) {
                animal = response.body();
                Call<AnimalColor> callGetAnimalColor = serverInterface.getAnimalColor(animal.getId());
                callGetAnimalColor.enqueue(new Callback<AnimalColor>() {
                    @Override
                    public void onResponse(Call<AnimalColor> call, Response<AnimalColor> response) {
                        animalColor = response.body();
                        Call<Color> callGetColor = serverInterface.getColor(animalColor.getIdColor());
                        callGetColor.enqueue(new Callback<Color>() {
                            @Override
                            public void onResponse(Call<Color> call, Response<Color> response) {
                                color = response.body();
                            }

                            @Override
                            public void onFailure(Call<Color> call, Throwable t) {
                                Log.v("Get Auth ID", t.toString());
                            }
                        });
                    }
                    @Override
                    public void onFailure(Call<AnimalColor> call, Throwable t) {
                        Log.v("Get Auth ID", t.toString());
                    }
                });
            }
            @Override
            public void onFailure(Call<Animal> call, Throwable t) {
                Log.v("Get Auth ID", t.toString());
            }
        });

        myViewHolder.ivAlerteCarte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                DescriptionDialog dialog = new DescriptionDialog(v.getContext());
                dialog.show();

                if(good)
                {
                    addAlert();
                }
            }
        });
    }

    public void setDescription(String desc)
    {
        description = desc;
        good = true;
    }

    public void addAlert()
    {
        Call<Integer> callIdAuth = serverInterface.getIdAuth();
        callIdAuth.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                userID = response.body();
                Call<Person> callGetUser = serverInterface.getUser(userID);
                callGetUser.enqueue(new Callback<Person>() {
                    @Override
                    public void onResponse(Call<Person> call, Response<Person> response) {
                        user = response.body();
                        Call<Boolean> callAddAlert = serverInterface.addAlert(animal.getId(), description, race.getRace(), color.getColor(), currentDate, user.getPhone());
                        callAddAlert.enqueue(new Callback<Boolean>() {
                            @Override
                            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                navController.navigate(R.id.action_fragmentAddAlerte_to_map);
                                Toast.makeText(fragmentAddAlerte.requireContext(), "Alerte ajoutée avec succès", Toast.LENGTH_LONG).show();
                            }
                            @Override
                            public void onFailure(Call<Boolean> call, Throwable t) {
                                Log.v("Get Auth ID", t.toString());
                            }
                        });
                    }
                    @Override
                    public void onFailure(Call<Person> call, Throwable t) {
                        Log.v("Get Auth ID", t.toString());
                    }
                });
            }
            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.v("Get Auth ID", t.toString());
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
