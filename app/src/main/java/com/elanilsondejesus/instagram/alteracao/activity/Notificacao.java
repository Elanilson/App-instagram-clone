package com.elanilsondejesus.instagram.alteracao.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.elanilsondejesus.instagram.R;
import com.elanilsondejesus.instagram.activity.CadastroActivity;
import com.elanilsondejesus.instagram.alteracao.helper.CopoouGarrafa;
import com.elanilsondejesus.instagram.alteracao.helper.PersistenciaDeDados;
import com.elanilsondejesus.instagram.receiver.NotificationReceiver;
import com.elanilsondejesus.instagram.receiver.SimpleBootReceiver;
import com.google.firebase.database.core.view.filter.NodeFilter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Notificacao extends AppCompatActivity {
    Button btnSalvar;
    Button btnBeberAgua, buttonTest;
    TextView txtTotal, resultadoAgua, test,tess;
    Spinner spinner;
    ProgressBar progresso, fg;
    ImageButton btn350, btn500;
    int tempoSelecionado;
    long tempo, total;
    int totalHoje, dia, diaAtual;
    int totalAgua;
    int pesoConvertidoEmLitro;
    EditText testecampo;
    Boolean resposta = false;
    String alertaPeso="false";
    String recup="0";
    int ui=0;
    String res="t";
    String gg;
    int progressoSalvo=0;

List<Integer> progressodaBarra = new ArrayList<>();

CopoouGarrafa copoouGarrafa;
    private PersistenciaDeDados dados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacao);
        spinner = findViewById(R.id.spinner);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnBeberAgua = findViewById(R.id.btnBeber);
        txtTotal = findViewById(R.id.txtQtdeTotal);
        btn350 = findViewById(R.id.copoML);
        btn500 = findViewById(R.id.garrafa500);
        progresso = findViewById(R.id.progressBarAgua);
        resultadoAgua = findViewById(R.id.textResultadoAgua);
        test = findViewById(R.id.idTeste);
        testecampo = findViewById(R.id.editnNomeUsuario);


        dados = new PersistenciaDeDados(getApplicationContext());
        copoouGarrafa = new CopoouGarrafa(getApplicationContext());


//carrergarconsumo();


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempoSelecionado = spinner.getSelectedItemPosition();
                getTempo();

                Calendar calendar = Calendar.getInstance();

                Intent intent = new Intent(getApplicationContext(), NotificationReceiver.class);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);


                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), tempo, pendingIntent);

                SharedPreferences.Editor editor = getEditor();
                editor.putLong(getString(R.string.lblIntervalo), tempo);
                editor.commit();


                ComponentName receiver = new ComponentName(getApplicationContext(), SimpleBootReceiver.class);
                PackageManager pm = getApplicationContext().getPackageManager();

                if (pm.getComponentEnabledSetting(receiver) != PackageManager.COMPONENT_ENABLED_STATE_ENABLED) {

                    pm.setComponentEnabledSetting(receiver,
                            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                            PackageManager.DONT_KILL_APP);
                }
            }
        });

        btnBeberAgua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                total += 1;
                totalHoje += 1;
                SharedPreferences.Editor editor = getEditor();
                editor.putInt(getString(R.string.dia_key), diaAtual);
                editor.putLong(getString(R.string.total_key), total);
                editor.putInt(getString(R.string.total_hoje_key), totalHoje);
                editor.commit();
                popularTela();
            }
        });

        btn350.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int  agua = 350;
                int rt=0;
                int xnxx=0;
                int numero=0;


              progressodaBarra.add(agua);
              for(Integer x: progressodaBarra){
                  rt += x;

              }
                String consumo = String.valueOf(rt);


                  progresso.setProgress(rt);


                resultadoAgua.setText(consumo + " /"+ pesoConvertidoEmLitro + " ML");
                    for(int i=0; i<=pesoConvertidoEmLitro;i++){
                        xnxx= i*350;
                        if(xnxx>=pesoConvertidoEmLitro){
                            numero=xnxx;
                            break;
                        }
                    }
                    test.setText(""+numero);
                    if(numero==rt) {



                        String titulo = "Parabens!";
                        String mensagem = "Você atingiu a quantidade de " +
                                "agua recomendada, Prentende continuar tomando água para se manter hidratado ?";

                        alerta(titulo, mensagem);

                    }

                total += 1;
                totalHoje += 1;
                SharedPreferences.Editor editor = getEditor();
                editor.putInt(getString(R.string.dia_key), diaAtual);
                editor.putLong(getString(R.string.total_key), total);
                editor.putInt(getString(R.string.total_hoje_key), totalHoje);
                editor.commit();
                popularTela();




            }
        });
        btn500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int  agua = 500;
                int rt=0;
                int xnxx=0;
                int numero=0;

                progressodaBarra.add(agua);
                for (Integer x : progressodaBarra) {
                    rt += x;


                }
                progresso.setProgress(rt);
                resultadoAgua.setText(rt + " /" + pesoConvertidoEmLitro + " ML");

                for (int i = 0; i <= pesoConvertidoEmLitro; i++) {
                    xnxx = i * 500;
                    if (xnxx >= pesoConvertidoEmLitro) {
                        numero = xnxx;
                        break;
                    }
                }
                test.setText("" + numero);

                if (numero == rt) {


                    String titulo = "Parabens,";
                    String mensagem = "Você atingiu a quantidade de " +
                            "agua recomendada, Prentende continuar tomando água para se manter hidratado ?";

                    alerta(titulo, mensagem);

                }
                String consumo = String.valueOf(rt);
                dados.salvarConsumoDeagua(consumo);
                total += 1;
                totalHoje += 1;
                SharedPreferences.Editor editor = getEditor();
                editor.putInt(getString(R.string.dia_key), diaAtual);
                editor.putLong(getString(R.string.total_key), total);
                editor.putInt(getString(R.string.total_hoje_key), totalHoje);
                editor.commit();
                popularTela();

            }


        });


    }


    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPref = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        total = sharedPref.getLong(getString(R.string.total_key), 0);
        totalHoje = sharedPref.getInt(getString(R.string.total_hoje_key), 0);
        dia = sharedPref.getInt(getString(R.string.dia_key), 1);
        Calendar calendar = Calendar.getInstance();
        diaAtual = calendar.get(Calendar.DATE);

        popularTela();
    }

    private void getTempo() {
        switch (tempoSelecionado) {
            case 0:
                tempo = TimeUnit.MINUTES.toMillis(1);
                return;
            case 1:
                tempo = (TimeUnit.MINUTES.toMillis(60));
                return;
            case 2:
                tempo = TimeUnit.MINUTES.toMillis(90);
                return;
            default:
                tempo = TimeUnit.MINUTES.toMillis(120);
                return;

        }
    }

    public SharedPreferences.Editor getEditor() {
        SharedPreferences sharedPref = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        return sharedPref.edit();
    }

    public void popularTela() {
        if ((diaAtual > dia) || ((dia >= 30) && (diaAtual == 1))) {
            totalHoje = 0;
            diaAtual = dia;
            totalAgua = 0;
            txtTotal.setText("Total: " + total + "\nTotal Hoje:" + totalHoje);
        } else {
            txtTotal.setText("Total: " + total + "\nTotal Hoje:" + totalHoje);
        }

    }




    public void alerta(String titulo, String mensagem) {
        //stancia alertaDialogo
        androidx.appcompat.app.AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        //Configurar o titulo e a mensagem
        dialogo.setTitle(titulo);
        dialogo.setMessage(mensagem);
        //configurar cancelamento
       //dialogo.setCancelable(false);
        //configurar icone
        dialogo.setIcon(android.R.drawable.stat_notify_voicemail);
        // configurações das ações
//        dialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//                resposta = true;
//            }
//
//        });
//        dialogo.setNegativeButton("Não", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });

        dialogo.create();
        dialogo.show();

    }
