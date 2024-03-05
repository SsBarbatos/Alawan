package com.example.alawan;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alawan.Class.Person;
import com.example.alawan.Server.RetrofitInstance;
import com.example.alawan.Server.ServerInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Signup extends Fragment {

    View view;
    Button btSignup;
    TextView tvConnecter;
    EditText etNom;
    EditText etPrenom;
    EditText etEmail;
    EditText etPassword;
    EditText etConfirmPassword;

    public Signup() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_signup, container, false);
        btSignup = view.findViewById(R.id.bt_creer_signup);
        tvConnecter = view .findViewById(R.id.tv_seconnecter_signup);
        etEmail = view.findViewById(R.id.et_mail_signup);
        etNom = view.findViewById(R.id.et_nomfamille_signup);
        etPrenom = view.findViewById(R.id.et_nom_signup);
        etPassword = view.findViewById(R.id.et_password_signup);
        etConfirmPassword = view.findViewById(R.id.et_confirmer_signup);
        ServerInterface serverInterface = RetrofitInstance.getInstance().create(ServerInterface.class);

        NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.fv_main_page);
        NavController navController = navHostFragment.getNavController();

        btSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ------------ Code pour changer de fragment inscription à se connecter ------------------
                // !!!!!!!!!!!!!! rentrez le code pour l'inscription !!!!!!!!!!!!!!!!!!!!!!!!!!!!
                Boolean validation = true;
                if (etNom.getText().toString().trim().length() == 0){
                    etNom.setError("Le champ est vide.");
                    validation = false;
                }
                if (etPrenom.getText().toString().trim().length() == 0){
                    etPrenom.setError("Le champ est vide.");
                    validation = false;
                }
                if (etEmail.getText().toString().trim().length() == 0){
                    etEmail.setError("Le champ est vide.");
                    validation = false;
                }
                else{
                    //Rentrez du code pour la vérification de email
                }
                if (etPassword.getText().toString().trim().length() == 0){
                    etPassword.setError("Le champ est vide.");
                    validation = false;
                }
                else{
                    // pareil ici
                }
                if(etConfirmPassword.getText().toString().trim().length() == 0){
                    etConfirmPassword.setError("Le champ est vide.");
                    validation = false;
                }
                else if(!etConfirmPassword.getText().toString().equals(etPassword.getText().toString())){
                    etConfirmPassword.setError("Les mot de passe ne corresponde pas");
                    validation = false;
                }


                if(validation){
                    Person person = new Person(
                            etPrenom.getText().toString(),
                            etNom.getText().toString(),
                            etEmail.getText().toString(),
                            etPassword.getText().toString()
                    );
                    Call<Boolean> call = serverInterface.addPerson(person.getName(),person.getLastName(),person.getEmail(),person.getPassword(),person.isInvite(),person.isAdmin(),person.getCreationDate());
                    call.enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            navController.navigate(R.id.action_nav_signup_to_nav_login);
                            Log.v("debug",call.toString());
                        }

                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {
                            Log.v("debug",t.toString());
                        }
                    });

                }

            }
        });

        tvConnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ------------ Code pour changer de fragment inscription à se connecter ------------------
                navController.navigate(R.id.action_nav_signup_to_nav_login);
            }
        });
        return view;
    }
}