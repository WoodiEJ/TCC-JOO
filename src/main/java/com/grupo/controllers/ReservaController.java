package com.grupo.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.Scanner;

import com.grupo.models.Cliente;
import com.grupo.models.Livro;
import com.grupo.models.Reserva;
import com.grupo.models.Status;
import com.grupo.utils.ConsoleUtil;

public class ReservaController {
    private LinkedList<Reserva> reservas = new LinkedList<>();
    private LinkedList<Livro> livros;
    private LinkedList<Cliente> clientes;
    private Scanner teclado = new Scanner(System.in);
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public ReservaController(LinkedList<Livro> livros, LinkedList<Cliente> clientes) {
        this.clientes = clientes;
        this.livros = livros;
    }

    private void listarReservas() {
        ConsoleUtil.imprimirTitulo("RESERVAS CADASTRADAS");
        if (reservas.isEmpty()) {
            System.out.println("Nenhuma reserva encontrada.");
            return;
        }
        for (Reserva reserva : reservas) {
            System.out.println(reserva);
        }
    }

    private Reserva buscarPorId(int id) {
        for (Reserva reserva : reservas) {
            if (reserva.getId() == id) {
                return reserva;
            }
        }
        return null;
    }

    private int lerInt() {
        while (!teclado.hasNextInt()) {
            System.out.println("Digite um numero valido: ");
            teclado.next();
        }
        int valor = teclado.nextInt();
        teclado.nextLine();
        return valor;
    }

    private LocalDate lerData(String mensagem) {
        LocalDate data = null;
        while (data == null) {
            System.out.println(mensagem + " (dd/MM/yyyy): ");
            String texto = teclado.nextLine();

            try {
                data = LocalDate.parse(texto, FORMATO);
            } catch (DateTimeParseException erro) {
                System.out.println("Data inválida, tente novamente.");
            }
        }
        return data;
    }

    private void buscarReserva() {
        ConsoleUtil.imprimirTitulo("BUSCAR RESERVA");
        System.out.println("Digite o ID da reserva: ");
        int id = lerInt();

        Reserva reserva = buscarPorId(id);
        if (reserva == null) {
            System.out.println("Reserva não encontrada.");
        } else {
            System.out.println(reserva);
        }
    }

    private Livro escolherLivro() {
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return null;
        }

        System.out.println("\n--- Livros disponíveis ---");
        for (Livro livro : livros) {
            System.out.println(livro);
        }

        System.out.println("Digite o ID do livro: ");
        int id = lerInt();

        for (Livro livro : livros) {
            if (livro.getId() == id) {
                return livro;
            }
        }

        System.out.println("Livro não encontrado.");
        return null;
    }

    private Cliente escolherCliente() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return null;
        }

        System.out.println("\n--- Clientes cadastrados ---");
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }

        System.out.println("Digite o ID do cliente: ");
        int id = lerInt();

        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }

        System.out.println("Cliente não encontrado.");
        return null;
    }

    private void registrarReserva() {
        ConsoleUtil.imprimirTitulo("REGISTRAR RESERVA");

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado. Cadastre um livro antes de reservar.");
            return;
        }

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado. Cadastre um cliente antes de reservar.");
            return;
        }

        Livro livro = escolherLivro();
        if (livro == null) {
            System.out.println("Reserva cancelada. Este livro não existe.");
            return;
        }

        if (livro.getCopias() <= 0) {
            System.out.println("Não há cópias disponíveis desse livro.");
            return;
        }

        Cliente cliente = escolherCliente();
        if (cliente == null) {
            System.out.println("Reserva cancelada. Este cliente não existe.");
            return;
        }

        LocalDate dataEmprestimo = LocalDate.now();
        LocalDate dataVolta = null;
        Reserva reserva = new Reserva(livro.getId(), cliente.getId(), dataEmprestimo, dataVolta);
        reservas.add(reserva);

        livro.setCopias(livro.getCopias() - 1);
        System.out.println("\nReserva registrada com sucesso! ID: " + reserva.getId());
    }

    private void editarReserva() {
        ConsoleUtil.imprimirTitulo("EDITAR RESERVA");

        System.out.println("Digite o ID da reserva que deseja editar: ");
        int id = lerInt();

        Reserva reserva = buscarPorId(id);
        if (reserva == null) {
            System.out.println("Reserva não encontrada.");
            return;
        }

        if (reserva.getStatus() != Status.RESERVADO) {
            System.out.println("Essa reserva já foi devolvida, não há o que editar.");
            return;
        }

        System.out.println("Deixe em branco para manter o valor atual.");

        System.out.println("Data do empréstimo atual: " + reserva.getDataEmprestimo().format(FORMATO));
        System.out.println("Deseja alterar a data do empréstimo? (Sim/Nao): ");
        String resposta = teclado.nextLine();
        if (resposta.equalsIgnoreCase("Sim")) {
            LocalDate novaData = lerData("Nova data do empréstimo");
            reserva.setDataEmprestimo(novaData);
        }

        System.out.println("\nReserva atualizada com sucesso.");
    }

    private void devolverLivro() {
        ConsoleUtil.imprimirTitulo("DEVOLVER LIVRO");

        System.out.println("Digite o ID da reserva que deseja devolver: ");
        int id = lerInt();

        Reserva reserva = buscarPorId(id);
        if (reserva == null) {
            System.out.println("Reserva não encontrada.");
            return;
        }

        if (reserva.getStatus() != Status.RESERVADO) {
            System.out.println("Esse livro já foi devolvido.");
            return;
        }

        reserva.setDataVolta(LocalDate.now());
        reserva.setStatus(Status.DISPONIVEL);

        Livro livro = buscarLivroPorId(reserva.getLivroId());
        if (livro != null) {
            livro.setCopias(livro.getCopias() + 1);
        }

        System.out.println("\nLivro devolvido com sucesso!");
    }

    private void deletarReserva() {
        ConsoleUtil.imprimirTitulo("DELETAR RESERVA");

        System.out.println("Digite o ID da reserva que deseja deletar: ");
        int id = lerInt();

        Reserva reserva = buscarPorId(id);
        if (reserva == null) {
            System.out.println("Reserva não encontrada.");
            return;
        }

        if (reserva.getStatus() == Status.RESERVADO) {
            Livro livro = buscarLivroPorId(reserva.getLivroId());
            if (livro != null) {
                livro.setCopias(livro.getCopias() + 1);
            }
        }

        reservas.remove(reserva);
        System.out.println("Reserva deletada com sucesso.");
    }

    private Livro buscarLivroPorId(int id) {
        for (Livro livro : livros) {
            if (livro.getId() == id) {
                return livro;
            }
        }
        return null;
    }

    public void menuReserva() {
        int opcao;
        do {
            ConsoleUtil.limparTela();
            ConsoleUtil.imprimirTitulo("RESERVAS");
            System.out.println("1 - Listar");
            System.out.println("2 - Buscar");
            System.out.println("3 - Registrar");
            System.out.println("4 - Editar");
            System.out.println("5 - Deletar");
            System.out.println("6 - Devolver Livro");
            System.out.println("0 - Voltar");
            System.out.println("Escolha uma opção: ");

            opcao = lerInt();
            System.out.println();

            switch (opcao) {
                case 1 -> listarReservas();
                case 2 -> buscarReserva();
                case 3 -> registrarReserva();
                case 4 -> editarReserva();
                case 5 -> deletarReserva();
                case 6 -> devolverLivro();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }

            if (opcao != 0) {
                ConsoleUtil.pausar(teclado);
            }
        } while (opcao != 0);
    }
}