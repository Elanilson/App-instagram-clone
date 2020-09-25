package com.elanilsondejesus.instagram.alteracao.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.elanilsondejesus.instagram.R;
import com.elanilsondejesus.instagram.alteracao.helper.SalvandoIMc;

public class CalculoImc extends AppCompatActivity {

    private EditText imcPeso,imcAltura,imcIdade;
    private TextView taxaMetabolicaBasal,manterPeso,
            perdadePesoLeve,perdadePesoRapido,ganhadePesoLeve,
    pesoSaudavel,kgPesoIdeal,kgSobrePeso,kgPesoSaudavel,
    txtResultadoAtividade,maiorPeso,maiorImc,porcentoGorudra,kgGordura;
    private ImageButton relaxado,leve,moderado,intenso,masculinoImc,femininoImc;
    private Button salvar;
    private double pesoImc=0.0 ;
    private double alturaImc=0.0 ;
    private int idadeImc =0;
    private double resultadoImc =0.0;
    private  String status="";
    private  String sexo="";
    private  String atividade="";
    private ProgressBar progressBar;

    private Tela3Fragment tela3Fragment = new Tela3Fragment();

private SalvandoIMc  salvandoIMc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo_imc);
        iniciarComponentes();
        salvandoIMc = new SalvandoIMc(getApplicationContext());


        String mPeso = salvandoIMc.carregarmaiorPeso();
        String mImc = salvandoIMc.carregarmaiorImc();
        if(!mPeso.equals("")){
            maiorImc.setText(mImc);
            maiorPeso.setText(mPeso);



        }


        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idt =imcIdade.getText().toString();
                String alt =imcAltura.getText().toString();
                String pes =imcPeso.getText().toString();

                if(idt.equals("") || alt.equals("")|| pes.equals("")){
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
                    idadeImc = Integer.parseInt(idt);
                    alturaImc = Double.parseDouble(alt);
                    pesoImc = Double.parseDouble(pes);


                    imc();
                    taxaBasal();
                    emagrecer();
                    perdePesoRapido();
                    ganhaPeso();
                    manterPeso();

                }

            }
        });


        leve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leve.setBackgroundResource(R.color.roxoEscuro);
                relaxado.setBackgroundResource(R.drawable.background_botao_perfil);
                moderado.setBackgroundResource(R.drawable.background_botao_perfil);
                intenso.setBackgroundResource(R.drawable.background_botao_perfil);
                txtResultadoAtividade.setText("leve");
                atividade="leve";
            }
        });
        relaxado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relaxado.setBackgroundResource(R.color.roxoEscuro);
                leve.setBackgroundResource(R.drawable.background_botao_perfil);
                moderado.setBackgroundResource(R.drawable.background_botao_perfil);
                intenso.setBackgroundResource(R.drawable.background_botao_perfil);
                txtResultadoAtividade.setText("Relaxado");
                atividade="Relaxado";
            }
        });
        moderado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moderado.setBackgroundResource(R.color.roxoEscuro);
                leve.setBackgroundResource(R.drawable.background_botao_perfil);
                relaxado.setBackgroundResource(R.drawable.background_botao_perfil);
                intenso.setBackgroundResource(R.drawable.background_botao_perfil);
                txtResultadoAtividade.setText("Moderado");
                atividade="Moderado";

            }
        });
        intenso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               intenso.setBackgroundResource(R.color.roxoEscuro);
                leve.setBackgroundResource(R.drawable.background_botao_perfil);
                relaxado.setBackgroundResource(R.drawable.background_botao_perfil);
                moderado.setBackgroundResource(R.drawable.background_botao_perfil);
                txtResultadoAtividade.setText("Intenso");
                atividade="Intenso";

            }
        });
        masculinoImc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                masculinoImc.setBackgroundResource(R.color.roxoEscuro);
                femininoImc.setBackgroundResource(R.drawable.background_botao_perfil);
                sexo="M";
                taxaBasal();

            }
        });
        femininoImc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                femininoImc.setBackgroundResource(R.color.roxoEscuro);
                masculinoImc.setBackgroundResource(R.drawable.background_botao_perfil);
                sexo="F";
                taxaBasal();

            }
        });


    }// fim



    public void taxaBasal(){
        //cotem um bug resolver dopois
        switch (sexo){
            case "M":
                if(pesoImc!=0 && alturaImc!=0 && idadeImc!=0){
                    double TMBMasculina = 66 + (13.8 * pesoImc) + (5 * alturaImc) - (6.8 * idadeImc);
                    String taxaFormatado = String.format("%2.1f", TMBMasculina);
                    taxaMetabolicaBasal.setText(taxaFormatado+" Kacl");
                }

                break;
            case "F":
                if(pesoImc!=0 && alturaImc!=0 && idadeImc!=0) {

                    double TMBFeminina = 655 + (9.6 * pesoImc) + (1.8 * alturaImc) - (4.7 * idadeImc);
                    String taxaForma = String.format("%2.1f", TMBFeminina);
                    taxaMetabolicaBasal.setText(taxaForma + " Kacl");
                }
       break;
        }

    }
    public void imc(){
        double taxaImc= pesoImc/(alturaImc*alturaImc);
        double taxaMetabolicaBasal=0;
        double imcMaior=0;
        if(imcMaior < taxaImc){
            imcMaior=taxaImc;
        }
        String imcFormatado = String.format("%2.1f", imcMaior);

        maiorImc.setText(imcFormatado);
        resultadoImc=taxaImc;
        if(imcFormatado.equals("")){
            Toast.makeText(getApplicationContext(),
                    "Preencha o campo",Toast.LENGTH_LONG).show();

        }else {

            salvandoIMc.salvarMaiorImc(imcFormatado);

            Toast.makeText(getApplicationContext(),
                    "Salvo com sucesso",Toast.LENGTH_LONG).show();
        }

pesoIdeal();
pesoSaudavel();
pesoMaior();
porcentoGorudra();
    }
    public void porcentoGorudra(){
        if(sexo.equals("M")){
            int s=1;
       double img = (1.2 * resultadoImc) - (10.8 * s) + (0.23 * idadeImc) - 5.4* 1000.1/ (25.5*25.5);
            String imgFormatado = String.format("%2.1f", img);
       porcentoGorudra.setText(imgFormatado);
        double pesoGordo = pesoImc*img/1000;
            String pesoGordoFormatado = String.format("%2.1f", pesoGordo);
            kgGordura.setText(pesoGordoFormatado);
        }else{
            int s=0;
       double img = (1.2 * resultadoImc) - (10.8 * s) + (0.23 * idadeImc) - 5.4* 1000.1/ (25.5*25.5);
            String imgFormatado = String.format("%2.1f", img);
            porcentoGorudra.setText(imgFormatado);
        double pesoGordo = pesoImc*img/1000;
            String pesoGordoFormatado = String.format("%2.1f", pesoGordo);
            kgGordura.setText(pesoGordoFormatado);
        }

    }


    public void pesoMaior(){
        double pesoMaior=0;
        if(pesoMaior < pesoImc){
            pesoMaior=pesoImc;
        }
        String pesomaiorFormatado = String.format("%2.1f", pesoMaior);
        maiorPeso.setText(pesomaiorFormatado);



        if(pesomaiorFormatado.equals("")){
            Toast.makeText(getApplicationContext(),
                    "Preencha o campo",Toast.LENGTH_LONG).show();

        }else {
                String peSo = String.valueOf(pesoImc);
            salvandoIMc.salvarmaiorPeso(pesomaiorFormatado);
            salvandoIMc.salvarpeso(peSo);

            Toast.makeText(getApplicationContext(),
                    "Salvo com sucesso",Toast.LENGTH_LONG).show();
        }

    }
    public void emagrecer(){
        int index=25;
        double perda = index*pesoImc;
        perdadePesoLeve.setText(perda+" Kacl");

    }
    public void manterPeso(){
        int index=30;
        double manter=index*pesoImc;
        manterPeso.setText(manter+" Kacl");

   }
   public void perdePesoRapido(){
       int index=20;
       double perderapido=index*pesoImc;
       perdadePesoRapido.setText(perderapido+" Kacl");

    }
    public void ganhaPeso(){
        int index=35;
        double ganha=index*pesoImc;
        ganhadePesoLeve.setText(ganha+" Kacl");

    }

    public void pesoIdeal(){
        double pesoideal=0.0;
        if(sexo.equals("M")){
           pesoideal= ((alturaImc*100)-100 ) * 0.90;
           kgPesoIdeal.setText(pesoideal+" Kg");
        }else{
            pesoideal= ((alturaImc*100)-100 ) * 0.85;
            kgPesoIdeal.setText(pesoideal+" Kg");
        }

    }
    public void pesoSaudavel(){
        if(sexo.equals("F")) {
            if (alturaImc <= 1.50) {
                kgPesoSaudavel.setText("45-52 Kg");
            }else if (alturaImc > 1.50  && alturaImc <= 1.52) {
                kgPesoSaudavel.setText("44-46 Kg");
            } else if (alturaImc > 1.52 && alturaImc <= 1.55) {
                kgPesoSaudavel.setText("47-48 Kg");
            } else if (alturaImc > 1.55 && alturaImc <= 1.57) {
                kgPesoSaudavel.setText("48-51 Kg");
            } else if (alturaImc > 1.57 && alturaImc <= 1.60) {
                kgPesoSaudavel.setText("49-53 Kg");
            } else if (alturaImc > 1.60 && alturaImc <= 1.63) {
                kgPesoSaudavel.setText("51-56 Kg");
            } else if (alturaImc > 1.63 && alturaImc <= 1.65) {
                kgPesoSaudavel.setText("52-59 Kg");
            } else if (alturaImc > 1.65 && alturaImc <= 1.68) {
                kgPesoSaudavel.setText("53-62 Kg");
            } else if (alturaImc > 1.68 && alturaImc <= 1.70) {
                kgPesoSaudavel.setText("55-64 Kg");
            } else if (alturaImc > 1.70 && alturaImc <= 1.73) {
                kgPesoSaudavel.setText("56-67 Kg");
            } else if (alturaImc > 1.73 && alturaImc <= 1.75) {
                kgPesoSaudavel.setText("58-69 Kg");
            } else if (alturaImc > 1.75 && alturaImc <= 1.78) {
                kgPesoSaudavel.setText("60-76 Kg");
            } else if (alturaImc > 1.78 && alturaImc <= 1.80) {
                kgPesoSaudavel.setText("61-77 Kg");
            } else if (alturaImc > 1.80 && alturaImc <= 1.83) {
                kgPesoSaudavel.setText("63-80 Kg Kg");
            } else if (alturaImc > 1.83 && alturaImc <= 1.85) {
                kgPesoSaudavel.setText("65-82 Kg");
            }
        }else{
            if (alturaImc <= 1.57) {
                kgPesoSaudavel.setText("52-55 Kg");
            } else if (alturaImc > 1.57 && alturaImc <= 1.60) {
                kgPesoSaudavel.setText("54-58 Kg");
            } else if (alturaImc > 1.60 && alturaImc <= 1.63) {
                kgPesoSaudavel.setText("57-62 Kg");
            } else if (alturaImc > 1.63 && alturaImc <= 1.65) {
                kgPesoSaudavel.setText("59-66 Kg");
            } else if ( alturaImc > 1.65 && alturaImc <= 1.68) {
                kgPesoSaudavel.setText("62-69 Kg");
            } else if (alturaImc > 1.68 && alturaImc <= 1.70) {
                kgPesoSaudavel.setText("63-73 Kg");
            } else if (alturaImc > 1.70 && alturaImc <= 1.73) {
                kgPesoSaudavel.setText("66-77 Kg");
            } else if (alturaImc > 1.73 && alturaImc <= 1.75) {
                kgPesoSaudavel.setText("68-80 Kg");
            } else if (alturaImc > 1.75 && alturaImc <= 1.78) {
                kgPesoSaudavel.setText("70-84 Kg");
            } else if (alturaImc > 1.78&& alturaImc <= 1.80) {
                kgPesoSaudavel.setText("72-85 Kg");
            } else if (alturaImc > 1.80 && alturaImc <= 1.83) {
                kgPesoSaudavel.setText("75-91 Kg");
            } else if (alturaImc > 1.83 && alturaImc <= 1.85) {
                kgPesoSaudavel.setText("77-95 Kg");
            } else if (alturaImc > 1.85 && alturaImc <= 1.88) {
                kgPesoSaudavel.setText("79-98 Kg");
            } else if (alturaImc > 1.88 && alturaImc <= 1.91) {
                kgPesoSaudavel.setText("82-102 Kg");
            } else if (alturaImc > 1.91 && alturaImc >= 1.93) {
                kgPesoSaudavel.setText("84-106 Kg");
            }

        }
    }



    public void iniciarComponentes(){
        imcPeso = findViewById(R.id.editTextPesoImc);
        imcAltura = findViewById(R.id.editTextAlturaImc);
        imcIdade = findViewById(R.id.editTextIdadeImc);
        taxaMetabolicaBasal = findViewById(R.id.textViewTaxaMetabolicaBasal);
        manterPeso = findViewById(R.id.textViewManterPeso);
        perdadePesoLeve = findViewById(R.id.textViewPesoLeve);
        perdadePesoRapido = findViewById(R.id.textViewPerdadePesoRapida);
        ganhadePesoLeve = findViewById(R.id.textViewGanhodePesoLeve);
        //pesoSaudavel = findViewById(R.id.textViewPesoIdeal);
        pesoSaudavel = findViewById(R.id.textViewPesoSaudavel);
        kgPesoIdeal = findViewById(R.id.textViewKgPesoIdeal);
        kgSobrePeso = findViewById(R.id.textViewKGSobrePeso);
        kgPesoSaudavel = findViewById(R.id.textViewKgPesoSaudavel);
        txtResultadoAtividade = findViewById(R.id.textViewResultadoAtividade);
        maiorPeso = findViewById(R.id.textViewMaiorPeso);
        maiorImc = findViewById(R.id.textViewMaiorImc);
        porcentoGorudra = findViewById(R.id.textViewPorcentGordura);
        kgGordura = findViewById(R.id.textViewKgGordura);
        relaxado = findViewById(R.id.imageButtonRelaxado);
        leve = findViewById(R.id.imageButtonLeve);
        intenso = findViewById(R.id.imageButtonIntenso);
        moderado = findViewById(R.id.imageButtonModerado);
        masculinoImc = findViewById(R.id.imageButtonMasculino);
        femininoImc = findViewById(R.id.imageButtonFemino);
        salvar = findViewById(R.id.buttonSalvarCalculoImc);
        progressBar = findViewById(R.id.progresoCircularprimary);
    }
}
