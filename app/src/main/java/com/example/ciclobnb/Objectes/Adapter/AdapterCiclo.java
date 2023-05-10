package com.example.ciclobnb.Objectes.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ciclobnb.Bici_per_llogar;
import com.example.ciclobnb.Objectes.Bici;
import com.example.ciclobnb.Objectes.Ofereix;
import com.example.ciclobnb.Objectes.Usser;
import com.example.ciclobnb.R;

import java.util.ArrayList;
import java.util.TreeSet;

public class AdapterCiclo extends RecyclerView.Adapter<AdapterCiclo.ViewHolder> {
    ArrayList<Ofereix> ofereixes;
    Context context;
    boolean mostrarImatge;
    boolean mostrarContrasenya;

    public AdapterCiclo(ArrayList<Ofereix> ofereixes, Context context) {
        this.ofereixes = ofereixes;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tUserLogin;
        TextView tTipusBici;
        TextView tDescripcio;
        TextView tDireccio;
        ImageView tFoto;

        public ViewHolder(View view) {
            super(view);
            tUserLogin = view.findViewById(R.id.loginPers);
            tTipusBici = view.findViewById(R.id.tipusBici);
            tDescripcio = view.findViewById(R.id.descripcioBici);
            tDireccio = view.findViewById(R.id.direccioBici);
            tFoto = view.findViewById(R.id.personaImatge);
        }

        @SuppressLint("SetTextI18n")
        public void bind(Integer position) {

            Log.d("LOGOfereix", "BuscandoBicis");
            tUserLogin.setText(ofereixes.get(position).ussers.get(0).getLogin());
            tTipusBici.setText(ofereixes.get(position).bicis.get(0).getTipus());
            tDescripcio.setText(ofereixes.get(position).bicis.get(0).getDescripcio());
            tDireccio.setText(ofereixes.get(position).bicis.get(0).direccio.getTipusVia() + " " + ofereixes.get(position).bicis.get(0).direccio.getNomCarrer());

            Log.d("LOGOfereix", "BiciEncontrada");
            /*if (mostrarImatge) {
                Integer imatge = getImage(bicis.get(position).getFoto(), this.itemView);
            }*/
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, Bici_per_llogar.class);
                    intent.putExtra("Bike", ofereixes.get(position));
                    context.startActivity(intent);
                }
            });

        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.bici_card, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.bind(position);
    }

    @Override
    public int getItemCount() {
        return ofereixes.size();
    }

    public Integer getImage(String imatge, View v) {
        Resources resources = context.getResources();

        return resources.getIdentifier(imatge, "drawable", context.getPackageName() );
    }
}
