package br.com.gsw.springBootCurso.SpringBootCurso.resource;

import br.com.gsw.springBootCurso.SpringBootCurso.domain.Pedido;
import br.com.gsw.springBootCurso.SpringBootCurso.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
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
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	//read
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<Pedido>> findPage(
												   @RequestParam(value="page", defaultValue="0") Integer page,
												   @RequestParam(value="linesPerPage",defaultValue="24")   Integer linePerPage
			                                       ,@RequestParam(value="orderBy",defaultValue="instante")   String orderBy,
												   @RequestParam(value="direction",defaultValue="DESC")	String direction) throws UnsupportedEncodingException {

		Page<Pedido> list = service.findPage(page, linePerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
}
