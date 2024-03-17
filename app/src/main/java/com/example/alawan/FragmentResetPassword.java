package com.example.alawan;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class FragmentResetPassword extends Fragment {

    Button btReset;
    View view;
    public FragmentResetPassword() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_reset_password, container, false);
        btReset = view.findViewById(R.id.bt_renitialiser_resetpwd);
        NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.fv_main_page);
        NavController navController = navHostFragment.getNavController();

        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ----------------- code pour changer de fragment resetpwd vers se connecter ----------------------
                // !!!!!!!!!!!!!!!! Mettre le code de reset mot de passe !!!!!!!!!!!!!!!!!!!!!
                navController.navigate(R.id.action_resetPassword_to_nav_login);
            }
        });
        return view;
    }
}