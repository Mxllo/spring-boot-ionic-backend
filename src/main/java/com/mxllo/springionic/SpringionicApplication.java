package com.mxllo.springionic;

import com.mxllo.springionic.model.Categoria;
import com.mxllo.springionic.model.Produto;
import com.mxllo.springionic.repositories.CategoriaRepository;
import com.mxllo.springionic.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class SpringionicApplication implements CommandLineRunner {

    @Autowired
    private CategoriaRepository repoCategoria;
    @Autowired
    private ProdutoRepository repoProduto;

    public static void main(String[] args) {
        SpringApplication.run(SpringionicApplication.class, args);
    }

    @Override
    public void run(String... args){
        Categoria cat1 = new Categoria(null, "Informática");
        Categoria cat2 = new Categoria(null, "Escritório");
        Produto p1 = new Produto(null, "Computador", 2000.00);
        Produto p2 = new Produto(null, "Impressora", 800.00);
        Produto p3 = new Produto(null, "Mouse", 27.50);

        cat1.getProduto().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProduto().add(p2);

        p1.getCategoria().addAll(Arrays.asList(cat1));
        p2.getCategoria().addAll(Arrays.asList(cat1, cat2));
        p3.getCategoria().addAll(Arrays.asList(cat1));

        repoCategoria.saveAll(Arrays.asList(cat1,cat2));
        repoProduto.saveAll(Arrays.asList(p1, p2, p3));
    }
}
