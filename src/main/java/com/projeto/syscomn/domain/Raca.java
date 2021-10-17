package com.projeto.syscomn.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.projeto.syscomn.domain.dtos.RacaDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Raca implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idRaca;
	
	private String descricao;
	
	public Raca() {
	}
	
	public Raca(RacaDTO pRacaDTO) {
		super();
		this.idRaca = pRacaDTO.getIdRaca();
		this.descricao = pRacaDTO.getDescricao();
	}

}
