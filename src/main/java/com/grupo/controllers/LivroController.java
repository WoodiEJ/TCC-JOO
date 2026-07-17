package com.grupo.controllers;

import java.util.LinkedList;
import java.util.TreeMap;

import com.grupo.models.Categoria;
import com.grupo.models.Livro;
import com.grupo.utils.ConsoleUtil;
import com.grupo.utils.Historico;

public class LivroController {
    private LinkedList<Livro> livros;
    private LinkedList<Categoria> categorias;

    public LivroController(LinkedList<Livro> livros, LinkedList<Categoria> categorias) {
        this.livros = livros;
        this.categorias = categorias;
    }

    private void listar() {
        ConsoleUtil.imprimirTitulo("LIVROS");
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
            return;
        }
        TreeMap<String, Livro> livrosOrdenados = new TreeMap<>();
        for (Livro livro : livros) {
            livrosOrdenados.put(livro.getNome() + " #" + livro.getId(), livro);
        }

        for (String chave : livrosOrdenados.keySet()) {
            System.out.println(livrosOrdenados.get(chave));
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

    private void buscarLivro() {
        ConsoleUtil.imprimirTitulo("BUSCAR LIVRO");
        System.out.println("Digite o ID do livro: ");
        int id = ConsoleUtil.lerInt();

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

        System.out.println("\n--- Categorias disponíveis ---");
        for (Categoria categoria : categorias) {
            System.out.println(categoria);
        }

        System.out.println("Digite o ID da categoria: ");
        int id = ConsoleUtil.lerInt();

        for (Categoria categoria : categorias) {
            if (categoria.getId() == id) {
                return categoria;
            }
        }

        System.out.println("Categoria não encontrada.");
        return null;
    }

    private void registrarLivro() {
        ConsoleUtil.imprimirTitulo("REGISTRAR LIVRO");

        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria cadastrada. Cadastre uma categoria antes de registrar um livro.");
            return;
        }

        System.out.println("Nome: ");
        String nome = ConsoleUtil.teclado.nextLine();

        System.out.println("Autor: ");
        String autor = ConsoleUtil.teclado.nextLine();

        System.out.println("Copias: ");
        int copias = ConsoleUtil.lerInt();

        Categoria categoria = escolherCategoria();
        if (categoria == null) {
            System.out.println("Registro cancelado.");
            return;
        }

        Livro livro = new Livro(nome, autor, copias, categoria);
        livros.add(livro);

        Historico.registrar("Livro registrado: " + livro.getNome() + " (ID " + livro.getId() + ")");
        System.out.println("\nLivro registrado com sucesso! ID: " + livro.getId());
    }

    private void editarLivro() {
        ConsoleUtil.imprimirTitulo("EDITAR LIVRO");

        System.out.println("Digite o ID que deseja editar: ");
        int id = ConsoleUtil.lerInt();

        Livro livro = buscarPorId(id);
        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        System.out.println("Deixe em branco para manter o valor atual.");

        System.out.println("Nome (" + livro.getNome() + "): ");
        String nome = ConsoleUtil.teclado.nextLine();
        if (!nome.isBlank()) {
            livro.setNome(nome);
        }

        System.out.println("Autor (" + livro.getAutor() + "): ");
        String autor = ConsoleUtil.teclado.nextLine();
        if (!autor.isBlank()) {
            livro.setAutor(autor);
        }

        System.out.println("Copias (" + livro.getCopias() + "): ");
        String copiasEmString = ConsoleUtil.teclado.nextLine();
        if (!copiasEmString.isBlank()) {
            try {
                livro.setCopias(Integer.parseInt(copiasEmString));
            } catch (NumberFormatException erro) {
                System.out.println("Valor inválido, cópias não alterada.");
            }
        }

        System.out.println("Deseja alterar a categoria (" + livro.getCategoria().getNome() + ")? (Sim/Nao): ");
        String resposta = ConsoleUtil.teclado.nextLine();
        if (resposta.equalsIgnoreCase("Sim")) {
            Categoria categoria = escolherCategoria();
            if (categoria != null) {
                livro.setCategoria(categoria);
            }
        }

        Historico.registrar("Livro editado: " + livro.getNome() + " (ID " + livro.getId() + ")");
        System.out.println("\nLivro atualizado com sucesso.");
    }

    private void deletarLivro() {
        ConsoleUtil.imprimirTitulo("DELETAR LIVRO");

        System.out.println("Digite o ID do livro que deseja deletar: ");
        int id = ConsoleUtil.lerInt();

        Livro livro = buscarPorId(id);
        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        livros.remove(livro);
        Historico.registrar("Livro deletado: " + livro.getNome() + " (ID " + livro.getId() + ")");
        System.out.println("Livro deletado com sucesso.");
    }

    public void menuLivro() {
        int opcao;
        do {
            ConsoleUtil.limparTela();
            ConsoleUtil.imprimirTitulo("LIVROS");
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
                case 2 -> buscarLivro();
                case 3 -> registrarLivro();
                case 4 -> editarLivro();
                case 5 -> deletarLivro();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }

            if (opcao != 0) {
                ConsoleUtil.pausar();
            }
        } while (opcao != 0);
    }
}