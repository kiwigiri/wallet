package com.example.wallet.clientservice;

import com.example.wallet.bean.Credito;
import com.example.wallet.bean.Debito;
import com.example.wallet.bean.TarjetaBancaria;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitApiFirebaseService {


    @POST("wallets/{areaNegocio}.json")
    Call<TarjetaBancaria>registraWalletCredito(@Path("areaNegocio") String areaNegocio, @Body Credito tbc);

    @POST("wallets/{areaNegocio}.json")
    Call<TarjetaBancaria>registraWalletDebito(@Path("areaNegocio") String areaNegocio, @Body Debito tbd);

}
