package br.com.gsw.springBootCurso.SpringBootCurso.service;

import br.com.gsw.springBootCurso.SpringBootCurso.domain.Cliente;
import br.com.gsw.springBootCurso.SpringBootCurso.domain.ItemPedido;
import br.com.gsw.springBootCurso.SpringBootCurso.domain.PagamentoComBoleto;
import br.com.gsw.springBootCurso.SpringBootCurso.domain.Pedido;
import br.com.gsw.springBootCurso.SpringBootCurso.domain.enums.EstadoPagamento;
import br.com.gsw.springBootCurso.SpringBootCurso.repositories.ClienteRepositories;
import br.com.gsw.springBootCurso.SpringBootCurso.repositories.ItemPedidoRepositories;
import br.com.gsw.springBootCurso.SpringBootCurso.repositories.PagamentoRepositories;
import br.com.gsw.springBootCurso.SpringBootCurso.repositories.PedidoRepositories;
import br.com.gsw.springBootCurso.SpringBootCurso.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {
	@Autowired
	BoletoService boletoService;
	
	@Autowired
	private PedidoRepositories repo;
	@Autowired
	private PagamentoRepositories pagamentoRepositories;

	@Autowired
	ProdutoService produtoService;

	@Autowired
	ItemPedidoRepositories itemPedidoRepositories;

	@Autowired
	ClienteRepositories clienteRepositories;

	@Autowired
	EmailService emailService;


	public Pedido find(Integer id) throws ObjectNotFoundException {
		Optional<Pedido> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontado do id :"+id+", classe: "+Cliente.class));
	}

    public Pedido insert(Pedido pedido) {
    	pedido.setId( null );
    	pedido.setInstante( new Date(  ) );
    	pedido.setCliente( clienteRepositories.findById( pedido.getCliente().getId() ).orElse( null ) );
    	pedido.getPagamento().setEstadoPagamento( EstadoPagamento.PENDENTE );
    	pedido.getPagamento().setPedido( pedido );
    	if(pedido.getPagamento() instanceof PagamentoComBoleto){
    		PagamentoComBoleto pag = (PagamentoComBoleto) pedido.getPagamento();
			boletoService.preencherPagamentoComBoleto(pag,pedido.getInstante());
    	}
    	pedido = repo.save( pedido );
		System.out.println(pedido.getValorTotal());
    	pagamentoRepositories.save(pedido.getPagamento());
		System.out.println(pedido.getPagamento().getEstadoPagamento().getDescricao());
    	for(ItemPedido ip:pedido.getItens()){
    		ip.setDesconto(0.0);
    		ip.setProduto( produtoService.find( ip.getProduto().getId() ) );
    		ip.setPreco( ip.getProduto().getPreco());
    		ip.setPedido(pedido);
		}
    	itemPedidoRepositories.saveAll( pedido.getItens() );
		emailService.sendOrderConfirmationHtmlEmail( pedido );
    	return pedido;

	}
}
