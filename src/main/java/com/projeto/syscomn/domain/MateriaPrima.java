package com.projeto.syscomn.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projeto.syscomn.domain.dtos.MateriaPrimaDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class MateriaPrima implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMateriaPrima; 	
	
	private String descricao;	
	
	private double saldoEstoque;
	
	private double custoMateriaPrima;	
	
	private String unidade;
	
	@JsonIgnore
	@OneToMany(mappedBy = "materiaPrima")
	private List<FormulaRacao> lstFormulaRacao = new ArrayList<>();
	
	public MateriaPrima() {}
	
	public MateriaPrima(MateriaPrimaDTO obj) {
		super();
		this.idMateriaPrima = obj.getIdMateriaPrima();
		this.descricao = obj.getDescricao();
		this.saldoEstoque = obj.getSaldoEstoque();
		this.custoMateriaPrima = obj.getCustoMateriaPrima();
		this.unidade = obj.getUnidade();
	}
		
}
