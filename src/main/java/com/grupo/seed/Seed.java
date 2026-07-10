package com.grupo.seed;

import java.util.LinkedList;

import com.grupo.models.Categoria;
import com.grupo.models.Cliente;
import com.grupo.models.Livro;
import com.grupo.utils.Historico;

public class Seed {
    public static void popular(LinkedList<Categoria> categorias, LinkedList<Livro> livros,
        LinkedList<Cliente> clientes) {

        Categoria romance = new Categoria("Romance");
        Categoria ficcaoCientifica = new Categoria("Ficção Científica");
        Categoria classicoBrasileiro = new Categoria("Clássico Brasileiro");

        categorias.add(romance);
        categorias.add(ficcaoCientifica);
        categorias.add(classicoBrasileiro);

        livros.add(new Livro("Dom Casmurro", "Machado de Assis", 3, classicoBrasileiro));
        livros.add(new Livro("Memórias Póstumas de Brás Cubas", "Machado de Assis", 2, classicoBrasileiro));
        livros.add(new Livro("Duna", "Frank Herbert", 1, ficcaoCientifica));
        livros.add(new Livro("Fundação", "Isaac Asimov", 2, ficcaoCientifica));
        livros.add(new Livro("Orgulho e Preconceito", "Jane Austen", 0, romance));

        clientes.add(new Cliente("Woodi Joly", "111.111.111-11"));
        clientes.add(new Cliente("Ana Beatriz Souza", "222.222.222-22"));
        clientes.add(new Cliente("Carlos Eduardo Lima", "333.333.333-33"));
        clientes.add(new Cliente("Fernanda Costa", "444.444.444-44"));

        Historico.registrar("Sistema iniciado com dados fictícios de exemplo");
    }
}