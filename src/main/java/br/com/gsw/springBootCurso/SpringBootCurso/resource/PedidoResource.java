package br.com.gsw.springBootCurso.SpringBootCurso.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.gsw.springBootCurso.SpringBootCurso.domain.Pedido;
import br.com.gsw.springBootCurso.SpringBootCurso.service.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {
	
	@Autowired
	PedidoService pedidoService; 
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<Pedido> find(@PathVariable Integer id){
		Pedido ped = pedidoService.find(id);
		return ResponseEntity.ok().body(ped);
	}

}
