package com.example.wallet.clientservice;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static final String URL_BASE_FIREBASE = "https://wallet-97113-default-rtdb.firebaseio.com/";

    private static Retrofit retrofit;

    public static RetrofitApiFirebaseService getApiFirebaseService(){

        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL_BASE_FIREBASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(RetrofitApiFirebaseService.class);

    }


}

