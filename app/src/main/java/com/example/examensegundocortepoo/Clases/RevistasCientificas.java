package com.example.examensegundocortepoo.Clases;

public class RevistasCientificas {
    Integer id;
    String abreviacion;
    String descripcion;
    String imagen;
    String nombre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAbreviacion() {
        return abreviacion;
    }

    public void setAbreviacion(String abreviacion) {
        this.abreviacion = abreviacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public RevistasCientificas(Integer id,String imagen, String abreviacion, String descripcion, String nombre) {
        this.id = id;
        this.abreviacion = abreviacion;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.nombre = nombre;
    }



}
