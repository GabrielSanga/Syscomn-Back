package com.projeto.syscomn.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.projeto.syscomn.domain.dtos.TipoFornecedorDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class TipoFornecedor implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTipoFornecedor;
	 
	private String descricao;
	
	public TipoFornecedor() {}
	
	public TipoFornecedor(TipoFornecedorDTO pTipoFornecedorDTO) {
		super();
		this.idTipoFornecedor = pTipoFornecedorDTO.getIdTipoFornecedor();
		this.descricao = pTipoFornecedorDTO.getDescricao();
	}

}
