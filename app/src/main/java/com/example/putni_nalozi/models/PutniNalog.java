package com.example.putni_nalozi.models;

import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class PutniNalog implements Serializable {

    private  int id;
    private String nazivDrustva;
    private int broj;
    private String mjestoFirme;
    private String dana;
    private String ime;
    private String prezime;
    private String zvanje;
    private String radnoMjesto;
    private String sluzbenoOtputuje;
    private String mjestoPutovanja;
    private String zadaca;
    private int trajanjePutovanja;
    private String trajanjePutovanjaSlovima;
    private String auto;  //vrsta Prijevoznog Sredstva
    private String troskoviPutovanjaTerete;
    private String isplataPredujma;
    private String odDatum;
    private String doDatum;
    private String pocetnoStanjeBrojila;
    private String zavrsnoStanjeBrojila;
    private int razlikaBrojila;
    private double brojSati;
    private int brojDnevnica;
    private int brojPoludnevnica;
    private String ukupno;
    @SerializedName("korisnik")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

 /*   private void writeObject(ObjectOutputStream oos)
            throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(user.getID());
        oos.writeObject(user.getIme());
        oos.writeObject(user.getPrezime());
        oos.writeObject(user.getRadnoMjesto());
        oos.writeObject(user.getZvanje());
    }

    private void readObject(ObjectInputStream ois)
            throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        Integer id = (Integer) ois.readObject();
        String ime = (String) ois.readObject();
        String prezime = (String) ois.readObject();
        String radnoMjesto = (String) ois.readObject();
        String zvanje = (String) ois.readObject();
        User user1 = new User();
        user1.setID(id);
        user1.setIme(ime);
        user1.setPrezime(prezime);
        user1.setRadnoMjesto(radnoMjesto);
        user1.setZvanje(zvanje);
        this.setUser(user1);
    } */


    public PutniNalog(String nazivDrustva, String mjestoFirme, String dana,
                      String ime,String prezime, String zvanje, String radnoMjesto, String sluzbenoOtputuje,
                      String mjestoPutovanja, String zadaca, int trajanjePutovanja, String trajanjePutovanjaSlovima,
                      String auto,String troskoviPutovanjaTerete, String isplataPredujma) {

        this.nazivDrustva = nazivDrustva;
        this.mjestoFirme = mjestoFirme;
        this.dana = dana;
        this.ime = ime;
        this.prezime = prezime;
        this.zvanje = zvanje;
        this.radnoMjesto = radnoMjesto;
        this.sluzbenoOtputuje = sluzbenoOtputuje;
        this.mjestoPutovanja = mjestoPutovanja;
        this.zadaca = zadaca;
        this.trajanjePutovanja = trajanjePutovanja;
        this.trajanjePutovanjaSlovima = trajanjePutovanjaSlovima;
        this.auto = auto;
        this.troskoviPutovanjaTerete = troskoviPutovanjaTerete;
        this.isplataPredujma = isplataPredujma;
    }

    public PutniNalog(int id,String nazivDrustva, int broj, String mjestoFirme, String dana, String ime, String prezime, String zvanje, String radnoMjesto,
                      String sluzbenoOtputuje, String mjestoPutovanja, String zadaca, int trajanjePutovanja, String trajanjePutovanjaSlovima, String auto,
                      String troskoviPutovanjaTerete, String isplataPredujma, String odDatum, String doDatum, String pocetnoStanjeBrojila, String zavrsnoStanjeBrojila,
                      int razlikaBrojila, double brojSati, int brojDnevnica, int brojPoludnevnica, String ukupno) {

        this.id=id;
        this.nazivDrustva = nazivDrustva;
        this.broj = broj;
        this.mjestoFirme = mjestoFirme;
        this.dana = dana;
        this.ime = ime;
        this.prezime = prezime;
        this.zvanje = zvanje;
        this.radnoMjesto = radnoMjesto;
        this.sluzbenoOtputuje = sluzbenoOtputuje;
        this.mjestoPutovanja = mjestoPutovanja;
        this.zadaca = zadaca;
        this.trajanjePutovanja = trajanjePutovanja;
        this.trajanjePutovanjaSlovima = trajanjePutovanjaSlovima;
        this.auto = auto;
        this.troskoviPutovanjaTerete = troskoviPutovanjaTerete;
        this.isplataPredujma = isplataPredujma;
        this.odDatum = odDatum;
        this.doDatum = doDatum;
        this.pocetnoStanjeBrojila = pocetnoStanjeBrojila;
        this.zavrsnoStanjeBrojila = zavrsnoStanjeBrojila;
        this.razlikaBrojila = razlikaBrojila;
        this.brojSati = brojSati;
        this.brojDnevnica = brojDnevnica;
        this.brojPoludnevnica = brojPoludnevnica;
        this.ukupno = ukupno;
    }

    public PutniNalog(String nazivDrustva, int broj, String mjestoFirme, String dana, String ime, String prezime, String zvanje, String radnoMjesto,
                      String sluzbenoOtputuje, String mjestoPutovanja, String zadaca, int trajanjePutovanja, String trajanjePutovanjaSlovima, String auto,
                      String troskoviPutovanjaTerete, String isplataPredujma, String odDatum, String doDatum, String pocetnoStanjeBrojila, String zavrsnoStanjeBrojila,
                      int razlikaBrojila, double brojSati, int brojDnevnica, int brojPoludnevnica, String ukupno) {

        this.nazivDrustva = nazivDrustva;
        this.broj = broj;
        this.mjestoFirme = mjestoFirme;
        this.dana = dana;
        this.ime = ime;
        this.prezime = prezime;
        this.zvanje = zvanje;
        this.radnoMjesto = radnoMjesto;
        this.sluzbenoOtputuje = sluzbenoOtputuje;
        this.mjestoPutovanja = mjestoPutovanja;
        this.zadaca = zadaca;
        this.trajanjePutovanja = trajanjePutovanja;
        this.trajanjePutovanjaSlovima = trajanjePutovanjaSlovima;
        this.auto = auto;
        this.troskoviPutovanjaTerete = troskoviPutovanjaTerete;
        this.isplataPredujma = isplataPredujma;
        this.odDatum = odDatum;
        this.doDatum = doDatum;
        this.pocetnoStanjeBrojila = pocetnoStanjeBrojila;
        this.zavrsnoStanjeBrojila = zavrsnoStanjeBrojila;
        this.razlikaBrojila = razlikaBrojila;
        this.brojSati = brojSati;
        this.brojDnevnica = brojDnevnica;
        this.brojPoludnevnica = brojPoludnevnica;
        this.ukupno = ukupno;
    }

    public PutniNalog(){}

    public void setId(int id) {
        this.id = id;
    }

    public String getOdDatum() {
        return odDatum;
    }

    public void setOdDatum(String odDatum) {
        this.odDatum = odDatum;
    }

    public String getDoDatum() {
        return doDatum;
    }

    public void setDoDatum(String doDatum) {
        this.doDatum = doDatum;
    }

    public String getPocetnoStanjeBrojila() {
        return pocetnoStanjeBrojila;
    }

    public void setPocetnoStanjeBrojila(String pocetnoStanjeBrojila) {
        this.pocetnoStanjeBrojila = pocetnoStanjeBrojila;
    }

    public String getZavrsnoStanjeBrojila() {
        return zavrsnoStanjeBrojila;
    }

    public void setZavrsnoStanjeBrojila(String zavrsnoStanjeBrojila) {
        this.zavrsnoStanjeBrojila = zavrsnoStanjeBrojila;
    }

    public int getRazlikaBrojila() {
        return razlikaBrojila;
    }

    public void setRazlikaBrojila(int razlikaBrojila) {
        this.razlikaBrojila = razlikaBrojila;
    }

    public double getBrojSati() {
        return brojSati;
    }

    public void setBrojSati(double brojSati) {
        this.brojSati = brojSati;
    }

    public int getBrojDnevnica() {
        return brojDnevnica;
    }

    public void setBrojDnevnica(int brojDnevnica) {
        this.brojDnevnica = brojDnevnica;
    }

    public int getBrojPoludnevnica() {
        return brojPoludnevnica;
    }

    public void setBrojPoludnevnica(int brojPoludnevnica) {
        this.brojPoludnevnica = brojPoludnevnica;
    }

    public String getUkupno() {
        return ukupno;
    }

    public void setUkupno(String ukupno) {
        this.ukupno = ukupno;
    }

    public int getId() {
        return id;
    } //mozda greska!!!!

    public String getNazivDrustva() {
        return nazivDrustva;
    }

    public void setNazivDrustva(String nazivDrustva) {
        this.nazivDrustva = nazivDrustva;
    }

    public int getBroj() {
        return broj;
    }

    public void setBroj(int broj) {
        this.broj = broj;
    }

    public String getMjestoFirme() {
        return mjestoFirme;
    }

    public void setMjestoFirme(String mjestoFirme) {
        this.mjestoFirme = mjestoFirme;
    }

    public String getDana() {
        return dana;
    }

    public void setDana(String dana) {
        this.dana = dana;
    }

    public String getSluzbenoOtputuje() {
        return sluzbenoOtputuje;
    }

    public void setSluzbenoOtputuje(String sluzbenoOtputuje) {
        this.sluzbenoOtputuje = sluzbenoOtputuje;
    }

    public String getMjestoPutovanja() {
        return mjestoPutovanja;
    }

    public void setMjestoPutovanja(String mjestoPutovanja) {
        this.mjestoPutovanja = mjestoPutovanja;
    }

    public String getZadaca() {
        return zadaca;
    }

    public void setZadaca(String zadaca) {
        this.zadaca = zadaca;
    }

    public int getTrajanjePutovanja() {
        return trajanjePutovanja;
    }

    public void setTrajanjePutovanja(int trajanjePutovanja) {
        this.trajanjePutovanja = trajanjePutovanja;
    }

    public String getTrajanjePutovanjaSlovima() {
        return trajanjePutovanjaSlovima;
    }

    public void setTrajanjePutovanjaSlovima(String trajanjePutovanjaSlovima) {
        this.trajanjePutovanjaSlovima = trajanjePutovanjaSlovima;
    }

    public String getAuto() {
        return auto;
    }

    public void setAuto(String auto) {
        this.auto = auto;
    }

    public String getTroskoviPutovanjaTerete() {
        return troskoviPutovanjaTerete;
    }

    public void setTroskoviPutovanjaTerete(String troskoviPutovanjaTerete) {
        this.troskoviPutovanjaTerete = troskoviPutovanjaTerete;
    }

    public String getIsplataPredujma() {
        return isplataPredujma;
    }

    public void setIsplataPredujma(String isplataPredujma) {
        this.isplataPredujma = isplataPredujma;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getZvanje() {
        return zvanje;
    }

    public void setZvanje(String zvanje) {
        this.zvanje = zvanje;
    }

    public String getRadnoMjesto() {
        return radnoMjesto;
    }

    public void setRadnoMjesto(String radnoMjesto) {
        this.radnoMjesto = radnoMjesto;
    }
}
