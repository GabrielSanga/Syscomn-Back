package com.projeto.syscomn.domain.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.syscomn.domain.Vacinacao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VacinacaoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer idVacinacao;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime data = LocalDateTime.now();
	
	private String loteVacina;
	
	@NotNull(message = "Quantidade é campo de preenchimento obrigatório!")
	@Range(min = 1, max = 999, message = "Quantidade deve ser entre que 1 a 999")
	private Integer quantidadeDose;
	
	private Double custoDose;
	
	@NotNull(message = "Necessário selecionar um Animal!")
	private Integer idAnimalChip;

	private Integer idPessoa;
	
	private Integer idVacina;

	public VacinacaoDTO() {
		super();
	}

	public VacinacaoDTO(Vacinacao pVacinacao) {
		super();
		this.idVacinacao = pVacinacao.getIdVacinacao();
		this.data = pVacinacao.getData();
		this.loteVacina = pVacinacao.getLoteVacina();
		this.quantidadeDose = pVacinacao.getQuantidadeDose();
		this.custoDose = pVacinacao.getCustoDose();
		this.idAnimalChip = pVacinacao.getAnimalChip().getIdAnimalChip();
		this.idPessoa = pVacinacao.getPessoa().getIdPessoa();
		this.idVacina = pVacinacao.getVacina().getIdVacina();
	}
	
}
