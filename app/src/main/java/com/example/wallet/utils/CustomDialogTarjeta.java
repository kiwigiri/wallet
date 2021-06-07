package com.example.wallet.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.wallet.R;
import com.example.wallet.bean.TarjetaBancaria;

public class CustomDialogTarjeta extends Dialog {

    private int layout;
    private TarjetaBancaria tarjeta;

    private TextView idTarjeta,fechaTarjeta,nombreUsuario;

    public CustomDialogTarjeta(@NonNull Context context, int layout, TarjetaBancaria tarjeta) {
        super(context);
        this.layout = layout;
        this.tarjeta = tarjeta;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout);
        getWindow().setLayout(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.WRAP_CONTENT);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        this.idTarjeta = findViewById(R.id.numtarjeta);
        this.fechaTarjeta = findViewById(R.id.fechatarjeta);
        this.nombreUsuario = findViewById(R.id.nombreUsu);

        idTarjeta.setText(tarjeta.getNumTarjeta());
        nombreUsuario.setText(tarjeta.getCliente().getNombre());

        nombreUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