//    public void carrergarconsumo(){
//        String cons = dados.carregarConsumodeAgua();
//        if(!cons.equals("")){
//            recup=cons;
//            resultadoAgua.setText(cons + " /" + pesoConvertidoEmLitro + " ML");
//            Toast.makeText(getApplicationContext(),
//                    "consumo de agua recuperado"+cons,Toast.LENGTH_LONG).show();
//                progressoSalvo = Integer.parseInt(cons);
//            resultadoAgua.setText(cons + " /"+ pesoConvertidoEmLitro + " ML");
//            tess.setText("+"+cons);
//
//        }
//    }



    public String alertainsiraPeso() {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(Notificacao.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_insirapeso, null);
        final EditText insiraPeso = (EditText) mView.findViewById(R.id.InsiraPeso);
        Button mLogin = (Button) mView.findViewById(R.id.btnLogin);

        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();
        //configurar cancelamento
        dialog.setCancelable(false);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!insiraPeso.getText().toString().isEmpty()) {
                   res = insiraPeso.getText().toString();
                    Toast.makeText(Notificacao.this,
                            "",
                            Toast.LENGTH_SHORT).show();
                    dados.salvar(res);
                    //pesoConvertidoEmLitro=Double.parseDouble(alertaPeso);
                    dialog.dismiss();
                } else {
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
                }
                onRestart();
                onResume();
                onStart();
            }
        });
 return res;

    }
    public void car (String pesoRecebido ){

    pesoConvertidoEmLitro =Integer.parseInt(pesoRecebido);
    pesoConvertidoEmLitro =35*pesoConvertidoEmLitro;
    ui=pesoConvertidoEmLitro;


    //calculodeProgressoB(pesoConvertidoEmLitro);
            resultadoAgua.setText(totalAgua +"/"+pesoConvertidoEmLitro +" ML");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    @Override
    protected void onStart() {
        super.onStart();

//        carrergarconsumo();
        gg = dados.carregar();
        if(!gg.isEmpty()) {
            progresso.setMax(Integer.parseInt(gg) * 35);
        }
        if(gg.equals("")) {
            String df = alertainsiraPeso();
        }
        if(!gg.isEmpty()){
//            Toast.makeText(Notificacao.this,
//                    "Não tem conteudo conteudo: "+gg ,
//                    Toast.LENGTH_SHORT).show();
            car(gg);
        }



    }
}
