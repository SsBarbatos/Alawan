package com.example.alawan;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
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
import com.example.alawan.Class.Animal;
import com.example.alawan.Class.AnimalColor;
import com.example.alawan.Class.Color;
import com.example.alawan.Class.Race;

import java.io.File;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import com.example.alawan.Class.Server.RetrofitInstance;
import com.example.alawan.Class.Server.ServerInterface;

public class FragmentAddAlerte extends Fragment {

    Race useRace;
    Color useColor;
    AnimalColor animalColor;
    View view;
    TextView tvPicture;
    EditText etDescription, etPhone;
    Spinner spRaces, spColors;
    Button btAddAlert;
    ImageView ivImageAlert, ivIconImageAlert;
    ServerInterface serverInterface ;
    NavController navController;
    Animal animal;
    String filePath = "";
    Bitmap imageBitmap;

    public FragmentAddAlerte()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
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

                    ivIconImageAlert.setVisibility(View.INVISIBLE);
                    tvPicture.setVisibility(View.INVISIBLE);
                }
                else
                    Log.v("debug error", result.toString());
            }
        });
    // _____________________________________________________________________________________________

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_add_alerte, container, false);

        tvPicture = view.findViewById(R.id.tv_inserer_photo);

        etDescription = view.findViewById(R.id.et_description_alerte);
        etPhone = view.findViewById(R.id.et_phone_alerte);

        spColors = view.findViewById(R.id.sp_couleur_alerte);
        spRaces = view.findViewById(R.id.sp_race_alerte);

        ivImageAlert = view.findViewById(R.id.iv_inserer_photo_alerte);
        ivIconImageAlert = view.findViewById(R.id.iv_icon_inserer_photo_alerte);

        btAddAlert = view.findViewById(R.id.bt_lancer_alerte);

        serverInterface = RetrofitInstance.getInstance().create(ServerInterface.class);

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

        tvPicture.setOnClickListener(new View.OnClickListener()
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
                    AddAlert(etDescription.getText().toString(), spRaces.getSelectedItem().toString(), spColors.getSelectedItem().toString(), etPhone.getText().toString());
            }
        });

        return view;
    }

    public void AddAlert(String description, String race, String color, String phone) {
    // __ GET USER ID ______________________________________________________________________________
        /*
            Call<Integer> callUser = serverInterface.getIdAuth();
            callUser.enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    userId = response.body();
                }
                @Override
                public void onFailure(Call<Integer> call, Throwable t) {
                    Log.v("debug error", t.toString());
                }
            });
        */
    // _____________________________________________________________________________________________
    // __ GET RACE ID _____________________________________________________________________________
        Call<Race> callRace = serverInterface.getRace(spRaces.getSelectedItem().toString());
        callRace.enqueue(new Callback<Race>() {
            @Override
            public void onResponse(Call<Race> call, Response<Race> response) {
                useRace = response.body();
            }
            @Override
            public void onFailure(Call<Race> call, Throwable t) {
                Log.v("debug error", t.toString());
            }
        });
    // _____________________________________________________________________________________________
    // __ CREATE ANIMAL ____________________________________________________________________________
        if(tvPicture.getText().toString().equals("Insérer une photo ici"))
            animal = new Animal(4, useRace.getId(), true);
        else
        {
            Call<String> callImage = serverInterface.uploadImage(imageBitmap);
            callImage.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    filePath = response.body();
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.v("debug error", t.toString());
                }
            });

            animal = new Animal(4, useRace.getId(), filePath, true); // RESTE A ALLER CHERCHER LA PHOTO SUR LE SERVEUR
        }
    // _____________________________________________________________________________________________
    // __ GET COLOR ID _____________________________________________________________________________
        Call<Color> callColor = serverInterface.getColor(spColors.getSelectedItem().toString());
        callColor.enqueue(new Callback<Color>() {
            @Override
            public void onResponse(Call<Color> call, Response<Color> response) {
                useColor = response.body();
            }
            @Override
            public void onFailure(Call<Color> call, Throwable t) {
                Log.v("debug error", t.toString());
            }
        });
    // _____________________________________________________________________________________________
    // __ CREATE ANIMAL COLOR ______________________________________________________________________
        Call<Boolean> callAnimalColor = serverInterface.addAnimalColor(animal.getId(), useColor.getId());
        callAnimalColor.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                animalColor = new AnimalColor(animal.getId(), useColor.getId());
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.v("debug error", t.toString());
            }
        });
    // _____________________________________________________________________________________________
        Date currentDate = new Date();

        Call<Boolean> callAlert = serverInterface.addAlert(
                animal.getPicture(), // REVERIFIER LE CHEMIN POUR GET LA PHOTO
                description,
                race,
                color,
                currentDate,
                phone);

        callAlert.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                navController.navigate(R.id.action_addAlerteInvite_to_map);
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.v("debug error", t.toString());
            }
        });
    }
}