package com.projeto.syscomn.domain.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.projeto.syscomn.domain.CurralPiquete;
import com.projeto.syscomn.domain.Lote;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurralPiqueteDTO implements Serializable{
	private static final long serialVersionUID = 1L;


	protected Integer idCurralPiquete;
	

	@NotNull(message = "Descrição é campo de preenchimento obrigatório!")
	protected String descricao;
	

	@NotNull(message = "Area Pasto é campo de preenchimento obrigatório!")
	protected Double areaPasto;
	

	@NotNull(message = "Curral Altura é campo de preenchimento obrigatório!")
	protected Double curralAltura;
	

	@NotNull(message = "Curral Largura é campo de preenchimento obrigatório!")
	protected Double curralLargura;
	

	@NotNull(message = "Quantidade de cabeças é campo de preenchimento obrigatório!")
	protected Integer quantidadeCabecas;
	
	@NotNull(message = "Status do Curral-Piquete é campo de preenchimento obrigatório!")
	private Integer statusCurralPiquete;
	
	@NotNull(message = "Tipo é campo de preenchimento obrigatório!")
	private Integer tipoCurralPiquete;
	

	@NotNull(message = "Propriedade é campo de preenchimento obrigatório!")
	private Integer propriedade; 
	

	private String nomePropriedade;
	

	private List<Lote> lstLote = new ArrayList<>();

	public CurralPiqueteDTO() {
		super();
	}

	public CurralPiqueteDTO(CurralPiquete pCurralPiquete) {
		super();
		this.idCurralPiquete = pCurralPiquete.getIdCurralPiquete();
		this.descricao = pCurralPiquete.getDescricao();
		this.areaPasto = pCurralPiquete.getAreaPasto();
		this.curralAltura = pCurralPiquete.getCurralAltura();
		this.curralLargura = pCurralPiquete.getCurralLargura();
		this.quantidadeCabecas = pCurralPiquete.getQuantidadeCabecas();
		this.statusCurralPiquete = pCurralPiquete.getStatusCurralPiquete();
	    this.tipoCurralPiquete = pCurralPiquete.getTipoCurralPiquete();
		this.propriedade = pCurralPiquete.getPropriedade().getIdPropriedade();
		this.nomePropriedade = pCurralPiquete.getPropriedade().getNomePropriedade();
		this.lstLote = pCurralPiquete.getLstLote();
	}
}
