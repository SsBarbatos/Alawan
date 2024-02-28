package com.example.alawan;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    LinearLayout layoutAccueil, layoutRecherche, layoutProfil, layout4;
    ImageView ivRecherche,ivProfile,ivAccueil;
    TextView tvProfil, tvRecherche, tvAccueil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        layoutAccueil = findViewById(R.id.layout_acceuil_menu);
        layoutProfil = findViewById(R.id.layout_profil_menu);
        layoutRecherche = findViewById(R.id.layout_recherche_menu);
        ivProfile = findViewById(R.id.iv_profile_menu);
        ivRecherche = findViewById(R.id.iv_recherche_menu);
        ivAccueil = findViewById(R.id.iv_acceuil_menu);
        tvAccueil = findViewById(R.id.tv_acceuil_menu);
        tvProfil = findViewById(R.id.tv_profile_menu);
        tvRecherche = findViewById(R.id.tv_recherche_menu);
        changeColor(tvAccueil,ivAccueil);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fv_mainpage);
        NavController navController = navHostFragment.getNavController();
        // continuer a rajouter les onclicklistener pour faire la navigation
        layoutRecherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColor(tvRecherche,ivRecherche);
                if(navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof Map){
                    navController.navigate(R.id.action_map_to_recherche);
                }
                else if(navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof Profil){
                    navController.navigate(R.id.action_vav_profil_to_recherche);
                }
            }
        });
        layoutProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColor(tvProfil,ivProfile);
                if(navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof Map){
                    navController.navigate(R.id.action_map_to_vav_profil);
                }
                else if(navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof Recherche){
                    navController.navigate(R.id.action_recherche_to_vav_profil);
                }
            }
        });
        layoutAccueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColor(tvAccueil,ivAccueil);
                if(navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof Profil){
                    navController.navigate(R.id.action_vav_profil_to_map);
                }
                else if(navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof Recherche){
                    navController.navigate(R.id.action_recherche_to_map);
                }
            }
        });
    }

    private void changeColor(TextView textView, ImageView imageView){
        ivProfile.setColorFilter(getResources().getColor(R.color.gris));
        ivRecherche.setColorFilter(getResources().getColor(R.color.gris));
        ivAccueil.setColorFilter(getResources().getColor(R.color.gris));
        tvProfil.setTextColor(getResources().getColor(R.color.gris));
        tvAccueil.setTextColor(getResources().getColor(R.color.gris));
        tvRecherche.setTextColor(getResources().getColor(R.color.gris));
        textView.setTextColor(getResources().getColor(R.color.GreenText));
        imageView.setColorFilter(getResources().getColor(R.color.GreenText));
    }

}