package br.com.gsw.springBootCurso.SpringBootCurso.domain.enums;

public enum TipoCliente {
	PESSOAFISICA(1,"Pessoa Fisica"),
	PESSOAJURIDICA(2,"Pessoa Juridica");
	
	
	private int cod;

	private String descricao;
	

	public String getDescricao() {
		return descricao;
	}
	public int getCod() {
		return cod;
	}
	
	
	
	
	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	
	public static TipoCliente toEnum(Integer cod) {
		if(cod==null) {
			return null;
		}
		
		for (TipoCliente  x: TipoCliente.values()) {
			if(cod == x.getCod()) {
				return x;
			}
		}
		throw new IllegalArgumentException("ID: "+cod+" não achado ");
	}
	
}
