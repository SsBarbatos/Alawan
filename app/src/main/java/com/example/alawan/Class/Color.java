package com.example.alawan.Class;

import com.google.gson.annotations.SerializedName;

public class Color {
    @SerializedName("id")
    int id;
    @SerializedName("color")
    String color;

    public Color(int id, String color) {
        this.id = id;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
