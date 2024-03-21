package com.example.alawan.Class;

import com.google.gson.annotations.SerializedName;

public class AnimalColor {
    @SerializedName("id")
    int id;
    @SerializedName("idAnimal")
    int idAnimal;
    @SerializedName("idColor")
    int idColor;

    public AnimalColor(int id, int idAnimal, int idColor) {
        this.id = id;
        this.idAnimal = idAnimal;
        this.idColor = idColor;
    }

    // CONSTRUCTEUR POUR ALERTE INVITE
    public AnimalColor(int idAnimal, int idColor) {
        this.idAnimal = idAnimal;
        this.idColor = idColor;
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

    public int getIdColor() {
        return idColor;
    }

    public void setIdColor(int idColor) {
        this.idColor = idColor;
    }
}
