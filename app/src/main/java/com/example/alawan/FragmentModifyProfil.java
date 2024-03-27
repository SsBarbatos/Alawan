package com.example.alawan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.alawan.Class.Person;
import com.example.alawan.Class.Server.RetrofitInstance;
import com.example.alawan.Class.Server.ServerInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentModifyProfil extends Fragment {

    View view;
    ImageView ivBack;
    Button btModifierAddress;
    Button btDeconnexion;
    EditText etPrenom, etNom, etEmail, etphone;
    String prenom, nom, phone, email;
    public FragmentModifyProfil() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_modify_profil, container, false);
        btDeconnexion = view.findViewById(R.id.bt_deconnexion_modifierprofil);
        btModifierAddress = view.findViewById(R.id.bt_modifieradresse_modifierprofil);
        ivBack = view.findViewById(R.id.iv_back_modifierprofil);

        etPrenom = view.findViewById(R.id.et_prenom_modifierprofil);
        etphone = view.findViewById(R.id.et_phone_modifierprofil);
        etNom = view.findViewById(R.id.et_nom_modifierprofil);
        etEmail = view.findViewById(R.id.et_mail_modifierprofil);

        int idAuth = getActivity().getPreferences(Context.MODE_PRIVATE).getInt("id",0);

        RetrofitInstance.getInstance().create(ServerInterface.class).getUser(idAuth).enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                Log.v("debug",response.toString());
                if(response.body() != null){
                    etPrenom.setText(response.body().getName());
                    prenom = response.body().getName();
                    etNom.setText(response.body().getLastName());
                    nom = response.body().getLastName();
                    if(response.body().getPhone() != null){
                        etphone.setText(response.body().getPhone());
                        phone = response.body().getPhone();
                    }
                    etEmail.setText(response.body().getEmail());
                    email = response.body().getEmail();
                }
            }
            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                Log.v("debug error", t.toString());
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(etphone.getText().toString().trim().length() != 0){
                        phone = etphone.getText().toString();
                    }
                    if(etNom.getText().toString().trim().length() != 0){
                        nom = etNom.getText().toString();
                    }
                    if(etEmail.getText().toString().trim().length() != 0){
                        email = etEmail.getText().toString();
                    }
                    if(etPrenom.getText().toString().trim().length() != 0){
                        prenom = etPrenom.getText().toString();
                    }
                    RetrofitInstance.getInstance().create(ServerInterface.class).modifyPerson(idAuth,prenom,nom,email,phone).enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            Log.v("debug",response.toString());
                            if(response.body() != null){
                                Intent intentRetour = new Intent();
                                intentRetour.putExtra("deconnexion",0);
                                getActivity().setResult(Activity.RESULT_OK,intentRetour);
                                getActivity().finish();
                            }
                        }
                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {
                            Log.v("Debug error", t.toString());
                        }
                    });



            }
        });
        /*btModifierAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.fv_modify);
                NavController navController = navHostFragment.getNavController();
                navController.navigate(R.id.action_modifyProfil_to_modifyAddress);
            }
        });*/

        btDeconnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRetour = new Intent();
                intentRetour.putExtra("deconnexion",1);
                getActivity().setResult(Activity.RESULT_OK,intentRetour);
                getActivity().finish();
            }
        });
        return view;
    }
}