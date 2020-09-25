package com.elanilsondejesus.instagram.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.elanilsondejesus.instagram.R;
import com.elanilsondejesus.instagram.activity.ComentariosActivity;
import com.elanilsondejesus.instagram.model.Comentario;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterComentario extends RecyclerView.Adapter<AdapterComentario.MyViewHolder> {
    private List<Comentario>comentarioList;
    private Context context;

    public AdapterComentario(List<Comentario> comentarioList, Context context) {
        this.comentarioList = comentarioList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemlista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_comentario,parent,false);

        return new AdapterComentario.MyViewHolder(itemlista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Comentario comentario = comentarioList.get(position);
        holder.nomeUsuario.setText(comentario.getNomeUsuario());
        holder.comentario.setText(comentario.getComentario());
        Glide.with(context).load(comentario.getCaminhoFoto()).into(holder.imagemPerfil);




    }

    @Override
    public int getItemCount() {
        return comentarioList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        CircleImageView  imagemPerfil;
        TextView nomeUsuario, comentario;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imagemPerfil = itemView.findViewById(R.id.imagemFotoComentario);
            nomeUsuario = itemView.findViewById(R.id.textNomeComentario);
            comentario = itemView.findViewById(R.id.textComentario);


        }
    }
}
