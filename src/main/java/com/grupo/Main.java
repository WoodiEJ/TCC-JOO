package com.grupo;

import java.util.LinkedList;

import com.grupo.controllers.CategoriaController;
import com.grupo.controllers.ClienteController;
import com.grupo.controllers.LivroController;
import com.grupo.controllers.ReservaController;
import com.grupo.models.Categoria;
import com.grupo.models.Cliente;
import com.grupo.models.Livro;
import com.grupo.seed.Seed;
import com.grupo.utils.ConsoleUtil;
import com.grupo.utils.Historico;

public class Main {
    static LinkedList<Categoria> categorias = new LinkedList<>();
    static LinkedList<Livro> livros = new LinkedList<>();
    static LinkedList<Cliente> clientes = new LinkedList<>();

    static LivroController livroController = new LivroController(livros, categorias);
    static ClienteController clienteController = new ClienteController(clientes);
    static CategoriaController categoriaController = new CategoriaController(categorias);
    static ReservaController reservaController = new ReservaController(livros, clientes);

    public static void main(String[] args) {
        Seed.popular(categorias, livros, clientes);
        int opcao;

        do {
            ConsoleUtil.limparTela();
            exibirMenu();
            System.out.println("Escolha uma opção: ");
            opcao = ConsoleUtil.lerInt();
            System.out.println();

            switch (opcao) {
                case 1:
                    livroController.menuLivro();
                    break;
                case 2:
                    clienteController.menuCliente();
                    break;
                case 3:
                    categoriaController.menuCategoria();
                    break;
                case 4:
                    reservaController.menuReserva();
                    break;
                case 5:
                    Historico.mostrar();
                    ConsoleUtil.pausar();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opcao invalida! Tente novamente.");
                    ConsoleUtil.pausar();
                    break;
            }

        } while (opcao != 0);

        ConsoleUtil.teclado.close();
    }

    static void exibirMenu() {
        ConsoleUtil.imprimirTitulo("BIBLIOTECA - MENU");
        System.out.println("1 - Livros");
        System.out.println("2 - Clientes");
        System.out.println("3 - Categorias");
        System.out.println("4 - Reservas");
        System.out.println("5 - Histórico de Ações");
        System.out.println("0 - Sair");
    }
}