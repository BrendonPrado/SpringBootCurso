package br.com.gsw.springBootCurso.SpringBootCurso.resource;



import br.com.gsw.springBootCurso.SpringBootCurso.domain.Categoria;
import br.com.gsw.springBootCurso.SpringBootCurso.dto.CategoriaDTO;
import br.com.gsw.springBootCurso.SpringBootCurso.service.CategoriaService;
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
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;
	

	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
		public ResponseEntity<Categoria> find(@PathVariable Integer id){
		Categoria cat = service.find(id);
		return ResponseEntity.ok().body(cat);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST )
	public ResponseEntity<Void> insert(@RequestBody @Valid CategoriaDTO objDto){
		Categoria obj = service.fromDto(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}",method = RequestMethod.PUT )
	public ResponseEntity<Void> update(@RequestBody CategoriaDTO obj, @PathVariable Integer id){
		
		obj.setId(id);
		service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE )
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll(){
		List<CategoriaDTO> list = service.findAll().stream()
				.map(cat -> new CategoriaDTO(cat))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}

	
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(value="page",defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage",defaultValue="24")   Integer linePerPage
			,@RequestParam(value="orderBy",defaultValue="nome")   String orderBy,
			@RequestParam(value="direction",defaultValue="ASC")	String direction){
		Page<CategoriaDTO> list = service.findPage(page, linePerPage, orderBy, direction)
				.map(cat -> new CategoriaDTO(cat));
		return ResponseEntity.ok().body(list);
	}
	
	
}
