package com.grupo.controllers;

import java.util.LinkedList;
import java.util.Scanner;

import com.grupo.models.Livro;

public class LivroController {
    private LinkedList<Livro> livros = new LinkedList<>();
    private Scanner teclado = new Scanner(System.in);

    private void listar() {
        if 
    }

    public void menu() {
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
                case 2 -> buscar();
                case 3 -> registrar();
                case 4 -> editar();
                case 5 -> deletar();
                case 6 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao = 0);
    }
}
