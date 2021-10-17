package com.projeto.syscomn.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.projeto.syscomn.domain.dtos.TipoMorteDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class TipoMorte implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTipoMorte;
	
	private String descricao;
	
	public TipoMorte() {}
	
	public TipoMorte(TipoMorteDTO pTipoMorteDTO) {
		super();
		this.idTipoMorte = pTipoMorteDTO.getIdTipoMorte();
		this.descricao = pTipoMorteDTO.getDescricao();
	}
	
}
