package com.grupo.seed;

import java.util.LinkedList;

import com.grupo.models.Categoria;
import com.grupo.models.Cliente;
import com.grupo.models.Livro;
import com.grupo.utils.Historico;

public class Seed {

    private static final int[] COPIAS_EM_CICLO = { 3, 2, 0, 1, 4, 2, 0, 1 };

    public static void popular(LinkedList<Categoria> categorias, LinkedList<Livro> livros,
            LinkedList<Cliente> clientes) {

        Categoria romance = new Categoria("Romance");
        Categoria ficcaoCientifica = new Categoria("Ficção Científica");
        Categoria classicoBrasileiro = new Categoria("Clássico Brasileiro");
        Categoria fantasia = new Categoria("Fantasia");
        Categoria suspense = new Categoria("Suspense");
        Categoria autoAjuda = new Categoria("Autoajuda");
        Categoria terror = new Categoria("Terror");
        Categoria biografia = new Categoria("Biografia");
        Categoria poesia = new Categoria("Poesia");

        categorias.add(romance);
        categorias.add(ficcaoCientifica);
        categorias.add(classicoBrasileiro);
        categorias.add(fantasia);
        categorias.add(suspense);
        categorias.add(autoAjuda);
        categorias.add(terror);
        categorias.add(biografia);
        categorias.add(poesia);

        adicionarLivros(livros, romance, new String[][] {
                { "Orgulho e Preconceito", "Jane Austen" },
                { "O Morro dos Ventos Uivantes", "Emily Brontë" },
                { "Como Eu Era Antes de Você", "Jojo Moyes" },
                { "O Diário de Bridget Jones", "Helen Fielding" },
                { "A Culpa é das Estrelas", "John Green" },
                { "Persuasão", "Jane Austen" },
                { "Razão e Sensibilidade", "Jane Austen" },
                { "Jane Eyre", "Charlotte Brontë" },
        });

        adicionarLivros(livros, ficcaoCientifica, new String[][] {
                { "Duna", "Frank Herbert" },
                { "Fundação", "Isaac Asimov" },
                { "Eu, Robô", "Isaac Asimov" },
                { "1984", "George Orwell" },
                { "Admirável Mundo Novo", "Aldous Huxley" },
                { "O Guia do Mochileiro das Galáxias", "Douglas Adams" },
                { "Neuromancer", "William Gibson" },
                { "Andróides Sonham com Ovelhas Elétricas?", "Philip K. Dick" },
        });

        adicionarLivros(livros, classicoBrasileiro, new String[][] {
                { "Dom Casmurro", "Machado de Assis" },
                { "Memórias Póstumas de Brás Cubas", "Machado de Assis" },
                { "Vidas Secas", "Graciliano Ramos" },
                { "Grande Sertão: Veredas", "Guimarães Rosa" },
                { "Iracema", "José de Alencar" },
                { "O Cortiço", "Aluísio Azevedo" },
                { "Capitães da Areia", "Jorge Amado" },
                { "Quincas Borba", "Machado de Assis" },
        });

        adicionarLivros(livros, fantasia, new String[][] {
                { "O Hobbit", "J.R.R. Tolkien" },
                { "O Nome do Vento", "Patrick Rothfuss" },
                { "Harry Potter e a Pedra Filosofal", "J.K. Rowling" },
                { "As Crônicas de Nárnia", "C.S. Lewis" },
                { "A Guerra dos Tronos", "George R.R. Martin" },
                { "O Senhor dos Anéis: A Sociedade do Anel", "J.R.R. Tolkien" },
                { "Mistborn: O Império Final", "Brandon Sanderson" },
                { "Eragon", "Christopher Paolini" },
        });

        adicionarLivros(livros, suspense, new String[][] {
                { "Garota Exemplar", "Gillian Flynn" },
                { "O Silêncio dos Inocentes", "Thomas Harris" },
                { "A Garota no Trem", "Paula Hawkins" },
                { "O Código Da Vinci", "Dan Brown" },
                { "Objetos Cortantes", "Gillian Flynn" },
                { "Sozinha", "Harlan Coben" },
                { "O Iluminado", "Stephen King" },
                { "A Menina que Roubava Livros", "Markus Zusak" },
        });

        adicionarLivros(livros, autoAjuda, new String[][] {
                { "O Poder do Hábito", "Charles Duhigg" },
                { "Mindset", "Carol Dweck" },
                { "Os 7 Hábitos das Pessoas Altamente Eficazes", "Stephen Covey" },
                { "Pai Rico, Pai Pobre", "Robert Kiyosaki" },
                { "O Milagre da Manhã", "Hal Elrod" },
                { "Essencialismo", "Greg McKeown" },
        });

        adicionarLivros(livros, terror, new String[][] {
                { "It: A Coisa", "Stephen King" },
                { "Drácula", "Bram Stoker" },
                { "Frankenstein", "Mary Shelley" },
                { "O Chamado de Cthulhu", "H.P. Lovecraft" },
                { "Bird Box", "Josh Malerman" },
                { "A Assombração da Casa da Colina", "Shirley Jackson" },
        });

        adicionarLivros(livros, biografia, new String[][] {
                { "Steve Jobs", "Walter Isaacson" },
                { "Longa Caminhada até a Liberdade", "Nelson Mandela" },
                { "Einstein: Sua Vida, Seu Universo", "Walter Isaacson" },
                { "A Sangue Frio", "Truman Capote" },
        });

        adicionarLivros(livros, poesia, new String[][] {
                { "Sentimento do Mundo", "Carlos Drummond de Andrade" },
                { "Antologia Poética", "Vinicius de Moraes" },
                { "Poemas Escolhidos", "Manuel Bandeira" },
                { "Romanceiro da Inconfidência", "Cecília Meireles" },
        });

        String[] nomesClientes = {
                "Woodi Joly", "Ana Beatriz Souza", "Carlos Eduardo Lima", "Fernanda Costa",
                "Rafael Almeida", "Juliana Martins", "Bruno Ferreira", "Camila Rocha",
                "Diego Santos", "Larissa Oliveira", "Thiago Pereira", "Beatriz Cardoso",
                "Gustavo Ribeiro", "Patrícia Gomes", "Eduardo Barbosa", "Mariana Teixeira",
                "Felipe Nascimento", "Isabela Correia", "Rodrigo Araújo", "Vinícius Monteiro",
        };

        for (int i = 0; i < nomesClientes.length; i++) {
            String cpf = String.format("%03d.%03d.%03d-%02d", i + 1, i + 1, i + 1, (i + 1) % 100);
            clientes.add(new Cliente(nomesClientes[i], cpf));
        }

        Historico.registrar("Sistema iniciado com dados fictícios de exemplo ("
                + livros.size() + " livros, " + clientes.size() + " clientes, "
                + categorias.size() + " categorias)");
    }

    private static void adicionarLivros(LinkedList<Livro> livros, Categoria categoria, String[][] dados) {
        for (int i = 0; i < dados.length; i++) {
            String nome = dados[i][0];
            String autor = dados[i][1];
            int copias = COPIAS_EM_CICLO[i % COPIAS_EM_CICLO.length];
            livros.add(new Livro(nome, autor, copias, categoria));
        }
    }
}