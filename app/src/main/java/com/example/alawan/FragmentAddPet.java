package com.example.alawan;

import android.app.Activity;
import android.content.Context;
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

import com.example.alawan.Class.Adapter.AdapterListeAnimalProfil;
import com.example.alawan.Class.Animal;
import com.example.alawan.Class.Color;
import com.example.alawan.Class.Race;
import com.example.alawan.Class.Server.RetrofitInstance;
import com.example.alawan.Class.Server.ServerInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentAddPet extends Fragment
{
    View view;

    EditText etName, etBirth, etDescription;
    Spinner spRace, spColor;
    Button btAddPet;
    Bitmap imageBitmap;
    ImageView ivAddAnimal;
    SimpleDateFormat format;
    Date birth;
    String filepath;
    Call<Integer> callAddAnimal;
    Integer animalID;

    ServerInterface serverInterface ;
    NavController navController;

    public FragmentAddPet()
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
                        ivAddAnimal.setImageBitmap(imageBitmap);
                        ivAddAnimal.setAutofillHints("picture");
                    }
                    else
                        Log.v("debug error", result.toString());
                }
            });
    // _____________________________________________________________________________________________

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_add_pet, container, false);

        etName = view.findViewById(R.id.et_nom_ajout_compagnon);
        etBirth = view.findViewById(R.id.et_date_ajout_compagnon);
        etDescription = view.findViewById(R.id.et_description_ajout_compagnon);

        spColor = view.findViewById(R.id.sp_couleur_ajout_compagnon);
        spRace = view.findViewById(R.id.sp_race_ajout_compagnon);

        btAddPet = view.findViewById(R.id.bt_ajout_compagnon);

        ivAddAnimal = view.findViewById(R.id.iv_image_add_compagnon);
        ivAddAnimal.setAutofillHints("no_picture");

        serverInterface = RetrofitInstance.getInstance().create(ServerInterface.class);

    // __ FILL THE COLORS SPINNER __________________________________________________________________
        ArrayAdapter<CharSequence> adapterColors = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.list_colors,
                android.R.layout.simple_spinner_item
        );
        adapterColors.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spColor.setAdapter(adapterColors);
    // _____________________________________________________________________________________________
    // __ FILL THE RACES SPINNER ___________________________________________________________________
        ArrayAdapter<CharSequence> adapterRaces = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.list_races,
                android.R.layout.simple_spinner_item
        );
        adapterRaces.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spRace.setAdapter(adapterRaces);
    // _____________________________________________________________________________________________
        ivAddAnimal.setOnClickListener(new View.OnClickListener()
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

        btAddPet.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                boolean validation = true;
                if (etName.getText().toString().trim().length() == 0){
                    etName.setError("Le champ est vide");
                    validation = false;
                }
                if (etBirth.getText().toString().trim().length() == 0){
                    etBirth.setError("Le champ est vide");
                    validation = false;
                }
                if (spColor.getSelectedItem().toString().equals("Couleur"))
                {
                    ((TextView)spColor.getSelectedView()).setError("Choisissez une couleur");
                    validation = false;
                }
                if (spRace.getSelectedItem().toString().equals("Race"))
                {
                    ((TextView)spRace.getSelectedView()).setError("Choisissez une race");
                    validation = false;
                }

                if(validation)
                    AddAnimal();
            }
        });

        return view;
    }

    public void AddAnimal()
    {
        format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            birth = format.parse(etBirth.getText().toString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Call<String> callImage = serverInterface.uploadImage(imageBitmap);
        callImage.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                filepath = response.body();

                if(ivAddAnimal.getAutofillHints().toString().equals("no_picture"))
                    callAddAnimal = serverInterface.addAnimal(getActivity().getPreferences(Context.MODE_PRIVATE).getInt("id",0), spRace.getSelectedItemPosition() - 1, 0, etName.getText().toString(), null, birth, false);
                else
                    callAddAnimal = serverInterface.addAnimal(getActivity().getPreferences(Context.MODE_PRIVATE).getInt("id", 0), spRace.getSelectedItemPosition() - 1, 0, etName.getText().toString(), filepath, birth, false);
                callAddAnimal.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        animalID = response.body();

                        Call<Boolean> callAnimalColor = serverInterface.addAnimalColor(animalID, spColor.getSelectedItemPosition() - 1);
                        callAnimalColor.enqueue(new Callback<Boolean>() {
                            @Override
                            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.fv_mainpage);
                                navController = navHostFragment.getNavController();

                                Toast.makeText(requireContext(), "Compagnon ajouté avec succès", Toast.LENGTH_LONG).show();
                                navController.navigate(R.id.action_addPet_to_vav_profil2);
                            }
                            @Override
                            public void onFailure(Call<Boolean> call, Throwable t) {
                                Log.v("debug error", t.toString());
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