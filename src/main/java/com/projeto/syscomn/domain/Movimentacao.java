package com.projeto.syscomn.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.syscomn.domain.dtos.MovimentacaoDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Movimentacao implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMovimentacao;

	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataHoraMovimentacao = LocalDateTime.now();
	
	@ManyToOne
	@JoinColumn(name = "idLote")
	private Lote lote;
	
	@ManyToOne
	@JoinColumn(name = "idCurralPiquete")
	private CurralPiquete curralPiquete;
	
	//@ManyToOne
	//@JoinColumn(name = "idPessoa")
	//private Funcionario funcionario;
	
	private Integer situacao;
	
	public Movimentacao() {
		super();
	}
	
	public Movimentacao(Lote lote, CurralPiquete curralPiquete,/* Funcionario funcionario ,*/Integer situacao) {
		super();
		this.dataHoraMovimentacao = LocalDateTime.now();
		this.situacao = situacao;
		this.curralPiquete = curralPiquete;
		this.lote = lote;
		//this.funcionario = funcionario;
	}	
	
	public Movimentacao(MovimentacaoDTO pMovimentacaoDTO) {
		super();
		this.idMovimentacao = pMovimentacaoDTO.getIdMovimentacao();
		this.dataHoraMovimentacao = pMovimentacaoDTO.getDataHoraMovimentacao();
		this.situacao = pMovimentacaoDTO.getSituacao();
	}
	
	//public Situacao getSituacao() {
	//	return Situacao.toEnum(this.situacao);
	//}
}