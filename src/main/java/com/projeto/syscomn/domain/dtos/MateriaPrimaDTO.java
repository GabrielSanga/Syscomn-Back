package com.projeto.syscomn.domain.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.projeto.syscomn.domain.FormulaRacao;
import com.projeto.syscomn.domain.MateriaPrima;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MateriaPrimaDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	protected Integer idMateriaPrima; 
	
	@NotNull(message = "Descrição é campo de preenchimento obrigatório!")
	protected String descricao;	
	
	@NotNull(message = "Saldo em Estoque é campo de preenchimento obrigatório!")
	protected double saldoEstoque;
	
	protected double custoMateriaPrima;	
	
	@NotNull(message = "Unidade é campo de preenchimento obrigatório!")
	protected String unidade;
	
	protected List<FormulaRacao> lstFormulaRacao = new ArrayList<>();
	
	public MateriaPrimaDTO() {}

	public MateriaPrimaDTO(MateriaPrima obj) {
		super();
		this.idMateriaPrima = obj.getIdMateriaPrima();
		this.descricao = obj.getDescricao();
		this.saldoEstoque = obj.getSaldoEstoque();
		this.custoMateriaPrima = obj.getCustoMateriaPrima();
		this.unidade = obj.getUnidade();
		this.lstFormulaRacao = obj.getLstFormulaRacao();
	}
		
}
