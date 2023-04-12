package com.example.ciclobnb.Objectes.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ciclobnb.Missatges;
import com.example.ciclobnb.Objectes.Usser;
import com.example.ciclobnb.Objectes.Xat.Missatge;
import com.example.ciclobnb.Objectes.Xat.Xat;
import com.example.ciclobnb.R;

import java.util.ArrayList;

public class AdapterMissatges extends RecyclerView.Adapter<AdapterMissatges.ViewHolder> {

    ArrayList<Missatge> missatges ;
    Context context;
    Usser loguejat;

    public AdapterMissatges(ArrayList<Missatge> missatges, Context context, Usser loguejat) {
        this.missatges = missatges;
        this.context = context;
        this.loguejat=loguejat;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textMissatge;
        CardView carta;

        public ViewHolder(View view) {
            super(view);
            textMissatge=view.findViewById(R.id.MisatgeXat);
            carta=view.findViewById(R.id.cartaMissatge);
        }

        public void bind(Integer position) {
            textMissatge.setText(missatges.get(position).getMissatge());
            if (loguejat.getIdUser()==missatges.get(position).getEmisor()){
                //si el missatge es del usuari qu ha iniciat sesió els missatges estaràn a la dreta
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(900, 5,0, 0); // establece el margen derecho en 10dp
                carta.setLayoutParams(params);
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
