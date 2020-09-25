package com.elanilsondejesus.instagram.alteracao.activity;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.elanilsondejesus.instagram.R;
import com.elanilsondejesus.instagram.alteracao.helper.DataUtil;
import com.elanilsondejesus.instagram.alteracao.helper.SalvandoIMc;
import com.elanilsondejesus.instagram.alteracao.helper.SalvandoMeta;
import com.elanilsondejesus.instagram.alteracao.model.Postagem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tela3Fragment extends Fragment {
    RecyclerView recyclerViewPostagem;
    List<Postagem> postagens = new ArrayList<>();
    TextView meta, metaClick;
    private ImageButton imageButton, imageButtonCadastro, imageButtonImc,imageButtonView
            ,buttonagua,buttonCadastroAlimentos;
    private ProgressBar progressBarmeta;
    private  TextView diasResta,pesoInicial,pesoatual,metav,pesorestante;
    private SalvandoMeta salvandoMeta;
    private SalvandoIMc salvandoIMc;
    private DataUtil dataUtil;
    int i=0;
    private ProgressBar progressBar,progressBar2;
    private  int processo=0;

    public Tela3Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_tela3, container, false);
        salvandoMeta = new SalvandoMeta(getActivity());
        salvandoIMc = new SalvandoIMc(getActivity());
        dataUtil = new DataUtil();
        imageButton = view.findViewById(R.id.button1Tess);
        imageButtonCadastro = view.findViewById(R.id.imagembtncadastro);
        imageButtonImc = view.findViewById(R.id.imageButtonImc);
        imageButtonView = view.findViewById(R.id.imageButtonView);
        meta = view.findViewById(R.id.metadepeso);
        metaClick = view.findViewById(R.id.textViewMeteClick);
        buttonagua = view.findViewById(R.id.imageButtonAgua);
        buttonCadastroAlimentos = view.findViewById(R.id.button1Tess);


        diasResta =view.findViewById(R.id.diasRestante);
        pesorestante =view.findViewById(R.id.pesoRestante);
        pesoInicial =view.findViewById(R.id.pesoinicialMeta);
        pesoatual =view.findViewById(R.id.pesoatualMeta);
        metav =view.findViewById(R.id.metadepeso);
        progressBar =view.findViewById(R.id.progresoCircularprimary);
        progressBar2 =view.findViewById(R.id.progressBar3);





carrergarcomp();






        metaClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MetaActivity.class));
            }
        });
        meta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MetaActivity.class));
            }
        });


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        imageButtonCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getContext(), MainCadastroActivity.class));
            }
        });

        imageButtonImc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CalculoImc.class));
            }
        });
        imageButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MetaActivity.class));
            }
        });
        buttonagua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Notificacao.class));
            }
        });
        buttonCadastroAlimentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Alimentos.class));
            }
        });


   return view;
    }
public void carrergarcomp(){
    String data = salvandoMeta.carregarData();
    String mta = salvandoMeta.carregarMeta();
    String pIn = salvandoMeta.carregarPesoInicial();
    String pesoAltual = salvandoIMc.carregarpeso();
    if(!mta.equals("")){
        onResume();

double inicalp = Double.parseDouble(pIn);
double metap = Double.parseDouble(mta);
double patual = Double.parseDouble(pesoAltual);
        String pesoiniFormatado = String.format("%2.1f", inicalp);
        pesoInicial.setText(pesoiniFormatado+" Kg");
        String pesomtaFormatado = String.format("%2.1f", metap);
        metav.setText(pesomtaFormatado+" Kg");
        String pesopatualaFormatado = String.format("%2.1f", patual);
        pesoatual.setText(pesopatualaFormatado+" Kg");
        String diasRestantes = DataUtil.dataAtual(data);
        diasResta.setText(diasRestantes+" dias");

        Double metap1 =Double.parseDouble(mta);
        Double pesoAtual1 =Double.parseDouble(pesoAltual);
        double pesofaltante = metap1-pesoAtual1;
        String pesorestFormatado = String.format("%2.1f", pesofaltante);
        pesorestante.setText(pesorestFormatado+" Kg");
        String test = pesorestFormatado.replaceAll("\\D+","");

        if(pesorestFormatado.equals("0,0")){
            progressBar.setProgress(100);
            progressBar2.setVisibility(View.GONE);

        }

    }
}
public void init(){

}

    @Override
    public void onStart() {
        super.onStart();
        carrergarcomp();
    }
}
