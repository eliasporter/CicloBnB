package com.example.ciclobnb.Objectes.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ciclobnb.Objectes.Disponibilitat;
import com.example.ciclobnb.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AdapterXat extends RecyclerView.Adapter<AdapterXat.ViewHolder> {

    /*TreeSet<Bici> bicis = new TreeSet<>();
    TreeSet<Usser> users = new TreeSet<>();*/
        ArrayList<Disponibilitat> dispo =new ArrayList<>();
        Context context;

    public AdapterXat( ArrayList<Disponibilitat> dispo, Context context) {
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

            public void bind(Integer position) {
                DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
                dataInici.setText(dateFormat.format(dispo.get(position).getDataInici()));
                dataFi.setText(dateFormat.format(dispo.get(position).getDataFi()));
                preu.setText(String.valueOf(dispo.get(position).getPreu()));
            }
        }


    @NonNull
    @Override
    public AdapterXat.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.disponibilitats_text, viewGroup, false);

        return new AdapterXat.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterXat.ViewHolder holder, int position) {
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
