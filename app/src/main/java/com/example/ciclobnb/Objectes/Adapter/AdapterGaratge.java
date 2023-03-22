package com.example.ciclobnb.Objectes.Adapter;

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
import com.example.ciclobnb.Garatge;
import com.example.ciclobnb.Objectes.Bici;
import com.example.ciclobnb.Objectes.Usser;
import com.example.ciclobnb.R;

import java.util.ArrayList;

public class AdapterGaratge extends RecyclerView.Adapter<AdapterGaratge.ViewHolder> {

    /*TreeSet<Bici> bicis = new TreeSet<>();
    TreeSet<Usser> users = new TreeSet<>();*/
    ArrayList<Usser> users=new ArrayList<>();
    ArrayList <Bici> bicis =new ArrayList<>();
    Context context;
    boolean mostrarImatge;
    boolean mostrarContrasenya;

    public AdapterGaratge( ArrayList<Bici> bicis, Context context) {
        this.users = users;
        this.bicis = bicis;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView marca;
        TextView descripcio;
        Button editar;
        TextView tDireccio;
        ImageView tFoto;

        public ViewHolder(View view) {
            super(view);
            marca = view.findViewById(R.id.MarcaModelBiciGaratge);
            descripcio = view.findViewById(R.id.DescripcioBiciGaratge);
            editar = view.findViewById(R.id.botoEditaBiciGaratge);
            tDireccio = view.findViewById(R.id.direccioBici);
            tFoto = view.findViewById(R.id.fotoBiciGaratge);

        }

        public void bind(Integer position) {

            marca.setText(bicis.get(position).getMarca());
            editar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String missatge="Actualment sols es podrà afegir bicicletes des de un ordenador";
                    AlertDialog.Builder build=new AlertDialog.Builder(context);
                    build.setTitle(R.string.atencio);
                    build.setMessage(missatge);
                    build.setPositiveButton("Dacord", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                }
            });

            if (mostrarImatge) {
                Integer imatge = getImage(bicis.get(position).getFoto(), this.itemView);

            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, Bici_per_llogar.class);
                    intent.putExtra("identificador",bicis.get(position).getIdBicicleta());

                    context.startActivity(intent);
                }
            });

        }
    }
    @Override
    public AdapterGaratge.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.bici_per_garatge, viewGroup, false);

        return new AdapterGaratge.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }



    @Override
    public int getItemCount() {
        return bicis.size();
    }

    public Integer getImage(String imatge, View v) {
        Resources resources = context.getResources();

        return resources.getIdentifier(imatge, "drawable", context.getPackageName() );
    }
}