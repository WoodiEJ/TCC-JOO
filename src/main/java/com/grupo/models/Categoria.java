package com.grupo.models;

public class Categoria extends Entidade {
    private static int contador = 1;

    private String nome;

    public Categoria(String nome) {
        super(contador++);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getDescricao() {
        return "ID: " + id + " | Nome: " + nome;
    }
}