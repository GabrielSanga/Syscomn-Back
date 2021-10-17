package com.projeto.syscomn.domain.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.projeto.syscomn.domain.Raca;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RacaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected Integer idRaca;
	
	@NotNull(message = "Descrição é campo de preenchimento obrigatório!")
	protected String descricao;
	
	public RacaDTO() {}

	public RacaDTO(Raca pRaca) {
		super();
		this.idRaca = pRaca.getIdRaca();
		this.descricao = pRaca.getDescricao();
	}
	
}
