package com.example.alawan;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.LinearLayout;

import androidx.core.view.WindowCompat;
import androidx.fragment.app.FragmentContainerView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.alawan.databinding.ActivityMenuBinding;

public class Menu extends AppCompatActivity {

    View view;
    LinearLayout layoutAccueil;
    LinearLayout layoutRecherche;
    LinearLayout layoutProfil;
    LinearLayout layout4; // nom temporaire

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*layoutAccueil = findViewById(R.id.layout_acceuil_menu);
        layoutProfil = findViewById(R.id.layout_profil_menu);
        layoutRecherche = findViewById(R.id.layout_recherche_menu);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fv_mainpage);
        NavController navController = navHostFragment.getNavController();
        // continuer a rajouter les onclicklistener pour faire la navigation
        layoutRecherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_map_to_recherche);
            }
        });*/

    }


}