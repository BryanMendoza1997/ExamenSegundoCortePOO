package com.example.examensegundocortepoo.Clases;

public class RevistasEdiciones {
    int id;
    int volumen;
    int number;
    int year;
    String fechapublicacion;
    String title;
    String doi;
    String imagen;

    public RevistasEdiciones(int id, int volumen, int number, int year, String fechapublicacion, String title, String doi, String imagen) {
        this.id = id;
        this.volumen = volumen;
        this.number = number;
        this.year = year;
        this.fechapublicacion = fechapublicacion;
        this.title = title;
        this.doi = doi;
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVolumen() {
        return volumen;
    }

    public void setVolumen(int volumen) {
        this.volumen = volumen;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getFechapublicacion() {
        return fechapublicacion;
    }

    public void setFechapublicacion(String fechapublicacion) {
        this.fechapublicacion = fechapublicacion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
