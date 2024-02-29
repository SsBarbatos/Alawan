package com.example.alawan.Class;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class Animal {
    @SerializedName("id")
    int id;
    @SerializedName("idUser")
    int idUser;
    @SerializedName("idRace")
    int idRace;
    @SerializedName("idNecklace")
    int idNecklace;
    @SerializedName("name")
    String name;
    @SerializedName("picture")
    String picture;
    @SerializedName("birth")
    Date birth;
    @SerializedName("research")
    Boolean research;

    public Animal(int id, int idUser, int idrace, int idNecklace, String name, String picture, Date birth, Boolean research) {
        this.id = id;
        this.idUser = idUser;
        this.idRace = idrace;
        this.idNecklace = idNecklace;
        this.name = name;
        this.picture = picture;
        this.birth = birth;
        this.research = research;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdrace() {
        return idRace;
    }

    public void setIdrace(int idrace) {
        this.idRace = idrace;
    }

    public int getIdNecklace() {
        return idNecklace;
    }

    public void setIdNecklace(int idNecklace) {
        this.idNecklace = idNecklace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Boolean getResearch() {
        return research;
    }

    public void setResearch(Boolean research) {
        this.research = research;
    }
}
