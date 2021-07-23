package com.example.wallet;

import android.os.Bundle;

import com.example.wallet.bean.BancoEmisor;
import com.example.wallet.bean.Cliente;
import com.example.wallet.bean.Debito;
import com.example.wallet.bean.Fecha;
import com.example.wallet.bean.NombreTarjeta;
import com.example.wallet.bean.TarjetaBancaria;
import com.example.wallet.persistencia.BaseSqlite;
import com.fevziomurtekin.payview.Payview;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class RegistrarTarjetaActivity extends AppCompatActivity {

    private Payview payview;
    private TextInputEditText tev_card_mes,tev_card_anio,tev_card_num,tev_card_cv,tev_card_nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_tarjeta);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        payview = findViewById(R.id.payview);
        FloatingActionButton fab = findViewById(R.id.fab);

        tev_card_anio = findViewById(R.id.tev_card_year);
        tev_card_mes = findViewById(R.id.tev_card_month);
        tev_card_nombre = findViewById(R.id.tev_card_name);
        tev_card_cv = findViewById(R.id.tev_card_cv);
        tev_card_num = findViewById(R.id.tev_card_no);




        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        payview.setPayOnclickListener(v -> {
            if(payview.isFillAllComponents()){

                TarjetaBancaria tb = new TarjetaBancaria(1,tev_card_num.getText().toString(), BancoEmisor.COOPERTARIVA_COOPEUCH,
                        new Cliente(123456789,tev_card_nombre.getText().toString()),false,Integer.parseInt(tev_card_cv.getText().toString()),
                        new Fecha(tev_card_mes.getText().toString(),tev_card_anio.getText().toString()), NombreTarjeta.valueOf(payview.getCardType().toString()));

                Debito debito = new Debito(tb,0.0);

                BaseSqlite bs = new BaseSqlite(getApplicationContext());

                bs.agregarTarjeta(debito);


                //CustomDialogGuardarTarjeta cdgt = new CustomDialogGuardarTarjeta(RegistroTarjetaActivity.this,tb);
                //cdgt.show();
            }
        });

    }
}