package com.projeto.syscomn.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.projeto.syscomn.domain.dtos.LocalArmazenamentoDTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class LocalArmazenamento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idLocalArmazenamento ;
	
	private String descricao;

	public LocalArmazenamento() {}
	
	public LocalArmazenamento(LocalArmazenamentoDTO pLocalArmazenamentoDTO) {
		super();
		this.idLocalArmazenamento = pLocalArmazenamentoDTO.getIdLocalArmazenamento();
		this.descricao = pLocalArmazenamentoDTO.getDescricao();
	}
	
}
