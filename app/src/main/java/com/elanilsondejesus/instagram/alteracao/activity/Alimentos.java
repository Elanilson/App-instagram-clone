package com.elanilsondejesus.instagram.alteracao.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.elanilsondejesus.instagram.R;

import java.util.ArrayList;
import java.util.List;

public class Alimentos extends AppCompatActivity {
    private RecyclerView rvCores;
    private CorAdapter   corAdapter;
    Button x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        //configurara Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("Cadastro de alimentos nutricional");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);

    }



    @Override
    protected void onResume() {
        super.onResume();
        carregarDados();
    }
    public void rt(){
        rvCores = findViewById(R.id.corrrrr);

        LinearLayoutManager m = new LinearLayoutManager(this);
        m.setOrientation(LinearLayoutManager.VERTICAL);

        rvCores.setLayoutManager( m );
        rvCores.setHasFixedSize( true );
    }



    private void carregarDados() {
        CorBD corBD = new CorBD(this);
        SQLiteDatabase bd = corBD.getReadableDatabase();
        List<Cor> lista = new ArrayList<>();
        Cursor dados = bd.query(
                CorBD.TabCor.TABELA, null, null, null,
                null, null, CorBD.TabCor.COL_NOME);
        while (dados.moveToNext()) {
            int id = dados.getInt(dados.getColumnIndexOrThrow(CorBD.TabCor.COL_ID));
            String nome = dados.getString(
                    dados.getColumnIndexOrThrow(CorBD.TabCor.COL_NOME) );
            String enegetico = dados.getString(
                    dados.getColumnIndexOrThrow(CorBD.TabCor.COL_VALOR_ENERGETICO) );
            String caboidrato = dados.getString(
                    dados.getColumnIndexOrThrow(CorBD.TabCor.COL_CARBIODRATO) );
            String proteina = dados.getString(
                    dados.getColumnIndexOrThrow(CorBD.TabCor.COL_PROTEINA) );
            String gordura = dados.getString(
                    dados.getColumnIndexOrThrow(CorBD.TabCor.COL_GORDURATOTAIS) );
            String sodio = dados.getString(
                    dados.getColumnIndexOrThrow(CorBD.TabCor.COL_SODIO) );
            String fibra = dados.getString(
                    dados.getColumnIndexOrThrow(CorBD.TabCor.COL_FIBRA) );
            String valorDiarioenegetico = dados.getString(
                    dados.getColumnIndexOrThrow(CorBD.TabCor.COL_VALOR_DIARIO_VALOR_ENERGETICO) );
            String valorDiariocaboidrato = dados.getString(
                    dados.getColumnIndexOrThrow(CorBD.TabCor.COL_VALOR_DIARIO_CARBIODRATO) );
            String valorDiarioproteina = dados.getString(
                    dados.getColumnIndexOrThrow(CorBD.TabCor.COL_VALOR_DIARIO_PROTEINA) );
            String valorDiariogordura = dados.getString(
                    dados.getColumnIndexOrThrow(CorBD.TabCor.COL_VALOR_DIARIO_GORDURATOTAIS) );
            String valorDiariosodio = dados.getString(
                    dados.getColumnIndexOrThrow(CorBD.TabCor.COL_VALOR_DIARIO_SODIO) );
            String valorDiariofibra = dados.getString(
                    dados.getColumnIndexOrThrow(CorBD.TabCor.COL_VALOR_DIARIO_FIBRA) );
            Cor cor = new Cor(id, nome,enegetico,caboidrato,proteina,gordura,sodio,fibra,valorDiarioenegetico,valorDiariocaboidrato,valorDiarioproteina,valorDiariogordura,valorDiariosodio,valorDiariofibra);
            lista.add( cor );
        }
        bd.close();
//        Log.e("TAMANHO LISTA", lista.size()+"");
        corAdapter = new CorAdapter(lista);
        rvCores.setAdapter( corAdapter );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        rt();
        carregarDados();


    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        int idClicado = item.getItemId();
        switch (idClicado) {
            case R.id.menu_adicionar:
                Intent i = new Intent(this, InserirCorActivity.class);
                startActivity( i );
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
