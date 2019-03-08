package br.com.gsw.springBootCurso.SpringBootCurso.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.gsw.springBootCurso.SpringBootCurso.domain.Categoria;

public class CategoriaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="O campo não pode ser vazio")
	@Length(min=5,max=80,message="O campo deve ter entre 5 a 80 caracteres")
	private String nome;
	

	
	public CategoriaDTO(Categoria cat) {
		this.id = cat.getId();
		this.nome = cat.getNome();
	}
	
	public CategoriaDTO() {
		
	}



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
	
	
	
	
}
