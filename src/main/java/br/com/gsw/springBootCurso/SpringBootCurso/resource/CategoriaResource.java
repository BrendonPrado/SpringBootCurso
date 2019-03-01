package br.com.gsw.springBootCurso.SpringBootCurso.resource;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.gsw.springBootCurso.SpringBootCurso.domain.Categoria;
import br.com.gsw.springBootCurso.SpringBootCurso.service.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;
	
    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public ResponseEntity<Categoria> listar(@PathVariable Integer id){
    	Categoria cat = service.buscar(id);
        return ResponseEntity.ok().body(cat);
    }
}
