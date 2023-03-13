package com.example.ciclobnb.Objectes.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ciclobnb.Objectes.Bici;
import com.example.ciclobnb.Objectes.Usser;

import java.util.ArrayList;

public class AdapterCiclo extends RecyclerView.Adapter<AdapterCiclo.ViewHolder> {

        ArrayList<Bici> bicis = new ArrayList<>();
        ArrayList<Usser> users = new ArrayList<>();
        Context context;
        boolean mostrarImatge;
        boolean mostrarContrasenya;

        public class ViewHolder extends RecyclerView.ViewHolder {
                TextView tNomCognoms;
                TextView tContrasenya;
                TextView tEmail;
                ImageView tFoto;

                public ViewHolder(View view) {
                        super(view);

                        tNomCognoms = view.findViewById(R.id.);
                        tContrasenya = view.findViewById(R.id.personaContrasenya);
                        tEmail = view.findViewById(R.id.personaEmail);
                        tFoto = view.findViewById(R.id.personaImatge);

                }

                public void bind(Integer position){
                        tNomCognoms.setText(bicis.get(position).getCognoms()+", "+persones.get(position).getNom());
                        tEmail.setText(persones.get(position).getEmail());

                        if (mostrarImatge) {
                                Integer imatge = getImage(persones.get(position).getFoto(), this.itemView);

                        }


                }
        }

 {
    ArrayList<Bici> bicis = new ArrayList<>();
    ArrayList<Usser> users = new ArrayList<>();

}
