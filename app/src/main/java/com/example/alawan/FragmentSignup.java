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

import com.example.alawan.Class.Adapter.AdapterListeAnimalProfil;
import com.example.alawan.Class.Animal;
import com.example.alawan.Class.Person;
import com.example.alawan.Server.RetrofitInstance;
import com.example.alawan.Server.ServerInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentSignup extends Fragment {

    View view;
    Button btSignup;
    TextView tvConnecter;
    EditText etNom, etPrenom, etEmail, etPassword, etConfirmPassword;
    List<Person> listPerson;

    public FragmentSignup() {
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

        tvConnecter = view.findViewById(R.id.tv_seconnecter_signup);

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

                boolean validation = true;

                if (etNom.getText().toString().trim().length() == 0)
                {
                    etNom.setError("Le champ est vide");
                    validation = false;
                }

                if (etPrenom.getText().toString().trim().length() == 0)
                {
                    etPrenom.setError("Le champ est vide");
                    validation = false;
                }

                if (etEmail.getText().toString().trim().length() == 0)
                {
                    etEmail.setError("Le champ est vide");
                    validation = false;
                }
                /*
                else
                {
                    Boolean mailExist = false;
                    serverInterface.getListPersons().enqueue(new Callback<List<Person>>()
                    {
                        @Override
                        public void onResponse(Call<List<Person>> call, Response<List<Person>> response)
                        {
                            listPerson = response.body();
                        }
                        @Override
                        public void onFailure(Call<List<Person>> call, Throwable t)
                        {
                            Log.v("debug",t.toString());
                        }
                    });

                    for (Person p : listPerson)
                    {
                        if (p.getEmail() == etEmail.getText().toString())
                            mailExist = true;
                    }

                    if(mailExist)
                    {
                        validation = false;
                        etConfirmPassword.setError("L'adresse courriel est déjà utilisée");
                    }
                }
                */

                if (etPassword.getText().toString().trim().length() == 0)
                {
                    etPassword.setError("Le champ est vide");
                    validation = false;
                }

                if(etConfirmPassword.getText().toString().trim().length() == 0)
                {
                    etConfirmPassword.setError("Le champ est vide");
                    validation = false;
                }
                else if(!etConfirmPassword.getText().toString().equals(etPassword.getText().toString()))
                {
                    etConfirmPassword.setError("Les mots de passe ne correspondent pas");
                    etPassword.setError("Les mots de passe ne correspondent pas");
                    validation = false;
                }

                if(validation)
                {
                    try
                    {
                        Person person = new Person(
                                etPrenom.getText().toString(),
                                etNom.getText().toString(),
                                etEmail.getText().toString(),
                                etPassword.getText().toString()
                        );

                        Call<Boolean> call = serverInterface.addPerson(person.getName(),person.getLastName(),person.getEmail(),person.getPassword(),person.getCreationDate());
                        call.enqueue(new Callback<Boolean>()
                        {
                            @Override
                            public void onResponse(Call<Boolean> call, Response<Boolean> response)
                            {
                                navController.navigate(R.id.action_nav_signup_to_nav_login);
                                Log.v("debug",call.toString());
                            }
                            @Override
                            public void onFailure(Call<Boolean> call, Throwable t)
                            {
                                Log.v("debug error",t.toString());
                            }
                        });
                    }
                    catch (Exception e)
                    {
                        Log.v("debug catch", e.toString());
                    }
                }
            }
        });

        tvConnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_nav_signup_to_nav_login);
            }
        });

        return view;
    }
}