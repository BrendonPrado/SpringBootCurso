package br.com.gsw.springBootCurso.SpringBootCurso.resource;

import br.com.gsw.springBootCurso.SpringBootCurso.dto.ProdutoDTO;
import br.com.gsw.springBootCurso.SpringBootCurso.resource.utils.URL;
import br.com.gsw.springBootCurso.SpringBootCurso.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
	
	@Autowired
	ProdutoService service;

	//Read
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<br.com.gsw.springBootCurso.SpringBootCurso.domain.Produto> find(@PathVariable Integer id){
		br.com.gsw.springBootCurso.SpringBootCurso.domain.Produto prod = service.find(id);
		return ResponseEntity.ok().body(prod);
	}


	//Read
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> search(@RequestParam(value = "nome",defaultValue = "") String nome,
												   @RequestParam(value = "categorias", defaultValue = "") String categoriaIds,
												   @RequestParam(value="page", defaultValue="0") Integer page,
												   @RequestParam(value="linesPerPage",defaultValue="24")   Integer linePerPage
													, @RequestParam(value="orderBy",defaultValue="nome")   String orderBy,
												   @RequestParam(value="direction",defaultValue="ASC")	String direction) throws UnsupportedEncodingException {
		String nomeDecode = URL.decodeParam( nome );
		List<Integer> lista = URL.decodeIntList(categoriaIds);
		Page<ProdutoDTO> list = service.findPage( nome,  lista,page, linePerPage, orderBy, direction).map( p -> new ProdutoDTO(p) );
		return ResponseEntity.ok().body(list);
	}



}
