package com.projeto.syscomn.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projeto.syscomn.domain.dtos.RacaoDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Racao implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idRacao;
	
	private String descricao;
	
	private Integer diasValidade;
	
	private String unidade;
	
	@JsonIgnore
	@OneToMany(mappedBy = "racao")
	private List<FormulaRacao> lstMateriaPrima = new ArrayList<>();
	
	public Racao() {}

	public Racao(RacaoDTO pRacaoDTO) {
		super();
		this.idRacao = pRacaoDTO.getIdRacao();
		this.descricao = pRacaoDTO.getDescricao();
		this.diasValidade = pRacaoDTO.getDiasValidade();
		this.unidade = pRacaoDTO.getUnidade();
	}

}
