package com.example.alawan;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class Signup extends Fragment {

    View view;
    Button btSignup;
    TextView tvConnecter;

    public Signup() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_signup, container, false);
        btSignup = view.findViewById(R.id.bt_creer_signup);
        tvConnecter = view .findViewById(R.id.tv_seconnecter_signup);
        NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.fv_main_page);
        NavController navController = navHostFragment.getNavController();

        btSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ------------ Code pour changer de fragment inscription à se connecter ------------------
                // !!!!!!!!!!!!!! rentrez le code pour l'inscription !!!!!!!!!!!!!!!!!!!!!!!!!!!!
                navController.navigate(R.id.action_nav_signup_to_nav_login);
            }
        });

        tvConnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ------------ Code pour changer de fragment inscription à se connecter ------------------
                navController.navigate(R.id.action_nav_signup_to_nav_login);
            }
        });
        return view;
    }
}