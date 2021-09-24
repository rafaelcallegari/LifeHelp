package com.example.lifehelp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class TelaLogin extends AppCompatActivity {

    private RadioButton rb_Profissional, rb_Usuario;
    private TextView txt_Cadastrar, txt_esqueciSenha, txt_esqueciUsuario;
    private ProgressBar progressBar;
    private EditText edt_senha, edt_Usuario;
    private Button button2, btn_logar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        rb_Profissional = findViewById(R.id.rb_Profissional);
        rb_Usuario = findViewById(R.id.rb_Usuario);
        txt_Cadastrar = findViewById(R.id.txt_Lembrei);
        txt_esqueciUsuario = findViewById(R.id.txt_esqueciUsuario);
        txt_esqueciSenha = findViewById(R.id.txt_esqueciPassw);
        edt_Usuario = findViewById(R.id.edt_email);
        edt_senha = findViewById(R.id.edt_senha);
        button2 = findViewById(R.id.button2);
        btn_logar = findViewById(R.id.btn_logar);
        progressBar = findViewById(R.id.progressbar);



        txt_Cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itn = new Intent(TelaLogin.this, DoisBotoes.class);
                startActivity(itn);
            }
        });

        txt_esqueciSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iitn = new Intent(TelaLogin.this, Perdemo.class);
                startActivity(iitn);
            }
        });

        txt_esqueciUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ittm = new Intent(TelaLogin.this, Perdemo_usuario.class);
                startActivity(ittm);
            }
        });



        btn_logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edt_Usuario.getText().toString();
                String senha = edt_senha.getText().toString();

                if (email.isEmpty() || senha.isEmpty()) {
                    edt_Usuario.setError("preencha todos os campos");
                } else {
                    LogarUsuario();
                }
            }
        });


    }

    private void ValidarPerfil() {
        if (rb_Usuario.isSelected()) {
            LogarUsuario();
        }
        else {
            LogarProfissional();
        }
    }


        private void LogarUsuario() {

        if (rb_Profissional.isChecked())
        {
            LogarProfissional();
        }
        else {

            String email = edt_Usuario.getText().toString();
            String senha = edt_senha.getText().toString();

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener < AuthResult >() {
                @Override
                public void onComplete(@NonNull Task < AuthResult > task) {

                    if (task.isSuccessful()) {
                        progressBar.setVisibility(View.VISIBLE);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                TelaPrincipal();
                            }
                        }, 3000);
                    } else {
                        String erro;

                        try {
                            throw task.getException();
                        } catch (Exception e) {
                            erro = "Erro ao logar";
                        }
                        edt_Usuario.setError(erro);
                    }

                }
            });

        }

        }

    private void TelaPrincipal() {
        Intent intent = new Intent(TelaLogin.this,CentralUm.class);
        startActivity(intent);
        finish();
    }


    private void LogarProfissional() {

        String email = edt_Usuario.getText().toString();
        String senha = edt_senha.getText().toString();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener < AuthResult >() {
            @Override
            public void onComplete(@NonNull Task < AuthResult > task) {

                if (task.isSuccessful()) {
                    progressBar.setVisibility(View.VISIBLE);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            TelaPrincipalProfissional();
                        }
                    },3000);
                }

                else {
                    String erro;

                    try {
                        throw task.getException();
                    }
                    catch (Exception e) {
                        erro = "Erro ao logar";
                    }
                    edt_Usuario.setError(erro);
                }

            }
        });

    }

    private void TelaPrincipalProfissional() {
        Intent intent = new Intent(TelaLogin.this,PerfilProf.class);
        startActivity(intent);
        finish();
    }



}