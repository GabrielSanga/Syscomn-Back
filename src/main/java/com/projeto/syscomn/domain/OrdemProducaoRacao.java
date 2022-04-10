package com.projeto.syscomn.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.projeto.syscomn.domain.dtos.OrdemProducaoRacaoDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class OrdemProducaoRacao implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idOrdemProducaoRacao; 	
	
	private LocalDate data;
	
	private Integer quantidadeProduzir;
	
	private Float valorOrdemProducao;
	
	@ManyToOne
	@JoinColumn(name = "idFuncionario")
	private Funcionario funcionario;

	public OrdemProducaoRacao() {
		super();
	}

	public OrdemProducaoRacao(OrdemProducaoRacaoDTO pOrdemProducaoRacaoDTO) {
		super();
		this.idOrdemProducaoRacao = pOrdemProducaoRacaoDTO.getIdOrdemProducaoRacao();
		this.data = pOrdemProducaoRacaoDTO.getData();
		this.quantidadeProduzir = pOrdemProducaoRacaoDTO.getQuantidadeProduzir();
		this.valorOrdemProducao = pOrdemProducaoRacaoDTO.getValorOrdemProducao();
		this.funcionario = new Funcionario();
	}	

}
