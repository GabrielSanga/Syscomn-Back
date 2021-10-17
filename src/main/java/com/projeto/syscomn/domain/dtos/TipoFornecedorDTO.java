package com.projeto.syscomn.domain.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.projeto.syscomn.domain.TipoFornecedor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoFornecedorDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected Integer IdTipoFornecedor;
	
	@NotNull(message = "Descrição é campo de preenchimento obrigatório!")
	protected String descricao;
	
	public TipoFornecedorDTO() {}
	
	public TipoFornecedorDTO(TipoFornecedor pTipoFornecedor) {
		super();
		this.IdTipoFornecedor = pTipoFornecedor.getIdTipoFornecedor();
		this.descricao = pTipoFornecedor.getDescricao();
	}
	
}
