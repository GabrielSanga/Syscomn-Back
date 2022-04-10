package com.projeto.syscomn.domain.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.projeto.syscomn.domain.FormulaRacao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormulaRacaoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer idFormulaRacao;
	
	private Integer quantidade;
	
	@NotNull(message = "Matéria Prima é campo de preenchimento obrigatório!")
	private Integer idMateriaPrima;
	private String descricaoMateriaPrima;
	
	@NotNull(message = "Ração é campo de preenchimento obrigatório!")
	private Integer idRacao;
	private String descricaoRacao;
	
	public FormulaRacaoDTO() {}

	public FormulaRacaoDTO(FormulaRacao pFormulaRacao) {
		super();
		this.idFormulaRacao = pFormulaRacao.getIdFormulaRacao();
		this.quantidade = pFormulaRacao.getQuantidade();
		this.idMateriaPrima = pFormulaRacao.getMateriaPrima().getIdMateriaPrima();
		this.descricaoMateriaPrima = pFormulaRacao.getMateriaPrima().getDescricao();
		this.idRacao = pFormulaRacao.getRacao().getIdRacao();
		this.descricaoRacao = pFormulaRacao.getRacao().getDescricao();
	}
	
}
