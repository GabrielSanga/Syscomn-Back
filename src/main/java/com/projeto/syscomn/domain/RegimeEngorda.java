package com.projeto.syscomn.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.projeto.syscomn.domain.dtos.RegimeEngordaDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class RegimeEngorda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idRegimeEngorda;
	private String descricao;

	public RegimeEngorda() {}

	public RegimeEngorda(RegimeEngordaDTO obj) {
		super();
		this.idRegimeEngorda = obj.getIdRegimeEngorda();
		this.descricao = obj.getDescricao();
	}

}
