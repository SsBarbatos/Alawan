package com.example.alawan.Server;

import com.example.alawan.Animal;
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
    @POST("/login")
    @FormUrlEncoded
    Call<Boolean> login(@Field("email") String email, @Field("password") String password);

    @GET("/persons")
    Call<List<Person>> getListPersons();

    @GET("/user")
    Call<Person> getUser();

    @POST("/person")
    @FormUrlEncoded
    Call<Boolean> addPerson(@Field("name") String name, @Field("lastName") String lastName, @Field("email") String email, @Field("password") String password, @Field("phone") String phone, @Field("invite") Boolean invite, @Field("admin") Boolean admin, @Field("creationDate")Date creationDate);

    //@POST("/logout")
    //@FormUrlEncoded
    //Call<Boolean> logout();

    @DELETE("/person/{id}")
    Call<Boolean> deletePerson();

    @GET("/animals")
    Call<List<Animal>> getListAnimal();

    @GET("/animals/person")
    Call<List<Animal>> getAnimalsPerson();

    @POST("/animal")
    @FormUrlEncoded
    Call<Boolean> addAnimal(@Field("idPerson") int idPerson,@Field("idRace") int idRace, @Field("idNecklace") int idNecklace, @Field("name") String name, @Field("Picture") String picture, @Field("birth") Date birth, @Field("research") Boolean research);

    @DELETE("/animal/{id}")
    Call<Boolean> deleteAnimal();

    @GET("/colors")
    Call<List<Color>> getListColor();

    @GET("/races")
    Call<List<Race>> getListRace();

}
