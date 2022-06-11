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
import com.projeto.syscomn.domain.dtos.VacinacaoDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Vacinacao implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idVacinacao;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime data;
	
	private String loteVacina;
	
	private Integer quantidadeDose;
	
	private Double custoDose;
	
	@ManyToOne
	@JoinColumn(name = "idAnimalChip")
	private AnimalChip animalChip;
	
	@ManyToOne
	@JoinColumn(name = "idPessoa")
	private Pessoa pessoa;
	
	@ManyToOne
	@JoinColumn(name = "idVacina")
	private Vacina vacina;

	public Vacinacao() {
		super();
	}

	public Vacinacao(VacinacaoDTO pVacinacaoDTO) {
		super();
		this.idVacinacao = pVacinacaoDTO.getIdVacinacao();
		this.data = pVacinacaoDTO.getData();
		this.loteVacina = pVacinacaoDTO.getLoteVacina();
		this.quantidadeDose = pVacinacaoDTO.getQuantidadeDose();
		this.custoDose = pVacinacaoDTO.getCustoDose();
	}
	
}
