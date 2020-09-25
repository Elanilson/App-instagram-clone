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
import com.elanilsondejesus.instagram.alteracao.activity.Notificacao;
import com.elanilsondejesus.instagram.alteracao.helper.PersistenciaDeDados;
import com.elanilsondejesus.instagram.helper.ConfiguracaoFirebase;
import com.elanilsondejesus.instagram.helper.UsuarioFirebase;
import com.elanilsondejesus.instagram.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastroActivity extends AppCompatActivity {
    private EditText campoNome, campoEmail, campoSenha, campoIdade, campoPeso, campoAltura;
    private Button botaoCadastrar;
    private ProgressBar progressBar;
    private PersistenciaDeDados dados;
    private Usuario usuario;

    private FirebaseAuth autenticacao;
    private Notificacao notificacao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        inicializarComponentes();


        dados = new PersistenciaDeDados(getApplicationContext());
//        pesoCadastro = Float.parseFloat(dados.carregar());



//        dados.salvar("150");
        //Cadastrar usuario


        progressBar.setVisibility(View.GONE);
        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoNome = campoNome.getText().toString();
                String textoEmail = campoEmail.getText().toString();
                String textosenha = campoSenha.getText().toString();
//                carregarInformacoes();

                if (!textoNome.isEmpty()) {
                    if (!textoEmail.isEmpty()) {
                        if (!textosenha.isEmpty()) {

                            usuario = new Usuario();
                            usuario.setNome(textoNome);
                            usuario.setEmail(textoEmail);
                            usuario.setSenha(textosenha);

                            cadastrar(usuario);


                        } else {
//                            Toast.makeText(CadastroActivity.this,
//                                    "Preencha a senha!",
//                                    Toast.LENGTH_SHORT).show();
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
                    } else {
//                        Toast.makeText(CadastroActivity.this,
//                                "Preencha o email!",
//                                Toast.LENGTH_SHORT).show();
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
                } else {
//                    Toast.makeText(CadastroActivity.this,
//                            "Preencha o nome!",
//                            Toast.LENGTH_SHORT).show();
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

    /**
     * Método responsável por cadastrar usuário com e-mail e senha
     * e fazer validações ao fazer o cadastro
     */
    public void cadastrar(final Usuario usuario) {

        progressBar.setVisibility(View.VISIBLE);
        autenticacao = ConfiguracaoFirebase.getRefenciaAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(
                this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            try {

                                progressBar.setVisibility(View.GONE);

                                //Salvar dados no firebase
                                String idUsuario = task.getResult().getUser().getUid();
                                usuario.setId(idUsuario);
                                usuario.salvar();



                                //Salvar dados no profile do Firebase
                                UsuarioFirebase.atualizarNomeUduario(usuario.getNome());

//                                Toast.makeText(CadastroActivity.this,
//                                        "Cadastro com sucesso",
//                                        Toast.LENGTH_SHORT).show();
                                Toast toast = Toast.makeText(getApplicationContext(), " Cadastro com sucesso", Toast.LENGTH_LONG);
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

                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                        } else {

                            progressBar.setVisibility(View.GONE);

                            String erroExcecao = "";
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthWeakPasswordException e) {
                                erroExcecao = "Digite uma senha mais forte!";
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                erroExcecao = "Por favor, digite um e-mail válido";
                            } catch (FirebaseAuthUserCollisionException e) {
                                erroExcecao = "Este conta já foi cadastrada";
                            } catch (Exception e) {
                                erroExcecao = "ao cadastrar usuário: " + e.getMessage();
                                e.printStackTrace();
                            }

//                            Toast.makeText(CadastroActivity.this,
//                                    "Erro: " + erroExcecao,
//                                    Toast.LENGTH_SHORT).show();
                            Toast toast = Toast.makeText(getApplicationContext(), "Erro: " + erroExcecao, Toast.LENGTH_LONG);
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

                }
        );


    }





    public void inicializarComponentes(){

        campoNome       = findViewById(R.id.editnNomeUsuario);
//        campoIdade       = findViewById(R.id.editidadeCadastro);
//        campoPeso       = findViewById(R.id.editPesoCadastro);
//        campoAltura       = findViewById(R.id.editAlturaCadastro);
        campoEmail      = findViewById(R.id.editEmailCadastro);
        campoSenha      = findViewById(R.id.editSenhaCadastro);
        botaoCadastrar  = findViewById(R.id.bottonCadastra);
        progressBar     = findViewById(R.id.progressCadastro);

        campoNome.requestFocus();

    }


}
