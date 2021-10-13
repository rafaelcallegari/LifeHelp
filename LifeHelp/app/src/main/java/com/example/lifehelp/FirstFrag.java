package com.example.lifehelp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lifehelp.especialidades.ListaCuidador;
import com.example.lifehelp.especialidades.ListaEnfermeiro;
import com.example.lifehelp.especialidades.ListaFisio;
import com.example.lifehelp.especialidades.ListaProfissional;
import com.example.lifehelp.especialidades.ListaPsico;

public class FirstFrag extends Fragment {
    private static final String TAG = "FirstFrag";


    private LinearLayout LinEnfer, LinCuid, LinFisio, LinPsico, LinTodos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_firstfrag, container, false);
        LinEnfer = (LinearLayout) view.findViewById(R.id.LinEnfer);
        LinCuid = (LinearLayout) view.findViewById(R.id.LinCuid);
        LinFisio = (LinearLayout) view.findViewById(R.id.LinFisio);
        LinPsico = (LinearLayout) view.findViewById(R.id.LinPsico);
        LinTodos = (LinearLayout) view.findViewById(R.id.LinTodos);

        Log.d(TAG, "onCreateView: starded.");




        LinEnfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirEnfermeiro();
                Toast.makeText(getActivity(), "Enfermeiros", Toast.LENGTH_SHORT).show();
            }
        });

        LinCuid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirCuidadores();
                Toast.makeText(getActivity(), "Cuidadores", Toast.LENGTH_SHORT).show();

            }
        });

        LinFisio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirFisioterapeutas();
                Toast.makeText(getActivity(), "Fisioterapeutas", Toast.LENGTH_SHORT).show();
            }
        });

        LinPsico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirPsicologos();
                Toast.makeText(getActivity(), "Psicologos", Toast.LENGTH_SHORT).show();
            }
        });

        LinTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirTodos();
                Toast.makeText(getActivity(), "Todos Profissionais", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void AbrirEnfermeiro() {
        Intent abrirOutraActivity = new Intent(getActivity(), ListaEnfermeiro.class);
        startActivity(abrirOutraActivity);
    }

    private void AbrirCuidadores() {
        Intent abrirOutraActivity = new Intent(getActivity(), ListaCuidador.class);
        startActivity(abrirOutraActivity);
    }

    private void AbrirFisioterapeutas() {
        Intent abrirOutraActivity = new Intent(getActivity(), ListaFisio.class);
        startActivity(abrirOutraActivity);
    }

    private void AbrirPsicologos() {
        Intent abrirOutraActivity = new Intent(getActivity(), ListaPsico.class);
        startActivity(abrirOutraActivity);
    }

    private void AbrirTodos() {
        Intent abrirOutraActivity = new Intent(getActivity(), ListaProfissional.class);
        startActivity(abrirOutraActivity);
    }


}