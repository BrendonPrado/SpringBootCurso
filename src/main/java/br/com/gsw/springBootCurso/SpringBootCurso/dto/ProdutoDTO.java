package br.com.gsw.springBootCurso.SpringBootCurso.dto;

import br.com.gsw.springBootCurso.SpringBootCurso.domain.Produto;

import java.io.Serializable;

public class ProdutoDTO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String nome;
    private double preco;

    public ProdutoDTO(Produto p) {
        this.id = p.getId();
        this.nome = p.getNome();
        this.preco = p.getPreco();
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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public ProdutoDTO(){

    }
}
