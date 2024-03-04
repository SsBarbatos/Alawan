package com.example.alawan.Server;

import com.example.alawan.Class.Person;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServerInterface {
    @POST("/login")
    @FormUrlEncoded
    Call<Boolean> login(@Field("email") String email, @Field("password") String password);

    @GET("/persons")
    Call<List<Person>> getListPersons();

    @GET("/user")
    Call<Person> getUser();

    @POST("/person")
    @FormUrlEncoded
    Call<Boolean> addPerson();
}
