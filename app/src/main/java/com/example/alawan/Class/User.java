package com.example.alawan.Class;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class User {
    @SerializedName("id")
    int id;
    @SerializedName("idAddress")
    int idAddress;
    @SerializedName("name")
    String name;
    @SerializedName("lastName")
    String lastName;
    @SerializedName("email")
    String email;
    @SerializedName("password")
    String password;
    @SerializedName("phone")
    String phone;
    @SerializedName("invite")
    boolean invite;
    @SerializedName("admin")
    boolean admin;
    @SerializedName("creationDate")
    Date creationDate;

    public User(int id, int idAddress, String name, String lastName, String email, String password, String phone, boolean invite, boolean admin, Date creationDate) {
        this.id = id;
        this.idAddress = idAddress;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.invite = invite;
        this.admin = admin;
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isInvite() {
        return invite;
    }

    public void setInvite(boolean invite) {
        this.invite = invite;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
