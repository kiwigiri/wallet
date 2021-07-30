package com.example.wallet.bean;

import java.io.Serializable;

public class Cliente implements Serializable {

    private int rut;
    private String nombre;


    public Cliente() {
    }


    public Cliente(int rut, String nombre) {
        this.rut = rut;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "rut=" + rut +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    public int getRut() {
        return rut;
    }

    public void setRut(int rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
