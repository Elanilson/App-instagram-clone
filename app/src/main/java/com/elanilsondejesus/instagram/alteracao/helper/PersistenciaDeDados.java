package com.elanilsondejesus.instagram.alteracao.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class PersistenciaDeDados {
    Context context;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    final static String NOME_ARQUIVO="arquivo";
    final static String CHAVE ="nome";
    final static String NOME_ARQUIVO1="arquivo1";
    final static String CHAVE1 ="nome1";

    public PersistenciaDeDados(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(NOME_ARQUIVO,0);
        editor = preferences.edit();
        preferences = context.getSharedPreferences(NOME_ARQUIVO1,0);
        editor = preferences.edit();
    }

    public void salvar(String nome){

        editor.putString(CHAVE,nome);
        editor.commit();

    }
    public void salvarConsumoDeagua(String nome){

        editor.putString(CHAVE1,nome);
        editor.commit();

    }

    public String carregar(){
        return preferences.getString(CHAVE,"");
    }
    public String carregarConsumodeAgua(){
        return preferences.getString(CHAVE1,"");
    }
}
