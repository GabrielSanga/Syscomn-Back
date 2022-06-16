package com.projeto.syscomn.domain.dtos;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.projeto.syscomn.domain.LoteRacao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoteRacaoDTO {
	
	private Integer idLoteRacao;
	
	private LocalDate dataFabricacao;
	
	private LocalDate dataValidade;
	
	@NotNull(message = "Quantidade é campo de preenchimento obrigatório!")
	@Range(min = 1, max = 999, message = "Quantidade deve ser maior que 0")
	private Double quantidade;
	
	private Double saldo;
	
	private Double custo;
	
	private String unidade;
	
	@NotNull(message = "Ração é campo de preenchimento obrigatório!")
	private Integer idRacaoProduzir;
	
	private Integer idRacao;
	private String descrRacao;
	
	@NotNull(message = "Local de Armazenamento é campo de preenchimento obrigatório!")
	private Integer idLocalArmazenamento;
	private String descrLocalArmazenamento;
	
	private Integer idOrdemProducao;

	public LoteRacaoDTO() {
		super();
	}
	
	public LoteRacaoDTO(LoteRacao pLoteRacao) {
		this.idLoteRacao = pLoteRacao.getIdLoteRacao();
		this.dataFabricacao = pLoteRacao.getDataFabricacao();
		this.dataValidade = pLoteRacao.getDataValidade();
		this.quantidade = pLoteRacao.getQuantidade();
		this.saldo = pLoteRacao.getSaldo();
		this.custo = pLoteRacao.getCusto();
		this.unidade = pLoteRacao.getRacaoProduzir().getRacao().getUnidade();
		this.idRacaoProduzir = pLoteRacao.getRacaoProduzir().getIdRacaoProduzir();
		this.idRacao = pLoteRacao.getRacaoProduzir().getRacao().getIdRacao();
		this.descrRacao = pLoteRacao.getRacaoProduzir().getRacao().getDescricao();
		this.idLocalArmazenamento = pLoteRacao.getLocalArmazenamento().getIdLocalArmazenamento();
		this.descrLocalArmazenamento = pLoteRacao.getLocalArmazenamento().getDescricao();
	}

}
