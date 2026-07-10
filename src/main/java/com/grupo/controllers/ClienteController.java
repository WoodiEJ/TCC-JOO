package com.grupo.controllers;

import java.util.LinkedList;
import java.util.Scanner;

import com.grupo.models.Cliente;
import com.grupo.utils.ConsoleUtil;

public class ClienteController {
    private LinkedList<Cliente> clientes;
    private Scanner teclado = new Scanner(System.in);

    public ClienteController(LinkedList<Cliente> clientes) {
        this.clientes = clientes;
    }

    private void listar() {
        ConsoleUtil.imprimirTitulo("CLIENTES CADASTRADOS");
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente encontrado.");
            return;
        }
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    private Cliente buscarPorId(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
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

    private void buscarCliente() {
        ConsoleUtil.imprimirTitulo("BUSCAR CLIENTE");
        System.out.println("Digite o ID do cliente: ");
        int id = lerInt();

        Cliente cliente = buscarPorId(id);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
        } else {
            System.out.println(cliente);
        }
    }

    private void registrarCliente() {
        ConsoleUtil.imprimirTitulo("REGISTRAR CLIENTE");

        System.out.println("Nome: ");
        String nome = teclado.nextLine();

        System.out.println("CPF: ");
        String cpf = teclado.nextLine();

        Cliente cliente = new Cliente(nome, cpf);
        clientes.add(cliente);

        System.out.println("\nCliente registrado com sucesso! ID: " + cliente.getId());
    }

    private void editarCliente() {
        ConsoleUtil.imprimirTitulo("EDITAR CLIENTE");

        System.out.println("Digite o ID do cliente que deseja editar: ");
        int id = lerInt();

        Cliente cliente = buscarPorId(id);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.println("Deixe em branco para manter o valor atual.");

        System.out.println("Nome (" + cliente.getNome() + "): ");
        String nome = teclado.nextLine();
        if (!nome.isBlank()) {
            cliente.setNome(nome);
        }

        System.out.println("CPF (" + cliente.getCpf() + "): ");
        String cpf = teclado.nextLine();
        if (!cpf.isBlank()) {
            cliente.setCpf(cpf);
        }

        System.out.println("\nCliente atualizado com sucesso.");
    }

    private void deletarCliente() {
        ConsoleUtil.imprimirTitulo("DELETAR CLIENTE");

        System.out.println("Digite o ID do cliente que deseja deletar: ");
        int id = lerInt();

        Cliente cliente = buscarPorId(id);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        clientes.remove(cliente);
        System.out.println("Cliente deletado com sucesso.");
    }

    public void menuCliente() {
        int opcao;
        do {
            ConsoleUtil.limparTela();
            ConsoleUtil.imprimirTitulo("CLIENTES");
            System.out.println("1 - Listar");
            System.out.println("2 - Buscar");
            System.out.println("3 - Registrar");
            System.out.println("4 - Editar");
            System.out.println("5 - Deletar");
            System.out.println("0 - Voltar");
            System.out.println("Escolha uma opção: ");

            opcao = lerInt();
            System.out.println();

            switch (opcao) {
                case 1 -> listar();
                case 2 -> buscarCliente();
                case 3 -> registrarCliente();
                case 4 -> editarCliente();
                case 5 -> deletarCliente();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }

            if (opcao != 0) {
                ConsoleUtil.pausar(teclado);
            }
        } while (opcao != 0);
    }
}