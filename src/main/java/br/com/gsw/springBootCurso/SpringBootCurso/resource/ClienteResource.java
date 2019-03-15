package br.com.gsw.springBootCurso.SpringBootCurso.resource;


import br.com.gsw.springBootCurso.SpringBootCurso.domain.Cliente;
import br.com.gsw.springBootCurso.SpringBootCurso.dto.ClienteDTO;
import br.com.gsw.springBootCurso.SpringBootCurso.dto.ClienteNewDTO;
import br.com.gsw.springBootCurso.SpringBootCurso.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	ClienteService service;

	//Read
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id){
		Cliente cli = service.find(id);
		return ResponseEntity.ok().body(cli);
	}
	
	//Create
	@RequestMapping(method = RequestMethod.POST )
	public ResponseEntity<Void> insert(@RequestBody @Valid ClienteNewDTO objDto){
		Cliente obj = service.fromDto(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	//Update
	@RequestMapping(value="/{id}",method = RequestMethod.PUT )
	public ResponseEntity<Void> update(@RequestBody @Valid ClienteDTO obj, @PathVariable Integer id){

		obj.setId(id);
		service.update(obj);
		return ResponseEntity.noContent().build();
	}

	//Delete
	@PreAuthorize( "hasAnyRole(ADMIN)" )
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE )
	public ResponseEntity<Void> delete(@PathVariable Integer id){

		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	//Read
	@PreAuthorize( "hasAnyRole(ADMIN)" )
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll(){
		List<ClienteDTO> list = service.findAll().stream()
				.map(obj -> new ClienteDTO(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}

	//Read
	@PreAuthorize( "hasAnyRole(ADMIN)" )
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value="page",defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage",defaultValue="24")   Integer linePerPage
			,@RequestParam(value="orderBy",defaultValue="nome")   String orderBy,
			@RequestParam(value="direction",defaultValue="ASC")	String direction){

		Page<ClienteDTO> list = service.findPage(page, linePerPage, orderBy, direction)
				.map(cli -> new ClienteDTO(cli));
		return ResponseEntity.ok().body(list);
	}
	

	
	
}
