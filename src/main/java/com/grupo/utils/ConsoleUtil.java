package com.grupo.utils;

import java.util.Scanner;

public class ConsoleUtil {

    private static final int LARGURA = 40;

    public static void limparTela() {
        try {
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception erro) {
        }
    }

    public static void pausar(Scanner teclado) {
        System.out.println("\nPressione ENTER para continuar...");
        teclado.nextLine();
    }

    public static void imprimirTitulo(String titulo) {
        String borda = "=".repeat(LARGURA);
        System.out.println(borda);
        System.out.println(centralizar(titulo));
        System.out.println(borda);
    }

    private static String centralizar(String texto) {
        int espacos = Math.max((LARGURA - texto.length()) / 2, 0);
        return " ".repeat(espacos) + texto;
    }
}