package com.projeto.syscomn.domain.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.syscomn.domain.Movimentacao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimentacaoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer idMovimentacao;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataHoraMovimentacao = LocalDateTime.now();
	
	@NotNull(message = "Lote é campo de preenchimento obrigatório!")
	private Integer lote; 
	
	@NotNull(message = "Curral Piquete é campo de preenchimento obrigatório!")
	private Integer curralPiquete; 
	
	@Getter 
	private String descLote;
	
	@Getter 
	private String descCurralPiquete;
	
	private Integer situacao;
	
	public MovimentacaoDTO() {
		super();
	}
	
	public MovimentacaoDTO(Movimentacao pMovimentacao) {
		super();
		this.idMovimentacao = pMovimentacao.getIdMovimentacao();
		this.dataHoraMovimentacao = pMovimentacao.getDataHoraMovimentacao();
		this.lote = pMovimentacao.getLote().getIdLote();
		this.descCurralPiquete = pMovimentacao.getCurralPiquete().getDescricao();
		this.curralPiquete = pMovimentacao.getCurralPiquete().getIdCurralPiquete();
		this.descLote = pMovimentacao.getLote().getDescricao();
		this.situacao = pMovimentacao.getSituacao();
	}
	
}
