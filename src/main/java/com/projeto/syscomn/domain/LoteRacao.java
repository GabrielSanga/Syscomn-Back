package com.projeto.syscomn.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.projeto.syscomn.domain.dtos.LoteRacaoDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class LoteRacao implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idLoteRacao;
	
	private LocalDate dataFabricacao;
	
	private LocalDate dataValidade;
	
	private Double quantidade;
	
	private Double saldo;
	
	private Double custo;
	
	@ManyToOne
	@JoinColumn(name = "idRacaoProduzir")
	private RacaoProduzir racao;
	
	@ManyToOne
	@JoinColumn(name = "idLocalArmazenamento")
	private LocalArmazenamento localArmazenamento;

	public LoteRacao() {
		super();
	}
	
	public LoteRacao(LoteRacaoDTO pLoteRacao) {
		this.idLoteRacao = pLoteRacao.getIdLoteRacao();
		this.dataFabricacao = pLoteRacao.getDataFabricacao();
		this.dataValidade = pLoteRacao.getDataValidade();
		this.quantidade = pLoteRacao.getQuantidade();
		this.saldo = pLoteRacao.getSaldo();
		this.custo = pLoteRacao.getCusto();
	}

}
