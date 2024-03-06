package com.example.alawan;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class FragmentLogin extends Fragment {

    View view;
    Button btConnecter;
    TextView tvInscrire;
    TextView tvMdpOublie;
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
                // --------- code pour changer de fragment se connecter à main ---------------
                // !!!!!!!!!!! Rajouter le code de login !!!!!!!!!!!!!!!!!!!!!!!!!!!
                //navController.navigate(R.id.action_nav_login_to_menu);
                Intent intent = new Intent(getActivity(), Menu.class);
                startActivity(intent);
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