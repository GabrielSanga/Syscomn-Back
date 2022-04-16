package com.projeto.syscomn.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	
	private String descricao;
	
	private LocalDate data;
	
	private Float valorOrdemProducao;
	
	private Integer status;
	
	@ManyToOne
	@JoinColumn(name = "idFuncionario")
	private Funcionario funcionario;
	
	@JsonIgnore
	@OneToMany(mappedBy = "ordemProducaoRacao")
	private List<RacaoProduzir> lstRacaoProduzir = new ArrayList<>();

	public OrdemProducaoRacao() {
		super();
	}

	public OrdemProducaoRacao(OrdemProducaoRacaoDTO pOrdemProducaoRacaoDTO) {
		super();
		this.idOrdemProducaoRacao = pOrdemProducaoRacaoDTO.getIdOrdemProducaoRacao();
		this.descricao = pOrdemProducaoRacaoDTO.getDescricao();
		this.data = pOrdemProducaoRacaoDTO.getData();
		this.valorOrdemProducao = pOrdemProducaoRacaoDTO.getValorOrdemProducao();
		this.funcionario = new Funcionario();
		this.status = pOrdemProducaoRacaoDTO.getStatus();
	}	

}
