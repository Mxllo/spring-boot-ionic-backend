package com.mxllo.springionic;

import com.mxllo.springionic.model.*;
import com.mxllo.springionic.model.enums.EstadoPagamento;
import com.mxllo.springionic.model.enums.TipoCliente;
import com.mxllo.springionic.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

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
    @Autowired
    private PedidoRepository repoPedido;
    @Autowired
    private PagamentoRepository repoPagamento;
    @Autowired
    private ItemPedidoRepository repoItemPedido;

    public static void main(String[] args) {
        SpringApplication.run(SpringionicApplication.class, args);
    }

    @Override
    public void run(String... args) throws ParseException {
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

        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Pedido ped1 = new Pedido(null, s.parse("30/11/2020 10:32"), cli1, e1);
        Pedido ped2 = new Pedido(null, s.parse("29/12/2020 22:35"), cli1, e2);

        Pagamento pagto1 = new PagamentoComCartão(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pagto1);
        Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2,
                s.parse("01/01/2021 00:00"), null);
        ped2.setPagamento(pagto2);

        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        repoPedido.saveAll(Arrays.asList(ped1, ped2));
        repoPagamento.saveAll(Arrays.asList(pagto1, pagto2));

        ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
        ItemPedido ip2 = new ItemPedido(ped1, p3 ,0.00, 2,  80.00);
        ItemPedido ip3 = new ItemPedido(ped2, p2,100.00, 1, 800.00);

        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().addAll(Collections.singletonList(ip3));

        p1.getItens().addAll(Collections.singletonList(ip1));
        p2.getItens().addAll(Collections.singletonList(ip3));
        p3.getItens().addAll(Collections.singletonList(ip2));

        repoItemPedido.saveAll(Arrays.asList(ip1, ip2, ip3));
    }
}
