package br.com.gsw.springBootCurso.SpringBootCurso.domain;

import java.io.Serializable;

public class Telefone implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String Numero;
	
	public Telefone(String numero) {
		Numero = numero;
	}

	public String getNumero() {
		return Numero;
	}

	public void setNumero(String numero) {
		Numero = numero;
	}

	
	

	public Telefone() {
		super();
	}
	
	
	

}
