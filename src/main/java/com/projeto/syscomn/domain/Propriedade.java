package com.projeto.syscomn.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.br.CNPJ;

import com.projeto.syscomn.domain.dtos.PropriedadeDTO;
import com.projeto.syscomn.domain.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Setter
@Entity
public class Propriedade implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPropriedade;
	
	@Getter
	private String nomePropriedade;
	
	@Getter
	private String endereco;
	
	@Getter
	@CNPJ
	@Column(unique = true) 
	private String cnpj;
	
	private Integer status;
	
	public Propriedade() {
		super();
	}
	
	public Propriedade(PropriedadeDTO pPropriedadeDTO) {
		super();
		this.idPropriedade = pPropriedadeDTO.getIdPropriedade();
		this.nomePropriedade = pPropriedadeDTO.getNomePropriedade();
		this.endereco = pPropriedadeDTO.getEndereco();
		this.cnpj = pPropriedadeDTO.getCnpj();
		this.status = pPropriedadeDTO.getStatus().getCodigo();
	}

	public Status getStatus() {
		return Status.toEnum(this.status);
	}
	
}
