package com.grupo.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Stack;

public class Historico {

    private static Stack<String> acoes = new Stack<>();
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static void registrar(String acao) {
        String horario = LocalDateTime.now().format(FORMATO);
        acoes.push("[" + horario + "] " + acao);
    }

    public static void mostrar() {
        ConsoleUtil.imprimirTitulo("HISTÓRICO DE AÇÕES");

        if (acoes.isEmpty()) { // STACK: isEmpty
            System.out.println("Nenhuma ação registrada ainda.");
            return;
        }

        System.out.println("(mais recente primeiro)\n");
        for (int i = acoes.size() - 1; i >= 0; i--) {
            System.out.println(acoes.get(i));
        }
    }

    public static String ultimaAcao() {
        if (acoes.isEmpty()) {
            return "Nenhuma ação registrada ainda.";
        }
        return acoes.peek();
    }
}