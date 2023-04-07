package com.example.ciclobnb.Objectes.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ciclobnb.Missatges;
import com.example.ciclobnb.Objectes.Usser;
import com.example.ciclobnb.Objectes.Xat.Missatge;
import com.example.ciclobnb.Objectes.Xat.Xat;
import com.example.ciclobnb.R;

import java.util.ArrayList;

public class AdapterMissatges extends RecyclerView.Adapter<AdapterMissatges.ViewHolder> {

    ArrayList<Missatge> missatges =new ArrayList<>();
    Context context;
    Usser loguejat;

    public AdapterMissatges(ArrayList<Missatge> missatges, Context context, Usser loguejat) {
        this.missatges = missatges;
        this.context = context;
        this.loguejat=loguejat;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textMissatge;


        public ViewHolder(View view) {
            super(view);
            textMissatge=view.findViewById(R.id.NomPersonaXat);
        }

        public void bind(Integer position) {
            textMissatge.setText(missatges.get(position).getMissatge());
            if (loguejat.getIdUser()==missatges.get(position).getEmisor().getIdUser()){
                //si el missatge es del usuari qu ha iniciat sesió els missatges estaràn a la dreta
                ViewGroup.MarginLayoutParams parametres = (ViewGroup.MarginLayoutParams) textMissatge.getLayoutParams();
                parametres.setMarginEnd(10);
                textMissatge.setLayoutParams(parametres);
            }
        }
    }


    @NonNull
    @Override
    public AdapterMissatges.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.missatge_sol, viewGroup, false);

        return new AdapterMissatges.ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull AdapterMissatges.ViewHolder holder, int position) {
        holder.bind(position);
    }


    @Override
    public int getItemCount() {
        return missatges.size();
    }

}
