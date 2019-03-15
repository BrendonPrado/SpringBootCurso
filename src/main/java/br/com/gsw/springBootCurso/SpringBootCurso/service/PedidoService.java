package br.com.gsw.springBootCurso.SpringBootCurso.service;

import br.com.gsw.springBootCurso.SpringBootCurso.domain.Cliente;
import br.com.gsw.springBootCurso.SpringBootCurso.domain.ItemPedido;
import br.com.gsw.springBootCurso.SpringBootCurso.domain.PagamentoComBoleto;
import br.com.gsw.springBootCurso.SpringBootCurso.domain.Pedido;
import br.com.gsw.springBootCurso.SpringBootCurso.domain.enums.EstadoPagamento;
import br.com.gsw.springBootCurso.SpringBootCurso.repositories.ItemPedidoRepositories;
import br.com.gsw.springBootCurso.SpringBootCurso.repositories.PagamentoRepositories;
import br.com.gsw.springBootCurso.SpringBootCurso.repositories.PedidoRepositories;
import br.com.gsw.springBootCurso.SpringBootCurso.security.UserSS;
import br.com.gsw.springBootCurso.SpringBootCurso.service.exceptions.AuthorizationException;
import br.com.gsw.springBootCurso.SpringBootCurso.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
	ClienteService clienteService;

	@Autowired
	EmailService emailService;


	public Pedido find(Integer id) throws ObjectNotFoundException {
		Optional<Pedido> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontado do id :"+id+", classe: "+Cliente.class));
	}

    public Pedido insert(Pedido pedido) {
    	pedido.setId( null );
    	pedido.setInstante( new Date(  ) );
    	pedido.setCliente( clienteService.find( pedido.getCliente().getId() ));
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

	public Page<Pedido> findPage(Integer page,Integer linesPerPage,String orderBy,String direction){
		UserSS user = UserService.authenticated();
		if(user == null){
			throw new AuthorizationException( "Acesso negado" );
		}
		PageRequest pageRequest =  PageRequest.of( page,linesPerPage, Sort.Direction.valueOf( direction ),orderBy );
		Cliente cliente = clienteService.find( user.getId() );
		return repo.findByCliente( cliente,pageRequest );
	}

}
