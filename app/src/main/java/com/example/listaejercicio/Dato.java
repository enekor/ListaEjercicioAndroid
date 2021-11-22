package com.example.listaejercicio;

public class Dato {
    private String titulo,contenido;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Dato(String titulo, String contenido) {
        this.titulo = titulo;
        this.contenido = contenido;
    }
}
