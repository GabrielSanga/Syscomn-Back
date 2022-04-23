package com.projeto.syscomn.domain.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.projeto.syscomn.domain.FormulaRacao;
import com.projeto.syscomn.domain.Racao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RacaoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idRacao;
	
	@NotNull(message = "Descrição é campo de preenchimento obrigatório!")
	private String descricao;
	
	@NotNull(message = "Dias de Validade é campo de preenchimento obrigatório!")
	private Integer diasValidade;

	@NotNull(message = "Unidade é campo de preenchimento obrigatório!")
	private String unidade;
	
	private List<FormulaRacao> lstMateriaPrima = new ArrayList<>();
	
	public RacaoDTO() {
		super();
	}
	
	public RacaoDTO(Racao pRacao) {
		super();
		this.idRacao = pRacao.getIdRacao();
		this.descricao = pRacao.getDescricao();
		this.lstMateriaPrima = pRacao.getLstMateriaPrima();
		this.diasValidade = pRacao.getDiasValidade();
		this.unidade =pRacao.getUnidade();
	}
	
}
