package com.projeto.syscomn.domain.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.projeto.syscomn.domain.SexoAnimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SexoAnimalDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer idSexoAnimal; 
	
	@NotNull(message = "Descrição é campo de preenchimento obrigatório!")
	private String descricao;		
	
	public SexoAnimalDTO() {}
 
	public SexoAnimalDTO(SexoAnimal obj) {
		super();
		this.idSexoAnimal = obj.getIdSexoAnimal();
		this.descricao = obj.getDescricao();
	}
	
}
