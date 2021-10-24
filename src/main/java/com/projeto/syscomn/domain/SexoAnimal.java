package com.projeto.syscomn.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.projeto.syscomn.domain.dtos.SexoAnimalDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SexoAnimal implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSexoAnimal;
	
	private String descricao;
	
	public SexoAnimal() {}
	
	public SexoAnimal(SexoAnimalDTO obj) {
		super();
		this.idSexoAnimal = obj.getIdSexoAnimal();
		this.descricao = obj.getDescricao();
	}
	
}
