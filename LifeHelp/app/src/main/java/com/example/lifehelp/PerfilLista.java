package com.example.lifehelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lifehelp.especialidades.ListaEnfermeiro;
import com.google.android.gms.dynamic.IFragmentWrapper;

import java.util.ArrayList;

public class PerfilLista extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_lista);


        getIncomingIntent();

        
    }

    private void getIncomingIntent() {

        if (getIntent().hasExtra("nome") && getIntent().hasExtra("especialidade")) {
            String Nome = getIntent().getStringExtra("nome");
            String s = getIntent().getStringExtra("especialidade");

            setPerfil(Nome, s);
        }

    }

    private void setPerfil(String s, String nome) {

        TextView NomePerfil = findViewById(R.id.txt_NomePerfil);
        NomePerfil.setText(nome);

        TextView Especialidade = findViewById(R.id.txt_idadePerfil);
        Especialidade.setText(s);

    }

}