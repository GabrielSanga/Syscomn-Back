package com.projeto.syscomn.domain;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Funcionario extends Pessoa {
	private static final long serialVersionUID = 1L;

	private Date dtaAdmissao;

	private Date dtaDemissao;

	private String NIS;

	private String status;

	public Funcionario() {
		super();
	}

	public Funcionario(Integer idPessoa, String nomePessoaa, String cpfCnpjPessoa, String telefonePessoa,
			String emailPessoa, Date dtaNascimentoPessoa, String enderecoPessoa, String statusPessoa, String rgPessoa,
			String observacaoPessoa, String login, String senha, Integer tipoPessoa, Assinante assinante) {
		super(idPessoa, nomePessoaa, cpfCnpjPessoa, telefonePessoa, emailPessoa, dtaNascimentoPessoa, enderecoPessoa,
				statusPessoa, rgPessoa, observacaoPessoa, login, senha, tipoPessoa, assinante);
	}
	
}
