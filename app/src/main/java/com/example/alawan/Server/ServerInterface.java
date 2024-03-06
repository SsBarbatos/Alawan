package com.example.alawan.Server;

import com.example.alawan.FragmentAnimal;
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
    Call<Boolean> addPerson(@Field("name") String name, @Field("lastName") String lastName, @Field("email") String email, @Field("password") String password,  @Field("invite") Boolean invite, @Field("admin") Boolean admin, @Field("creationDate")Date creationDate);

    //@POST("/logout")
    //@FormUrlEncoded
    //Call<Boolean> logout();

    @DELETE("/api/person/{id}")
    Call<Boolean> deletePerson();

    @GET("/api/animals")
    Call<List<FragmentAnimal>> getListAnimal();

    @GET("/api/animals/person")
    Call<List<FragmentAnimal>> getAnimalsPerson();

    @POST("/api/animal")
    @FormUrlEncoded
    Call<Boolean> addAnimal(@Field("idPerson") int idPerson,@Field("idRace") int idRace, @Field("idNecklace") int idNecklace, @Field("name") String name, @Field("Picture") String picture, @Field("birth") Date birth, @Field("research") Boolean research);

    @DELETE("/api/animal/{id}")
    Call<Boolean> deleteAnimal();

    @GET("/api/colors")
    Call<List<Color>> getListColor();

    @GET("/api/races")
    Call<List<Race>> getListRace();

}
