package com.elanilsondejesus.instagram.fragment;


import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.elanilsondejesus.instagram.R;
import com.elanilsondejesus.instagram.activity.FiltroActivity;
import com.elanilsondejesus.instagram.helper.Permissao;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostagemFragment extends Fragment {
    private Button abrirGaleria,abrirCamera;
    private static  final int SELECAO_CAMERA=100;
    private static  final int SELECAO_SELECAO_GALERIA=200;

    private  String[] permissoesNescessarias = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };


    public PostagemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_postagem, container, false);

        abrirCamera = view.findViewById(R.id.abrirCamera);
        abrirGaleria = view.findViewById(R.id.abrirGaleria);

        //validar permissoes
        Permissao.validarPermissoes(permissoesNescessarias,getActivity(),1);

        //adiconando evento de click  no butao camera
        abrirCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(intent.resolveActivity(getActivity().getPackageManager())!=null){

                    startActivityForResult(intent,SELECAO_CAMERA);


                }
            }
        });
        //adionar evento  de clique  no botao da galeria
        abrirGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {

                    startActivityForResult(intent, SELECAO_SELECAO_GALERIA);

                }
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==getActivity().RESULT_OK){
            Bitmap imagem= null;

            try{
                //validar tipo de selecao de imagem

                switch (requestCode){
                    case SELECAO_CAMERA:
                        imagem =(Bitmap) data.getExtras().get("data");

                        break;
                    case SELECAO_SELECAO_GALERIA:
                        Uri localImagemSelecionada = data.getData();
                        imagem= MediaStore.Images.Media.getBitmap(getActivity()
                                .getContentResolver(),localImagemSelecionada);

                        break;

                }

                //valida imagem escolhida
                if(imagem!= null){
                    //converter imagem em    byte array
                    ByteArrayOutputStream baos  = new ByteArrayOutputStream();
                    imagem.compress(Bitmap.CompressFormat.JPEG,70,baos);
                    byte[]dadosImagens = baos.toByteArray();

                    //enviar  imagem  escolhida para aplicacao de filtro
                    Intent intent = new Intent(getActivity(), FiltroActivity.class);
                    intent.putExtra("fotoEscolhida",dadosImagens);
                    startActivity(intent);

                }
            }catch (Exception e){
                e.printStackTrace();

            }
        }
    }
}
