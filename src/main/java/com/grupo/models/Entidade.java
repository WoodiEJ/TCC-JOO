package com.grupo.models;

public abstract class Entidade {

    protected int id;

    public Entidade(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public abstract String getDescricao();

    @Override
    public String toString() {
        return getDescricao();
    }
}