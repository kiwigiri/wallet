package com.example.wallet.adapters.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wallet.R;
import com.example.wallet.adapters.support.IItemTouchHelperAdapter;
import com.example.wallet.bean.Debito;
import com.example.wallet.bean.TarjetaBancaria;
import com.example.wallet.persistencia.BaseSqlite;
import com.example.wallet.utils.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class TarjetaAdapter extends RecyclerView.Adapter<TarjetaAdapter.TarjetaViewHolder> implements IItemTouchHelperAdapter {
    private LinkedList<TarjetaBancaria> tarjetas;
    private Context context;
    public TarjetaAdapter(LinkedList<TarjetaBancaria> tarjetas){
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

        BaseSqlite bs = new BaseSqlite(context);
        bs.eliminarTarjeta(tarjetas.get(position));
        tarjetas.remove(position);
        notifyItemRemoved(position);


    }

    @Override
    public void onItemDismissFavorite(int position) {


        TarjetaBancaria aux = tarjetas.get(position);
        boolean favorite = aux.isFavorite();
        tarjetas.get(position).setFavorite(!favorite);

        if(!aux.isFavorite()){
            notifyItemChanged(position);
        }else{
            tarjetas.addFirst(aux);
            tarjetas.remove(position+1);
            notifyDataSetChanged();
        }

    }


    public class TarjetaViewHolder extends RecyclerView.ViewHolder{

        private TextView idTarjeta,fechaTarjeta,nombreUsuario,creditoodebito;
        private ImageView favorito,cardType,logo;
        private LinearLayout ll;
        public TarjetaViewHolder(@NonNull View view) {
            super(view);
            context = view.getContext();
            this.idTarjeta = view.findViewById(R.id.numtarjeta);
            this.fechaTarjeta = view.findViewById(R.id.fechatarjeta);
            this.nombreUsuario = view.findViewById(R.id.nombreUsu);
            this.favorito = view.findViewById(R.id.favorite);
            this.ll = view.findViewById(R.id.linearLayout);
            this.cardType = view.findViewById(R.id.tipotarjeta);
            this.creditoodebito = view.findViewById(R.id.creditoodebito);
            this.logo = view.findViewById(R.id.logoTarjeta);

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

        holder.nombreUsuario.setText(tb.getCliente().getNombre().toUpperCase());

        holder.idTarjeta.setText(tb.getNumTarjeta());
        holder.fechaTarjeta.setText(tb.getFecha().toString());
        holder.logo.setImageResource(Util.getImageBanco(tb));



        holder.ll.setBackgroundResource(Util.getColorCard(tb));
        holder.cardType.setImageResource(Util.getTypeCard(tb));

        if(tb.isFavorite()){
            holder.favorito.setVisibility(View.VISIBLE);
        }else{
            holder.favorito.setVisibility(View.INVISIBLE);
        }
        if(tb instanceof Debito){
            holder.creditoodebito.setText("débito");
        }else{
            holder.creditoodebito.setText("crédito");
        }



    }

    @Override
    public int getItemViewType(int position) {
        return Util.getType();
    }

    @Override
    public int getItemCount() {
        return tarjetas.size();
    }


}
