package com.example.examensegundocortepoo.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.examensegundocortepoo.Clases.RevistasEdiciones;
import com.example.examensegundocortepoo.R;

import java.util.List;

public class AdaptadorEdiciones extends RecyclerView.Adapter<AdaptadorEdiciones.ViewHolder> {
    private List<RevistasEdiciones> codigos;
    private Context Ctx;
    private OnItemClickListener listener;
    public AdaptadorEdiciones( Context cont,List<RevistasEdiciones> names, OnItemClickListener listener)
    {
        this.codigos=names;
        this.Ctx=cont;
        this.listener=listener;
    }
    @Override
    public AdaptadorEdiciones.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(Ctx);
        View view = inflater.inflate(R.layout.item_ediciones,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdaptadorEdiciones.ViewHolder holder, int position) {
        holder.bind(listener);
        holder.titulo.setAnimation(AnimationUtils.loadAnimation(Ctx,R.anim.transaccionizquierda));
        holder.titulo.setText(codigos.get(position).getTitle());
        holder.doi.setText(codigos.get(position).getDoi());
        holder.volumen.setText(String.valueOf(codigos.get(position).getVolumen()));
        holder.numero.setText(String.valueOf(codigos.get(position).getNumber()));
        holder.anio.setText(String.valueOf(codigos.get(position).getYear()));
        holder.fechapublicacion.setText(codigos.get(position).getFechapublicacion());
        holder.imagenediciones.setAnimation(AnimationUtils.loadAnimation(Ctx,R.anim.fadetransaccionanimation));
        Glide.with(Ctx)
                .load(codigos.get(position).getImagen())
                .into(holder.imagenediciones);
    }

    @Override
    public int getItemCount() {
        return codigos.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView titulo;
        TextView doi;
        TextView volumen;
        TextView numero;
        TextView anio;
        TextView fechapublicacion;
        ImageView imagenediciones;

        public ViewHolder(View itemView) {
            super(itemView);
            this.titulo=(TextView) itemView.findViewById(R.id.txttituloediciones);
            this.doi=(TextView) itemView.findViewById(R.id.txtdoiediciones);
            this.volumen=(TextView) itemView.findViewById(R.id.txtvolumenediciones);
            this.numero=(TextView) itemView.findViewById(R.id.txtnumeroediciones);
            this.anio=(TextView) itemView.findViewById(R.id.txtanioediciones);
            this.fechapublicacion=(TextView) itemView.findViewById(R.id.txtfechapubliediciones);
            this.imagenediciones=(ImageView) itemView.findViewById(R.id.imageViewEdiciones);
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
