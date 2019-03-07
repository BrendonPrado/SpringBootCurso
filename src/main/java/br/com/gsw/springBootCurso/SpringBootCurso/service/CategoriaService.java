package br.com.gsw.springBootCurso.SpringBootCurso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.gsw.springBootCurso.SpringBootCurso.domain.Categoria;
import br.com.gsw.springBootCurso.SpringBootCurso.dto.CategoriaDTO;
import br.com.gsw.springBootCurso.SpringBootCurso.repositories.CategoriaRepositories;
import br.com.gsw.springBootCurso.SpringBootCurso.service.exceptions.DataIntegrityException;
import br.com.gsw.springBootCurso.SpringBootCurso.service.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepositories repo;
	
	
	public Categoria find(Integer id) throws ObjectNotFoundException {
		Optional<Categoria> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontado do id :"+id+", Categoria: "+Categoria.class));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);	
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não foi possível deletar está categoria pois possui um ou mais produtos");
		}
	}

	public List<Categoria> findAll() {
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page,Integer linePerPage,String orderBy,String direction){
		PageRequest pageRequest = PageRequest.of(page, linePerPage,Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
}
