package com.example.wallet.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.wallet.bean.BancoEmisor;
import com.example.wallet.bean.Cliente;
import com.example.wallet.bean.Credito;
import com.example.wallet.bean.Debito;
import com.example.wallet.bean.Fecha;
import com.example.wallet.bean.NombreTarjeta;
import com.example.wallet.bean.TarjetaBancaria;
import com.example.wallet.colecciones.Tarjetas;

public class BaseSqlite extends SQLiteOpenHelper {
    private static final String NOMBRE_DB = "mibasededatos.db";
    private static final int VERSION_DB = 1;
    private static final String TABLA_TARJETAS = "CREATE TABLE TARJETAS(ID_TARJETA INTEGER PRIMARY KEY AUTOINCREMENT,NIDENTIFICADOR INTEGER," +
            "NUMTARJETA TEXT NOT NULL UNIQUE,BANCOEMISOR TEXT,RUT INTEGER,NOMBRE TEXT,ISFAVORITE INTEGER,CV INTEGER,MES TEXT,ANIO TEXT,SALDO REAL,ISDEBITO INTEGER," +
            "NOMBRETARJETA TEXT,CUPONACIONAL REAL,GASTONACIONAL REAL)";

    public BaseSqlite(@Nullable Context context) {
        super(context, NOMBRE_DB, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA_TARJETAS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long agregarTarjeta(TarjetaBancaria tb){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("NIDENTIFICADOR",tb.getnIdentificador());
        values.put("NUMTARJETA",tb.getNumTarjeta());
        values.put("BANCOEMISOR",tb.getBancoEmisor().toString());
        values.put("RUT",tb.getCliente().getRut());
        values.put("NOMBRE",tb.getCliente().getNombre());
        values.put("ISFAVORITE",tb.isFavorite());
        values.put("CV",tb.getCv());
        values.put("MES",tb.getFecha().getMes());
        values.put("ANIO",tb.getFecha().getAnio());
        values.put("NOMBRETARJETA",tb.getNombreTarjeta().toString());

        if(tb instanceof Debito){
            values.put("SALDO",((Debito) tb).getSaldo());
            values.put("ISDEBITO",true);
        }else{
            values.put("CUPONACIONAL",((Credito) tb).getCupoNacional());
            values.put("GASTONACIONAL",((Credito) tb).getGastoNacional());
            values.put("ISDEBITO",false);
        }

        return db.insert("TARJETAS",null,values);
    }

    public long eliminarTarjeta(TarjetaBancaria tb){

        SQLiteDatabase db = this.getWritableDatabase();

        String selection = "NUMTARJETA = ?";
        String[] selectionArgs = {tb.getNumTarjeta()};

        return db.delete("TARJETAS",selection,selectionArgs);

    }

    public Tarjetas obtenerTarjetas(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                "TARJETAS",   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        Tarjetas tarjetas = new Tarjetas();

        while(cursor.moveToNext()){

            long nIdentificador = cursor.getLong(cursor.getColumnIndexOrThrow("ID_TARJETA"));
            String numTarjeta = cursor.getString(cursor.getColumnIndexOrThrow("NUMTARJETA"));
            BancoEmisor bancoEmisor = BancoEmisor.valueOf(cursor.getString(cursor.getColumnIndexOrThrow("BANCOEMISOR")));
            int rut = cursor.getInt(cursor.getColumnIndexOrThrow("RUT"));
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow("NOMBRE"));
            int isfavorite = cursor.getInt(cursor.getColumnIndexOrThrow("ISFAVORITE"));
            int cv = cursor.getInt(cursor.getColumnIndexOrThrow("CV"));
            String mes = cursor.getString(cursor.getColumnIndexOrThrow("MES"));
            String anio = cursor.getString(cursor.getColumnIndexOrThrow("ANIO"));
            String nomTarjeta = cursor.getString(cursor.getColumnIndexOrThrow("NOMBRETARJETA"));

            int isDebito = cursor.getInt(cursor.getColumnIndexOrThrow("ISDEBITO"));

            TarjetaBancaria tb = new TarjetaBancaria((int)nIdentificador,numTarjeta,bancoEmisor,new Cliente(rut,nombre),
                    isfavorite!=0,cv,new Fecha(mes,anio), NombreTarjeta.valueOf(nomTarjeta));


            if(isDebito!=0){
                tarjetas.addTarjeta(new Debito(tb,0.0));
            }else{
                tarjetas.addTarjeta(new Credito(tb,0.0,0.0));
            }



        }
        cursor.close();

        return tarjetas;

    }
}
