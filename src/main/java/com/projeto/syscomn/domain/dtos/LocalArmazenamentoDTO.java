package com.projeto.syscomn.domain.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.projeto.syscomn.domain.LocalArmazenamento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalArmazenamentoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	protected Integer idLocalArmazenamento;

	@NotNull(message = "Descrição é campo de preenchimento obrigatório!")
	protected String descricao;
	
	public LocalArmazenamentoDTO() {}

	public LocalArmazenamentoDTO(LocalArmazenamento pLocalArmazenamento) {
		super();
		this.idLocalArmazenamento = pLocalArmazenamento.getIdLocalArmazenamento();
		this.descricao = pLocalArmazenamento.getDescricao();
	}

}
