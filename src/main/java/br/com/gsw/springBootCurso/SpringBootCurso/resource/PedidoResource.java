package br.com.gsw.springBootCurso.SpringBootCurso.resource;

import br.com.gsw.springBootCurso.SpringBootCurso.domain.Pedido;
import br.com.gsw.springBootCurso.SpringBootCurso.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {
	
	@Autowired
	PedidoService service;

	//read
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<Pedido> find(@PathVariable Integer id){
		Pedido ped = service.find(id);
		return ResponseEntity.ok().body(ped);
	}

	//create
	@RequestMapping(method = RequestMethod.POST )
	public ResponseEntity<Void> insert(@RequestBody @Valid Pedido pedido){
		Pedido obj = service.insert(pedido);
		System.out.println(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
