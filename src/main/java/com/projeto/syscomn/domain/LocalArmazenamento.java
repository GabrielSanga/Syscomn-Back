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
	
	private Integer capacidade;
	
	private String status;
	
	@JsonIgnore
	@OneToMany(mappedBy = "localArmazenamento")
	private List<LoteRacao> lstLoteRacao = new ArrayList<>();
	
	public LocalArmazenamento() {}
	
	public LocalArmazenamento(LocalArmazenamentoDTO pLocalArmazenamentoDTO) {
		super();
		this.idLocalArmazenamento = pLocalArmazenamentoDTO.getIdLocalArmazenamento();
		this.descricao = pLocalArmazenamentoDTO.getDescricao();
		this.capacidade = pLocalArmazenamentoDTO.getCapacidade();
		this.status = pLocalArmazenamentoDTO.getStatus();
	}
	
}
