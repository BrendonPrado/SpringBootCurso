package br.com.gsw.springBootCurso.SpringBootCurso.resource;


import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.gsw.springBootCurso.SpringBootCurso.domain.Categoria;
import br.com.gsw.springBootCurso.SpringBootCurso.domain.Cliente;
import br.com.gsw.springBootCurso.SpringBootCurso.dto.ClienteDTO;
import br.com.gsw.springBootCurso.SpringBootCurso.service.ClienteService;


@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	ClienteService service;
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id){
		Cliente cli = service.find(id);
		return ResponseEntity.ok().body(cli);
	}
	
	
	@RequestMapping(method = RequestMethod.POST )
	public ResponseEntity<Void> insert(@RequestBody @Valid ClienteDTO objDto){
		Cliente obj = service.fromDto(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.PUT )
	public ResponseEntity<Void> update(@RequestBody @Valid ClienteDTO obj, @PathVariable Integer id){
		
		obj.setId(id);
		service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE )
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll(){
		List<ClienteDTO> list = service.findAll().stream()
				.map(obj -> new ClienteDTO(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}

	
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value="page",defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage",defaultValue="24")   Integer linePerPage
			,@RequestParam(value="orderBy",defaultValue="nome")   String orderBy,
			@RequestParam(value="direction",defaultValue="ASC")	String direction){
		Page<ClienteDTO> list = service.findPage(page, linePerPage, orderBy, direction)
				.map(cat -> new ClienteDTO(cat));
		return ResponseEntity.ok().body(list);
	}
	

	
	
}
