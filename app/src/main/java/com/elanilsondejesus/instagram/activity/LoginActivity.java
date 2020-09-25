package com.elanilsondejesus.instagram.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.elanilsondejesus.instagram.R;
import com.elanilsondejesus.instagram.helper.ConfiguracaoFirebase;
import com.elanilsondejesus.instagram.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText campoEmail, campoSenha;
    private Button botaoEntrar;
    private ProgressBar progressBar;

    private Usuario usuario;

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        verificarUsuarioLogado();
        inicializarComponentes();

        //Fazer login do usuario
        progressBar.setVisibility( View.GONE );
        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoEmail = campoEmail.getText().toString();
                String textosenha = campoSenha.getText().toString();

                if( !textoEmail.isEmpty() ){
                    if( !textosenha.isEmpty() ){

                        usuario = new Usuario();
                        usuario.setEmail( textoEmail );
                        usuario.setSenha( textosenha );
                        validarLogin( usuario );

                    }else{
//                        Toast.makeText(LoginActivity.this,
//                                "Preencha a senha!",
//                                Toast.LENGTH_SHORT).show();
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
                }else{
//                    Toast.makeText(LoginActivity.this,
//                            "Preencha o e-mail!",
//                            Toast.LENGTH_SHORT).show();
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

            }
        });

    }

    public void verificarUsuarioLogado(){
        autenticacao = ConfiguracaoFirebase.getRefenciaAutenticacao();
        if( autenticacao.getCurrentUser() != null ){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }

    public void validarLogin( Usuario usuario ){

        progressBar.setVisibility( View.VISIBLE );
        autenticacao = ConfiguracaoFirebase.getRefenciaAutenticacao();

        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if ( task.isSuccessful() ){
                    progressBar.setVisibility( View.GONE );
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }else {
//                    Toast.makeText(LoginActivity.this,
//                            "Erro ao fazer login",
//                            Toast.LENGTH_SHORT).show();
//                    progressBar.setVisibility( View.GONE );
                    Toast toast = Toast.makeText(getApplicationContext(), " Erro ao fazer login", Toast.LENGTH_LONG);
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

            }
        });


    }

    public void abrirCadastro(View view){
        Intent i = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity( i );
    }

    public void inicializarComponentes(){

        campoEmail   = findViewById(R.id.loginEmail);
        campoSenha   = findViewById(R.id.loginSenha);
        botaoEntrar  = findViewById(R.id.bottonLogin);
        progressBar  = findViewById(R.id.progresslogin);

        campoEmail.requestFocus();


    }

}