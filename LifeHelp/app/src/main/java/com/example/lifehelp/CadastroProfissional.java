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


public class CadastroProfissional extends AppCompatActivity {

     Button btn_limparProf, btn_confirProf;
     EditText edt_NomeProf, edt_EspecialidadeProf, edt_EmailProf, edt_SenhaProf, edt_cadastroSenhaProf, edt_CpfProf;
     RadioButton rb_MasculinoProf, rb_FemininoProf;

    String ProfId;
    String[] mensagens = {"Preencha todos os campos", "Cadastro realizado com sucesso"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_profissional);

        btn_confirProf = findViewById(R.id.btn_confirProf);
        btn_limparProf = findViewById(R.id.btn_limparProf);

        edt_cadastroSenhaProf = findViewById(R.id.edt_cadastroSenhaProf);
        edt_EmailProf = findViewById(R.id.edt_EmailProf);
        edt_NomeProf = findViewById(R.id.edt_NomeProf);
        edt_EspecialidadeProf = findViewById(R.id.edt_EspecialidadeProf);
        edt_SenhaProf = findViewById(R.id.edt_cadastroSenhaProf);
        edt_CpfProf = findViewById(R.id.edt_CpfProf);

        rb_FemininoProf = findViewById(R.id.rb_FemininoProf);
        rb_MasculinoProf = findViewById(R.id.rb_MasculinoProf);


        btn_confirProf.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                String nome = edt_NomeProf.getText().toString();
                String usuario = edt_EspecialidadeProf.getText().toString();
                String email = edt_EmailProf.getText().toString();
                String cpf = edt_CpfProf.getText().toString();
                String senha = edt_cadastroSenhaProf.getText().toString();

                if (nome.isEmpty() || usuario.isEmpty() || email.isEmpty() || cpf.isEmpty() || senha.isEmpty()) {
                    Toast.makeText(CadastroProfissional.this, mensagens[0], Toast.LENGTH_SHORT).show();
                } else {
                    CadastrarProf(v);
                }
            }
        });


    }

    private void CadastrarProf(View v) {

        String email = edt_EmailProf.getText().toString();
        String senha = edt_cadastroSenhaProf.getText().toString();


        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener < AuthResult >() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onComplete(@NonNull Task < AuthResult > task) {

                if (task.isSuccessful()) {

                    SalvarDadosProf();

                    Toast.makeText(CadastroProfissional.this, mensagens[1], Toast.LENGTH_SHORT).show();

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

                    Toast.makeText(CadastroProfissional.this, erro, Toast.LENGTH_SHORT).show();

                }
            }

        });

    }

    private void SalvarDadosProf() {

        String nome = edt_NomeProf.getText().toString();
        String especialidade = edt_EspecialidadeProf.getText().toString();
        String cpf = edt_CpfProf.getText().toString();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> profissional = new HashMap <>();
        profissional.put("nome", nome);
        profissional.put("especialidade", especialidade);
        profissional.put("CPF", cpf);

        ProfId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("profissional").document(ProfId);
        documentReference.set(profissional).addOnSuccessListener(new OnSuccessListener < Void >() {
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