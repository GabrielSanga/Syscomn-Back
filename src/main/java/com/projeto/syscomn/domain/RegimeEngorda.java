package com.projeto.syscomn.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projeto.syscomn.domain.dtos.RegimeEngordaDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class RegimeEngorda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idRegimeEngorda;
	
	@Getter
	private String descricao;
	
	@JsonIgnore
	@OneToMany(mappedBy = "idLote")
	private List<Lote> lstLote = new ArrayList<>();

	public RegimeEngorda() {}

	public RegimeEngorda(RegimeEngordaDTO obj) {
		super();
		this.idRegimeEngorda = obj.getIdRegimeEngorda();
		this.descricao = obj.getDescricao();
	}

}
