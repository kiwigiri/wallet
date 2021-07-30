package com.example.wallet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.wallet.bean.BancoEmisor;
import com.example.wallet.bean.Cliente;
import com.example.wallet.bean.Debito;
import com.example.wallet.bean.Fecha;
import com.example.wallet.bean.NombreTarjeta;
import com.example.wallet.bean.TarjetaBancaria;
import com.example.wallet.persistencia.BaseSqlite;
import com.example.wallet.utils.CustomDialogGuardarTarjeta;
import com.fevziomurtekin.payview.Payview;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import cards.pay.paycardsrecognizer.sdk.Card;
import cards.pay.paycardsrecognizer.sdk.ScanCardIntent;

public class RegistrarTarjetaActivity extends AppCompatActivity {

    private Payview payview;
    private TextInputEditText tev_card_mes,tev_card_anio,tev_card_num,tev_card_cv,tev_card_nombre;

    static final int REQUEST_CODE_SCAN_CARD = 1;

    static final String TAG = "INFOSCAN";

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
                Intent intent = new ScanCardIntent.Builder(getApplicationContext()).build();
                startActivityForResult(intent, REQUEST_CODE_SCAN_CARD);
            }
        });

        payview.setPayOnclickListener(v -> {
            if(payview.isFillAllComponents()){


                TarjetaBancaria tb = new TarjetaBancaria(1,tev_card_num.getText().toString(), BancoEmisor.COOPERTARIVA_COOPEUCH,
                        new Cliente(123456789,tev_card_nombre.getText().toString()),false,Integer.parseInt(tev_card_cv.getText().toString()),
                        new Fecha(tev_card_mes.getText().toString(),tev_card_anio.getText().toString()), NombreTarjeta.valueOf(payview.getCardType().toString()));
                CustomDialogGuardarTarjeta cdt = new CustomDialogGuardarTarjeta(RegistrarTarjetaActivity.this,tb);
                cdt.show();

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SCAN_CARD) {
            if (resultCode == Activity.RESULT_OK) {
                Card card = data.getParcelableExtra(ScanCardIntent.RESULT_PAYCARDS_CARD);

                String[] fecha = card.getExpirationDate().split("/");
                tev_card_nombre.setText(card.getCardHolderName());
                StringBuilder sb = new StringBuilder(card.getCardNumber());

                for(int i = 4;i<sb.length();i++){
                    sb.insert(i," ");
                }

                tev_card_num.setText(sb.toString());
                tev_card_mes.setText(fecha[0]);
                tev_card_anio.setText(fecha[1]);

            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i(TAG, "Scan canceled");
            } else {
                Log.i(TAG, "Scan failed");
            }
        }
    }

}