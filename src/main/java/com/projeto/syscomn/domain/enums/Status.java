package com.projeto.syscomn.domain.enums;

import lombok.Getter;

@Getter
public enum Status {

	ATIVO(0, "Ativo"), INATIVO(1, "Inativo");
	
	private Integer codigo;
	private String descricao;
	
	private Status(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public static Status toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		for(Status x : Status.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Status Inválido"); 
	}	
	
}
