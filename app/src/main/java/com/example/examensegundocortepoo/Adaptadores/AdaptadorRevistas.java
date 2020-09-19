package com.example.examensegundocortepoo.Adaptadores;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.examensegundocortepoo.Clases.RevistasCientificas;
import com.example.examensegundocortepoo.R;

import java.util.ArrayList;

public class AdaptadorRevistas extends RecyclerView.Adapter<AdaptadorRevistas.ViewHolder>  {
    private ArrayList<RevistasCientificas> names;
    private OnItemClickListener mListener;
    private Context contexto;

    public interface OnItemClickListener {
        void onClick(int position);

    }
    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener = listener;
    }
    public AdaptadorRevistas(ArrayList<RevistasCientificas> names, Context context)
    {
        this.names=names;
        this.contexto=context;
    }
    @Override
    public AdaptadorRevistas.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_revistas, parent, false);
        ViewHolder evh = new ViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.titulo.setText(names.get(position).getNombre());
        holder.descripcion.setText(Html.fromHtml(names.get(position).getDescripcion()));
        Glide.with(contexto)
                .load(names.get(position).getImagen())
                .into(holder.fotoproducto);
    }

    @Override
    public int getItemCount() {
        return names.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView titulo;
        TextView descripcion;
        ImageView fotoproducto;
        Button contactar;
        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            titulo=(TextView) itemView.findViewById(R.id.textView3TituloRevistas);
            descripcion=(TextView) itemView.findViewById(R.id.txtDescripcionRevistas);
            fotoproducto=(ImageView) itemView.findViewById(R.id.imageViewRevistas);
            contactar=(Button)itemView.findViewById(R.id.btnenlistarrevistas);

            contactar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onClick(position);
                        }
                    }
                }
            });
        }

    }
}
