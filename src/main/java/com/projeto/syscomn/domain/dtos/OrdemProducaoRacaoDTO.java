package com.projeto.syscomn.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.projeto.syscomn.domain.OrdemProducaoRacao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdemProducaoRacaoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer idOrdemProducaoRacao; 
	
	@NotNull(message = "Descrição é campo de preenchimento obrigatório!")
	private String descricao;
	
	@NotNull(message = "Data da Produção é campo de preenchimento obrigatório!")
	private LocalDate data;
	
	private Float valorOrdemProducao;
	
	private Integer idFuncionario;
	private String nomeFuncionario;
	
	private Integer status;
	
	private List<RacaoProduzirDTO> lstRacaoProduzir = new ArrayList<>();

	public OrdemProducaoRacaoDTO() {
		super();
	}
	
	public OrdemProducaoRacaoDTO(OrdemProducaoRacao pOrdemProducaoRacao) {
		super();
		this.idOrdemProducaoRacao = pOrdemProducaoRacao.getIdOrdemProducaoRacao();
		this.descricao = pOrdemProducaoRacao.getDescricao();
		this.data = pOrdemProducaoRacao.getData();
		this.valorOrdemProducao = pOrdemProducaoRacao.getValorOrdemProducao();
		this.idFuncionario = pOrdemProducaoRacao.getFuncionario().getIdPessoa();
		this.nomeFuncionario = pOrdemProducaoRacao.getFuncionario().getNomePessoa();
		this.status = pOrdemProducaoRacao.getStatus();
		this.lstRacaoProduzir = pOrdemProducaoRacao.getLstRacaoProduzir().stream().map(x -> new RacaoProduzirDTO(x)).collect(Collectors.toList());
	}

}
