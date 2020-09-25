package com.elanilsondejesus.instagram.alteracao.activity;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.elanilsondejesus.instagram.R;
import com.elanilsondejesus.instagram.alteracao.helper.PersistenciaDeDados;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tela1Fragment extends Fragment {
    RadioButton masculino,feminino,leve,moderada,intensa;
    RadioGroup opcaoSexo,opcaoAtividade;
    TextView textStatus, idade,nome,nomeusuario,textImc,textTaxa;
    SeekBar seekBarIdade;
    EditText peso, altura;
    Button calcular;
    double taxaMetabolicaBasal=0.0;
    int idadeAtual=0;
    double pesoAtual=0.0;
    String sexo="";
    String atividade,status;
    private Notificacao notificacao;
    private PersistenciaDeDados dados;

    public Tela1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_tela1, container, false);

        masculino = view.findViewById(R.id.idMasculino);
        feminino= view.findViewById(R.id.idFeminino);
        leve= view.findViewById(R.id.idLeve);
        moderada= view.findViewById(R.id.idModerada);
        intensa= view.findViewById(R.id.idIntensa);
        opcaoSexo= view.findViewById(R.id.opcaoSexo);
        opcaoAtividade= view.findViewById(R.id.opcaoAtividade);

        seekBarIdade = view.findViewById(R.id.seekBarIdade);
        idade = view.findViewById(R.id.textIdade);
        textImc = view.findViewById(R.id.textImc);
        calcular = view.findViewById(R.id.idCalcular);
        peso = view.findViewById(R.id.editPeso);
        altura = view.findViewById(R.id.editAltura);

        nomeusuario = view.findViewById(R.id.editnNomeUsuario);
        textStatus = view.findViewById(R.id.textStatus);
        textTaxa = view.findViewById(R.id.textTaxa);

            dados = new PersistenciaDeDados(getActivity());

            notificacao = new Notificacao();

        seekBarIdade.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                idade.setText("Idade: "+progress);
                if(idadeAtual!=progress) {
                    idadeAtual = progress;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        opcaoSexo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.idMasculino){
                    sexo="M";
                }else{
                    sexo="F";
                }
            }
        });
        opcaoAtividade.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.idLeve) {

                    atividade = "leve";
                } else if (checkedId == R.id.idModerada) {

                    atividade = "moderada";

                } else {

                    atividade = "intensa";

                }
            }
        });
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pesoatual =  peso.getText().toString();
                String estatura = altura.getText().toString();
                if(pesoatual.equals("")|| estatura.equals("")){
                    Toast.makeText(getActivity(),
                            "Preencha o campo",Toast.LENGTH_SHORT).show();

                }else {


                    double peso1 = Double.parseDouble(pesoatual);
                    double estatura2 = Double.parseDouble(estatura);
                    double imc = peso1 / (estatura2 * estatura2);
                    String s = String.format("%2.2f", imc);
                    textImc.setText(s);
                    pesoAtual=peso1;

                    if(imc <16){
                        status="Magreza grave";

                    }
                    else if(imc >=16 && imc <=17){
                        status="Magreza moderada";

                    }else if (imc >=17 && imc <=18.5){
                        status="Magreza leve";

                    }else if (imc >=18.5 && imc <=25){
                        status="Saudável";

                    }else if (imc >=25 && imc <=30){
                        status="Sobrepeso";

                    }else if (imc >=30 && imc <= 35){
                        status="Obesidade de grau I";

                    }else if (imc >=35 && imc <= 40){
                        status="Obesidade de grau II(Severa)";

                    }else if (imc >40){
                        status="Obesidade de grau I(Mórbida)";

                    }
                    textStatus.setText(status);

                }


                if(idadeAtual >= 18 && idadeAtual <=30){
                    if(sexo.equals("M")){
                        if(atividade.equals("leve")){
                            taxaMetabolicaBasal = 1.55*(15.3*pesoAtual+679);


                        }else if (atividade.equals("moderada")){
                            taxaMetabolicaBasal = 1.78*(15.3*pesoAtual+679);

                        }else{
                            taxaMetabolicaBasal = 2.10*(15.3*pesoAtual+679);

                        }

                    }else{
                        if(atividade.equals("leve")){
                            taxaMetabolicaBasal = 1.56*(15.3*pesoAtual+496);


                        }else if (atividade.equals("moderada")){
                            taxaMetabolicaBasal = 1.64*(15.3*pesoAtual+496);

                        }else{
                            taxaMetabolicaBasal = 1.82*(15.3*pesoAtual+496);

                        }

                    }



                }else if(idadeAtual >= 31 && idadeAtual <=60){
                    if(sexo.equals("M")){
                        if(atividade.equals("leve")){
                            taxaMetabolicaBasal = 1.55*(11.6 *pesoAtual+879);


                        }else if (atividade.equals("moderada")){
                            taxaMetabolicaBasal = 1.78*(11.6*pesoAtual+879);

                        }else{
                            taxaMetabolicaBasal = 2.10*(11.6*pesoAtual+879);

                        }

                    }else{
                        if(atividade.equals("leve")){
                            taxaMetabolicaBasal = 1.56*(8.7*pesoAtual+829 );


                        }else if (atividade.equals("moderada")){
                            taxaMetabolicaBasal = 1.64*(8.7*pesoAtual+829 );

                        }else{
                            taxaMetabolicaBasal = 1.82*(8.7*pesoAtual+829 );

                        }

                    }

                }else  if (idadeAtual>= 61){
                    if(sexo.equals("M")){
                        if(atividade.equals("leve")){
                            taxaMetabolicaBasal = 1.55*(13.5 *pesoAtual+ 487 );


                        }else if (atividade.equals("moderada")){
                            taxaMetabolicaBasal = 1.78*(13.5*pesoAtual+ 487 );

                        }else{
                            taxaMetabolicaBasal = 2.10*(13.5*pesoAtual+ 487 );

                        }

                    }else{
                        if(atividade.equals("leve")){
                            taxaMetabolicaBasal = 1.56*(10.5 *pesoAtual+ 596 );


                        }else if (atividade.equals("moderada")){
                            taxaMetabolicaBasal = 1.64*(10.5*pesoAtual+ 596 );

                        }else{
                            taxaMetabolicaBasal = 1.82*(10.5*pesoAtual+ 596 );

                        }

                    }

                }
                    textTaxa.setText(""+taxaMetabolicaBasal);
//                dados.salvar(String.valueOf(pesoAtual));
//                pesoAtual = Double.parseDouble(dados.carregar());
            }

        });


   return  view;
    }


    @Override
    public void onStart() {
        super.onStart();
//        pesoAtual = Double.parseDouble(dados.carregar());
//        peso.setText(""+pesoAtual);
//        notificacao.setPesoteste(""+pesoAtual);
    }
}
