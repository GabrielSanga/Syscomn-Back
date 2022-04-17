package com.projeto.syscomn.domain.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.projeto.syscomn.domain.CurralPiquete;
import com.projeto.syscomn.domain.Lote;
import com.projeto.syscomn.domain.enums.StatusCurralPiquete;
import com.projeto.syscomn.domain.enums.TipoCurralPiquete;

import lombok.Getter;
import lombok.Setter;

@Setter
public class CurralPiqueteDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	@Getter
	protected Integer idCurralPiquete;
	
	@Getter
	@NotNull(message = "Descrição é campo de preenchimento obrigatório!")
	protected String descricao;
	
	@Getter
	@NotNull(message = "Area Pasto é campo de preenchimento obrigatório!")
	protected Double areaPasto;
	
	@Getter
	@NotNull(message = "Curral Altura é campo de preenchimento obrigatório!")
	protected Double curralAltura;
	
	@Getter
	@NotNull(message = "Curral Largura é campo de preenchimento obrigatório!")
	protected Double curralLargura;
	
	@Getter
	@NotNull(message = "Quantidade de cabeças é campo de preenchimento obrigatório!")
	protected Integer quantidadeCabecas;
	
	@NotNull(message = "Status do Curral-Piquete é campo de preenchimento obrigatório!")
	private Integer statusCurralPiquete;
	
	@NotNull(message = "Tipo é campo de preenchimento obrigatório!")
	private Integer tipoCurralPiquete;
	
	@Getter
	@NotNull(message = "Propriedade é campo de preenchimento obrigatório!")
	private Integer propriedade; 
	
	@Getter 
	private String nomePropriedade;
	
	@Getter
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
		this.statusCurralPiquete = pCurralPiquete.getStatusCurralPique().getCodigo();
	    this.tipoCurralPiquete = pCurralPiquete.getTipoCurralPiquete().getCodigo();
		this.propriedade = pCurralPiquete.getPropriedade().getIdPropriedade();
		this.nomePropriedade = pCurralPiquete.getPropriedade().getNomePropriedade();
		this.lstLote = pCurralPiquete.getLstLote();
	}
	
	public StatusCurralPiquete getStatusCurralPiquete() {
		return StatusCurralPiquete .toEnum(this.statusCurralPiquete);
	}	
	
	public TipoCurralPiquete getTipoCurralPiquete() {
		return TipoCurralPiquete .toEnum(this.tipoCurralPiquete);
	}
}
