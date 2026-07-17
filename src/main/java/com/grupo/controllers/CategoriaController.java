package com.grupo.controllers;

import java.util.LinkedList;
import java.util.TreeMap;

import com.grupo.models.Categoria;
import com.grupo.models.Livro;
import com.grupo.utils.ConsoleUtil;
import com.grupo.utils.Historico;

public class CategoriaController {
    private LinkedList<Categoria> categorias;
    private LinkedList<Livro> livros;

    public CategoriaController(LinkedList<Categoria> categorias, LinkedList<Livro> livros) {
        this.categorias = categorias;
        this.livros = livros;
    }

    private void listar() {
        ConsoleUtil.imprimirTitulo("CATEGORIAS");
        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria encontrada.");
            return;
        }

        TreeMap<String, Categoria> categoriasOrdenadas = new TreeMap<>();
        for (Categoria categoria : categorias) {
            categoriasOrdenadas.put(categoria.getNome() + " #" + categoria.getId(), categoria);
        }

        for (String chave : categoriasOrdenadas.keySet()) {
            System.out.println(categoriasOrdenadas.get(chave));
        }
    }

    private Categoria buscarPorId(int id) {
        for (Categoria categoria : categorias) {
            if (categoria.getId() == id) {
                return categoria;
            }
        }
        return null;
    }

    private void buscarCategoria() {
        ConsoleUtil.imprimirTitulo("BUSCAR CATEGORIA");
        System.out.println("Digite o ID da categoria: ");
        int id = ConsoleUtil.lerInt();

        Categoria categoria = buscarPorId(id);
        if (categoria == null) {
            System.out.println("Categoria não encontrada.");
        } else {
            System.out.println(categoria);
        }
    }

    private void registrarCategoria() {
        ConsoleUtil.imprimirTitulo("REGISTRAR CATEGORIA");

        System.out.println("Nome: ");
        String nome = ConsoleUtil.teclado.nextLine();

        Categoria categoria = new Categoria(nome);
        categorias.add(categoria);

        Historico.registrar("Categoria registrada: " + categoria.getNome() + " (ID " + categoria.getId() + ")");
        System.out.println("\nCategoria registrada com sucesso! ID: " + categoria.getId());
    }

    private void editarCategoria() {
        ConsoleUtil.imprimirTitulo("EDITAR CATEGORIA");

        System.out.println("Digite o ID da categoria que deseja editar: ");
        int id = ConsoleUtil.lerInt();

        Categoria categoria = buscarPorId(id);
        if (categoria == null) {
            System.out.println("Categoria não encontrada.");
            return;
        }

        System.out.println("Deixe em branco para manter o valor atual.");

        System.out.println("Nome (" + categoria.getNome() + "): ");
        String nome = ConsoleUtil.teclado.nextLine();
        if (!nome.isBlank()) {
            categoria.setNome(nome);
        }

        Historico.registrar("Categoria editada: " + categoria.getNome() + " (ID " + categoria.getId() + ")");
        System.out.println("\nCategoria atualizada com sucesso.");
    }

    private void deletarCategoria() {
        ConsoleUtil.imprimirTitulo("DELETAR CATEGORIA");

        System.out.println("Digite o ID da categoria que deseja deletar: ");
        int id = ConsoleUtil.lerInt();

        Categoria categoria = buscarPorId(id);
        if (categoria == null) {
            System.out.println("Categoria não encontrada.");
            return;
        }

        categorias.remove(categoria);
        Historico.registrar("Categoria deletada: " + categoria.getNome() + " (ID " + categoria.getId() + ")");
        System.out.println("Categoria deletada com sucesso.");
    }

    private void listarLivrosDaCategoria() {
        ConsoleUtil.imprimirTitulo("LIVROS POR CATEGORIA");

        Categoria categoria = buscarCategoriaParaFiltro();
        if (categoria == null) {
            return;
        }

        TreeMap<String, Livro> livrosDaCategoria = new TreeMap<>();
        for (Livro livro : livros) {
            if (livro.getCategoria().getId() == categoria.getId()) {
                livrosDaCategoria.put(livro.getNome() + " #" + livro.getId(), livro);
            }
        }

        System.out.println("\n--- Livros na categoria: " + categoria.getNome() + " ---");
        if (livrosDaCategoria.isEmpty()) {
            System.out.println("Nenhum livro encontrado nessa categoria.");
            return;
        }

        for (String chave : livrosDaCategoria.keySet()) {
            System.out.println(livrosDaCategoria.get(chave));
        }
    }

    private Categoria buscarCategoriaParaFiltro() {
        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria cadastrada.");
            return null;
        }

        System.out.println("\n--- Categorias disponíveis ---");
        for (Categoria categoria : categorias) {
            System.out.println(categoria);
        }

        System.out.println("Digite o ID da categoria: ");
        int id = ConsoleUtil.lerInt();

        Categoria categoria = buscarPorId(id);
        if (categoria == null) {
            System.out.println("Categoria não encontrada.");
        }
        return categoria;
    }

    public void menuCategoria() {
        int opcao;
        do {
            ConsoleUtil.limparTela();
            ConsoleUtil.imprimirTitulo("CATEGORIAS");
            System.out.println("1 - Listar");
            System.out.println("2 - Buscar");
            System.out.println("3 - Registrar");
            System.out.println("4 - Editar");
            System.out.println("5 - Deletar");
            System.out.println("6 - Listar Livros da Categoria");
            System.out.println("0 - Voltar");
            System.out.println("Escolha uma opção: ");

            opcao = ConsoleUtil.lerInt();
            System.out.println();

            switch (opcao) {
                case 1 -> listar();
                case 2 -> buscarCategoria();
                case 3 -> registrarCategoria();
                case 4 -> editarCategoria();
                case 5 -> deletarCategoria();
                case 6 -> listarLivrosDaCategoria();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }

            if (opcao != 0) {
                ConsoleUtil.pausar();
            }
        } while (opcao != 0);
    }
}