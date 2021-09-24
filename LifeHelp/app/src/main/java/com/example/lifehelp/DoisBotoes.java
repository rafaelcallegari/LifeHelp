package com.example.lifehelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DoisBotoes extends AppCompatActivity {

private Button btn_User, btn_Prof;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dois_botoes);

        btn_Prof = findViewById(R.id.buttonProf);
        btn_User = findViewById(R.id.buttonUser);


        btn_User.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent itn = new Intent(DoisBotoes.this, Cadastro.class);
                startActivity(itn);
            }
        });

        btn_Prof.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent itn = new Intent(DoisBotoes.this, CadastroProfissional.class);
                startActivity(itn);
            }
        });
    }



}