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


public class FragmentMap extends Fragment {

    View view;
    FragmentContainerView fvmain;
    LinearLayout layoutAccueil;
    LinearLayout layoutRecherche;
    LinearLayout layoutProfil;
    LinearLayout layout4; // nom temporaire

        public FragmentMap(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_map,container,false);
       return view;
    }
}