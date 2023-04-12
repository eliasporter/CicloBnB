package com.example.ciclobnb.Objectes.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ciclobnb.Bici_per_llogar;
import com.example.ciclobnb.Missatges;
import com.example.ciclobnb.Objectes.Disponibilitat;
import com.example.ciclobnb.Objectes.Usser;
import com.example.ciclobnb.Objectes.Xat.Xat;
import com.example.ciclobnb.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AdapterXat extends RecyclerView.Adapter<AdapterXat.ViewHolder> {

        ArrayList<Xat> xat =new ArrayList<>();
        Context context;
        Usser usuari;

    public AdapterXat(ArrayList<Xat> dispo, Context context,Usser usuari) {
            this.xat = dispo;
            this.context = context;
            this.usuari=usuari;
        }
        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView user2;
            TextView ultimMissatge;

            public ViewHolder(View view) {
                super(view);
                user2=view.findViewById(R.id.NomPersonaXat);
                ultimMissatge=view.findViewById(R.id.UltimMisatgeXat);


            }

            public void bind(Integer position) {
                Usser u=new Usser().getUserPerId(xat.get(position).getUser2());
                user2.setText(u.getLogin());
                ultimMissatge.setText(xat.get(position).getUltimMissatge());
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(context, Missatges.class);
                        intent.putExtra("idXat",xat.get(position).getIdXat());
                        intent.putExtra("id",usuari.getIdUser());
                        context.startActivity(intent);
                    }
                });
            }
        }


    @NonNull
    @Override
    public AdapterXat.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.xat_sol, viewGroup, false);

        return new AdapterXat.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterXat.ViewHolder holder, int position) {
        holder.bind(position);
    }


        @Override
        public int getItemCount() {
            return xat.size();
        }

    }
