package com.example.alawan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;


import com.example.alawan.Class.Server.RetrofitInstance;
import com.example.alawan.Class.Server.ServerInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentFirstPage extends Fragment {

    public FragmentFirstPage() {
        // Required empty public constructor
    }

    Button btConnecter;
    Button btInscrire;
    Button btInvite;
    View view;

    ServerInterface serverInterface;
    NavHostFragment navHostFragment;
    NavController navController;

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
        navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.fv_main_page);
        navController = navHostFragment.getNavController();
        serverInterface = RetrofitInstance.getInstance().create(ServerInterface.class);
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
                Call<Integer> call = serverInterface.login("invite", "invite");
                call.enqueue(new Callback<Integer>()
                {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response)
                    {
                        Intent intent = new Intent(getActivity(), ActivityMenu.class);
                        intent.putExtra("id",4);
                        startActivity(intent);
                    }
                    @Override
                    public void onFailure(Call<Integer> call, Throwable t)
                    {
                        Log.v("debug error",t.toString());
                    }
                });
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