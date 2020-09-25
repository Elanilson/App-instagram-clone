package com.elanilsondejesus.instagram.alteracao.activity;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.elanilsondejesus.instagram.R;
import com.elanilsondejesus.instagram.alteracao.helper.PersistenciaDeDados;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tela2Fragment extends Fragment {
    Button btnSalvar;
    Button btnBeberAgua;
    TextView txtTotal;
    Spinner spinner;
    int tempoSelecionado;
    long tempo, total;
    int totalHoje, dia, diaAtual;
    PersistenciaDeDados dados;
    Notificacao notificacao = new Notificacao();


    public Tela2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dados = new PersistenciaDeDados(getContext());
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_tela2, container, false);
            btnBeberAgua = view.findViewById(R.id.btnBeber);
            btnBeberAgua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    startActivity(new Intent(getContext(), Notificacao.class));

                }
            });

       return view;
    }
    public void carregar(View view){
        startActivity(new Intent(getContext(),Notificacao.class));

    }

}
