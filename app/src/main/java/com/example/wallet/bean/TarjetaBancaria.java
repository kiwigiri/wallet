package com.example.wallet.bean;

public class TarjetaBancaria extends Tarjeta{

    private BancoEmisor bancoEmisor;
    private Cliente cliente;
    private boolean favorite;

    public TarjetaBancaria(int nIdentificador, String numTarjeta, BancoEmisor bancoEmisor, Cliente cliente, boolean favorite) {
        super(nIdentificador, numTarjeta);
        this.bancoEmisor = bancoEmisor;
        this.cliente = cliente;
        this.favorite = favorite;
    }

    public TarjetaBancaria(BancoEmisor bancoEmisor, Cliente cliente) {
        this.bancoEmisor = bancoEmisor;
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "TarjetaBancaria{" +
                "bancoEmisor='" + bancoEmisor + '\'' +
                ", cliente=" + cliente +
                '}';
    }
    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public BancoEmisor getBancoEmisor() {
        return bancoEmisor;
    }

    public void setBancoEmisor(BancoEmisor bancoEmisor) {
        this.bancoEmisor = bancoEmisor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
