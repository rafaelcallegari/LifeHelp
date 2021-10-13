package com.example.lifehelp;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.sip.SipSession;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;

public class MyAdpter extends RecyclerView.Adapter<MyAdpter.MyViewHolder> {

    Context context;
    ArrayList<Profissional> profissionalArrayList;
    OnNoteListener mOnNoteListener;

    public MyAdpter(Context context, ArrayList < Profissional > profissionalArrayList, OnNoteListener onNoteListener) {
        this.context = context;
        this.profissionalArrayList = profissionalArrayList;
        this.mOnNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public MyAdpter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.teste_main,parent,false);

        return new MyViewHolder(v, mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdpter.MyViewHolder holder, int position) {

        Profissional profissional = profissionalArrayList.get(position);

        holder.txt_Nome.setText(profissional.nome);
        holder.txt_idade.setText(profissional.especialidade);

    }

    @Override
    public int getItemCount() {
        return profissionalArrayList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txt_Nome,txt_idade;
        ImageView imgFoto;
        OnNoteListener onNoteListener;

        public MyViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);

            txt_Nome = itemView.findViewById(R.id.txt_Nome);
            txt_idade = itemView.findViewById(R.id.txt_idade);
            imgFoto = itemView.findViewById(R.id.imageView4);

            this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            onNoteListener.OnNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener {
        void OnNoteClick(int position);
    }

}