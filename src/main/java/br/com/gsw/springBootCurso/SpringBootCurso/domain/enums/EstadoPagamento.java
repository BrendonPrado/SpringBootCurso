package br.com.gsw.springBootCurso.SpringBootCurso.domain.enums;

public enum EstadoPagamento {
	PENDENTE(1,"Pendente"),
	QUITADO(2,"Quitado"),
	CANCELADO(3,"Cancelado");
	
	private int cod;

	private String descricao;
	

	public String getDescricao() {
		return descricao;
	}
	public int getCod() {
		return cod;
	}
	
	
	
	
	private EstadoPagamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	
	public static EstadoPagamento toEnum(Integer cod) {
		if(cod==null) {
			return null;
		}
		
		for (EstadoPagamento  x: EstadoPagamento.values()) {
			if(cod == x.getCod()) {
				return x;
			}
		}
		throw new IllegalArgumentException("ID: "+cod+" não achado ");
	}
}
