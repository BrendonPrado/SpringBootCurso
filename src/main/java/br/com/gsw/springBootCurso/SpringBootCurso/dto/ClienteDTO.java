package br.com.gsw.springBootCurso.SpringBootCurso.dto;

import br.com.gsw.springBootCurso.SpringBootCurso.domain.Cliente;
import br.com.gsw.springBootCurso.SpringBootCurso.service.validation.ClienteUpdate;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ClienteUpdate
public class ClienteDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	
	@NotEmpty(message="O campo não pode ser vazio")
	@Length(min=5,max=80,message="O campo deve ter entre 5 a 80 caracteres")
	private String nome;
	
	@NotEmpty(message="O campo não pode ser vazio")
	@Email(message="Email inválido")
	private String email;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ClienteDTO(Integer id,
					  @NotEmpty(message = "O campo não pode ser vazio") @Length(min = 5, max = 80, message = "O campo deve ter entre 5 a 80 caracteres") String nome,
					  @NotEmpty(message = "O campo não pode ser vazio") @Email(message = "Email inválido") String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}

	public ClienteDTO(Cliente obj) {
		this.id = obj.getId();
		this.email = obj.getEmail();
		this.nome = obj.getNome();
	}
	
	
}
