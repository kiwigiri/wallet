package com.example.wallet.bean;

public class Credito extends TarjetaBancaria{
    private double cupoNacional;
    private double gastoNacional;

    public Credito(int nIdentificador, String numTarjeta, BancoEmisor bancoEmisor, Cliente cliente, boolean favorite, NombreTarjeta nt, double cupoNacional, double gastoNacional) {
        super(nIdentificador, numTarjeta, bancoEmisor, cliente, favorite);

        this.cupoNacional = cupoNacional;
        this.gastoNacional = gastoNacional;
    }

    public Credito(TarjetaBancaria tb ,double cupoNacional, double gastoNacional) {
        super((int) tb.getnIdentificador(), tb.getNumTarjeta(), tb.getBancoEmisor(), tb.getCliente(), tb.isFavorite(), tb.getCv(), tb.getFecha(), tb.getNombreTarjeta());
        this.cupoNacional = cupoNacional;
        this.gastoNacional = gastoNacional;
    }
    public Credito(BancoEmisor bancoEmisor, Cliente cliente, double cupoNacional, double gastoNacional) {
        super(bancoEmisor, cliente);
        this.cupoNacional = cupoNacional;
        this.gastoNacional = gastoNacional;
    }

    @Override
    public String toString() {
        return "Credito{" +
                "nt="+
                ", cupoNacional=" + cupoNacional +
                ", gastoNacional=" + gastoNacional +
                '}';
    }


    public double getCupoNacional() {
        return cupoNacional;
    }

    public void setCupoNacional(double cupoNacional) {
        this.cupoNacional = cupoNacional;
    }

    public double getGastoNacional() {
        return gastoNacional;
    }

    public void setGastoNacional(double gastoNacional) {
        this.gastoNacional = gastoNacional;
    }
}
