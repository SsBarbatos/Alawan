package com.example.alawan.Class;

import android.util.Log;

import com.example.alawan.Class.Server.RetrofitInstance;
import com.example.alawan.Class.Server.ServerInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tests {

    ServerInterface serverInterface = RetrofitInstance.getInstance().create(ServerInterface.class);
    List<String> listEmails;
    Boolean emailExist;

    public boolean TestEmail(String test)
    {
        Call<List<String>> call = serverInterface.getListEmails();
        call.enqueue(new Callback<List<String>>()
        {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) { listEmails = response.body(); }
            @Override
            public void onFailure(Call<List<String>> call, Throwable t) { Log.v("debug error",t.toString()); }
        });

        for(String email: listEmails)
        {
            if(email == test)
                emailExist = true;
        }

        if(emailExist)
            return  true;
        else
            return false;
    }
}
