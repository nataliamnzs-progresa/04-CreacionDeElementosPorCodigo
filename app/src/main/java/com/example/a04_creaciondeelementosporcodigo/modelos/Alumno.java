package com.example.a04_creaciondeelementosporcodigo.modelos;

import java.io.Serializable;

public class Alumno implements Serializable {
    private String nombre;
    private String apellidos;
    private String ciclos;
    private char grupo;

    public Alumno(String nombre, String apellidos, String ciclos, char grupo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.ciclos = ciclos;
        this.grupo = grupo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCiclos() {
        return ciclos;
    }

    public void setCiclos(String ciclos) {
        this.ciclos = ciclos;
    }

    public char getGrupo() {
        return grupo;
    }

    public void setGrupo(char grupo) {
        this.grupo = grupo;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", ciclos='" + ciclos + '\'' +
                ", grupo=" + grupo +
                '}';
    }
}
