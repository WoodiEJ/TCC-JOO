
package com.grupo;

import java.util.Scanner;

public class Main {

    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            System.out.println("Escolha uma opção: ");
            opcao = teclado.nextInt();

            switch (opcao) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
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

        teclado.close();
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
}