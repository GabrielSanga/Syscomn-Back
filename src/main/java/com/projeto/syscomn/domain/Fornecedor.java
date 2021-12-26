package com.projeto.syscomn.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

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
	
	@ManyToMany
	@JoinColumn(name = "idTipoForncedor")
	private TipoFornecedor tipoFornecedor;

	public Fornecedor() {
		super();
	}

	public Fornecedor(Integer idPessoa, String nomePessoaa, String cpfCnpjPessoa, String telefonePessoa,
			String emailPessoa, Date dtaNascimentoPessoa, String enderecoPessoa, String statusPessoa, String rgPessoa,
			String observacaoPessoa, String login, String senha, Integer tipoPessoa, Assinante assinante) {
		super(idPessoa, nomePessoaa, cpfCnpjPessoa, telefonePessoa, emailPessoa, dtaNascimentoPessoa, enderecoPessoa,
				statusPessoa, rgPessoa, observacaoPessoa, login, senha, tipoPessoa, assinante);
	}

}
