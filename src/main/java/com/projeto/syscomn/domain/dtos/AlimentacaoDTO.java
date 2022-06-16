package com.projeto.syscomn.domain.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.syscomn.domain.Alimentacao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlimentacaoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer idAlimentacao;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime data = LocalDateTime.now();
	
	@NotNull(message = "Quantidade é campo de preenchimento obrigatório!")
	@Range(min = 1, max = 999, message = "Quantidade deve ser entre que 1 a 999")
	private Integer quantidade;
	
	private Double custo;
	
	@NotNull(message = "Necessário selecionar um Animal!")
	private Integer idAnimalChip;

	private Integer idPessoa;
	private String nomePessoa;
	
	private String tipoAlimentacao;
	
	private Integer idLote;
	
	private Integer idLoteRacao;
	
	private Integer idRacao;
	private String descrRacao;

	public AlimentacaoDTO() {
		super();
	}

	public AlimentacaoDTO(Alimentacao pAlimentacao) {
		super();
		this.idAlimentacao = pAlimentacao.getIdAlimentacao();
		this.data = pAlimentacao.getData();
		this.quantidade = pAlimentacao.getQuantidade();
		this.custo = pAlimentacao.getCusto();
		this.idAnimalChip = pAlimentacao.getAnimalChip().getIdAnimalChip();
		this.idPessoa = pAlimentacao.getPessoa().getIdPessoa();
		this.nomePessoa = pAlimentacao.getPessoa().getNomePessoa();
		if(pAlimentacao.getLoteRacao() != null) {
			this.idRacao = pAlimentacao.getLoteRacao().getRacaoProduzir().getRacao().getIdRacao();
			this.descrRacao = pAlimentacao.getLoteRacao().getRacaoProduzir().getRacao().getDescricao();
		}
	}

}
