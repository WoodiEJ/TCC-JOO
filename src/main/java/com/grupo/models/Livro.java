/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.grupo.models;

public class Livro extends Entidade {
    private static int contador = 1;

    private String nome;
    private String autor;
    private int copias;
    private Status status;
    private String categoria;

    public Livro(String nome, String autor, int copias, String categoria) {
        super(contador++);
        this.nome = nome;
        this.autor = autor;
        this.copias = copias;
        this.categoria = categoria;
        this.status = copias > 0 ? Status.DISPONIVEL : Status.RESERVADO;
    }

    public String getNome() {
        return nome;
    }

    public String getAutor() {
        return autor;
    }

    public int getCopias() {
        return copias;
    }

    public Status getStatus() {
        return status;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCopias(int copias) {
        this.copias = copias;
        this.status = copias > 0 ? Status.DISPONIVEL : Status.RESERVADO;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String getDescricao() {
        return "ID: " + id + " | Nome: " + nome + " | Autor: " + autor
                + " | Categoria: " + categoria + " | Copias: " + copias
                + " | Status: " + status;
    }
}