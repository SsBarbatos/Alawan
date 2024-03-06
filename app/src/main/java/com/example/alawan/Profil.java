package com.example.alawan;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import javax.crypto.spec.IvParameterSpec;


public class Profil extends Fragment {

    ImageView ivSetting;
    View view;
    public Profil() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profil, container, false);
        ivSetting = view.findViewById(R.id.iv_setting_profile);
        ivSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Rajouter le code de deconnexion
                Intent intent = new Intent(getActivity(), ActivityModifyProfil.class);
                ((Menu)getActivity()).changePage(intent);
            }
        });
        return view;
    }
}