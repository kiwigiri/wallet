package com.example.wallet.bean;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Fecha implements Serializable {

    private String mes;
    private String anio;

    public Fecha(String mes, String anio) {
        this.mes = mes;
        this.anio = anio;
    }

    @NonNull
    @Override
    public String toString() {
        return this.mes+"/"+anio;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }
}
