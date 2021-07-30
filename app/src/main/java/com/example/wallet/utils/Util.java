package com.example.wallet.utils;

import com.example.wallet.R;
import com.example.wallet.bean.BancoEmisor;
import com.example.wallet.bean.Credito;
import com.example.wallet.bean.Debito;
import com.example.wallet.bean.TarjetaBancaria;

import java.util.ArrayList;

public class Util {


    static public int getType(){

        return R.layout.card;

    }

    static public ArrayList<String>getBancosEmisores(){
        ArrayList<String> bancos = new ArrayList<>();

        bancos.add(BancoEmisor.BANCO_CHILE.toString());
        bancos.add(BancoEmisor.BANCO_BCI.toString());
        bancos.add(BancoEmisor.BANCO_ESTADO.toString());
        bancos.add(BancoEmisor.BANCO_BICE.toString());
        bancos.add(BancoEmisor.COOPERTARIVA_COOPEUCH.toString());
        bancos.add(BancoEmisor.BANCO_FALABELLA.toString());
        bancos.add(BancoEmisor.BANCO_RIPLEY.toString());

        return bancos;
    }

    static public int getTypeCard(TarjetaBancaria tb){

        // VISA,MASTERCARD,AMERICANEXPRESS,DISCOVER,JCB,DINNERSCLUB,UNDEFINED
        if(tb.getNombreTarjeta().name().equals("VISA")){
            return R.drawable.ic_visa;
        }
        if(tb.getNombreTarjeta().name().equals("MASTERCARD")){
            return R.drawable.ic_mastercard;
        }

        if(tb.getNombreTarjeta().name().equals("AMERICANEXPRESS")){
            return R.drawable.ic_american_express;
        }
        if(tb.getNombreTarjeta().name().equals("DISCOVER")){
            return R.drawable.ic_discover;
        }
        if(tb.getNombreTarjeta().name().equals("JCB")){
            return R.drawable.ic_jcb;
        }
        if(tb.getNombreTarjeta().name().equals("DINNERSCLUB")){
            return R.drawable.ic_dinners;
        }

        return R.drawable.ic_warning;
    }

    static public int getColorCard(TarjetaBancaria tb){
        //BANCO_CHILE,BANCO_BCI,BANCO_ESTADO,BANCO_BICE,COOPERTARIVA_COOPEUCH,BANCO_FALABELLA,BANCO_RIPLEY
        if(tb.getBancoEmisor().equals(BancoEmisor.BANCO_CHILE)){
            return R.color.amarilloBancochile;
        }

        if(tb.getBancoEmisor().equals(BancoEmisor.BANCO_BCI)){
            return R.color.black;
        }

        if(tb.getBancoEmisor().equals(BancoEmisor.BANCO_ESTADO)){
            return R.color.grisdefault;
        }

        if(tb.getBancoEmisor().equals(BancoEmisor.BANCO_BICE)){
            return R.color.teal_200;
        }
        if(tb.getBancoEmisor().equals(BancoEmisor.COOPERTARIVA_COOPEUCH)){
            return R.color.rojocoopeuch;
        }

        if(tb.getBancoEmisor().equals(BancoEmisor.BANCO_FALABELLA)){
            return R.color.verdecmr;
        }

        if(tb.getBancoEmisor().equals(BancoEmisor.BANCO_RIPLEY)){
            return R.color.purple_500;
        }

        return R.color.grisdefault;
    }

    static public int getImageBanco(TarjetaBancaria tb){
        if(tb.getBancoEmisor().equals(BancoEmisor.BANCO_CHILE)){
            return R.mipmap.logobancochile;
        }

        if(tb.getBancoEmisor().equals(BancoEmisor.BANCO_BCI)){
            return R.mipmap.logobancochile;
        }

        if(tb.getBancoEmisor().equals(BancoEmisor.BANCO_ESTADO)){
            return R.mipmap.logobancochile;
        }

        if(tb.getBancoEmisor().equals(BancoEmisor.BANCO_BICE)){
            return R.mipmap.logobancochile;
        }
        if(tb.getBancoEmisor().equals(BancoEmisor.COOPERTARIVA_COOPEUCH)){
            return R.mipmap.logocoopeuch;
        }

        if(tb.getBancoEmisor().equals(BancoEmisor.BANCO_FALABELLA)){
            return R.mipmap.logocmrfalabella;
        }

        if(tb.getBancoEmisor().equals(BancoEmisor.BANCO_RIPLEY)){
            return R.mipmap.logobancochile;
        }

        return R.color.grisdefault;
    }

}
