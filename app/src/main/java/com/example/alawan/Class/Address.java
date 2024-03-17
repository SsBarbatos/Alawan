package com.example.alawan.Class;

import com.google.gson.annotations.SerializedName;

public class Address {
    @SerializedName("id")
    int id;
    @SerializedName("city")
    String city;
    @SerializedName("street")
    String street;
    @SerializedName("doorNumber")
    int doorNumber;
    @SerializedName("postalCode")
    String postalCode;

    public Address(int id, String city, String street, int doorNumber, String postalCode) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.doorNumber = doorNumber;
        this.postalCode = postalCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getDoorNumber() {
        return doorNumber;
    }

    public void setDoorNumber(int doorNumber) {
        this.doorNumber = doorNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
