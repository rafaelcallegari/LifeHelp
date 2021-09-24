package com.example.lifehelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Perdemo_usuario extends AppCompatActivity {

    private TextView txt_Lembrei, txt_esqueciPassw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perdemo_usuario);

        txt_Lembrei = findViewById(R.id.txt_Lembrei);
        txt_esqueciPassw = findViewById(R.id.txt_esqueciPassw);

        txt_Lembrei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itm = new Intent(Perdemo_usuario.this, TelaLogin.class);
                startActivity(itm);
            }
        });

        txt_esqueciPassw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itm = new Intent(Perdemo_usuario.this, Perdemo.class);
                startActivity(itm);
            }
        });

    }
}