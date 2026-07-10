package com.grupo.controllers;

import java.util.LinkedList; // LINKEDLIST
import java.util.TreeMap;

import com.grupo.models.Cliente;
import com.grupo.utils.ConsoleUtil;
import com.grupo.utils.Historico;

public class ClienteController {
    private LinkedList<Cliente> clientes;

    public ClienteController(LinkedList<Cliente> clientes) {
        this.clientes = clientes;
    }

    private void listar() {
        ConsoleUtil.imprimirTitulo("CLIENTES");
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente encontrado.");
            return;
        }

        TreeMap<String, Cliente> clientesOrdenados = new TreeMap<>();
        for (Cliente cliente : clientes) {
            clientesOrdenados.put(cliente.getNome() + " #" + cliente.getId(), cliente);
        }

        for (String chave : clientesOrdenados.keySet()) {
            System.out.println(clientesOrdenados.get(chave));
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

    private void buscarCliente() {
        ConsoleUtil.imprimirTitulo("BUSCAR CLIENTE");
        System.out.println("Digite o ID do cliente: ");
        int id = ConsoleUtil.lerInt();

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
        String nome = ConsoleUtil.teclado.nextLine();

        System.out.println("CPF: ");
        String cpf = ConsoleUtil.teclado.nextLine();

        Cliente cliente = new Cliente(nome, cpf);
        clientes.add(cliente);

        Historico.registrar("Cliente registrado: " + cliente.getNome() + " (ID " + cliente.getId() + ")");
        System.out.println("\nCliente registrado com sucesso! ID: " + cliente.getId());
    }

    private void editarCliente() {
        ConsoleUtil.imprimirTitulo("EDITAR CLIENTE");

        System.out.println("Digite o ID do cliente que deseja editar: ");
        int id = ConsoleUtil.lerInt();

        Cliente cliente = buscarPorId(id);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.println("Deixe em branco para manter o valor atual.");

        System.out.println("Nome (" + cliente.getNome() + "): ");
        String nome = ConsoleUtil.teclado.nextLine();
        if (!nome.isBlank()) {
            cliente.setNome(nome);
        }

        System.out.println("CPF (" + cliente.getCpf() + "): ");
        String cpf = ConsoleUtil.teclado.nextLine();
        if (!cpf.isBlank()) {
            cliente.setCpf(cpf);
        }

        Historico.registrar("Cliente editado: " + cliente.getNome() + " (ID " + cliente.getId() + ")");
        System.out.println("\nCliente atualizado com sucesso.");
    }

    private void deletarCliente() {
        ConsoleUtil.imprimirTitulo("DELETAR CLIENTE");

        System.out.println("Digite o ID do cliente que deseja deletar: ");
        int id = ConsoleUtil.lerInt();

        Cliente cliente = buscarPorId(id);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        clientes.remove(cliente);
        Historico.registrar("Cliente deletado: " + cliente.getNome() + " (ID " + cliente.getId() + ")");
        System.out.println("Cliente deletado com sucesso.");
    }

    public void menuCliente() {
        int opcao;
        do { // DO-WHILE
            ConsoleUtil.limparTela();
            ConsoleUtil.imprimirTitulo("CLIENTES");
            System.out.println("1 - Listar");
            System.out.println("2 - Buscar");
            System.out.println("3 - Registrar");
            System.out.println("4 - Editar");
            System.out.println("5 - Deletar");
            System.out.println("0 - Voltar");
            System.out.println("Escolha uma opção: ");

            opcao = ConsoleUtil.lerInt();
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
                ConsoleUtil.pausar();
            }
        } while (opcao != 0);
    }
}