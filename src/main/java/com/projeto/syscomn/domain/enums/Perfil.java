package com.projeto.syscomn.domain.enums;

public enum Perfil {

	ADMIN(0, "ROLE_ADMIN"), FUNCIONARIO(1, "ROLE_FUNCIONARIO");
	
	private Integer codigo;
	private String descricao;
	
	private Perfil(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Perfil toEnum(Integer codigo) {
		if(codigo == null) {
			return null;
		}
		
		for(Perfil ePerfil : Perfil.values()) {
			if(codigo.equals(ePerfil.getCodigo())) {
				return ePerfil;
			}
		}
		
		throw new IllegalArgumentException("Perfil inválido");	
	}
		
}
