package com.elanilsondejesus.instagram.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.elanilsondejesus.instagram.R;
import com.elanilsondejesus.instagram.model.Postagem;
import com.elanilsondejesus.instagram.model.Usuario;

import de.hdodenhof.circleimageview.CircleImageView;

public class VizualizarPostagemActivity extends AppCompatActivity {

    private TextView textPerfilPostagem,textQtdCurtidasPostagem,
            textDescricaoPostagem,textVisualizarComentariosPostagem;
    private ImageView imagePostagemSelecionada;
    private CircleImageView imagePerfilPostagem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vizualizar_postagem);

        //Inicializar componentes
        inicializarComponentes();

        //Configura toolbar
        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("Visualizar postagem");
        setSupportActionBar( toolbar );

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);

        //Recupera dados da activity
        Bundle bundle = getIntent().getExtras();
        if( bundle != null ){

            Postagem postagem = (Postagem) bundle.getSerializable("postagem");
            Usuario usuario = (Usuario) bundle.getSerializable("usuario");

            //Exibe dados de usuário
            Uri uri = Uri.parse( usuario.getCaminhoFoto() );
            Glide.with(VizualizarPostagemActivity.this)
                    .load( uri )
                    .into( imagePerfilPostagem );
            textPerfilPostagem.setText( usuario.getNome() );

            //Exibe dados da postagem
            Uri uriPostagem = Uri.parse( postagem.getCaminhoFoto() );
            Glide.with(VizualizarPostagemActivity.this)
                    .load( uriPostagem )
                    .into( imagePostagemSelecionada );
            textDescricaoPostagem.setText( postagem.getDescricao() );

        }

    }

    private void inicializarComponentes(){
        textPerfilPostagem = findViewById(R.id.textNomePostagem);
        textQtdCurtidasPostagem = findViewById(R.id.textCurtidasPostagem);
        textDescricaoPostagem = findViewById(R.id.textVDescricaoPostagem);
        imagePostagemSelecionada = findViewById(R.id.imageFotoPostagemFeed);
        imagePerfilPostagem = findViewById(R.id.imagePerfilPostagemFeed);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }

}