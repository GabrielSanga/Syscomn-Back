package com.projeto.syscomn.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.projeto.syscomn.domain.OrdemProducaoRacao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdemProducaoRacaoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer idOrdemProducaoRacao; 	
	
	@NotNull(message = "Data da Produção é campo de preenchimento obrigatório!")
	private LocalDate data;
	
	@NotNull(message = "Quantidade a produzir é campo de preenchimento obrigatório")
	private Integer quantidadeProduzir;
	
	private Float valorOrdemProducao;
	
	private Integer idFuncionario;
	private String nomeFuncionario;
	

	public OrdemProducaoRacaoDTO() {
		super();
	}
	
	public OrdemProducaoRacaoDTO(OrdemProducaoRacao pOrdemProducaoRacao) {
		super();
		this.idOrdemProducaoRacao = pOrdemProducaoRacao.getIdOrdemProducaoRacao();
		this.data = pOrdemProducaoRacao.getData();
		this.quantidadeProduzir = pOrdemProducaoRacao.getQuantidadeProduzir();
		this.valorOrdemProducao = pOrdemProducaoRacao.getValorOrdemProducao();
		this.idFuncionario = pOrdemProducaoRacao.getFuncionario().getIdPessoa();
		this.nomeFuncionario = pOrdemProducaoRacao.getFuncionario().getNomePessoa();
	}

}
