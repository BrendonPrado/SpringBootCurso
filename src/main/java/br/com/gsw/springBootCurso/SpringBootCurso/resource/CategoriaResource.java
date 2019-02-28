package br.com.gsw.springBootCurso.SpringBootCurso.resource;


import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.gsw.springBootCurso.SpringBootCurso.domain.Categoria;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @RequestMapping(method = RequestMethod.GET)
    public List<Categoria> listar(){
    	
    	Categoria categoria = new Categoria(1,"informatica");
    	Categoria categoria2 = new Categoria(2,"Aventura");
    	List<Categoria> lista = new ArrayList<>();
    	lista.add(categoria);
    	lista.add(categoria2);
    	
        return lista;
    }
}
