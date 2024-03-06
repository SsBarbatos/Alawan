package com.example.alawan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


public class FragmentModifyAddress extends Fragment {
    View view;
    ImageView ivBack;
    Button btModifier;
    Button btDeconnexion;

    public FragmentModifyAddress() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_modify_address, container, false);
        ivBack = view.findViewById(R.id.iv_alerte_main);
        btDeconnexion = view.findViewById(R.id.bt_deconnecter_adresse);
        btModifier = view.findViewById(R.id.bt_modifier_adresse);
        NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.fv_modify);
        NavController navController = navHostFragment.getNavController();
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_modifyAddress_to_modifyProfil);
            }
        });
        btDeconnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRetour = new Intent();
                intentRetour.putExtra("deconnexion",1);
                getActivity().setResult(Activity.RESULT_OK,intentRetour);
                getActivity().finish();
            }
        });
        btModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //RAJOUTER LE CODE DE MODIFICATION
                navController.navigate(R.id.action_modifyAddress_to_modifyProfil);
            }
        });
        return view;
    }
}