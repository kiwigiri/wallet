package com.example.wallet.bean;

public class Credito extends TarjetaBancaria{
    private NombreTarjeta nt;
    private double cupoNacional;
    private double gastoNacional;

    public Credito(int nIdentificador, String numTarjeta, BancoEmisor bancoEmisor, Cliente cliente, boolean favorite, NombreTarjeta nt, double cupoNacional, double gastoNacional) {
        super(nIdentificador, numTarjeta, bancoEmisor, cliente, favorite);
        this.nt = nt;
        this.cupoNacional = cupoNacional;
        this.gastoNacional = gastoNacional;
    }

    public Credito(BancoEmisor bancoEmisor, Cliente cliente, NombreTarjeta nt, double cupoNacional, double gastoNacional) {
        super(bancoEmisor, cliente);
        this.nt = nt;
        this.cupoNacional = cupoNacional;
        this.gastoNacional = gastoNacional;
    }

    @Override
    public String toString() {
        return "Credito{" +
                "nt=" + nt +
                ", cupoNacional=" + cupoNacional +
                ", gastoNacional=" + gastoNacional +
                '}';
    }

    public NombreTarjeta getNt() {
        return nt;
    }

    public void setNt(NombreTarjeta nt) {
        this.nt = nt;
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
