package com.example.alawan;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.alawan.Class.Server.RetrofitInstance;
import com.example.alawan.Class.Server.ServerInterface;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class FragmentAddAlerte extends Fragment
{
    View view;
    TextView tvPicture;
    EditText etDescription, etPhone;
    Spinner spRaces, spColors;
    Button btAddAlert;
    ImageView ivImageAlert;
    ServerInterface serverInterface ;
    Integer animalId;
    String filePath;
    Bitmap imageBitmap;
    NavController navController;
    Date currentDate;
    Call<Integer> callAddAnimal;


    public FragmentAddAlerte()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        utiliserCoordonnees();
    }

    // __ MANAGE CAMERA ____________________________________________________________________________
        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result)
            {
                if (result.getResultCode() == Activity.RESULT_OK)
                {
                    Intent data = result.getData();
                    Bundle extras = data.getExtras();

                    imageBitmap = (Bitmap) extras.get("data");
                    ivImageAlert.setImageBitmap(imageBitmap);
                    ivImageAlert.setAutofillHints("picture");

                }
                else
                    Log.v("debug error", result.toString());
            }
        });
    // _____________________________________________________________________________________________

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.fv_mainpage);
        navController = navHostFragment.getNavController();

        view = inflater.inflate(R.layout.fragment_add_alerte, container, false);

        tvPicture = view.findViewById(R.id.tv_inserer_photo);

        etDescription = view.findViewById(R.id.et_description_alerte_invite);
        etPhone = view.findViewById(R.id.et_phone_alerte_invite);

        spColors = view.findViewById(R.id.sp_couleur_alerte_invite);
        spRaces = view.findViewById(R.id.sp_race_alerte_invite);

        ivImageAlert = view.findViewById(R.id.iv_logo_image_add_alerte_invite);
        ivImageAlert.setAutofillHints("no_picture");

        btAddAlert = view.findViewById(R.id.bt_lancer_alerte_invite);

        serverInterface = RetrofitInstance.getInstance().create(ServerInterface.class);

        currentDate = new Date();

    // __ FILL THE COLORS SPINNER __________________________________________________________________
        ArrayAdapter<CharSequence> adapterColors = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.list_colors,
                android.R.layout.simple_spinner_item
        );
        adapterColors.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spColors.setAdapter(adapterColors);
    // _____________________________________________________________________________________________
    // __ FILL THE RACES SPINNER ___________________________________________________________________
        ArrayAdapter<CharSequence> adapterRaces = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.list_races,
                android.R.layout.simple_spinner_item
        );
        adapterRaces.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spRaces.setAdapter(adapterRaces);
    // _____________________________________________________________________________________________

        ivImageAlert.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(requireActivity(), new String[] {
                            android.Manifest.permission.CAMERA
                    }, 100);
                }

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                launcher.launch(intent);
            }
        });

        btAddAlert.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                boolean validation;

                if (etDescription.getText().toString().trim().length() == 0)
                {
                    etDescription.setError("Le champ est vide");
                    validation = false;
                }
                else if (etPhone.getText().toString().trim().length() == 0)
                {
                    etPhone.setError("Le champ est vide");
                    validation = false;
                }
                else if (spColors.getSelectedItem().toString().equals("Couleur"))
                {
                    ((TextView)spColors.getSelectedView()).setError("Choisissez une couleur");
                    validation = false;
                }
                else if (spRaces.getSelectedItem().toString().equals("Race"))
                {
                    ((TextView)spRaces.getSelectedView()).setError("Choisissez une race");
                    validation = false;
                }
                else
                    validation = true;

                if (validation)
                    AddAlert();
            }
        });

        return view;
    }


    public void AddAlert()
    {
        if(ivImageAlert.getAutofillHints().toString().equals("no_picture"))
        {
            callAddAnimal = serverInterface.addAnimal(4, spRaces.getSelectedItemPosition() - 1, 0, "Inconnu", null, null, true);
        }
        else
        {
            Call<String> callImage = serverInterface.uploadImage(imageBitmap);
            callImage.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    filePath = response.body();
                    callAddAnimal = serverInterface.addAnimal(4, spRaces.getSelectedItemPosition() - 1, 0, "Inconnu", filePath, null, true);
                    callAddAnimal.enqueue(new Callback<Integer>() {
                        @Override
                        public void onResponse(Call<Integer> call, Response<Integer> response) {
                            animalId = response.body();

                            Call<Boolean> callAnimalColor = serverInterface.addAnimalColor(animalId, spColors.getSelectedItemPosition() - 1);
                            callAnimalColor.enqueue(new Callback<Boolean>() {
                                @Override
                                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                    Log.v("Temporary animal/color", "Created successfully");
                                }
                                @Override
                                public void onFailure(Call<Boolean> call, Throwable t) {
                                    Log.v("Temporary animal/color", t.toString());
                                }
                            });

                            Call<Boolean> callAddAlert = serverInterface.addAlert(  animalId,
                                    etDescription.getText().toString(),
                                    spRaces.getSelectedItem().toString(),
                                    spColors.getSelectedItem().toString(),
                                    currentDate,
                                    etPhone.getText().toString());


                            callAddAlert.enqueue(new Callback<Boolean>() {
                                @Override
                                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                    navController.navigate(R.id.action_fragmentAddAlerte_to_map);
                                }
                                @Override
                                public void onFailure(Call<Boolean> call, Throwable t) {
                                    Log.v("Add alert", t.toString());
                                }
                            });
                        }
                        @Override
                        public void onFailure(Call<Integer> call, Throwable t) {
                            Log.v("debug error", t.toString());
                        }
                    });
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.v("Create animal", t.toString());
                }
            });
        }
    }

    private String utiliserCoordonnees() {
        // Récupérer l'activité parente
        ActivityMenu activite = (ActivityMenu) getActivity();

            // Utiliser les méthodes publiques de l'activité pour obtenir les coordonnées
            double latitude = activite.getUserLatitude();
            double longitude = activite.getUserLongitude();
            String retour = latitude +" "+longitude;
            //return retour ;

        return retour ;
    }

}