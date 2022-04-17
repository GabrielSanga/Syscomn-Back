package com.projeto.syscomn.domain.enums;

import lombok.Getter;

@Getter
public enum StatusCurralPiquete {
	
	ABERTO(0, "Aberto"), FECHADO(1, "Fechado"), ENCERRADO(2, "Encerrado");
	
	private Integer codigo;
	private String descricao;
	
	private StatusCurralPiquete(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public static StatusCurralPiquete toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		for(StatusCurralPiquete x : StatusCurralPiquete.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Status do Curral Piquete Inválido"); 
	}	

}

