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
import com.example.wallet.bean.Fecha;
import com.example.wallet.bean.NombreTarjeta;
import com.example.wallet.bean.TarjetaBancaria;
import com.example.wallet.clientservice.RetrofitApiFirebaseService;
import com.example.wallet.clientservice.RetrofitClient;
import com.example.wallet.colecciones.Tarjetas;
import com.example.wallet.persistencia.BaseSqlite;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.internal.LinkedTreeMap;

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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //private ListView listView;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ItemTouchHelper itemTouchHelper;
    LinkedList<TarjetaBancaria> tarjetaBancariaLinkedList;
    TarjetaAdapter tarjetaAdapter;
    private RetrofitApiFirebaseService retrofitApiFirebaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        retrofitApiFirebaseService = RetrofitClient.getApiFirebaseService();

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);

        BaseSqlite bs = new BaseSqlite(getApplicationContext());
        //Tarjetas tarjetas = bs.obtenerTarjetas();
        Tarjetas tarjetas = new Tarjetas();


        tarjetaBancariaLinkedList = new LinkedList<>();
        //tarjetaBancariaLinkedList.addAll(tarjetas.getTarjetas());

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

        retrofitApiFirebaseService.getWallets("Banca").enqueue(new Callback<HashMap<String, Object>>() {
            @Override
            public void onResponse(Call<HashMap<String, Object>> call, Response<HashMap<String, Object>> response) {

                /*
                Set set = response.body().entrySet();

                Iterator it = set.iterator();

                while(it.hasNext()){

                    Map.Entry map  = (Map.Entry) it.next();

                    /*
                    System.out.println(map.getKey());
                    System.out.println(map.getValue());

                    LinkedTreeMap<String, Object> map2 = (LinkedTreeMap<String, Object>) map.getValue();

                    System.out.println(map2.get("bancoEmisor"));

                }*/

                response.body().forEach(
                        (k,v) -> {
                            BancoEmisor bancoEmisor = null;
                            NombreTarjeta nombreTarjeta = null;

                            LinkedTreeMap<String, Object> map = (LinkedTreeMap<String, Object>)v;
                            bancoEmisor = bancoEmisor.valueOf((String)map.get("bancoEmisor"));
                            int cv = (int)(double)map.get("cv");
                            boolean favorite = (boolean)map.get("favorite");
                            int nIdentificador = (int)(double)map.get("nIdentificador");
                            String numTarjeta = (String)map.get("numTarjeta");
                            nombreTarjeta = nombreTarjeta.valueOf((String)map.get("nombreTarjeta"));

                            LinkedTreeMap<String, Object> map1 = (LinkedTreeMap<String, Object>)map.get("fecha");
                            Fecha fecha = new Fecha((String)map1.get("mes"), (String) map1.get("anio"));

                            LinkedTreeMap<String, Object> map1 = (LinkedTreeMap<String, Object>)map.get("cliente");
                            Cliente cliente = new Cliente((int)(double)map1.get("rut"), (String) map1.get("nombre"));

                            

                            if (map.get("saldo")!=null){
                                double saldo = (double)map.get("saldo");
                            }
                            else{
                                double cupoNacional = (double)map.get("cupoNacional");;
                                double gastoNacional = (double)map.get("gastoNacional");
                            }

                        }
                );



            }

            @Override
            public void onFailure(Call<HashMap<String, Object>> call, Throwable t) {

            }
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