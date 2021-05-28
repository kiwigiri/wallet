package com.example.wallet.utils;

import com.example.wallet.R;
import com.example.wallet.bean.BancoEmisor;
import com.example.wallet.bean.Credito;
import com.example.wallet.bean.Debito;
import com.example.wallet.bean.TarjetaBancaria;

public class Util {

    static public int getType(TarjetaBancaria tb){

        if(tb.getBancoEmisor().equals(BancoEmisor.BANCO_CHILE) && (tb instanceof Credito)){
            return R.layout.card_bancochile_credito;
        }

        if(tb.getBancoEmisor().equals(BancoEmisor.COOPERTARIVA_COOPEUCH) && (tb instanceof Debito)){
            return R.layout.card_coopeuch_debito;
        }

        if(tb.getBancoEmisor().equals(BancoEmisor.BANCO_FALABELLA) && (tb instanceof Credito)){
            return R.layout.card_falabella_cmr_credito;
        }


        return R.layout.card_default;

    }

}
