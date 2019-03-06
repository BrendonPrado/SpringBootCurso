package br.com.gsw.springBootCurso.SpringBootCurso.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.com.gsw.springBootCurso.SpringBootCurso.domain.Cliente;
import br.com.gsw.springBootCurso.SpringBootCurso.repositories.ClienteRepositories;
import br.com.gsw.springBootCurso.SpringBootCurso.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepositories repo;
	
	
	public Cliente buscar(Integer id) throws ObjectNotFoundException {
		Optional<Cliente> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontado do id :"+id+", classe: "+Cliente.class));
	}
}
