package br.com.gsw.springBootCurso.SpringBootCurso.domain.enums;

public enum Perfil {
	ADMIN(1,"ROLE_ADMIN"),
	CLIENTE(2,"ROLE_CLIENTE");


	private int cod;

	private String descricao;


	public String getDescricao() {
		return descricao;
	}
	public int getCod() {
		return cod;
	}




	private Perfil(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	
	public static Perfil toEnum(Integer cod) {
		if(cod==null) {
			return null;
		}
		
		for (Perfil x: Perfil.values()) {
			if(cod == x.getCod()) {
				return x;
			}
		}
		throw new IllegalArgumentException("ID: "+cod+" não achado ");
	}
	
}
