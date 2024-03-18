package com.example.alawan.Server;

import com.example.alawan.Class.Animal;
import com.example.alawan.Class.Color;
import com.example.alawan.Class.Person;
import com.example.alawan.Class.Race;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServerInterface {
    @POST("/api/login")
    @FormUrlEncoded
    Call<Boolean> login(@Field("email") String email, @Field("password") String password);

    @GET("/api/persons")
    Call<List<Person>> getListPersons();

    @GET("/api/user")
    Call<Person> getUser();

    @POST("/api/person")
    @FormUrlEncoded
    Call<Boolean> addPerson(@Field("name") String name, @Field("lastName") String lastName, @Field("email") String email, @Field("password") String password, @Field("creationDate")Date creationDate);

    //@POST("/logout")
    //@FormUrlEncoded
    //Call<Boolean> logout();

    @POST("/api/finAlert")
    @FormUrlEncoded
    Call<Boolean> finAlerte(@Field("id") int id);

    @DELETE("/api/person/{id}")
    Call<Boolean> deletePerson();

    @GET("/api/animals")
    Call<List<Animal>> getListAnimal();

    @POST("/api/animalAlertProfil")
    @FormUrlEncoded
    Call<List<Animal>> getAnimalsAlertProfil(@Field("id") int id);

    @GET("/api/animalAlert")
    Call<List<Animal>> getAnimalsAlert();

    @GET("/api/animals/person")
    Call<List<Animal>> getAnimalsPerson();

    @POST("/api/animal")
    @FormUrlEncoded
    Call<Boolean> addAnimal(@Field("idPerson") int idPerson,@Field("idRace") int idRace, @Field("idNecklace") int idNecklace, @Field("name") String name, @Field("Picture") String picture, @Field("birth") Date birth, @Field("research") Boolean research);

    @DELETE("/api/animal/{id}")
    Call<Boolean> deleteAnimal();

    @GET("/api/colors")
    Call<List<Color>> getListColor();

    @GET("/api/races")
    Call<List<Race>> getListRace();


    @GET("/api/getIdAuth")
    Call<Integer> getIdAuth();

    @GET("/api/emails")
    Call<List<String>> getListEmails();


}
