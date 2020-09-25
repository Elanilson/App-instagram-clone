package com.elanilsondejesus.instagram.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.elanilsondejesus.instagram.R;
import com.elanilsondejesus.instagram.alteracao.activity.Tela3Fragment;
import com.elanilsondejesus.instagram.fragment.FreedFragment;
import com.elanilsondejesus.instagram.fragment.PerfilFragment;
import com.elanilsondejesus.instagram.fragment.PesquisaFragment;
import com.elanilsondejesus.instagram.fragment.PostagemFragment;
import com.elanilsondejesus.instagram.helper.ConfiguracaoFirebase;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth autenticacao;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






        //configurara Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("More file");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);

        //configuções de objetos
        autenticacao = ConfiguracaoFirebase.getRefenciaAutenticacao();

        //configurar botton navigation
        configuraraBottonNavigation();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.viewPage,new FreedFragment()).commit();


    }

    //metodo responsavel por criar navigation
    private   void configuraraBottonNavigation(){
            BottomNavigationViewEx viewEx = findViewById(R.id.bottonNevegacao);
//



        //habilitar navegacao
        habilitarnavegacao(viewEx);
//            marcar um deteminado item
//        Menu menu = viewEx.getMenu();
//        MenuItem menuItem = menu.getItem(1);
//        menuItem.setChecked(true);


//



    }
    //METODO RESPONSAVEL POR TRATA ENVENTOS DE CLICKS
        private void habilitarnavegacao(BottomNavigationViewEx viewEx){
        viewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                switch (menuItem.getItemId()){

                    case R.id.ic_principal:
                        fragmentTransaction.replace(R.id.viewPage,new Tela3Fragment()).commit();
                        return  true;

                    case R.id.ic_home:
                        fragmentTransaction.replace(R.id.viewPage,new FreedFragment()).commit();
                        return  true;
                    case R.id.ic_pesquisa:
                        fragmentTransaction.replace(R.id.viewPage,new PesquisaFragment()).commit();
                        return  true;

                    case R.id.ic_postagem:
                        fragmentTransaction.replace(R.id.viewPage,new PostagemFragment()).commit();
                        return  true;

                    case R.id.ic_perfil:
                        fragmentTransaction.replace(R.id.viewPage,new PerfilFragment()).commit();
                        return  true;



                }

                return false;
            }
        });


        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater  = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_sair:
                    deslogarUsuario();
                    startActivity( new Intent(getApplicationContext(),LoginActivity.class));

                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private  void deslogarUsuario(){
        try{
            autenticacao.signOut();

        }catch (Exception e){
            e.printStackTrace();

        }
    }



}
