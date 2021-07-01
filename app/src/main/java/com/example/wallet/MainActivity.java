package com.example.wallet;

import android.os.Bundle;

import com.example.wallet.adapters.support.MyItemTouchHelperCallback;
import com.example.wallet.adapters.recyclerview.TarjetaAdapter;
import com.example.wallet.bean.BancoEmisor;
import com.example.wallet.bean.Cliente;
import com.example.wallet.bean.Credito;
import com.example.wallet.bean.Debito;
import com.example.wallet.bean.NombreTarjeta;
import com.example.wallet.bean.TarjetaBancaria;
import com.example.wallet.colecciones.Tarjetas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    //private ListView listView;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ItemTouchHelper itemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);

        Tarjetas tarjetas = new Tarjetas();

        Debito d1 = new Debito(1,"5285   0321   1596   6724",BancoEmisor.COOPERTARIVA_COOPEUCH,new Cliente(123456789,"MARIA TERESA HENRIQUEZ"),false,200000);
        Debito d2 = new Debito(2,"8246   8164   0314   9734",BancoEmisor.BANCO_BICE,new Cliente(123456789,"MARIA TERESA HENRIQUEZ"),false,10000);
        Debito d3 = new Debito(3,"1547   3016   9463   3174",BancoEmisor.BANCO_CHILE,new Cliente(123456789,"MARIA TERESA HENRIQUEZ"),false,200000);
        Debito d4 = new Debito(4,"9463   8462   8234   3791",BancoEmisor.BANCO_ESTADO,new Cliente(123456789,"MARIA TERESA HENRIQUEZ"),false,10000);
        Debito d5 = new Debito(5,"9347   0347   1596   8541",BancoEmisor.BANCO_FALABELLA,new Cliente(123456789,"MARIA TERESA HENRIQUEZ"),false,200000);


        Credito c1 = new Credito(7,"1348   6702   6741   9765",BancoEmisor.BANCO_CHILE,new Cliente(123456789,"MARIA TERESA HENRIQUEZ"), false,NombreTarjeta.Visa,400000,10000);
        Credito c2 = new Credito(7,"8763   5034   9756   3487",BancoEmisor.BANCO_FALABELLA,new Cliente(123456789,"MARIA TERESA HENRIQUEZ"),false, NombreTarjeta.MasterCard,400000,10000);

        tarjetas.addTarjeta(d1);
        tarjetas.addTarjeta(d2);
        tarjetas.addTarjeta(d3);
        tarjetas.addTarjeta(d4);
        tarjetas.addTarjeta(d5);

        tarjetas.addTarjeta(c1);
        tarjetas.addTarjeta(c2);

        LinkedList<TarjetaBancaria> tarjetaBancariaLinkedList = new LinkedList<>();
        tarjetaBancariaLinkedList.addAll(tarjetas.getTarjetas());

        TarjetaAdapter tarjetaAdapter = new TarjetaAdapter(tarjetaBancariaLinkedList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(tarjetaAdapter);

        itemTouchHelper = new ItemTouchHelper(new MyItemTouchHelperCallback(tarjetaAdapter));

        itemTouchHelper.attachToRecyclerView(recyclerView);


        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(v -> Toast.makeText(getApplicationContext(),"Cantidad de tarjetas Debito: "+tarjetas.obtenerCantidadTDebito(),Toast.LENGTH_SHORT).show());
        fab.setOnClickListener(view -> Snackbar.make(view, "Tarjetas Debito: "+tarjetas.obtenerCantidadTDebito(), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}