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
import com.projeto.syscomn.domain.dtos.PesagemDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Pesagem  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPesagem;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime data;
	
	private Double peso;
	
	private String observacao;
	
	@ManyToOne
	@JoinColumn(name = "idAnimalChip")
	private AnimalChip animalChip;
	
	@ManyToOne
	@JoinColumn(name = "idPessoa")
	private Pessoa pessoa;

	public Pesagem() {
		super();
	}

	public Pesagem(PesagemDTO pPesagemDTO) {
		super();
		this.idPesagem = pPesagemDTO.getIdPesagem();
		this.data = pPesagemDTO.getData();
		this.peso = pPesagemDTO.getPeso();
		this.observacao = pPesagemDTO.getObservacao();
	}	

}
