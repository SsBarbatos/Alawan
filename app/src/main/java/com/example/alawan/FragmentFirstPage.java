package com.example.alawan;

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


public class FragmentFirstPage extends Fragment {

    public FragmentFirstPage() {
        // Required empty public constructor
    }

    Button btConnecter;
    Button btInscrire;
    Button btInvite;
    View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_first_page, container, false);
        btConnecter = view.findViewById(R.id.bt_connecter_first);
        btInscrire = view.findViewById(R.id.bt_inscrire_first);
        btInvite = view.findViewById(R.id.bt_inviter_first);
        NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.fv_main_page);
        NavController navController = navHostFragment.getNavController();

        btConnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // -------- Code pour changer de fragment de la première page à la page de connexion ---------
                navController.navigate(R.id.action_first_to_nav_login);
            }
        });

        btInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // --------- Code pour changer de la première page à la page invité --------------------------
                // !!!!!!!!!!!!!! Il reste a rajouter le code ici pour dire que s'est un invité !!!!!!!!!!!!!!!
                Intent intent = new Intent(getActivity(), ActivityMenu.class);
                startActivity(intent);
            }
        });

        btInscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // --------- Code pour changer de la première page à la page inscription --------------------------
                navController.navigate(R.id.action_first_to_nav_signup);
            }
        });
        return view;
    }
}