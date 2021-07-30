package com.example.wallet;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
import com.example.wallet.persistencia.BaseSqlite;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
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
    LinkedList<TarjetaBancaria> tarjetaBancariaLinkedList;
    TarjetaAdapter tarjetaAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);

        BaseSqlite bs = new BaseSqlite(getApplicationContext());
        Tarjetas tarjetas = bs.obtenerTarjetas();

        tarjetaBancariaLinkedList = new LinkedList<>();
        tarjetaBancariaLinkedList.addAll(tarjetas.getTarjetas());

        tarjetaAdapter = new TarjetaAdapter(tarjetaBancariaLinkedList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(tarjetaAdapter);

        itemTouchHelper = new ItemTouchHelper(new MyItemTouchHelperCallback(tarjetaAdapter));

        itemTouchHelper.attachToRecyclerView(recyclerView);


        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(v -> Toast.makeText(getApplicationContext(),"Cantidad de tarjetas Debito: "+tarjetas.obtenerCantidadTDebito(),Toast.LENGTH_SHORT).show());
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),RegistrarTarjetaActivity.class);
            startActivityForResult(intent,0);
        });
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==500){//debito
            Debito tb = (Debito) data.getSerializableExtra("tb");
            tarjetaBancariaLinkedList.addFirst(tb);
            tarjetaAdapter.notifyDataSetChanged();

        }

        if(resultCode==600){//credito
            Credito tb = (Credito) data.getSerializableExtra("tb");
            tarjetaBancariaLinkedList.addFirst(tb);
            tarjetaAdapter.notifyDataSetChanged();

        }
    }
}