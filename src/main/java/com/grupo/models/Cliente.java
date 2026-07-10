package com.grupo.models;

public class Cliente extends Entidade {
    private static int contador = 1;

    private String nome;
    private String cpf;

    public Cliente(String nome, String cpf) {
        super(contador++);
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String getDescricao() {
        return "ID: " + id + " | Nome: " + nome + " | CPF: " + cpf;
    }
}