package com.grupo.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reserva extends Entidade {
    private static int contador = 1;
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private int livro_id;
    private int cliente_id;
    private LocalDate data_emprestimo;
    private LocalDate data_volta;
    private Status status;

    public Reserva(int livro_id, int cliente_id, LocalDate data_emprestimo, LocalDate data_volta) {
        super(contador++);
        this.livro_id = livro_id;
        this.cliente_id = cliente_id;
        this.data_emprestimo = data_emprestimo;
        this.data_volta = data_volta;
        this.status = Status.RESERVADO;
    }

    public int getLivroId() {
        return livro_id;
    }

    public int getClienteId() {
        return cliente_id;
    }

    public LocalDate getDataEmprestimo() {
        return data_emprestimo;
    }

    public LocalDate getDataVolta() {
        return data_volta;
    }

    public Status getStatus() {
        return status;
    }

    public void setLivroId(int livro_id) {
        this.livro_id = livro_id;
    }

    public void setClienteId(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public void setDataEmprestimo(LocalDate data_emprestimo) {
        this.data_emprestimo = data_emprestimo;
    }

    public void setDataVolta(LocalDate data_volta) {
        this.data_volta = data_volta;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String getDescricao() {
        String dataVoltaFormatada = (data_volta == null) ? "Pendente" : data_volta.format(FORMATO);

        return "ID: " + id + " | Livro ID: " + livro_id + " | Cliente ID: " + cliente_id
                + " | Emprestimo: " + data_emprestimo.format(FORMATO)
                + " | Devolucao: " + dataVoltaFormatada
                + " | Status: " + status;
    }
}