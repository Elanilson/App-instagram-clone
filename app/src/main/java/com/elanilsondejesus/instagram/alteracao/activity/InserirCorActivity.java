package com.elanilsondejesus.instagram.alteracao.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.elanilsondejesus.instagram.R;

public class InserirCorActivity extends AppCompatActivity {
    private EditText editCor, energetico,fibra,proteina,carboidrato,gordura,sodio;
    private EditText valorDiarioenergetico,valorDiarioproteina,valorDiariofibra,
            valorDiariosodio,valorDiariogordura,valorDiariocarboidrato;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_cor);

        editCor = findViewById(R.id.edit_cor);
        energetico = findViewById(R.id.edit_energetico);
        fibra = findViewById(R.id.edit_fibra);
        proteina = findViewById(R.id.edit_proteina);
        carboidrato = findViewById(R.id.edit_carboidrato);
        gordura = findViewById(R.id.edit_gorduras);
        sodio = findViewById(R.id.edit_sodio);
        valorDiarioenergetico = findViewById(R.id.edit_energetico);
        valorDiarioproteina = findViewById(R.id.edit_proteina);
        valorDiariofibra = findViewById(R.id.edit_fibra);
        valorDiariosodio = findViewById(R.id.edit_sodio);
        valorDiariogordura = findViewById(R.id.edit_gorduras);
        valorDiariocarboidrato = findViewById(R.id.edit_carboidrato);
    }

    public void inserirCor(View v) {
        CorBD corBD = new CorBD(v.getContext());
        SQLiteDatabase bd = corBD.getWritableDatabase();
        try {
            String corDigitada = editCor.getText().toString();
            String corenergetico = energetico.getText().toString();
            String corfibra = fibra.getText().toString();
            String corproteina = proteina.getText().toString();
            String corcarboidrato = carboidrato.getText().toString();
            String corgordura = gordura.getText().toString();
            String corsodio = sodio.getText().toString();
            String valorcorenergetico = valorDiarioenergetico.getText().toString();
            String valorcorfibra = valorDiariofibra.getText().toString();
            String valorcorproteina = valorDiarioproteina.getText().toString();
            String valorcorcarboidrato = valorDiariocarboidrato.getText().toString();
            String valorcorgordura = valorDiariogordura.getText().toString();
            String valorcorsodio = valorDiariosodio.getText().toString();
            if(corDigitada.length() > 0) {
                ContentValues valores = new ContentValues();
                valores.put(CorBD.TabCor.COL_NOME, corDigitada);
                valores.put(CorBD.TabCor.COL_VALOR_ENERGETICO, corenergetico);
                valores.put(CorBD.TabCor.COL_CARBIODRATO, corcarboidrato);
                valores.put(CorBD.TabCor.COL_PROTEINA, corproteina);
                valores.put(CorBD.TabCor.COL_GORDURATOTAIS, corgordura);
                valores.put(CorBD.TabCor.COL_SODIO, corsodio);
                valores.put(CorBD.TabCor.COL_FIBRA, corfibra);
                valores.put(CorBD.TabCor.COL_VALOR_DIARIO_VALOR_ENERGETICO, valorcorenergetico);
                valores.put(CorBD.TabCor.COL_VALOR_DIARIO_CARBIODRATO, valorcorcarboidrato);
                valores.put(CorBD.TabCor.COL_VALOR_DIARIO_PROTEINA, valorcorproteina);
                valores.put(CorBD.TabCor.COL_VALOR_DIARIO_GORDURATOTAIS, valorcorgordura);
                valores.put(CorBD.TabCor.COL_VALOR_DIARIO_SODIO, valorcorsodio);
                valores.put(CorBD.TabCor.COL_VALOR_DIARIO_FIBRA, valorcorfibra);

                long idCor = bd.insert(CorBD.TabCor.TABELA, null, valores);
                if(idCor > 0) {
                    Toast.makeText(this, "Cor inserida.", Toast.LENGTH_SHORT).show();
                    editCor.setText("");
                } else {
                    Toast.makeText(this, "Cor não inserida. Erro BD.", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, "Erro! Detalhes: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        } finally {
            bd.close();
        }

    }

}
