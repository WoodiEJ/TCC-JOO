package com.grupo.controllers;

import java.util.LinkedList;
import java.util.Scanner;

import com.grupo.models.Categoria;
import com.grupo.models.Livro;

public class LivroController {
    private LinkedList<Livro> livros = new LinkedList<>();
    private LinkedList<Categoria> categorias;
    private Scanner teclado = new Scanner(System.in);

    public LivroController(LinkedList<Categoria> categorias) {
        this.categorias = categorias;
    }

    private void listar() {
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
            return;
        }
        System.out.println("\nOS LIVROS DISPONIVEIS");
        for (Livro livro : livros) {
            System.out.println(livro);
        }
    }

    private Livro buscarPorId(int id) {
        for (Livro livro : livros) {
            if (livro.getId() == id) {
                return livro;
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

    private void buscarLivro() {
        System.out.println("Digite o ID do livro: ");
        int id = lerInt();

        Livro livro = buscarPorId(id);
        if (livro == null) {
            System.out.println("Livro não encontrado.");
        } else {
            System.out.println(livro);
        }
    }

    private Categoria escolherCategoria() {
        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria encontrada.");
            return null;
        }

        System.out.println("\n--- CATEGORIA DISPONIVEIS ---");
        for (Categoria categoria : categorias) {
            System.out.println(categoria);
        }

        System.out.println("Digite o ID da categoria: ");
        int id = lerInt();

        for (Categoria categoria : categorias) {
            if (categoria.getId() == id) {
                return categoria;
            }
        }

        System.out.println("Categoria não encontrada.");
        return null;
    }

    private void registrarLivro() {
        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria cadastrada. Cadastre uma categoria antes de registrar um livro.");
            return;
        }

        System.out.println("Nome: ");
        String nome = teclado.nextLine();

        System.out.println("Autor: ");
        String autor = teclado.nextLine();

        System.out.println("Copias: ");
        int copias = lerInt();

        Categoria categoria = escolherCategoria();
        if (categoria == null) {
            System.out.println("Registro Cancelado.");
            return;
        }

        Livro livro = new Livro(nome, autor, copias, categoria);
        livros.add(livro);

        System.out.println("Livro registrado com sucesso! ID: " + livro.getId());
    }

    private void editarLivro() {
        System.out.println("Digite o ID que deseja editar: ");
        int id = lerInt();

        Livro livro = buscarPorId(id);
        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        System.out.println("Deixe em branco para manter o valor atual.");

        System.out.println("Nome: (" + livro.getNome() + "): ");
        String nome = teclado.nextLine();
        if (!nome.isBlank()) {
            livro.setNome(nome);
        }

        System.out.println("Autor (" + livro.getAutor() + "): ");
        String autor = teclado.nextLine();
        if (!autor.isBlank()) {
            livro.setAutor(autor);
        }

        System.out.println("Copias (" + livro.getCopias() + "): ");
        String copiasEmString = teclado.nextLine();
        if (!copiasEmString.isBlank()) {
            try {
                livro.setCopias(Integer.parseInt(copiasEmString));
            } catch (NumberFormatException erro) {
                System.out.println("Valor invalido, copias não alterada.");
            }
        }

        System.out.println("Deseja alter a categoria (" + livro.getCategoria().getNome() + ")? (Sim/Nao): ");
        String resposta = teclado.nextLine();
        if (resposta.equalsIgnoreCase("Sim")) {
            Categoria categoria = escolherCategoria();
            if (categoria != null) {
                livro.setCategoria(categoria);
            }
        }
        System.out.println("Livro atualizado com sucesso.");
    }

    private void deletarLivro() {
        System.out.println("Digite o ID do livro que deseja deletar: ");
        int id = lerInt();

        Livro livro = buscarPorId(id);
        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        livros.remove(livro);
        System.out.println("Livro deletado com sucesso.");
    }

    public void menuLivro() {
        int opcao;
        do {
            System.out.println("\n===== LIVROS =====");
            System.out.println("1 - Listar");
            System.out.println("2 - Buscar");
            System.out.println("3 - Registrar");
            System.out.println("4 - Editar");
            System.out.println("5 - Deletar");
            System.out.println("0 - Voltar");
            System.out.println("Escolha uma opção: ");

            opcao = lerInt();

            switch (opcao) {
                case 1 -> listar();
                case 2 -> buscarLivro();
                case 3 -> registrarLivro();
                case 4 -> editarLivro();
                case 5 -> deletarLivro();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }
}