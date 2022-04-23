package com.projeto.syscomn.domain.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.projeto.syscomn.domain.LoteRacao;
import com.projeto.syscomn.domain.RacaoProduzir;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RacaoProduzirDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idRacaoProduzir;

	private Integer idRacao;
	private String descrRacao;
	
	private Integer idOrdemProducaoRacao;
	private String descrOrdemProducaoRacao;
	
	private Integer quantidade;
	
	private Double quantidadeProduzido;
		
	private List<LoteRacaoDTO> lstLoteRacao = new ArrayList<>();
	
	public RacaoProduzirDTO() {
		super();
	}
	
	public RacaoProduzirDTO(Integer idRacaoProduzir, Integer idRacao, String descrRacao, Integer idOrdemProducaoRacao,
			Integer quantidade) {
		super();
		this.idRacaoProduzir = idRacaoProduzir;
		this.idRacao = idRacao;
		this.descrRacao = descrRacao;
		this.idOrdemProducaoRacao = idOrdemProducaoRacao;
		this.quantidade = quantidade;
	}
		
	public RacaoProduzirDTO(RacaoProduzir pRacaoProduzir) {
		this.idRacaoProduzir = pRacaoProduzir.getIdRacaoProduzir();
		this.idRacao = pRacaoProduzir.getRacao().getIdRacao();
		this.descrRacao = pRacaoProduzir.getRacao().getDescricao();
		this.idOrdemProducaoRacao = pRacaoProduzir.getOrdemProducaoRacao().getIdOrdemProducaoRacao();
		this.quantidade = pRacaoProduzir.getQuantidade();
		this.lstLoteRacao = pRacaoProduzir.getLstLoteRacao().stream().map(x -> new LoteRacaoDTO(x)).collect(Collectors.toList());
		this.quantidadeProduzido = pRacaoProduzir.getLstLoteRacao().stream().mapToDouble(LoteRacao::getQuantidade).sum();
	}
	

}
