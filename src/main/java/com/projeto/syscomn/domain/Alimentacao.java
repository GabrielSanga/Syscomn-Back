package com.projeto.syscomn.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.syscomn.domain.dtos.AlimentacaoDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Alimentacao implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAlimentacao;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime data;
	
	private Integer quantidade;
	
	private Double custo;
	
	@ManyToOne
	@JoinColumn(name = "idAnimalChip")
	private AnimalChip animalChip;
	
	@ManyToOne
	@JoinColumn(name = "idLoteRacao")
	private LoteRacao loteRacao;
	
	@ManyToOne
	@JoinColumn(name = "idPessoa")
	private Pessoa pessoa;

	public Alimentacao() {
		super();
	}

	public Alimentacao(AlimentacaoDTO pAlimentacaoDTO) {
		super();
		this.idAlimentacao = pAlimentacaoDTO.getIdAlimentacao();
		this.data = pAlimentacaoDTO.getData();
		this.quantidade = pAlimentacaoDTO.getQuantidade();
		this.custo = pAlimentacaoDTO.getCusto();
	}

}
