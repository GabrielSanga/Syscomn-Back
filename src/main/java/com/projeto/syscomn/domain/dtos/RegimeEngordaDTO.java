package com.projeto.syscomn.domain.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.projeto.syscomn.domain.Lote;
import com.projeto.syscomn.domain.RegimeEngorda;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegimeEngordaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	protected Integer idRegimeEngorda;

	@NotNull(message = "Descrição é campo de preenchimento obrigatório!")
	protected String descricao;
	
	@Getter
	private List<Lote> lstLote = new ArrayList<>();

	public RegimeEngordaDTO() {}
	
	public RegimeEngordaDTO(RegimeEngorda obj) {
		super();
		this.idRegimeEngorda = obj.getIdRegimeEngorda();
		this.descricao = obj.getDescricao();
	}

}
