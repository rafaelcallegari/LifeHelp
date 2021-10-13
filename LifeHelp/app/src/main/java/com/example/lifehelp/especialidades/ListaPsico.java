package com.example.lifehelp.especialidades;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.lifehelp.MyAdpter;
import com.example.lifehelp.PerfilLista;
import com.example.lifehelp.Profissional;
import com.example.lifehelp.R;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import static android.app.PendingIntent.getActivity;

public class ListaPsico extends AppCompatActivity implements MyAdpter.OnNoteListener {

    RecyclerView recyclerView;
    ArrayList< Profissional > profissionalArrayList;
    MyAdpter myAdpter;
    FirebaseFirestore db;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_profissional);

        TextView NomeProfisao = (TextView) findViewById(R.id.NomeProfisao);

        NomeProfisao.setText("psicólogo");

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Procurando");
        progressDialog.show();


        recyclerView = findViewById(R.id.ReciclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        profissionalArrayList = new ArrayList <Profissional>();
        myAdpter = new MyAdpter(ListaPsico.this,profissionalArrayList, this);

        recyclerView.setAdapter(myAdpter);


        EventChangeListener();
    }

    private void EventChangeListener() {
        db.collection("profissional").whereEqualTo("especialidade", "psicologa").orderBy("nome",Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener < QuerySnapshot >() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null) {

                            if (progressDialog.isShowing())
                                progressDialog.dismiss();

                            Log.e("error no firestore", error.getMessage());
                            return;
                        }

                        for (DocumentChange dc: value.getDocumentChanges()) {


                            if (dc.getType() == DocumentChange.Type.ADDED) {

                                profissionalArrayList.add(dc.getDocument().toObject(Profissional.class));

                            }

                            myAdpter.notifyDataSetChanged();
                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                        }


                    }
                });
    }

    @Override
    public void OnNoteClick(int position) {
        Intent intent = new Intent(ListaPsico.this, PerfilLista.class);
        intent.putExtra("nome", profissionalArrayList.get(position).getNome());
        intent.putExtra("especialidade", profissionalArrayList.get(position).getEspecialidade());
        startActivity(intent);
    }
}