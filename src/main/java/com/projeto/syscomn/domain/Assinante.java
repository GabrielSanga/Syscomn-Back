package com.projeto.syscomn.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

import com.projeto.syscomn.domain.dtos.AssinanteDTO;
import com.projeto.syscomn.interfaces.CnpjGroup;
import com.projeto.syscomn.interfaces.CpfGroup;

import lombok.Getter;
import lombok.Setter;

@Setter
@Entity
@GroupSequenceProvider(AssinanteGroupSequenceProvider.class)
public class Assinante implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAssinante;
	
	@Getter
	private String nomeAssinante;
		
	@Getter
	private Integer tipoPessoa;
	
	@Getter
	@CPF(groups = CpfGroup.class)
	@CNPJ(groups = CnpjGroup.class)
	@Column(unique = true)
	private String cpfCnpj;
	 
	public Assinante() {
		super();
	}
	
	public Assinante(AssinanteDTO pAssinanteDTO) {
		super();
		this.idAssinante = pAssinanteDTO.getIdAssinante();
		this.nomeAssinante = pAssinanteDTO.getNomeAssinante();
		this.tipoPessoa = pAssinanteDTO.getTipoPessoa();
		this.cpfCnpj = pAssinanteDTO.getCpfCnpj();
	}
		
}
