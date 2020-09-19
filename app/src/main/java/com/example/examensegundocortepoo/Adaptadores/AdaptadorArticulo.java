package com.example.examensegundocortepoo.Adaptadores;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examensegundocortepoo.Clases.RevistaArticulo;
import com.example.examensegundocortepoo.Clases.RevistasEdiciones;
import com.example.examensegundocortepoo.R;

import java.util.List;

public class AdaptadorArticulo extends RecyclerView.Adapter<AdaptadorArticulo.ViewHolder> {
    private List<RevistaArticulo> codigos;
    private Context Ctx;
    private OnItemClickListener listener;

    public AdaptadorArticulo(List<RevistaArticulo> codigos, Context ctx, OnItemClickListener listener) {
        this.codigos = codigos;
        Ctx = ctx;
        this.listener = listener;
    }

    @Override
    public AdaptadorArticulo.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(Ctx);
        View view = inflater.inflate(R.layout.item_articulos,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( AdaptadorArticulo.ViewHolder holder, int position) {
        holder.bind(listener);
        holder.contenedor.setAnimation(AnimationUtils.loadAnimation(Ctx,R.anim.fadetransaccionanimation));
        holder.titulo.setText(codigos.get(position).getTitulo());
        holder.autores.setText(codigos.get(position).getAutores());
        holder.seccion.setText(String.valueOf(codigos.get(position).getSeccion()));
        holder.fechapublicacion.setText(codigos.get(position).getFechapublicación());
    }

    @Override
    public int getItemCount() {
        return codigos.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView titulo;
        TextView autores;
        TextView seccion;
        TextView fechapublicacion;
        RelativeLayout contenedor;
        public ViewHolder(View itemView) {
            super(itemView);
            this.titulo=(TextView) itemView.findViewById(R.id.txttutuloarticulo);
            this.autores=(TextView) itemView.findViewById(R.id.txtautoresarticulo);
            this.seccion=(TextView) itemView.findViewById(R.id.textseccionarticulo);
            this.fechapublicacion=(TextView) itemView.findViewById(R.id.txtfechaarticulo);
            this.contenedor=(RelativeLayout) itemView.findViewById(R.id.Relativecontenedor);
        }
        public void bind(final OnItemClickListener listener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(getAdapterPosition());
                }
            });
        }

    }
    public  interface OnItemClickListener{
        void onItemClick(int position);

    }
}
