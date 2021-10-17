package com.projeto.syscomn.domain.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.projeto.syscomn.domain.TipoMorte;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoMorteDTO  implements Serializable{
	private static final long serialVersionUID = 1L;

	protected Integer idTipoMorte;
	
	@NotNull(message = "Descrição é campo de preenchimento obrigatório!")
	protected String descricao;
	
	public TipoMorteDTO() {}
	
	public TipoMorteDTO(TipoMorte pTipoMorte) {
		super();
		this.idTipoMorte = pTipoMorte.getIdTipoMorte();
		this.descricao = pTipoMorte.getDescricao();
	}
	
}
