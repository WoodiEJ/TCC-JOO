package com.grupo.seed;

import com.grupo.models.Categoria;

public class Seed {
     public static void carregar(CategoriaController controller) {
        controller.adicionar(new Categoria("Terror"));
        controller.adicionar(new Categoria("Comédia"));
        controller.adicionar(new Categoria("Drama"));
        controller.adicionar(new Categoria("Romance"));
    }
}
