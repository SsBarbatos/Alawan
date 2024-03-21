package com.example.alawan.Class;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class Alert {
    @SerializedName("id")
    int id;
    @SerializedName("idAnimal")
    int idAnimal;
    @SerializedName("dateLost")
    Date dateLost;
    @SerializedName("dateFind")
    Date dateFind;
    @SerializedName("place")
    String place;
    @SerializedName("description")
    String description;
    @SerializedName("alerteFound")
    boolean alerteFound;

    public Alert(int id, int idAnimal, Date dateLost, Date dateFind, String place, String description, boolean alerteFound) {
        this.id = id;
        this.idAnimal = idAnimal;
        this.dateLost = dateLost;
        this.dateFind = dateFind;
        this.place = place;
        this.description = description;
        this.alerteFound = alerteFound;
    }

    // CONSTRUCTEUR POUR ALERTE INVITE
    public Alert(int idAnimal, Date dateLost, String place, String description, boolean alerteFound) {
        this.idAnimal = idAnimal;
        this.dateLost = dateLost;
        this.place = place;
        this.description = description;
        this.alerteFound = alerteFound;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public Date getDateLost() {
        return dateLost;
    }

    public void setDateLost(Date dateLost) {
        this.dateLost = dateLost;
    }

    public Date getDateFind() {
        return dateFind;
    }

    public void setDateFind(Date dateFind) {
        this.dateFind = dateFind;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAlerteFound() {
        return alerteFound;
    }

    public void setAlerteFound(boolean alerteFound) {
        this.alerteFound = alerteFound;
    }

}
