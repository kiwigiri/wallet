package com.example.wallet.persistencia;

import com.example.wallet.bean.BancoEmisor;
import com.example.wallet.bean.Cliente;
import com.example.wallet.bean.Credito;
import com.example.wallet.bean.Debito;
import com.example.wallet.bean.Fecha;
import com.example.wallet.bean.NombreTarjeta;
import com.example.wallet.bean.TarjetaBancaria;
import com.example.wallet.clientservice.RetrofitApiFirebaseService;
import com.example.wallet.clientservice.RetrofitClient;
import com.example.wallet.colecciones.Tarjetas;
import com.google.gson.internal.LinkedTreeMap;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Firebase {

    private RetrofitApiFirebaseService retrofitApiFirebaseService;

    public Firebase(RetrofitApiFirebaseService retrofitApiFirebaseService){
        this.retrofitApiFirebaseService = retrofitApiFirebaseService;
    }

    public Tarjetas obtenerTarjetas(){

        Tarjetas tarjetas = new Tarjetas();

        retrofitApiFirebaseService.getWallets("Banca").enqueue(new Callback<HashMap<String, Object>>() {
            @Override
            public void onResponse(Call<HashMap<String, Object>> call, Response<HashMap<String, Object>> response) {

                response.body().forEach(
                        (k,v) -> {
                            BancoEmisor bancoEmisor = null;
                            NombreTarjeta nombreTarjeta = null;

                            LinkedTreeMap<String, Object> map = (LinkedTreeMap<String, Object>)v;
                            bancoEmisor = bancoEmisor.valueOf((String)map.get("bancoEmisor"));
                            int cv = (int)(double)map.get("cv");
                            boolean favorite = (boolean)map.get("favorite");
                            int nIdentificador = (int)(double)map.get("nIdentificador");
                            String numTarjeta = (String)map.get("numTarjeta");
                            nombreTarjeta = nombreTarjeta.valueOf((String)map.get("nombreTarjeta"));

                            LinkedTreeMap<String, Object> map1 = (LinkedTreeMap<String, Object>)map.get("fecha");
                            Fecha fecha = new Fecha((String)map1.get("mes"), (String) map1.get("anio"));

                            LinkedTreeMap<String, Object> map2 = (LinkedTreeMap<String, Object>)map.get("cliente");
                            Cliente cliente = new Cliente((int)(double)map2.get("rut"), (String) map2.get("nombre"));

                            TarjetaBancaria tb = new TarjetaBancaria(nIdentificador,numTarjeta,bancoEmisor,cliente,favorite,cv,fecha,nombreTarjeta);

                            if (map.get("saldo")!=null){
                                double saldo = (double)map.get("saldo");
                                tarjetas.addTarjeta(new Debito(tb, saldo));
                            }
                            else{
                                double cupoNacional = (double)map.get("cupoNacional");;
                                double gastoNacional = (double)map.get("gastoNacional");
                                tarjetas.addTarjeta(new Credito(tb, cupoNacional, gastoNacional));
                            }

                        }
                );


            }

            @Override
            public void onFailure(Call<HashMap<String, Object>> call, Throwable t) {

            }


        });
        return tarjetas;
    }

}

