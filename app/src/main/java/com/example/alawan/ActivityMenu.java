package com.example.alawan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.alawan.Server.RetrofitInstance;
import com.example.alawan.Server.ServerInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityMenu extends AppCompatActivity {

    public ActivityMenu(){}
    View view;
    LinearLayout layoutAccueil, layoutRecherche, layoutProfil, layout4;
    ImageView ivRecherche,ivProfile,ivAccueil;
    TextView tvProfil, tvRecherche, tvAccueil;
    ActivityResultLauncher<Intent> resultLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        retourActivity();
        setIdAuth();
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
                if(navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof FragmentMap){
                    navController.navigate(R.id.action_map_to_recherche);
                }
                else if(navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof FragmentProfil){
                    navController.navigate(R.id.action_vav_profil_to_recherche);
                }
            }
        });
        layoutProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColor(tvProfil,ivProfile);
                if(navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof FragmentMap){
                    navController.navigate(R.id.action_map_to_vav_profil);
                }
                else if(navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof FragmentRecherche){
                    navController.navigate(R.id.action_recherche_to_vav_profil);
                }
            }
        });
        layoutAccueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColor(tvAccueil,ivAccueil);
                if(navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof FragmentProfil){
                    navController.navigate(R.id.action_vav_profil_to_map);
                }
                else if(navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof FragmentRecherche){
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

    private void retourActivity(){
        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                if(o.getData().getIntExtra(("deconnexion"),0) == 1){
                    setResult(RESULT_OK);
                    finish();
                }
            }
        });
    }

    public void changePage(Intent intent){
        resultLauncher.launch(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        System.exit(0);
    }

    private void setIdAuth(){
        RetrofitInstance.getInstance().create(ServerInterface.class).getIdAuth().enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                SharedPreferences pref = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                int id = response.body();
                editor.putInt("id",id);
                editor.commit();
            }
            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.v("error",t.toString());
            }
        });
    }
}