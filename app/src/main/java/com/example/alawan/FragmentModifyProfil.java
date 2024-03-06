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

public class FragmentModifyProfil extends Fragment {

    View view;
    ImageView ivBack;
    Button btModifierAddress;
    Button btDeconnexion;
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
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRetour = new Intent();
                intentRetour.putExtra("deconnexion",0);
                getActivity().setResult(Activity.RESULT_OK,intentRetour);
                getActivity().finish();
            }
        });
        btModifierAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.fv_modify);
                NavController navController = navHostFragment.getNavController();
                navController.navigate(R.id.action_modifyProfil_to_modifyAddress);
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
        return view;
    }
}