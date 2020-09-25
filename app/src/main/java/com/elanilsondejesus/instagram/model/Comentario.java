package com.elanilsondejesus.instagram.model;

import com.elanilsondejesus.instagram.helper.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;

public class Comentario {
    private  String idComentario;
    private  String idPostagem;
    private  String idUsuario;
    private  String caminhoFoto;
    private  String nomeUsuario;
    private  String comentario;

    public Comentario() {
    }
    public boolean salvar (){

        DatabaseReference comentarioRef = ConfiguracaoFirebase.getFirebase()
                .child("comentarios")
                .child(getIdPostagem());
        String chave = comentarioRef.push().getKey();
        setIdComentario(chave);
        comentarioRef.child(getIdComentario()).setValue(this);

        return true;
    }

    public String getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(String idComentario) {
        this.idComentario = idComentario;
    }

    public String getIdPostagem() {
        return idPostagem;
    }

    public void setIdPostagem(String idPostagem) {
        this.idPostagem = idPostagem;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
