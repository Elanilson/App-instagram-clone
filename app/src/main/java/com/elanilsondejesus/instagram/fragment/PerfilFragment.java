package com.elanilsondejesus.instagram.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.elanilsondejesus.instagram.R;
import com.elanilsondejesus.instagram.activity.EditarPerfilActivity;
import com.elanilsondejesus.instagram.activity.PerfilAmigoActivity;
import com.elanilsondejesus.instagram.adapter.AdapterGrid;
import com.elanilsondejesus.instagram.helper.ConfiguracaoFirebase;
import com.elanilsondejesus.instagram.helper.UsuarioFirebase;
import com.elanilsondejesus.instagram.model.Postagem;
import com.elanilsondejesus.instagram.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {
    private ProgressBar progressBar;
    private CircleImageView imagemPerfil;
    public GridView gridViewPerfil;
    private TextView txtPublicacoes, txtSeguidores, txtSeguindo;
    private Button buttonEditarPerfil;
    private Usuario usuarioLogado;
    private DatabaseReference usuarioRef;
    private DatabaseReference usuarioLogadoRef;
    private ValueEventListener valueEventListenerPerfil;
    private DatabaseReference firebaseRef;
    private DatabaseReference postagemUsuarioRef;
    private AdapterGrid adapterGrid;


    public PerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        //Configurações iniciais
        usuarioLogado = UsuarioFirebase.getDadosUsuarioLogado();
        firebaseRef = ConfiguracaoFirebase.getFirebase();
        usuarioRef = firebaseRef.child("usuarios");

        //Configurar referencia postagens usuario
        postagemUsuarioRef = ConfiguracaoFirebase.getFirebase()
                .child("postagens")
                .child(usuarioLogado.getId());

        //Configurações dos componentes
        inicializarcomponentes(view);


        //abrir edição de perfil
        buttonEditarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), EditarPerfilActivity.class);
                startActivity(i);
            }
        });

        //inicializa imagem load
        inicializarImagemLoad();
        //carregar as fotos  das  postagens de um usario
        carrregarPostagens();


        return view;
    }

    public void carrregarPostagens() {

        //Recupera as fotos postadas pelo usuario
        postagemUsuarioRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Configurar o tamanho do grid
                int tamanhoGrid = getResources().getDisplayMetrics().widthPixels;
                int tamanhoImagem = tamanhoGrid / 3;
                gridViewPerfil.setColumnWidth(tamanhoImagem);

                List<String> urlFotos = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Postagem postagem = ds.getValue(Postagem.class);
                    urlFotos.add(postagem.getCaminhoFoto());
                    //Log.i("postagem", "url:" + postagem.getCaminhoFoto() );
                }

                //Configurar adapter
                adapterGrid = new AdapterGrid(getActivity(), R.layout.grid_postagem, urlFotos);
                gridViewPerfil.setAdapter(adapterGrid);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void inicializarImagemLoad() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(getActivity())
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(100)
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
                .build();
        ImageLoader.getInstance().init(config);

    }

    private void inicializarcomponentes(View view) {
        gridViewPerfil = view.findViewById(R.id.grisViewPerfil);
        progressBar = view.findViewById(R.id.progressBarPerfil);
        imagemPerfil = view.findViewById(R.id.imageEditarPerfil);
        txtPublicacoes = view.findViewById(R.id.textPublicacoes);
        txtSeguidores = view.findViewById(R.id.textSeguidores);
        txtSeguindo = view.findViewById(R.id.textSeguindo);
        buttonEditarPerfil = view.findViewById(R.id.buttonAcaoPerfil);

    }

    public void recuperarDadosDoUsuarioLogado() {
        usuarioLogadoRef = usuarioRef.child(usuarioLogado.getId());
        valueEventListenerPerfil = usuarioLogadoRef.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Usuario usuario = dataSnapshot.getValue(Usuario.class);

                        String postagens = String.valueOf(usuario.getPostagens());
                        String seguindo = String.valueOf(usuario.getSeguindo());
                        String seguidores = String.valueOf(usuario.getSeguidores());

                        //Configura valores recuperados
                        txtPublicacoes.setText(postagens);
                        txtSeguidores.setText(seguidores);
                        txtSeguindo.setText(seguindo);


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
        );


    }

    private void recuperarFotoUsuario() {

        usuarioLogado = UsuarioFirebase.getDadosUsuarioLogado();

        //Recuperar foto do usuário
        String caminhoFoto = usuarioLogado.getCaminhoFoto();
        if (caminhoFoto != null) {
            Uri url = Uri.parse(caminhoFoto);
            Glide.with(getActivity())
                    .load(url)
                    .into(imagemPerfil);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        //Recuperar dados do usuario logado
        recuperarDadosDoUsuarioLogado();

        //Recuperar foto usuário
        recuperarFotoUsuario();

    }

    @Override
    public void onStop() {
        super.onStop();
        usuarioLogadoRef.removeEventListener(valueEventListenerPerfil);
    }
}
