package com.projeto.syscomn.domain.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.projeto.syscomn.domain.EstadoAnimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoAnimalDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	protected Integer idEstadoAnimal; 
	
	@NotNull(message = "Descrição é campo de preenchimento obrigatório!")
	protected String descricao;		
	
	public EstadoAnimalDTO() {}

	public EstadoAnimalDTO(EstadoAnimal obj) {
		super();
		this.idEstadoAnimal = obj.getIdEstadoAnimal();
		this.descricao = obj.getDescricao();
	}
		
}
