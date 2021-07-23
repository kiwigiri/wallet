package com.example.wallet.bean;

public class TarjetaBancaria extends Tarjeta{

    private BancoEmisor bancoEmisor;
    private Cliente cliente;
    private boolean favorite;
    private int cv;
    private Fecha fecha;
    private NombreTarjeta nombreTarjeta;

    public TarjetaBancaria(int nIdentificador, String numTarjeta, BancoEmisor bancoEmisor, Cliente cliente, boolean favorite, int cv, Fecha fecha, NombreTarjeta nombreTarjeta) {
        super(nIdentificador, numTarjeta);
        this.bancoEmisor = bancoEmisor;
        this.cliente = cliente;
        this.favorite = favorite;
        this.cv = cv;
        this.fecha = fecha;
        this.nombreTarjeta = nombreTarjeta;
    }

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

    public int getCv() {
        return cv;
    }

    public void setCv(int cv) {
        this.cv = cv;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

    public NombreTarjeta getNombreTarjeta() {
        return nombreTarjeta;
    }

    public void setNombreTarjeta(NombreTarjeta nombreTarjeta) {
        this.nombreTarjeta = nombreTarjeta;
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
