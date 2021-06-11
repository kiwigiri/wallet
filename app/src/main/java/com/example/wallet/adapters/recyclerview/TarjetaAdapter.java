package com.example.wallet.adapters.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wallet.R;
import com.example.wallet.adapters.IItemTouchHelperAdapter;
import com.example.wallet.bean.TarjetaBancaria;
import com.example.wallet.utils.Util;

import java.util.ArrayList;
import java.util.Collections;

public class TarjetaAdapter extends RecyclerView.Adapter<TarjetaAdapter.TarjetaViewHolder> implements IItemTouchHelperAdapter {

    private ArrayList<TarjetaBancaria> tarjetas;

    public TarjetaAdapter(ArrayList<TarjetaBancaria> tarjetas){
        this.tarjetas = tarjetas;
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {

        Collections.swap(tarjetas,fromPosition,toPosition);
        //notifyDataSetChanged();
        notifyItemMoved(fromPosition,toPosition);

    }

    @Override
    public void onItemDismiss(int position) {

        tarjetas.remove(position);
        notifyItemRemoved(position);

    }


    public class TarjetaViewHolder extends RecyclerView.ViewHolder{

        private TextView idTarjeta,fechaTarjeta,nombreUsuario;
        public TarjetaViewHolder(@NonNull View view) {
            super(view);
            this.idTarjeta = view.findViewById(R.id.numtarjeta);
            this.fechaTarjeta = view.findViewById(R.id.fechatarjeta);
            this.nombreUsuario = view.findViewById(R.id.nombreUsu);

        }
    }

    @NonNull
    @Override
    public TarjetaAdapter.TarjetaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //TarjetaBancaria tb = tarjetas.get(ad)

        View v = LayoutInflater.from(parent.getContext()).inflate(viewType,parent,false);

        return new TarjetaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TarjetaAdapter.TarjetaViewHolder holder, int position) {
        TarjetaBancaria tb = tarjetas.get(position);

        holder.nombreUsuario.setText(tb.getCliente().getNombre());

        holder.idTarjeta.setText(tb.getNumTarjeta());


    }

    @Override
    public int getItemViewType(int position) {
        return Util.getType(tarjetas.get(position));
    }

    @Override
    public int getItemCount() {
        return tarjetas.size();
    }


}
