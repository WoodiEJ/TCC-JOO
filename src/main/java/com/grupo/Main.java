
package com.grupo;

import com.grupo.models.Categoria;
import com.grupo.models.Cliente;
import com.grupo.models.Livro;
import com.grupo.models.Reserva;
import com.grupo.models.Status;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    static ArrayList<Categoria> categorias = new ArrayList<>();
    static ArrayList<Cliente> clientes = new ArrayList<>();
    static ArrayList<Livro> livros = new ArrayList<>();
    static ArrayList<Reserva> reservas = new ArrayList<>();

    public static void main(String[] args) {

        // categorias pre-cadastradas para testes (nao ha opcao de cadastro no menu)
        categorias.add(new Categoria("Romance"));
        categorias.add(new Categoria("Ficcao Cientifica"));
        categorias.add(new Categoria("Tecnico"));

        int opcao;

        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opcao: ");

            switch (opcao) {
                case 1:
                    listarLivros();
                    break;
                case 2:
                    listarClientes();
                    break;
                case 3:
                    listarCategorias();
                    break;
                case 4:
                    listarReservas();
                    break;
                case 5:
                    cadastrarLivro();
                    break;
                case 6:
                    cadastrarCliente();
                    break;
                case 7:
                    reservarLivro();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opcao invalida! Tente novamente.");
                    break;
            }

            System.out.println();

        } while (opcao != 0);

        scanner.close();
    }

    static void exibirMenu() {
        System.out.println("===== BIBLIOTECA - MENU =====");
        System.out.println("1 - Listar livros");
        System.out.println("2 - Listar clientes");
        System.out.println("3 - Listar categorias");
        System.out.println("4 - Listar reservas");
        System.out.println("5 - Cadastrar livro");
        System.out.println("6 - Cadastrar cliente");
        System.out.println("7 - Reservar livro");
        System.out.println("0 - Sair");
        System.out.println("==============================");
    }

    // ---------- LISTAGENS ----------

    static void listarLivros() {
        System.out.println("--- Lista de Livros ---");
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }
        for (Livro l : livros) {
            System.out.println(l);
        }
    }

    static void listarClientes() {
        System.out.println("--- Lista de Clientes ---");
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        for (Cliente c : clientes) {
            System.out.println(c);
        }
    }

    static void listarCategorias() {
        System.out.println("--- Lista de Categorias ---");
        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria cadastrada.");
            return;
        }
        for (Categoria c : categorias) {
            System.out.println(c);
        }
    }

    static void listarReservas() {
        System.out.println("--- Lista de Reservas ---");
        if (reservas.isEmpty()) {
            System.out.println("Nenhuma reserva cadastrada.");
            return;
        }
        for (Reserva r : reservas) {
            System.out.println(r);
        }
    }

    // ---------- CADASTROS ----------

    static void cadastrarLivro() {
        System.out.println("--- Cadastrar Livro ---");

        System.out.print("Nome do livro: ");
        String nome = scanner.nextLine();

        System.out.print("Autor: ");
        String autor = scanner.nextLine();

        int copias = lerInteiro("Quantidade de copias: ");

        listarCategorias();
        System.out.print("Digite o nome da categoria (conforme lista acima): ");
        String categoria = scanner.nextLine();

        Livro livro = new Livro(nome, autor, copias, categoria);
        livros.add(livro);

        System.out.println("Livro cadastrado com sucesso! " + livro);
    }

    static void cadastrarCliente() {
        System.out.println("--- Cadastrar Cliente ---");

        System.out.print("Nome do cliente: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        Cliente cliente = new Cliente(nome, cpf);
        clientes.add(cliente);

        System.out.println("Cliente cadastrado com sucesso! " + cliente);
    }

    // ---------- RESERVA ----------

    static void reservarLivro() {
        System.out.println("--- Reservar Livro ---");

        if (livros.isEmpty()) {
            System.out.println("Nao ha livros cadastrados.");
            return;
        }
        if (clientes.isEmpty()) {
            System.out.println("Nao ha clientes cadastrados.");
            return;
        }

        listarLivros();
        int livroId = lerInteiro("Digite o ID do livro que deseja reservar: ");
        Livro livro = buscarLivroPorId(livroId);

        if (livro == null) {
            System.out.println("Livro nao encontrado.");
            return;
        }
        if (livro.getStatus() == Status.RESERVADO || livro.getCopias() <= 0) {
            System.out.println("Este livro nao esta disponivel para reserva.");
            return;
        }

        listarClientes();
        int clienteId = lerInteiro("Digite o ID do cliente: ");
        Cliente cliente = buscarClientePorId(clienteId);

        if (cliente == null) {
            System.out.println("Cliente nao encontrado.");
            return;
        }

        Date dataEmprestimo = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataEmprestimo);
        cal.add(Calendar.DAY_OF_MONTH, 7); // prazo padrao de 7 dias
        Date dataVolta = cal.getTime();

        Reserva reserva = new Reserva(livro.getId(), cliente.getId(), dataEmprestimo, dataVolta);
        reservas.add(reserva);

        livro.setCopias(livro.getCopias() - 1);

        System.out.println("Reserva realizada com sucesso! " + reserva);
    }

    // ---------- AUXILIARES ----------

    static Livro buscarLivroPorId(int id) {
        for (Livro l : livros) {
            if (l.getId() == id) {
                return l;
            }
        }
        return null;
    }

    static Cliente buscarClientePorId(int id) {
        for (Cliente c : clientes) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    static int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextInt()) {
            System.out.println("Digite um numero valido.");
            System.out.print(mensagem);
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine(); // limpar buffer
        return valor;
    }
}