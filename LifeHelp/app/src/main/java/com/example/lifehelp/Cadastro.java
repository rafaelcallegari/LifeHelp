package com.example.lifehelp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Cadastro extends AppCompatActivity {
    private Button btn_limpar, btn_confir, button;
    private EditText edt_Nome, edt_Usu, edt_Email, edt_Senha, edt_ConfirmSenha, edt_Cpf;
    private RadioButton rb_Masculino, rb_Feminino;
    String usuarioId;
    String[] mensagens = {"Preencha todos os campos", "Cadastro realizado com sucesso"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);


        edt_Email = findViewById(R.id.edt_Email);
        edt_Nome = findViewById(R.id.edt_Nome);
        edt_Senha = findViewById(R.id.edt_senha);
        edt_Usu = findViewById(R.id.edt_Usu);
        edt_Cpf = findViewById(R.id.edt_Cpf);

        edt_ConfirmSenha = findViewById(R.id.edt_ConfirmSenha);


        btn_confir = findViewById(R.id.btn_confir);
        btn_limpar = findViewById(R.id.btn_limpar);
        button = findViewById(R.id.button2);

        rb_Masculino = findViewById(R.id.rb_Masculino);
        rb_Feminino = findViewById(R.id.rb_Feminino);


        btn_confir.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                String nome = edt_Nome.getText().toString();
                String usuario = edt_Usu.getText().toString();
                String email = edt_Email.getText().toString();
                String cpf = edt_Cpf.getText().toString();
                String senha = edt_ConfirmSenha.getText().toString();

                if (nome.isEmpty() || usuario.isEmpty() || email.isEmpty() || cpf.isEmpty() || senha.isEmpty()) {
                    Toast.makeText(Cadastro.this, mensagens[0], Toast.LENGTH_SHORT).show();
                } else {
                    CadastrarUsuario(v);
                }
            }
        });


    }

    private void CadastrarUsuario(View v) {

        String email = edt_Email.getText().toString();
        String senha = edt_ConfirmSenha.getText().toString();


        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener < AuthResult >() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onComplete(@NonNull Task < AuthResult > task) {

                if (task.isSuccessful()) {

                    SalvarDadosUsuario();

                    Toast.makeText(Cadastro.this, mensagens[1], Toast.LENGTH_SHORT).show();

                }
                else{
                    String erro;

                    try {
                        throw task.getException();
                    }
                    catch (FirebaseAuthWeakPasswordException e) {
                        erro = "Digite uma senha com no minimo 6 caracteres";
                    }
                    catch (FirebaseAuthUserCollisionException e) {
                        erro = "email ja cadastrado";
                    }
                    catch (FirebaseAuthInvalidCredentialsException e) {
                        erro = "E-mail invalido";
                    }
                    catch (Exception e) {
                        erro = "erro ao cadastrar usuario";
                    }

                    Toast.makeText(Cadastro.this, erro, Toast.LENGTH_SHORT).show();

                }
            }

        });

    }

    private void SalvarDadosUsuario() {

        String nome = edt_Nome.getText().toString();
        String usuario = edt_Usu.getText().toString();
        String cpf = edt_Cpf.getText().toString();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> usuarios = new HashMap <>();
        usuarios.put("nome", nome);
        usuarios.put("usuario", usuario);
        usuarios.put("CPF", cpf);

        usuarioId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("usuarios").document(usuarioId);
        documentReference.set(usuarios).addOnSuccessListener(new OnSuccessListener < Void >() {
            @Override
            public void onSuccess(Void aVoid) {
                startActivity(new Intent(getApplicationContext(),TelaLogin.class));
                finish();
                Log.d("db", "Sucesso ao salvar os dados");
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        startActivity(new Intent(getApplicationContext(),DoisBotoes.class));
                        finish();
                        Log.d("db_error", "Erro ao salvar os dados" + e.toString());
                    }
                });


    }

}