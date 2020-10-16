package com.example.putni_nalozi.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {

    @SerializedName("id")
    private int ID;
    @SerializedName("ime")
    private String Ime;
    @SerializedName("prezime")
    private String Prezime;
    @SerializedName("radnoMjesto")
    private String RadnoMjesto;
    @SerializedName("zvanje")
    private String Zvanje;
    @SerializedName("email")
    private String EMail;
    @SerializedName("lozinka")
    private String Lozinka;
    @SerializedName("roles")
    private String Roles;

    public User(String ime, String prezime, String radnoMjesto, String zvanje, String EMail, String lozinka, String roles) {

        this.Ime = ime;
        this.Prezime = prezime;
        this.RadnoMjesto = radnoMjesto;
        this.Zvanje = zvanje;
        this.EMail = EMail;
        this.Lozinka = lozinka;
        this.Roles = roles;
    }
    public User() {}

    public User(String EMail, String lozinka) {


        this.EMail = EMail;
        this.Lozinka = lozinka;
    }

    public User(int ID, String ime, String prezime, String radnoMjesto, String zvanje) {

        this.ID=ID;
        this.Ime = ime;
        this.Prezime = prezime;
        this.RadnoMjesto = radnoMjesto;
        this.Zvanje = zvanje;
    }


    public String getIme() {
        return Ime;
    }

    public void setIme(String ime) {
        Ime = ime;
    }

    public String getPrezime() {
        return Prezime;
    }

    public void setPrezime(String prezime) {
        Prezime = prezime;
    }

    public String getRadnoMjesto() {
        return RadnoMjesto;
    }

    public void setRadnoMjesto(String radnoMjesto) {
        RadnoMjesto = radnoMjesto;
    }

    public String getZvanje() {
        return Zvanje;
    }

    public void setZvanje(String zvanje) {
        Zvanje = zvanje;
    }

    public String getEMail() {
        return EMail;
    }

    public void setEMail(String eMail) {
        this.EMail = EMail;
    }

    public String getLozinka() {
        return Lozinka;
    }

    public void setLozinka(String lozinka) {
        Lozinka = lozinka;
    }

    public String getRoles() {
        return Roles;
    }

    public void setRoles(String roles) {
        Roles = roles;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
