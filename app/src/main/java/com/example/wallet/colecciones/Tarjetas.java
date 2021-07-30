package com.example.wallet.colecciones;

import com.example.wallet.bean.Credito;
import com.example.wallet.bean.Debito;
import com.example.wallet.bean.TarjetaBancaria;

import java.util.ArrayList;

public class Tarjetas {

    private ArrayList<TarjetaBancaria> tarjetas;

    public Tarjetas(ArrayList<TarjetaBancaria> tarjetas) {
        this.tarjetas = tarjetas;
    }
    public Tarjetas() {
        this.tarjetas = new ArrayList<>();
    }

    public long obtenerCantidadTDebito(){
       /*
        int contador = 0;

        for (TarjetaBancaria tb:
             tarjetas) {
            if(tb instanceof Debito){
                contador++;
            }
        }
        return contador;
        */
        return tarjetas.stream().filter(tb -> tb instanceof Debito).count();

    }
    public void addTarjeta(TarjetaBancaria tb){
        this.tarjetas.add(tb);
    }
    public long obtenerCantidadTCredito(){
        return tarjetas.stream().filter(tb -> tb instanceof Credito).count();

    }

    public ArrayList<TarjetaBancaria> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(ArrayList<TarjetaBancaria> tarjetas) {
        this.tarjetas = tarjetas;
    }
}
