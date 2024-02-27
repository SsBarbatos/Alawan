package com.example.alawan;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


public class Map extends Fragment {

    View view;
    FragmentContainerView fvmain;
    LinearLayout layoutAccueil;
    LinearLayout layoutRecherche;
    LinearLayout layoutProfil;
    LinearLayout layout4; // nom temporaire
    public Map(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_map,container,false);
       fvmain = view.findViewById(R.id.fv_mainpage);
       layoutAccueil = view.findViewById(R.id.layout_acceuil_menu);
       layoutProfil = view.findViewById(R.id.layout_profil_menu);
       layoutRecherche = view.findViewById(R.id.layout_recherche_menu);
       // continuer a rajouter les onclicklistener pour faire la navigation

       return view;
    }
}