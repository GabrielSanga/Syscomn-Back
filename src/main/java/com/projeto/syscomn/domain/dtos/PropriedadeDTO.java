package com.projeto.syscomn.domain.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.projeto.syscomn.domain.Propriedade;
import com.projeto.syscomn.domain.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Setter
public class PropriedadeDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	@Getter
	protected Integer idPropriedade; 
	
	@Getter
	@NotNull(message = "Nome da Propriedade é campo de preenchimento obrigatório!")
	protected String nomePropriedade;	
	
	@Getter
	@NotNull(message = "Endereço é campo de preenchimento obrigatório!")
	protected String endereco;	
	
	@Getter
	@NotNull(message = "CNPJ é campo de preenchimento obrigatório!")
	protected String cnpj;	
	
	@NotNull(message = "Status é campo de preenchimento obrigatório!")
	private Integer status;
	
	public PropriedadeDTO() {
		super();		
	}

	public PropriedadeDTO(Propriedade pPropriedade) {
		super();
		this.idPropriedade = pPropriedade.getIdPropriedade();
		this.nomePropriedade = pPropriedade.getNomePropriedade();
		this.endereco = pPropriedade.getEndereco();
		this.cnpj = pPropriedade.getCnpj();
		this.status = pPropriedade.getStatus().getCodigo(); 
		
	}

	public Status getStatus() {
		return Status.toEnum(this.status);
	}	
		
}
