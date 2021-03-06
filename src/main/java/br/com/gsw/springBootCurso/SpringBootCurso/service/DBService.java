package br.com.gsw.springBootCurso.SpringBootCurso.service;

import br.com.gsw.springBootCurso.SpringBootCurso.domain.*;
import br.com.gsw.springBootCurso.SpringBootCurso.domain.enums.EstadoPagamento;
import br.com.gsw.springBootCurso.SpringBootCurso.domain.enums.Perfil;
import br.com.gsw.springBootCurso.SpringBootCurso.domain.enums.TipoCliente;
import br.com.gsw.springBootCurso.SpringBootCurso.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    CategoriaRepositories categoriaRepo;

    @Autowired
    ProdutoRepositories produtoRepositories;

    @Autowired
    CidadeRepositories cidadeRepositories;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    EstadoRepositories estadoRepositories;

    @Autowired
    ClienteRepositories clienteRepositories;

    @Autowired
    EnderecoRepositories enderecoRepositories;

    @Autowired
    PedidoRepositories pedidoRepositories;

    @Autowired
    PagamentoRepositories PagamentoRepositories;


    @Autowired
    ItemPedidoRepositories itemPedidoRepositories;

    public void instantiateTestDatabase() throws ParseException {
        Categoria cat1 = new Categoria(null, "Informatica");
        Categoria cat2 = new Categoria(null,"Escritorio");
        Categoria cat3 = new Categoria(null, "Casa Mesa e Bug");
        Categoria cat4 = new Categoria(null,"Eletronicos");
        Categoria cat5 = new Categoria(null, "Jardinagem");
        Categoria cat6 = new Categoria(null,"Decoração");
        Categoria cat7 = new Categoria(null, "Perfumaria");

        Produto p1 = new Produto(null,"computador", 2000.0);
        Produto p2 = new Produto(null,"Impressora", 800.0);
        Produto p3 = new Produto(null,"Mouse", 80.0);
        Produto p4 = new Produto( null,"Mesa de escritorio",300.0 );
        Produto p5 = new Produto( null , "Toalha",50.0);
        Produto p6 = new Produto( null,"Colcha", 200.0 );
        Produto p7 = new Produto( null ,"TV",1200.0);
        Produto p8 = new Produto( null ,"Roçadeira",800.0);
        Produto p9 = new Produto( null,"Abajour",100.0);
        Produto p10 = new Produto( null,"Pendente",180.0);
        Produto p11 = new Produto( null,"Shampoo",90);

        cat1.getProdutos().addAll( Arrays.asList(p1,p2,p3));
        cat2.getProdutos().addAll(Arrays.asList(p2,p4));
        cat3.getProdutos().addAll(Arrays.asList(p5,p6));
        cat4.getProdutos().addAll(Arrays.asList(p1,p2,p3,p7));
        cat5.getProdutos().addAll(Arrays.asList(p8));
        cat6.getProdutos().addAll(Arrays.asList(p9,p10));
        cat7.getProdutos().addAll(Arrays.asList(p11));

        p1.getCategorias().addAll(Arrays.asList(cat1,cat4));
        p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1));
        p4.getCategorias().addAll(Arrays.asList(cat2));
        p5.getCategorias().addAll(Arrays.asList(cat3));
        p6.getCategorias().addAll(Arrays.asList(cat3));
        p7.getCategorias().addAll(Arrays.asList(cat4,cat1));
        p8.getCategorias().addAll(Arrays.asList(cat5));
        p9.getCategorias().addAll(Arrays.asList(cat6));
        p10.getCategorias().addAll(Arrays.asList(cat6));
        p11.getCategorias().addAll(Arrays.asList(cat7));

        Cidade c1 = new Cidade(null, "Uberlândia");
        Cidade c2 = new Cidade(null,"São Paulo");
        Cidade c3 = new Cidade(null, "Campinas");

        Estado e1 = new Estado(null, "Minas Gerais");
        Estado e2 = new Estado(null,"São Paulo");
        c1.setEstado(e1);
        c2.setEstado(e2);
        c3.setEstado(e2);

        e1.getCidades().add(c1);

        e2.getCidades().addAll(Arrays.asList(c2,c3));


        Cliente cl1 = new Cliente(null, "Maria Silva", "jonathas.moraes@gsw.com.br","621461461", TipoCliente.PESSOAFISICA,encoder.encode( "123456" ));
        Cliente cl2 = new Cliente(null, "Ana C", "ana@g.com","621461461", TipoCliente.PESSOAFISICA,encoder.encode( "123456" ));
        cl2.addPerfil( Perfil.ADMIN );

        Endereco end1 = new Endereco(null, "Rua Flores", "300", "apto 203", "Jardim", "297187178781", c1, cl1);
        Endereco end2 = new Endereco(null, "Avenida Matos", "105", "sala 800", "Centro", "917929127", c2, cl1);

        Telefone t1 = new Telefone("12314424343");
        Telefone t2 = new Telefone("28982989289");

        Telefone t3 = new Telefone("1238484414424343");
        Telefone t4 = new Telefone("54428982989289");

        cl1.getTels().addAll(Arrays.asList(t1.getNumero(),t2.getNumero()));
        cl1.getEnderecos().addAll(Arrays.asList(end1,end2));

        cl2.getTels().addAll( Arrays.asList( t3.getNumero(),t4.getNumero() ) );
        cl2.getEnderecos().addAll(Arrays.asList(end1,end2));


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cl1, end1);
        Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cl1, end2);

        cl1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);

        ped1.setPagamento(pagto1);
        ped2.setPagamento(pagto2);


        ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.0);
        ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.0);
        ItemPedido ip3 = new ItemPedido(ped2, p2, 100.0, 1, 800.0);

        ped1.getItens().addAll(Arrays.asList(ip1,ip2));
        ped2.getItens().addAll(Arrays.asList(ip3));

        p1.getItens().addAll(Arrays.asList(ip1,ip2));
        p2.getItens().addAll(Arrays.asList(ip3));
        p3.getItens().addAll(Arrays.asList(ip2));


        categoriaRepo.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
        produtoRepositories.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11));

        estadoRepositories.saveAll(Arrays.asList(e1,e2));
        cidadeRepositories.saveAll(Arrays.asList(c1,c2,c3));

        clienteRepositories.saveAll(Arrays.asList(cl1,cl2));
        enderecoRepositories.saveAll(Arrays.asList(end1,end2));

        pedidoRepositories.saveAll(Arrays.asList(ped1, ped2));
        PagamentoRepositories.saveAll(Arrays.asList(pagto1, pagto2));

        itemPedidoRepositories.saveAll(Arrays.asList(ip1,ip2,ip3));
    }
}
