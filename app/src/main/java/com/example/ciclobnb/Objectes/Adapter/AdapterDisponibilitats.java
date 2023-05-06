package com.example.ciclobnb.Objectes.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ciclobnb.Bici_per_llogar;
import com.example.ciclobnb.Objectes.Bici;
import com.example.ciclobnb.Objectes.Disponibilitat;
import com.example.ciclobnb.Objectes.Usser;
import com.example.ciclobnb.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AdapterDisponibilitats extends RecyclerView.Adapter<AdapterDisponibilitats.ViewHolder> {
    ArrayList <Disponibilitat> dispo;
    Context context;
    @SuppressLint("SimpleDateFormat")
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public AdapterDisponibilitats( ArrayList<Disponibilitat> dispo, Context context) {
        this.dispo = dispo;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dataInici;
        TextView dataFi;
        TextView preu;

        public ViewHolder(View view) {
            super(view);
            dataInici=view.findViewById(R.id.DiaInici);
            dataFi=view.findViewById(R.id.DiaFi);
            preu=view.findViewById(R.id.Preu);
        }

        @SuppressLint("SetTextI18n")
        public void bind(Integer position) {
            dataInici.setText("Des de: "+dispo.get(position).getDataInici());
            dataFi.setText("Fins: "+dispo.get(position).getDataFi());
            //preu.setText(String.valueOf(dispo.get(position).getPreu()));
        }
    }
    @Override
    public AdapterDisponibilitats.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.disponibilitats_text, viewGroup, false);

        return new AdapterDisponibilitats.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }


    @Override
    public int getItemCount() {
        return dispo.size();
    }

    public Integer getImage(String imatge, View v) {
        Resources resources = context.getResources();

        return resources.getIdentifier(imatge, "drawable", context.getPackageName() );
    }
}