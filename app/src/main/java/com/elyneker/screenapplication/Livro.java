package com.elyneker.screenapplication;

public class Livro {

    private int id;
    private String materia;
    private String titulo;

    public Livro(int id, String materia, String titulo) {
        this.id = id;
        this.materia = materia;
        this.titulo = titulo;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}