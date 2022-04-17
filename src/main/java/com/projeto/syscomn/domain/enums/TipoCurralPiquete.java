package com.projeto.syscomn.domain.enums;

import lombok.Getter;

@Getter
public enum TipoCurralPiquete {
		
	CURRAL(0, "Curral"), PIQUETE(1, "Piquete");
	
	private Integer codigo;
	private String descricao;
	
	private TipoCurralPiquete(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public static TipoCurralPiquete toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		for(TipoCurralPiquete x : TipoCurralPiquete.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Tipo Curral ou Piquete Inválido"); 
	}	

}
