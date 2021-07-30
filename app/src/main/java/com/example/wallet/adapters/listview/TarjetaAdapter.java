package com.example.wallet.adapters.listview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.wallet.R;
import com.example.wallet.bean.BancoEmisor;
import com.example.wallet.bean.Debito;
import com.example.wallet.bean.TarjetaBancaria;
import com.example.wallet.utils.CustomDialogTarjeta;
import com.example.wallet.utils.Util;

import java.util.ArrayList;
import java.util.Collections;

public class TarjetaAdapter extends ArrayAdapter<TarjetaBancaria> {

    private ArrayList<TarjetaBancaria> tarjetas;
    public TarjetaAdapter(@NonNull Activity context, ArrayList<TarjetaBancaria> tarjetas) {
        super(context, 0,tarjetas);
        this.tarjetas = tarjetas;
    }

    public class TarjetaBancariaViewHolder extends View{

        private TextView idTarjeta,fechaTarjeta,nombreUsuario;
        //private LinearLayout linearLayout;

        public TarjetaBancariaViewHolder(View view) {
            super(view.getContext());
            this.idTarjeta = view.findViewById(R.id.numtarjeta);
            this.fechaTarjeta = view.findViewById(R.id.fechatarjeta);
            this.nombreUsuario = view.findViewById(R.id.nombreUsu);
          //  this.linearLayout = view.findViewById(R.id.linearLayout);
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        TarjetaBancaria tarjeta = getItem(position);

        convertView = LayoutInflater.from(getContext()).inflate(Util.getType(),parent,false);

        TarjetaBancariaViewHolder tarjetaBancariaViewHolder = new TarjetaBancariaViewHolder(convertView);
        tarjetaBancariaViewHolder.nombreUsuario.setText(tarjeta.getCliente().getNombre());
        //tarjetaBancariaViewHolder.fechaTarjeta.setText(tarjeta.getBancoEmisor();
        tarjetaBancariaViewHolder.idTarjeta.setText(tarjeta.getNumTarjeta());

        convertView.setOnClickListener(v -> Toast.makeText(getContext(),tarjetas.get(position).getBancoEmisor().toString(),Toast.LENGTH_SHORT).show());

        /*
        if(tarjeta.getBancoEmisor().equals(BancoEmisor.COOPERTARIVA_COOPEUCH) && (tarjeta instanceof Debito)){
            tarjetaBancariaViewHolder.linearLayout.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        }*/


        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                CustomDialogTarjeta cdt = new CustomDialogTarjeta(getContext(),Util.getType(),tarjeta);
                cdt.show();


                return true;
            }
        });


        tarjetaBancariaViewHolder.nombreUsuario.setOnClickListener(v -> Toast.makeText(getContext(),"Rut: "+tarjetas.get(position).getCliente().getRut(),Toast.LENGTH_SHORT).show());




        return convertView;
    }

}
