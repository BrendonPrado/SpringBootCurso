package br.com.gsw.springBootCurso.SpringBootCurso.domain;

import br.com.gsw.springBootCurso.SpringBootCurso.domain.enums.Perfil;
import br.com.gsw.springBootCurso.SpringBootCurso.domain.enums.TipoCliente;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Cliente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String email;
	private String cpfOuCnpj;
	private Integer tipoCliente;

	@JsonIgnore
	private String senha;



    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PERFIS")
    private Set<Integer> perfis = new HashSet<>(  );

    @ElementCollection
	@CollectionTable(name="Telefone")
	private Set<String> tels = new HashSet<>();
	
	@OneToMany(mappedBy="cliente",cascade = CascadeType.ALL)
	private Set<Endereco> enderecos = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="cliente")
	private List<Pedido> pedidos = new ArrayList<>();
	
	public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipoCliente,String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipoCliente = tipoCliente.getCod();
		this.senha = senha;
        addPerfil( Perfil.CLIENTE );
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}
	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public TipoCliente getTipoCliente() {
		return TipoCliente.toEnum(this.tipoCliente);
	}
	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente.getCod();
	}


	public Set<String> getTels() {
		return tels;
	}


	public void setTels(Set<String> tels) {
		this.tels = tels;
	}


	public Set<Endereco> getEnderecos() {
		return enderecos;
	}


	public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	public Cliente() {
		addPerfil( Perfil.CLIENTE );

	}


	public List<Pedido> getPedidos() {
		return pedidos;
	}


	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}


	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}



    public Set<Perfil> getPerfis() {
        return perfis.stream().map( x -> Perfil.toEnum(x) ).collect( Collectors.toSet() );
    }

    public void addPerfil(Perfil perfil){
	    this.perfis.add(perfil.getCod() );
    }
}
