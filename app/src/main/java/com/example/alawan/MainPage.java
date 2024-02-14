package com.example.alawan;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainPage extends AppCompatActivity
        //implements BottomNavigationView.OnNavigationItemSelectedListener
    {

    BottomNavigationView bottomMenu;
    Map map = new Map();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        //bottomMenu = findViewById(R.id.bottomMenu);
        //bottomMenu.setOnNavigationItemReselectedListener(this);
    }

/*
    @Override // C'est se qui permet de naviguer avec le menu
    public boolean onNavigationItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.mapMenu:getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, map).commit();return true;
            case R.id.rechercheMenu:return true;

        }

        return false;
    }*/

}