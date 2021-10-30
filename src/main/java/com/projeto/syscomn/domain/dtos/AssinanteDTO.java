package com.projeto.syscomn.domain.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.projeto.syscomn.domain.Assinante;

import lombok.Getter;
import lombok.Setter;

@Setter
public class AssinanteDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Getter
	private Integer idAssinante;
	
	@Getter
	@NotBlank(message = "Nome é campo de preenchimento obrigatório!")
	private String nomeAssinante;
	
	@Getter
	@NotNull(message = "Tipo Pessoa é campo de preenchimento obrigatório!")
	private Integer tipoPessoa;
	
	@Getter
	@NotBlank(message = "CPF/CNPJ é campo de preenchimento obrigatório!")
	private String cpfCnpj;
	
	public AssinanteDTO() {
		super();
	}
	
	public AssinanteDTO(Assinante pAssinante) {
		super();
		this.idAssinante = pAssinante.getIdAssinante();
		this.nomeAssinante = pAssinante.getNomeAssinante();
		this.tipoPessoa = pAssinante.getTipoPessoa();
		this.cpfCnpj = pAssinante.getCpfCnpj();
	}
	
}
