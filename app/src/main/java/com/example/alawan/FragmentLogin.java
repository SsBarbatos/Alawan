package com.example.alawan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.alawan.Server.RetrofitInstance;
import com.example.alawan.Server.ServerInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentLogin extends Fragment {

    View view;
    Button btConnecter;
    TextView tvInscrire;
    TextView tvMdpOublie;
    EditText etEmail, etPassword;
    public FragmentLogin() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        btConnecter = view.findViewById(R.id.bt_connecter_login);
        tvInscrire = view.findViewById(R.id.bt_creercompte_login);
        tvMdpOublie = view.findViewById(R.id.tv_motdepasseoublier_login);
        NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.fv_main_page);
        NavController navController = navHostFragment.getNavController();
        btConnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // --------- code pour changer d'activity se connecter à main ---------------
                etEmail = view.findViewById(R.id.et_email_login);
                etPassword = view.findViewById(R.id.et_password_login);
                Boolean validation = true;
                if(etEmail.getText().toString().trim().length() == 0){
                    etEmail.setError("Erreur rentrez un email");
                    validation = false;
                }
                if (etPassword.getText().toString().trim().length() == 0){
                    etPassword.setError("Erreur rentrez un mot de passe");
                    validation = false;
                }
                if (validation){
                    RetrofitInstance.getInstance().create(ServerInterface.class).login(etEmail.getText().toString(), etPassword.getText().toString()).enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            if(response.body()){
                                Intent intent = new Intent(getActivity(), ActivityMenu.class);
                                startActivity(intent);
                            }

                        }

                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {
                            Log.v("debug",t.toString());
                        }
                    });

                }

            }
        });

        tvInscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // --------- code pour changer de fragment se connecter à inscription ---------------
                navController.navigate(R.id.action_nav_login_to_nav_signup);
            }
        });

        tvMdpOublie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // --------- code pour changer de fragment se connecter à mot de passe oublier ---------------
                navController.navigate(R.id.action_nav_login_to_resetPassword);
            }
        });

        return view;
    }
}