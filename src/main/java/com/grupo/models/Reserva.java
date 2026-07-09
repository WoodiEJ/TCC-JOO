package com.grupo.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reserva extends Entidade {
    private static int contador = 1;
    private static final SimpleDateFormat FORMATO = new SimpleDateFormat("dd/MM/yyyy");

    private int livro_id;
    private int cliente_id;
    private Date data_emprestimo;
    private Date data_volta;

    public Reserva(int livro_id, int cliente_id, Date data_emprestimo, Date data_volta) {
        super(contador++);
        this.livro_id = livro_id;
        this.cliente_id = cliente_id;
        this.data_emprestimo = data_emprestimo;
        this.data_volta = data_volta;
    }

    public int getLivroId() {
        return livro_id;
    }

    public int getClienteId() {
        return cliente_id;
    }

    public Date getDataEmprestimo() {
        return data_emprestimo;
    }

    public Date getDataVolta() {
        return data_volta;
    }

    @Override
    public String getDescricao() {
        return "ID: " + id + " | Livro ID: " + livro_id + " | Cliente ID: " + cliente_id
                + " | Emprestimo: " + FORMATO.format(data_emprestimo)
                + " | Devolucao: " + FORMATO.format(data_volta);
    }
}