package com.mxllo.springionic;

import com.mxllo.springionic.model.*;
import com.mxllo.springionic.model.enums.TipoCliente;
import com.mxllo.springionic.repositories.*;
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
    @Autowired
    private EstadoRepository repoEstado;
    @Autowired
    private CidadeRepository repoCidade;
    @Autowired
    private ClienteRepository repoCliente;
    @Autowired
    private EnderecoRepository repoEndereco;

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

        Estado est1 = new Estado(null,"Minas Gerais");
        Estado est2 = new Estado(null,"São Paulo");

        Cidade c1 = new Cidade(null, "Uberlândia", est1);
        Cidade c2 = new Cidade(null, "São Paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas", est2);
        Cidade c4 = new Cidade(null, "Mogi das Cruzes", est2);

        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2,c3,c4));

        repoEstado.saveAll(Arrays.asList(est1,est2));
        repoCidade.saveAll(Arrays.asList(c1,c2,c3,c4));

        Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com",
                "38399127842", TipoCliente.PESSOAFISICA);
        cli1.getTelefones().addAll(Arrays.asList("11957709902","11971123475"));

        Endereco e1 = new Endereco(null,"Rua Flores", "300",
                "Apto 303","Jardim","08810020", cli1, c4);
        Endereco e2 = new Endereco(null,"Avenida Matos", "32",
                "","Centro","08530040", cli1, c2);

        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

        repoCliente.saveAll(Arrays.asList(cli1));
        repoEndereco.saveAll(Arrays.asList(e1, e2));
    }
}
