package com.elanilsondejesus.instagram.alteracao.activity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class CorBD extends SQLiteOpenHelper {

    public static class TabCor implements BaseColumns {
        public static final String TABELA = "tab_cor";
        public static final String COL_ID = "_id";
        public static final String COL_NOME = "nome";
        public static final String COL_VALOR_ENERGETICO = "valor_energetico";
        public static final String COL_CARBIODRATO = "carboidrato";
        public static final String COL_PROTEINA = "proteIna";
        public static final String COL_GORDURATOTAIS = "gorduras_totais";
        public static final String COL_SODIO = "sodio";
        public static final String COL_FIBRA = "fibra_alimentar";
        public static final String COL_VALOR_DIARIO_VALOR_ENERGETICO = "valorDiarioenergetico";
        public static final String COL_VALOR_DIARIO_CARBIODRATO = "valorDiariocarboidrato";
        public static final String COL_VALOR_DIARIO_PROTEINA = "valorDiarioproteina";
        public static final String COL_VALOR_DIARIO_GORDURATOTAIS = "valorDiariogordura";
        public static final String COL_VALOR_DIARIO_SODIO = "valorDiariosodio";
        public static final String COL_VALOR_DIARIO_FIBRA = "valorDiariofibra";
    }

    public static final String SQL_TABELA = "CREATE TABLE IF NOT EXISTS " + TabCor.TABELA
            + " ( " +
            TabCor.COL_ID + " INTEGER PRIMARY KEY , " +
            TabCor.COL_NOME + " TEXT, "+ TabCor.COL_VALOR_ENERGETICO + " TEXT, "+
            TabCor.COL_CARBIODRATO + " TEXT, "+TabCor.COL_PROTEINA + " TEXT, "+
            TabCor.COL_GORDURATOTAIS + " TEXT, "+TabCor.COL_SODIO + " TEXT, "+
            TabCor.COL_FIBRA + " TEXT, "+ TabCor.COL_VALOR_DIARIO_VALOR_ENERGETICO + " TEXT, "+
            TabCor.COL_VALOR_DIARIO_CARBIODRATO + " TEXT, "+TabCor.COL_VALOR_DIARIO_PROTEINA + " TEXT, "+
            TabCor.COL_VALOR_DIARIO_GORDURATOTAIS + " TEXT, "+TabCor.COL_VALOR_DIARIO_SODIO + " TEXT, "+
            TabCor.COL_VALOR_DIARIO_FIBRA + " TEXT ) ";

    public static int VERSAO_BD = 1;
    public static String NOME_BD = "Cores.db";

    public CorBD(Context context) {
        super( context, NOME_BD, null, VERSAO_BD );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_TABELA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
