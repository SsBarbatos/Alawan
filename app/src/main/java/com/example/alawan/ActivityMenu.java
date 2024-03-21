package com.example.alawan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.annotation.NonNull;


import com.example.alawan.Class.Adapter.AdapterAlertRecherche;
import com.example.alawan.Class.Server.RetrofitInstance;
import com.example.alawan.Class.Server.ServerInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import androidx.core.app.ActivityCompat;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;



import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.util.List;



public class ActivityMenu extends AppCompatActivity implements OnMapReadyCallback, AdapterAlertRecherche.InterfaceAlertRecherche {

    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationClient;
    private LatLngBounds bounds;


    public ActivityMenu() {
    }

    View view;
    NavController navController;
    NavHostFragment navHostFragment;
    LinearLayout layoutAccueil, layoutRecherche, layoutProfil, layout4;
    ImageView ivRecherche, ivProfile, ivAccueil, ivAlerte, iv4;
    TextView tvProfil, tvRecherche, tvAccueil, tv4;
    ActivityResultLauncher<Intent> resultLauncher;
    SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        retourActivity();
        setIdAuth();
        setContentView(R.layout.activity_menu);
        // Layout
        layoutAccueil = findViewById(R.id.layout_acceuil_menu);
        layoutProfil = findViewById(R.id.layout_profil_menu);
        layoutRecherche = findViewById(R.id.layout_recherche_menu);
        layout4 = findViewById(R.id.layout_chat_menu);
        // Image view
        ivProfile = findViewById(R.id.iv_profile_menu);
        ivRecherche = findViewById(R.id.iv_recherche_menu);
        ivAccueil = findViewById(R.id.iv_acceuil_menu);
        iv4 = findViewById(R.id.iv_chat_menu);
        // Text view
        tvAccueil = findViewById(R.id.tv_acceuil_menu);
        tvProfil = findViewById(R.id.tv_profile_menu);
        tvRecherche = findViewById(R.id.tv_recherche_menu);
        ivAlerte = findViewById(R.id.iv_alerte_front_main);
        tv4 = findViewById(R.id.tv_chat_menu);

        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fv_mainpage);
        navController = navHostFragment.getNavController();
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mv_map);
        Navigation();


        mapFragment.getMapAsync(this);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // Demande de permission d'accès à la localisation si ce n'est pas déjà fait
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        } else {
            // Afficher la localisation de l'utilisateur si la permission est déjà accordée
            showUserLocation();
        }


    }

    private void Navigation() {
        // Met la couleur de base de à accueil
        changeColor(tvAccueil, ivAccueil);
        layoutRecherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColor(tvRecherche, ivRecherche);
                if (navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof FragmentMap) {
                    navController.navigate(R.id.action_map_to_recherche);
                } else if (navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof FragmentProfil) {
                    navController.navigate(R.id.action_vav_profil_to_recherche);
                }
                else if (navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof FragmentAddAlerte) {
                    navController.navigate(R.id.action_fragmentAddAlerte_to_recherche);
                }
                else if(navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof FragmentAddPet){
                    navController.navigate(R.id.action_addPet_to_recherche);
                }
                else if(navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof FragmentFindMed){
                    navController.navigate(R.id.action_fragment_find_med_to_recherche);
                }
                else if(navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof FragmentZoomAlert){
                    navController.navigate(R.id.action_fragmentZoomAlert_to_recherche);
                }
            }

        });
        layoutProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColor(tvProfil, ivProfile);
                if (navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof FragmentMap) {
                    navController.navigate(R.id.action_map_to_vav_profil);
                } else if (navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof FragmentRecherche) {
                    navController.navigate(R.id.action_recherche_to_vav_profil);
                }
                else if (navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof FragmentAddAlerte) {
                    navController.navigate(R.id.action_fragmentAddAlerte_to_vav_profil);
                }
                else if(navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof FragmentAddPet){
                    navController.navigate(R.id.action_addPet_to_vav_profil);
                }
                else if(navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof FragmentFindMed){
                    navController.navigate(R.id.action_fragment_find_med_to_vav_profil);
                }
                else if(navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof FragmentZoomAlert){
                    navController.navigate(R.id.action_fragmentZoomAlert_to_vav_profil);
                }
            }
        });
        layoutAccueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColor(tvAccueil, ivAccueil);
                if (navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof FragmentProfil) {
                    navController.navigate(R.id.action_vav_profil_to_map);
                } else if (navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof FragmentRecherche) {
                    navController.navigate(R.id.action_recherche_to_map);
                }
                else if (navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof FragmentAddAlerte) {
                    navController.navigate(R.id.action_fragmentAddAlerte_to_map);
                }
                else if(navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof FragmentAddPet){
                    navController.navigate(R.id.action_addPet_to_map);
                }
                else if(navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof FragmentFindMed){
                    navController.navigate(R.id.action_fragment_find_med_to_map);
                }
                else if(navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof FragmentZoomAlert){
                    navController.navigate(R.id.action_fragmentZoomAlert_to_map);
                }
            }
        });
        layout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColor(tv4, iv4);
                if (navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof FragmentProfil) {
                    navController.navigate(R.id.action_vav_profil_to_fragment_find_med);
                } else if (navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof FragmentRecherche) {
                    navController.navigate(R.id.action_recherche_to_fragment_find_med);
                }
                else if (navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof FragmentAddAlerte) {
                    navController.navigate(R.id.action_fragmentAddAlerte_to_fragment_find_med);
                }
                else if(navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof FragmentAddPet){
                    navController.navigate(R.id.action_addPet_to_fragment_find_med);
                }
                else if(navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof FragmentMap){
                    navController.navigate(R.id.action_map_to_fragment_find_med);
                }
                else if(navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof FragmentZoomAlert){
                    navController.navigate(R.id.action_fragmentZoomAlert_to_fragment_find_med);
                }
            }
        });

        ivAlerte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColor(null,null);
                if (navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof FragmentProfil) {
                    navController.navigate(R.id.action_vav_profil_to_fragmentAddAlerte);
                } else if (navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof FragmentRecherche) {
                    navController.navigate(R.id.action_recherche_to_fragmentAddAlerte);
                }
                else if(navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof FragmentMap){
                    navController.navigate(R.id.action_map_to_fragmentAddAlerte);
                }
                else if (navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof FragmentAddPet) {
                    navController.navigate(R.id.action_addPet_to_fragmentAddAlerte);
                }
                else if(navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof FragmentFindMed){
                    navController.navigate(R.id.action_fragment_find_med_to_fragmentAddAlerte);
                }
                else if(navHostFragment.getChildFragmentManager().getFragments().get(0) instanceof FragmentZoomAlert){
                    navController.navigate(R.id.action_fragmentZoomAlert_to_fragmentAddAlerte);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission accordée, afficher la localisation
                showUserLocation();
            } else {
                // Permission refusée, gérer le cas où l'utilisateur refuse l'accès à la localisation
            }
        }
    }

    private void changeColor(TextView textView, ImageView imageView) {
        //Change la couleur de toutes les image en gris
        ivProfile.setColorFilter(getResources().getColor(R.color.gris));
        ivRecherche.setColorFilter(getResources().getColor(R.color.gris));
        ivAccueil.setColorFilter(getResources().getColor(R.color.gris));
        iv4.setColorFilter(getResources().getColor(R.color.gris));
        //Change la couleur de toutes les texts en gris
        tvProfil.setTextColor(getResources().getColor(R.color.gris));
        tvAccueil.setTextColor(getResources().getColor(R.color.gris));
        tvRecherche.setTextColor(getResources().getColor(R.color.gris));
        tv4.setTextColor(getResources().getColor(R.color.gris));
        //Code pour changer la couleur en vert de lui cliquer
        if(textView != null){
            textView.setTextColor(getResources().getColor(R.color.GreenText));
            imageView.setColorFilter(getResources().getColor(R.color.GreenText));
            // Cache la map si on est pas sur le fragment de map
            if (textView.equals(tvAccueil)){
                mapFragment.getView().setVisibility(View.VISIBLE);
            }
            else {
                mapFragment.getView().setVisibility(View.INVISIBLE);
            }
        }
        else { // Cache la map quand on clique sur le bouton alerte
            mapFragment.getView().setVisibility(View.INVISIBLE);
        }

    }

    private void retourActivity() {
        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        if (o.getData().getIntExtra(("deconnexion"), 0) == 1) {
                            setResult(RESULT_OK);
                            finish();
                        }
                    }
                });
    }

    public void changePage(Intent intent) {
        resultLauncher.launch(intent);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng address1 = getLocationFromAddress("7331 Rue Notre Dame O, Trois-Rivières, QC G9B 1L7");
        if (address1 != null) {
            mMap.addMarker(new MarkerOptions()
                    .position(address1)
                    .title("Address 1")
                    .snippet("Votre adresse 1"));
        }

        LatLng address2 = getLocationFromAddress("3750 Rue de l'aéroport, Trois-Rivières QC G9B 2N8");
        if (address2 != null) {
            mMap.addMarker(new MarkerOptions()
                    .position(address2)
                    .title("Address 2")
                    .snippet("Votre adresse 2"));
        }

        LatLng address3 = getLocationFromAddress("Pavillon des Sciences, 3500 Rue de Courval, Trois-Rivières, QC G8Z 1T2");
        if (address3 != null) {
            mMap.addMarker(new MarkerOptions()
                    .position(address3)
                    .title("Address 3")
                    .snippet("Votre adresse 3"));
        }

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        if (address1 != null) {
            builder.include(address1);
        }
        if (address2 != null) {
            builder.include(address2);
        }
        if (address3 != null) {
            builder.include(address3);
        }
        bounds = builder.build();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && mMap != null && bounds != null) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));
        }
    }




    // Convertir l adresse en localisation
    private LatLng getLocationFromAddress(String strAddress) {

        Geocoder coder = new Geocoder(this);
        List<Address> address;
        LatLng p1 = null;

        try {
            // Getting a maximum of 1 Address that matches the input text
            address = coder.getFromLocationName(strAddress, 1);

            if (address == null || address.size() == 0) {
                return null;
            }

            Address location = address.get(0);
            p1 = new LatLng(location.getLatitude(), location.getLongitude());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return p1;
    }

    //localisation du telephone
    private void showUserLocation() {
        // Obtenir la dernière localisation connue de l'utilisateur
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            // localisation
                            LatLng userLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                            // ajouter un marker
                            mMap.addMarker(new MarkerOptions().position(userLatLng).title("Votre position"));
                            // conrole cam
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLatLng, 15));
                        }
                    }
                });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        System.exit(0);
    }

    private void setIdAuth() {
        Intent intent = getIntent();
        int id;
        if(intent.hasExtra("id")){
            id = intent.getIntExtra("id",0);
            if(id != 0){
                SharedPreferences pref = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("id", id);
                editor.apply();
                editor.commit();
            }
            else {
                setResult(RESULT_CANCELED);
                finish();
            }
        }

       /* RetrofitInstance.getInstance().create(ServerInterface.class).getIdAuth().enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                SharedPreferences pref = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                if (response.body() != null) {

                    int id = response.body().intValue();
                    editor.putInt("id", id);
                    editor.apply();
                } else {
                    // Gérer le cas où le corps de la réponse est null

                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.v("error", t.toString());
            }
        });*/
    }

    public void zoomAlert(int i){
        SharedPreferences pref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("idZoom", i);
        editor.apply();
        editor.commit();
        navController.navigate(R.id.action_recherche_to_fragmentZoomAlert);
    }
}
