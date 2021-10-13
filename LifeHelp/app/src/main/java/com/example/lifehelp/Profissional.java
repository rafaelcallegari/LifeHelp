package com.example.lifehelp;

public class Profissional {

    String nome, especialidade;
    int id;

    public Profissional() {}

    public Profissional(String nome, String especialidade, int id) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
