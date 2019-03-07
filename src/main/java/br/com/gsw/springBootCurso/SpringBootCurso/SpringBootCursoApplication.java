package br.com.gsw.springBootCurso.SpringBootCurso;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.gsw.springBootCurso.SpringBootCurso.domain.Categoria;
import br.com.gsw.springBootCurso.SpringBootCurso.domain.Cidade;
import br.com.gsw.springBootCurso.SpringBootCurso.domain.Cliente;
import br.com.gsw.springBootCurso.SpringBootCurso.domain.Endereco;
import br.com.gsw.springBootCurso.SpringBootCurso.domain.Estado;
import br.com.gsw.springBootCurso.SpringBootCurso.domain.ItemPedido;
import br.com.gsw.springBootCurso.SpringBootCurso.domain.Pagamento;
import br.com.gsw.springBootCurso.SpringBootCurso.domain.PagamentoComBoleto;
import br.com.gsw.springBootCurso.SpringBootCurso.domain.PagamentoComCartao;
import br.com.gsw.springBootCurso.SpringBootCurso.domain.Pedido;
import br.com.gsw.springBootCurso.SpringBootCurso.domain.Produto;
import br.com.gsw.springBootCurso.SpringBootCurso.domain.Telefone;
import br.com.gsw.springBootCurso.SpringBootCurso.domain.enums.EstadoPagamento;
import br.com.gsw.springBootCurso.SpringBootCurso.domain.enums.TipoCliente;
import br.com.gsw.springBootCurso.SpringBootCurso.repositories.CategoriaRepositories;
import br.com.gsw.springBootCurso.SpringBootCurso.repositories.CidadeRepositories;
import br.com.gsw.springBootCurso.SpringBootCurso.repositories.ClienteRepositories;
import br.com.gsw.springBootCurso.SpringBootCurso.repositories.EnderecoRepositories;
import br.com.gsw.springBootCurso.SpringBootCurso.repositories.EstadoRepositories;
import br.com.gsw.springBootCurso.SpringBootCurso.repositories.ItemPedidoRepositories;
import br.com.gsw.springBootCurso.SpringBootCurso.repositories.PagamentoRepositories;
import br.com.gsw.springBootCurso.SpringBootCurso.repositories.PedidoRepositories;
import br.com.gsw.springBootCurso.SpringBootCurso.repositories.ProdutoRepositories;

@SpringBootApplication
public class SpringBootCursoApplication  implements CommandLineRunner{
	
	
	@Autowired
	CategoriaRepositories categoriaRepo;
	
	@Autowired
	ProdutoRepositories produtoRepositories;
	
	@Autowired
	CidadeRepositories cidadeRepositories;
	
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
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootCursoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null,"Escritorio");
		Categoria cat3 = new Categoria(null, "Remedios");
		Categoria cat4 = new Categoria(null,"Casa Mesa e Bug");
		Categoria cat5 = new Categoria(null, "Jogos");
		Categoria cat6 = new Categoria(null,"Mobilias");
		Categoria cat7 = new Categoria(null, "eletronicos");
		Categoria cat8 = new Categoria(null,"eletros");
		
		Produto p1 = new Produto(null,"computador", 2000.0);
		Produto p2 = new Produto(null,"Impressora", 800.0);
		Produto p3 = new Produto(null,"Mouse", 80.0);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().add(cat1);
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
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
		
		
		Cliente cl1 = new Cliente(null, "Maria Silva", "maria@gmail.com","621461461", TipoCliente.PESSOAFISICA);

		Endereco end1 = new Endereco(null, "Rua Flores", "300", "apto 203", "Jardim", "297187178781", c1, cl1);
		Endereco end2 = new Endereco(null, "Avenida Matos", "105", "sala 800", "Centro", "917929127", c2, cl1);
		
		Telefone t1 = new Telefone("12314424343");
		Telefone t2 = new Telefone("28982989289");

		cl1.getTels().addAll(Arrays.asList(t1.getNumero(),t2.getNumero()));
		cl1.getEnderecos().addAll(Arrays.asList(end1,end2));
					

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
		
		categoriaRepo.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7,cat8));
		produtoRepositories.saveAll(Arrays.asList(p1,p2,p3));
		
		estadoRepositories.saveAll(Arrays.asList(e1,e2));
		cidadeRepositories.saveAll(Arrays.asList(c1,c2,c3));
		
		clienteRepositories.save(cl1);
		enderecoRepositories.saveAll(Arrays.asList(end1,end2));
		
		pedidoRepositories.saveAll(Arrays.asList(ped1, ped2));
		PagamentoRepositories.saveAll(Arrays.asList(pagto1, pagto2));
		
		itemPedidoRepositories.saveAll(Arrays.asList(ip1,ip2,ip3));
	}

}
