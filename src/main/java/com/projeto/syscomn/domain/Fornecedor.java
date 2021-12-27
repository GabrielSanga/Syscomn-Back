package com.projeto.syscomn.domain;


import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Fornecedor extends Pessoa{
	private static final long serialVersionUID = 1L;
	
	private String razaoSocial;
	
	private String nomeFantasia;
	
	private String inscricaoEstadual;
	
	private String cnae;
	
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "idTipoForncedor")
	private TipoFornecedor tipoFornecedor;

	public Fornecedor() {
		super();
	}

	public Fornecedor(Integer idPessoa, String nomePessoa, String cpfCnpjPessoa, String telefonePessoa,
			String emailPessoa, LocalDate dtaNascimentoPessoa, String enderecoPessoa, String statusPessoa, String rgPessoa,
			String observacaoPessoa, String login, String senha, Integer tipoPessoa, Assinante assinante) {
		super(idPessoa, nomePessoa, cpfCnpjPessoa, telefonePessoa, emailPessoa, dtaNascimentoPessoa, enderecoPessoa,
				statusPessoa, rgPessoa, observacaoPessoa, login, senha, tipoPessoa, assinante);
	}

}
