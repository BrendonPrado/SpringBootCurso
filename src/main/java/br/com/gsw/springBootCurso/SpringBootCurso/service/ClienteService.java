package br.com.gsw.springBootCurso.SpringBootCurso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.gsw.springBootCurso.SpringBootCurso.domain.Cliente;
import br.com.gsw.springBootCurso.SpringBootCurso.dto.ClienteDTO;
import br.com.gsw.springBootCurso.SpringBootCurso.repositories.ClienteRepositories;
import br.com.gsw.springBootCurso.SpringBootCurso.service.exceptions.DataIntegrityException;
import br.com.gsw.springBootCurso.SpringBootCurso.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepositories repo;
	
	
	public Cliente find(Integer id) throws ObjectNotFoundException {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontado do id :"+id+", classe: "+Cliente.class));
	}
	
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Cliente update(ClienteDTO obj) {
		Cliente cli = find(obj.getId());
		updateData(cli,obj);
		return repo.save(cli);
	}

	private void updateData(Cliente cli, ClienteDTO obj) {
		cli.setEmail((obj.getEmail() == null)? null : obj.getEmail());
		cli.setNome((obj.getNome() == null )? null : obj.getNome());
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);	
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não foi possível deletar está categoria pois possui entidades relacionadas");
		}
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page,Integer linePerPage,String orderBy,String direction){
		PageRequest pageRequest = PageRequest.of(page, linePerPage,Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDto(ClienteDTO obj) {
		return  new Cliente(obj.getId(), obj.getNome(),obj.getEmail(),null,null);

	}
}
