package com.example.alawan.Class.Server;

import android.graphics.Bitmap;
import com.example.alawan.Class.Alert;
import com.example.alawan.Class.Animal;
import com.example.alawan.Class.Color;
import com.example.alawan.Class.Person;
import com.example.alawan.Class.Race;

import org.checkerframework.checker.units.qual.C;

import java.util.Date;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServerInterface
{
    @POST("/api/login")
    @FormUrlEncoded
    Call<Integer> login(@Field("email") String email, @Field("password") String password);

    @GET("/api/persons")
    Call<List<Person>> getListPersons();

    @GET("/api/user")
    Call<Person> getUser();

    @POST("/api/animals/person")
    @FormUrlEncoded
    Call<List<Animal>> getUserAnimal(@Field("id") int id);

    @POST("/api/person")
    @FormUrlEncoded
    Call<Boolean> addPerson(@Field("name") String name, @Field("lastName") String lastName, @Field("email") String email, @Field("password") String password, @Field("creationDate")Date creationDate);

    @POST("/api/finAlert")
    @FormUrlEncoded
    Call<Boolean> finAlerte(@Field("id") int id);

    @DELETE("/api/person/{id}")
    Call<Boolean> deletePerson(@Path("id") int id);

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
    Call<Integer> addAnimal(@Field("idPerson") int idPerson,@Field("idRace") int idRace, @Field("idNecklace") int idNecklace, @Field("name") String name, @Field("picture") String picture, @Field("birth") Date birth, @Field("research") Boolean research);

    @DELETE("/api/animal/{id}")
    Call<Boolean> deleteAnimal(@Path("id") int id);

    @GET("/api/colors")
    Call<List<Color>> getListColor();

    @GET("/api/races")
    Call<List<Race>> getListRace();

    @GET("/api/getIdAuth")
    Call<Integer> getIdAuth();

    @GET("/api/emails")
    Call<List<String>> getListEmails();

    @POST("/api/alert")
    @FormUrlEncoded
    Call<Boolean> addAlert(@Field("idAnimal") int idAnimal, @Field("description") String description, @Field("race") String race, @Field("color") String color, @Field("dateLost") Date dateLost, @Field("phone") String phone);

    @POST("/api/animalColor")
    @FormUrlEncoded
    Call<Boolean> addAnimalColor(@Field("idAnimal") int idAnimal, @Field("idColor") int idColor);

    @POST("/api/animalFromAlert")
    @FormUrlEncoded
    Call<Animal> getAnimalFromAlert(@Field("id") int id);

    @POST("/api/getAlert")
    @FormUrlEncoded
    Call<Alert> getAlert(@Field("id") int id);

    @GET("/api/Alerts")
    Call<List<Alert>> getAllAlert();

    @POST("/api/getMaster")
    @FormUrlEncoded
    Call<Person> getMaster(@Field("id") int id);

    @POST("/api/getRaceAnimal")
    @FormUrlEncoded
    Call<Race> getRaceAnimal(@Field("id") int id);

    @POST("/api/uploadImage")
    @FormUrlEncoded
    Call<String> uploadImage(@Field("bitmap") Bitmap bitmap);
}
