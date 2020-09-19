package com.example.examensegundocortepoo.Clases;

public class RevistaArticulo {
    String seccion;
    int idpublicación;
    String titulo;
    String doi;
    String abstracto;
    String fechapublicación;
    int idsubmision;
    int idsecuencia;
    String pdf;
    String html;
    String palabrasclaves;
    String autores;

    public RevistaArticulo(String seccion, int idpublicación, String titulo, String doi, String abstracto, String fechapublicación, int idsubmision, int idsecuencia, String pdf, String html, String palabrasclaves, String autores) {
        this.seccion = seccion;
        this.idpublicación = idpublicación;
        this.titulo = titulo;
        this.doi = doi;
        this.abstracto = abstracto;
        this.fechapublicación = fechapublicación;
        this.idsubmision = idsubmision;
        this.idsecuencia = idsecuencia;
        this.pdf = pdf;
        this.html = html;
        this.palabrasclaves = palabrasclaves;
        this.autores = autores;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public int getIdpublicación() {
        return idpublicación;
    }

    public void setIdpublicación(int idpublicación) {
        this.idpublicación = idpublicación;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getAbstracto() {
        return abstracto;
    }

    public void setAbstracto(String abstracto) {
        this.abstracto = abstracto;
    }

    public String getFechapublicación() {
        return fechapublicación;
    }

    public void setFechapublicación(String fechapublicación) {
        this.fechapublicación = fechapublicación;
    }

    public int getIdsubmision() {
        return idsubmision;
    }

    public void setIdsubmision(int idsubmision) {
        this.idsubmision = idsubmision;
    }

    public int getIdsecuencia() {
        return idsecuencia;
    }

    public void setIdsecuencia(int idsecuencia) {
        this.idsecuencia = idsecuencia;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getPalabrasclaves() {
        return palabrasclaves;
    }

    public void setPalabrasclaves(String palabrasclaves) {
        this.palabrasclaves = palabrasclaves;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }
}
