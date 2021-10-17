package com.projeto.syscomn.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.projeto.syscomn.domain.dtos.EstadoAnimalDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class EstadoAnimal implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEstadoAnimal;
	
	private String descricao;
	
	public EstadoAnimal() {}
	
	public EstadoAnimal(EstadoAnimalDTO obj) {
		super();
		this.idEstadoAnimal = obj.getIdEstadoAnimal();
		this.descricao = obj.getDescricao();
	}
	
}
