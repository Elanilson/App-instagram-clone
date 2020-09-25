package com.elanilsondejesus.instagram.alteracao.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.elanilsondejesus.instagram.R;
import com.elanilsondejesus.instagram.alteracao.helper.DataUtil;
import com.elanilsondejesus.instagram.alteracao.helper.SalvandoMeta;

import java.util.Date;

public class MetaActivity extends AppCompatActivity {
    private ProgressBar progressBarCircular;
    private TextView pesoincial, meta, pesoatual, diasRestantes, pesoRestante, test;
    private EditText dataEs, metaEscolhida, pesoinicialEscolhido;
    DataUtil dataUtil = new DataUtil();
    private CalendarView calendarView;
    private Button buttondata,salvarMeta;
    private double metadePeso =0.0;
    private double pesoInicialDefinido =0.0;
    private SalvandoMeta salvandoMeta;
    private String dia ="";
    private String  peso ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meta);
        progressBarCircular = findViewById(R.id.progresoCircularprimary);
        meta = findViewById(R.id.metadepeso);
        pesoincial = findViewById(R.id.pesoinicialMeta);
        pesoatual = findViewById(R.id.pesoatualMeta);
        pesoRestante = findViewById(R.id.pesoRestante);
        diasRestantes = findViewById(R.id.diasRestante);
        dataEs = findViewById(R.id.editTextData);
        metaEscolhida = findViewById(R.id.editTextMeta);
        pesoinicialEscolhido = findViewById(R.id.editTextPesoInicial);

        salvarMeta = findViewById(R.id.salvarMeta);

        calendarView = findViewById(R.id.calendarView);
        salvandoMeta = new SalvandoMeta(getApplicationContext());


        String texto = salvandoMeta.carregarData();
        String mta = salvandoMeta.carregarMeta();
        String pIn = salvandoMeta.carregarPesoInicial();
        if(!texto.equals("")){
            dataEs.setText(texto);
            metaEscolhida.setText(mta);
            pesoinicialEscolhido.setText(pIn);


        }


        //Configura toolbar
        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("Definir meta");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);

//

//        dia = metaEscolhida.getText().toString();
//        peso = pesoinicialEscolhido.getText().toString();
//        metadePeso = Double.parseDouble(dia);
//        pesoInicialDefinido = Double.parseDouble(peso);


        salvarMeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tex = dataEs.getText().toString();
                String met = metaEscolhida.getText().toString();
                String pesoIn = pesoinicialEscolhido.getText().toString();

                if(tex.equals("") || met.equals("") || pesoIn.equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(), " Preencha todos os campos", Toast.LENGTH_LONG);
                    View toastView = toast.getView(); // This'll return the default View of the Toast.

                    /* And now you can get the TextView of the default View of the Toast. */
                    TextView toastMessage = (TextView) toastView.findViewById(android.R.id.message);
                    toastMessage.setTextSize(14);
                    toastMessage.setTextColor(getResources().getColor(R.color.white));
                    toastMessage.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_notificacao_24dp, 0, 0, 0);
                    toastMessage.setGravity(Gravity.CENTER);
                    toastMessage.setCompoundDrawablePadding(8);
                    toastView.setBackgroundColor(getResources().getColor(R.color.roxoEscuro));
                    toast.show();

                }else {

                    salvandoMeta.salvarData(tex);
                    salvandoMeta.salvarmeta(met);
                    salvandoMeta.salvarPesoInicial(pesoIn);
                    //salvandoMeta.salvarPeso(pesoIn);
                    dataEs.setText(tex);
                    Toast.makeText(getApplicationContext(),
                            "Salvo com sucesso",Toast.LENGTH_LONG).show();
                }

            }
        });
    }




    public void alertaCalendario() {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MetaActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.calendario_alert, null);
        //final EditText insiraPeso = (EditText) mView.findViewById(R.id.InsiraPeso);
        Button mLogin = (Button) mView.findViewById(R.id.buttonConfirmar);

        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();
        //configurar cancelamento
        dialog.setCancelable(false);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!dataEs.getText().toString().isEmpty()) {
                    long v = calendarView.getDate();
                    Toast.makeText(MetaActivity.this,
                            R.string.success_login_msg+""+v,
                            Toast.LENGTH_SHORT).show();


                    dialog.dismiss();
                } else {
                    Toast.makeText(MetaActivity.this,
                            R.string.error_login_msg,
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    @Override
    public boolean onSupportNavigateUp() {

        finish();
        return false;

    }

    @Override
    protected void onStart() {
        super.onStart();
        String datacarregada = salvandoMeta.carregarData();
        Toast.makeText(getApplicationContext(),datacarregada,Toast.LENGTH_LONG).show();
    }
}
