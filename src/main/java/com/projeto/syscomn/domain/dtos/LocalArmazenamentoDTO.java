package com.projeto.syscomn.domain.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.projeto.syscomn.domain.LocalArmazenamento;
import com.projeto.syscomn.domain.LoteRacao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalArmazenamentoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	protected Integer idLocalArmazenamento;

	@NotNull(message = "Descrição é campo de preenchimento obrigatório!")
	protected String descricao;
	
	@NotNull(message = "Capacidade é campo de preenchimento obrigatório!")
	private Integer capacidade;
	
	private Double enderecado;
	
	private String status;
	
	private List<LoteRacaoDTO> lstLoteProducao = new ArrayList<>();
	
	public LocalArmazenamentoDTO() {}

	public LocalArmazenamentoDTO(LocalArmazenamento pLocalArmazenamento) {
		super();
		this.idLocalArmazenamento = pLocalArmazenamento.getIdLocalArmazenamento();
		this.descricao = pLocalArmazenamento.getDescricao();
		this.capacidade  = pLocalArmazenamento.getCapacidade();
		this.status = pLocalArmazenamento.getStatus();
		this.lstLoteProducao = pLocalArmazenamento.getLstLoteRacao().stream().map(x -> new LoteRacaoDTO(x)).collect(Collectors.toList());
		this.enderecado = pLocalArmazenamento.getLstLoteRacao().stream().mapToDouble(LoteRacao::getSaldo).sum();
	}

}
