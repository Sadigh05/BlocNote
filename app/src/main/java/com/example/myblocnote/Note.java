package com.example.myblocnote;

public class Note {

    private long ID;
    private String titre;
    private String contenu;
    private String date;
    private String time;
    private long id_user;

    Note() {
    }

    Note(String titre, String contenu, String date, String time) {
        this.titre = titre;
        this.contenu = contenu;
        this.date = date;
        this.time = time;
    }

    Note(long id, String titre, String contenu, String date, String time) {
        this.ID = id;
        this.titre = titre;
        this.contenu = contenu;
        this.date = date;
        this.time = time;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }
}