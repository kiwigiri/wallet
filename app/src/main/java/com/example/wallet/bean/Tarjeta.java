package com.example.wallet.bean;

public class Tarjeta {
    private int nIdentificador;
    private String numTarjeta;

    public Tarjeta(int nIdentificador, String numTarjeta) {
        this.nIdentificador = nIdentificador;
        this.numTarjeta = numTarjeta;
    }


    public Tarjeta() {
    }

    @Override
    public String toString() {
        return "Tarjeta{" +
                "nIdentificador=" + nIdentificador +
                '}';
    }

    public String getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(String numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public long getnIdentificador() {
        return nIdentificador;
    }

    public void setnIdentificador(int nIdentificador) {
        this.nIdentificador = nIdentificador;
    }
}
