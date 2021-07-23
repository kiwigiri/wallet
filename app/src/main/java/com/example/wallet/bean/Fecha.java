package com.example.wallet.bean;

public class Fecha {

    private String mes;
    private String anio;

    public Fecha(String mes, String anio) {
        this.mes = mes;
        this.anio = anio;
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
