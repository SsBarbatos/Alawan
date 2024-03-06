package com.example.alawan.Class;

import com.google.gson.annotations.SerializedName;

public class Race {
    @SerializedName("id")
    int id;
    @SerializedName("race")
    String race;

    public Race(int id, String race) {
        this.id = id;
        this.race = race;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }
}
