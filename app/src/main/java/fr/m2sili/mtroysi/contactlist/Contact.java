package fr.m2sili.mtroysi.contactlist;

/**
 * Created by Morgane TROYSI on 12/3/16.
 */

public class Contact {
    private String nom;
    private String prenom;
    int day;
    int month;
    int year;
    String mail;

    public Contact(String nom, String prenom, int day, int month, int year, String mail) {
        this.nom = nom;
        this.prenom = prenom;
        this.day = day;
        this.month = month;
        this.year = year;
        this.mail = mail;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
