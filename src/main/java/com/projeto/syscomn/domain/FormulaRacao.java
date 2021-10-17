package com.projeto.syscomn.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class FormulaRacao implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idFormulaRacao;
	
	private Integer quantidade;
	
	@ManyToOne
	@JoinColumn(name = "idMateriaPrima")
	private MateriaPrima materiaPrima;
	
	@ManyToOne
	@JoinColumn(name = "idRacao")
	private Racao racao;
	
	public FormulaRacao() {}
	
}
