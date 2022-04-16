package com.projeto.syscomn.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.projeto.syscomn.domain.dtos.RacaoProduzirDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class RacaoProduzir implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idRacaoProduzir;
	
	@ManyToOne
	@JoinColumn(name = "idRacao")
	private Racao racao;
	
	@ManyToOne
	@JoinColumn(name = "idOrdemProducaoRacao")
	private OrdemProducaoRacao ordemProducaoRacao;
	
	private Integer quantidade;

	public RacaoProduzir() {
		super();
	}

	public RacaoProduzir(Integer idRacaoProduzir, Racao racao, OrdemProducaoRacao ordemProducaoRacao, Integer quantidade) {
		super();
		this.idRacaoProduzir = idRacaoProduzir;
		this.racao = racao;
		this.ordemProducaoRacao = ordemProducaoRacao;
		this.quantidade = quantidade;
	}
	
	public RacaoProduzir(RacaoProduzirDTO pRacaoProduzirDTO) {
		this.idRacaoProduzir = pRacaoProduzirDTO.getIdRacaoProduzir();
		this.quantidade = pRacaoProduzirDTO.getQuantidade();
	}
	
	public RacaoProduzir(RacaoProduzirDTO pRacaoProduzirDTO, OrdemProducaoRacao pOrdemProducao, Racao pRacao) {
		this.idRacaoProduzir = pRacaoProduzirDTO.getIdRacaoProduzir();
		this.ordemProducaoRacao = pOrdemProducao;
		this.racao = pRacao;
		this.quantidade = pRacaoProduzirDTO.getQuantidade();
	}	

}
