package com.projeto.syscomn.domain.enums;

import com.projeto.syscomn.interfaces.CnpjGroup;
import com.projeto.syscomn.interfaces.CpfGroup;

import lombok.Getter;

@Getter
public enum TipoPessoa {
	
	Fisica(0, "Física", "CPF", "000.000.000-00", CpfGroup.class),
	Juridica(1, "Jurídica", "CNPJ", "00.000.000/0000-00", CnpjGroup.class);
	
	private final Integer codigo;
	private final String descricao;
	private final String documento;
	private final String mascara;
	private final Class<?> group;
	
	private TipoPessoa(Integer codigo, String descricao, String documento, String mascara, Class<?> group) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.documento = documento;
		this.mascara = mascara;
		this.group = group;
	}
	
	public static TipoPessoa toEnum(Integer codigo) {
		if(codigo == null) {
			return null;
		}
		
		for(TipoPessoa ePerfil : TipoPessoa.values()) {
			if(codigo.equals(ePerfil.getCodigo())) {
				return ePerfil;
			}
		}
		
		throw new IllegalArgumentException("Tipo Pessoa inválido!");	
	}
	
}
