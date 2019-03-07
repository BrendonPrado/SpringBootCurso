package br.com.gsw.springBootCurso.SpringBootCurso.domain;

import javax.persistence.Entity;

import br.com.gsw.springBootCurso.SpringBootCurso.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer numeroDeParcelas;

	public PagamentoComCartao(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Integer numeroDeParcelas) {
		super(id, estadoPagamento, pedido);
		this.setNumeroDeParcelas(numeroDeParcelas);
	}

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}

	public PagamentoComCartao() {
	}

	
	
	
}
