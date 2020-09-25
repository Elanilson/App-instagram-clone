package com.elanilsondejesus.instagram.alteracao.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class SalvandoMeta {

    Context context;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    final static String NOME_ARQUIVO1="arquivo1";
    final static String CHAVE1 ="nome1";
    final static String NOME_ARQUIVO2="arquivo2";
    final static String CHAVE2 ="nome2";
    final static String NOME_ARQUIVO3="arquivo3";
    final static String CHAVE3="nome3";
    final static String NOME_ARQUIVO4="arquivo4";
    final static String CHAVE4="nome4";


    public SalvandoMeta(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(NOME_ARQUIVO1,0);
        editor = preferences.edit();
        preferences = context.getSharedPreferences(NOME_ARQUIVO2,0);
        editor = preferences.edit();
        preferences = context.getSharedPreferences(NOME_ARQUIVO3,0);
        editor = preferences.edit();
        preferences = context.getSharedPreferences(NOME_ARQUIVO4,0);
        editor = preferences.edit();
    }

    public void salvarData(String nome){

        editor.putString(CHAVE1,nome);
        editor.commit();

    }
    public void salvarmeta(String nome){

        editor.putString(CHAVE2,nome);
        editor.commit();

    }
    public void salvarPesoInicial(String nome){

        editor.putString(CHAVE3,nome);
        editor.commit();

    }
    public void salvarPeso(String nome){

        editor.putString(CHAVE4,nome);
        editor.commit();

    }

    public String carregarData(){
        return preferences.getString(CHAVE1,"");
    }
    public String carregarMeta(){
        return preferences.getString(CHAVE2,"");
    }
    public String carregarPesoInicial(){
        return preferences.getString(CHAVE3,"");
    }
    public String carregarPeso(){
        return preferences.getString(CHAVE4,"");
    }

}
