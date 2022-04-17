package com.projeto.syscomn.domain.dtos;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.projeto.syscomn.domain.LoteRacao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoteRacaoDTO {
	
	private Integer idLoteRacao;
	
	private LocalDate dataFabricacao;
	
	private LocalDate dataValidade;
	
	@NotNull(message = "Saldo é campo de preenchimento obrigatório!")
	private Double saldo;
	
	@NotNull(message = "Unidade é campo de preenchimento obrigatório!")
	private String unidade;
	
	private Double custo;
	
	@NotNull(message = "Ração é campo de preenchimento obrigatório!")
	private Integer idRacao;
	
	@NotNull(message = "Local de Armazenamento é campo de preenchimento obrigatório!")
	private Integer idLocalArmazenamento;

	public LoteRacaoDTO() {
		super();
	}
	
	public LoteRacaoDTO(LoteRacao pLoteRacao) {
		this.idLoteRacao = pLoteRacao.getIdLoteRacao();
		this.dataFabricacao = pLoteRacao.getDataFabricacao();
		this.dataValidade = pLoteRacao.getDataValidade();
		this.saldo = pLoteRacao.getSaldo();
		this.unidade = pLoteRacao.getUnidade();
		this.custo = pLoteRacao.getCusto();
		this.idRacao = pLoteRacao.getRacao().getIdRacaoProduzir();
		this.idLocalArmazenamento = pLoteRacao.getLocalArmazenamento().getIdLocalArmazenamento();
	}

}
