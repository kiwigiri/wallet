package com.example.wallet.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.wallet.R;
import com.example.wallet.bean.BancoEmisor;
import com.example.wallet.bean.Credito;
import com.example.wallet.bean.Debito;
import com.example.wallet.bean.TarjetaBancaria;
import com.example.wallet.persistencia.BaseSqlite;

public class CustomDialogGuardarTarjeta extends Dialog {

    private TarjetaBancaria tarjeta;
    private RadioButton debito;
    private Spinner spinner;
    private BaseSqlite bd;

    private Button ok;
    private Activity ac;

    public CustomDialogGuardarTarjeta(@NonNull Activity context, TarjetaBancaria tarjeta) {
        super(context);
        this.ac = context;
        this.tarjeta = tarjeta;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog_registro_tarjeta);
        getWindow().setLayout(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.WRAP_CONTENT);
        bd = new BaseSqlite(getContext());
        this.debito = findViewById(R.id.rdbDebito);
        this.ok = findViewById(R.id.btn_ok);

        this.debito.setChecked(true);
        this.spinner = findViewById(R.id.spinner);

        ArrayAdapter<String> mAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,Util.getBancosEmisores());

        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(mAdapter);


        ok.setOnClickListener(v -> {

            tarjeta.setBancoEmisor(BancoEmisor.valueOf(spinner.getSelectedItem().toString()));

            if(debito.isChecked()){

                Debito tdb = new Debito(tarjeta,0);
                long id=  bd.agregarTarjeta(tdb);
                if(id==-1){
                    Toast.makeText(getContext(),"Tarjeta Debito ingresada ya existe",Toast.LENGTH_SHORT).show();
                    dismiss();
                }else{
                    dismiss();
                    Intent intent = ac.getIntent();
                    intent.putExtra("tb", tdb);
                    ac.setResult(500, intent);
                    ac.finish();
                }

            }else {
                Credito tdc = new Credito(tarjeta,0.0,0.0);
                long id=  bd.agregarTarjeta(new Credito(tarjeta,0.0,0.0));
                if(id==-1){
                    Toast.makeText(getContext(),"Tarjeta Credito ingresada ya existe",Toast.LENGTH_SHORT).show();
                    dismiss();
                }else{
                    dismiss();
                    Intent intent = ac.getIntent();
                    intent.putExtra("tb", tdc);
                    ac.setResult(600, intent);
                    ac.finish();
                }
            }

        });

    }


    @Override
    public void dismiss() {
        super.dismiss();
    }
}
