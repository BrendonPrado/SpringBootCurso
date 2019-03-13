package br.com.gsw.springBootCurso.SpringBootCurso.service;

import br.com.gsw.springBootCurso.SpringBootCurso.domain.Cidade;
import br.com.gsw.springBootCurso.SpringBootCurso.domain.Cliente;
import br.com.gsw.springBootCurso.SpringBootCurso.domain.Endereco;
import br.com.gsw.springBootCurso.SpringBootCurso.domain.enums.TipoCliente;
import br.com.gsw.springBootCurso.SpringBootCurso.dto.ClienteDTO;
import br.com.gsw.springBootCurso.SpringBootCurso.dto.ClienteNewDTO;
import br.com.gsw.springBootCurso.SpringBootCurso.repositories.ClienteRepositories;
import br.com.gsw.springBootCurso.SpringBootCurso.repositories.EnderecoRepositories;
import br.com.gsw.springBootCurso.SpringBootCurso.service.exceptions.DataIntegrityException;
import br.com.gsw.springBootCurso.SpringBootCurso.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepositories repo;

	@Autowired
	private EnderecoRepositories repoEnderecos;

	@Autowired
	BCryptPasswordEncoder encoder;

	public Cliente find(Integer id) throws ObjectNotFoundException {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontado do id :"+id+", classe: "+Cliente.class));
	}

    @Transactional
    public Cliente insert(Cliente obj) {
        obj.setId(null);
        obj = repo.save(obj);
		repoEnderecos.saveAll(obj.getEnderecos());
        return obj;
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
			throw new DataIntegrityException("Não foi possível deletar está categoria pois possui pedidos relacionados");
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
		return  new Cliente(obj.getId(), obj.getNome(),obj.getEmail(),null,null,null);
	}

	public Cliente fromDto(ClienteNewDTO obj) {

		Cliente cliente =  new Cliente(null, obj.getNome(),obj.getEmail(),obj.getCpfOuCnpj(), TipoCliente.toEnum( obj.getTipoCliente() ) ,encoder.encode( obj.getSenha() ));
		Cidade  cidade = new Cidade( obj.getCidadeId(),null,null );
		Endereco end = new Endereco(null,obj.getLogradouro(),obj.getNumero(),obj.getComplemento(),obj.getBairro(),obj.getCep(),cidade,cliente );

		cliente.getEnderecos().add(end);
		cliente.getTels().add(obj.getTelefone1());
		if(obj.getTelefone2() != null){
			cliente.getTels().add( obj.getTelefone2() );
		}

		if(obj.getTelefone3() != null){
			cliente.getTels().add( obj.getTelefone3() );
		}
		return cliente;
	}
}
