package com.elanilsondejesus.instagram.alteracao.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class CopoouGarrafa {
    Context context;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    final static String NOME_ARQUIVO="arquivo";
    final static String CHAVE ="nomecopo";

    public CopoouGarrafa(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(NOME_ARQUIVO,0);
        editor = preferences.edit();
    }

    public void salvar(boolean nome){

        editor.putBoolean(CHAVE,nome);
        editor.commit();

    }

    public String carregar(){
        return preferences.getString(CHAVE,"");
    }
}
