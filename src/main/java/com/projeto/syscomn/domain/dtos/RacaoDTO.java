package com.projeto.syscomn.domain.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	
	private String descricao;
	
	private List<FormulaRacao> lstMateriaPrima = new ArrayList<>();
	
	public RacaoDTO() {
		super();
	}
	
	public RacaoDTO(Racao pRacao) {
		super();
		this.idRacao = pRacao.getIdRacao();
		this.descricao = pRacao.getDescricao();
		this.lstMateriaPrima = pRacao.getLstMateriaPrima();
	}
	
}
