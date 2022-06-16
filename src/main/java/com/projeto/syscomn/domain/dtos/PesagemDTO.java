package com.projeto.syscomn.domain.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.syscomn.domain.Pesagem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PesagemDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer idPesagem;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime data = LocalDateTime.now();
	
	@JsonFormat(pattern = "###.000,000")
	@NotNull(message = "Peso é campo de preenchimento obrigatório!")
	private Double peso;
	
	private Double pesoBrutoVeiculo;
	private Double pesoTaraVeiculo;
	
	private String tipoPesagem;
	
	private String observacao;
	
	@NotNull(message = "Necessário selecionar um Animal!")
	private Integer idAnimalChip;
	
	private Integer idPessoa;
	private String nomePessoa;
	
	private Integer idLote;

	public PesagemDTO() {
		super();
	}

	public PesagemDTO(Pesagem pPesagem) {
		super();
		this.idPesagem = pPesagem.getIdPesagem();
		this.data = pPesagem.getData();
		this.peso = pPesagem.getPeso();
		this.observacao = pPesagem.getObservacao();
		this.idAnimalChip = pPesagem.getAnimalChip().getIdAnimalChip();
		this.idPessoa = pPesagem.getPessoa().getIdPessoa();
		this.nomePessoa = pPesagem.getPessoa().getNomePessoa();
	}
		
}
