package com.example.listaejercicio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {

    private List<Dato> datos;
    private OnClick onClick = null;

    public Adaptador(List<Dato> datos, OnClick onClick){
        this.datos=datos;
        this.onClick=onClick;
    }

    @NonNull
    @Override
    public Adaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dato_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptador.ViewHolder holder, int position) {
       holder.titulo.setText(datos.get(position).getTitulo());
       holder.contenido.setText(datos.get(position).getContenido());
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        private TextView titulo,contenido;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            titulo = itemView.findViewById(R.id.tituloAdapter);
            contenido = itemView.findViewById(R.id.contenidoAdapter);
        }

        @Override
        public void onClick(View v) {
            int posicion = getAdapterPosition();
            onClick.onClick(posicion);
        }

        @Override
        public boolean onLongClick(View v) {
            int posicion = getAdapterPosition();
            onClick.onLongClick(posicion);
            return false;
        }
    }
}
