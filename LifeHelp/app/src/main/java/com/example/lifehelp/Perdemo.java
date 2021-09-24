package com.example.lifehelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Perdemo extends AppCompatActivity {

    private TextView txt_esqueciUser, txt_Lembrei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perdemo);

        txt_esqueciUser = findViewById(R.id.txt_esqueciPassw);
        txt_Lembrei = findViewById(R.id.txt_Lembrei);

        txt_esqueciUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itmm = new Intent(Perdemo.this, Perdemo_usuario.class);
                startActivity(itmm);
            }
        });


        txt_Lembrei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itm = new Intent(Perdemo.this, TelaLogin.class);
                startActivity(itm);
            }
        });
    }
}