package com.projeto.syscomn.domain.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.projeto.syscomn.domain.Vacina;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VacinaDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer idVacina; 
	
	@NotNull(message = "Nome Vacina é campo de preenchimento obrigatório!")
	private String nomeVacina;	
	
	@NotNull(message = "Fabricante é campo de preenchimento obrigatório!")
	private String fabricante;
	
	private Integer fornecedor; 
	
	private String nomeFantasia;
	
	public VacinaDTO() {}
 
	public VacinaDTO(Vacina pVacina) {
		super();
		this.idVacina = pVacina.getIdVacina();
		this.nomeVacina = pVacina.getNomeVacina();
		this.fabricante = pVacina.getFabricante();
		this.fornecedor = pVacina.getFornecedor().getIdPessoa();
		this.nomeFantasia = pVacina.getFornecedor().getNomeFantasia();
		}
	
}
