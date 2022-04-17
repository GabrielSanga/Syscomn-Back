package com.projeto.syscomn.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projeto.syscomn.domain.dtos.CurralPiqueteDTO;
import com.projeto.syscomn.domain.enums.StatusCurralPiquete;
import com.projeto.syscomn.domain.enums.TipoCurralPiquete;

import lombok.Getter;
import lombok.Setter;

@Setter
@Entity
public class CurralPiquete implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCurralPiquete;
	
	@Getter
	private String descricao;
	
	@Getter
	private Double areaPasto;
	
	@Getter
	private Double curralAltura;
	
	@Getter
	private Double curralLargura;
	
	@Getter
	private Integer quantidadeCabecas;
	
	private Integer statusCurralPiquete;
	
	private Integer tipoCurralPiquete;
	
	@Getter
	@ManyToOne
	@JoinColumn(name = "idPropriedade")
	private Propriedade propriedade;
	
	@Getter
	@JsonIgnore
	@OneToMany(mappedBy = "idLote")
	private List<Lote> lstLote = new ArrayList<>();
	
	public CurralPiquete() {
		super();
	}
		
	public CurralPiquete(CurralPiqueteDTO pCurralPiqueteDTO) {
		super();
		this.idCurralPiquete = pCurralPiqueteDTO.getIdCurralPiquete();
		this.descricao = pCurralPiqueteDTO.getDescricao();
		this.areaPasto = pCurralPiqueteDTO.getAreaPasto();
		this.curralAltura = pCurralPiqueteDTO.getCurralAltura();
		this.curralLargura = pCurralPiqueteDTO.getCurralLargura();
		this.quantidadeCabecas = pCurralPiqueteDTO.getQuantidadeCabecas();
		this.statusCurralPiquete = pCurralPiqueteDTO.getStatusCurralPiquete().getCodigo();
		this.tipoCurralPiquete = pCurralPiqueteDTO.getTipoCurralPiquete().getCodigo();
	}
	
	public StatusCurralPiquete getStatusCurralPique() {
		return StatusCurralPiquete.toEnum(this.statusCurralPiquete);
	}
	
	public TipoCurralPiquete getTipoCurralPiquete() {
		return TipoCurralPiquete.toEnum(this.tipoCurralPiquete);
	}
	
}
