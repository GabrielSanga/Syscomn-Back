package com.projeto.syscomn.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Pessoa {
	
	protected Integer idPessoa;
	protected String nomePessoa;
	protected String cpfCnpjPessoa;
	protected String telefonePessoa;
	protected String emailPessoa;
	protected Date dtaNascimentoPessoa;
	protected String enderecoPessoa;
	protected String statusPessoa;
	protected String rgPessoa;
	protected String observacaoPessoa;
	protected String login;
	protected String senha;
	protected Integer TipoPessoa;
	
	public Pessoa() {}
	
	public Pessoa(Integer idPessoa, String nomePessoa, String cpfCnpjPessoa, String telefonePessoa, String emailPessoa,
			Date dtaNascimentoPessoa, String enderecoPessoa, String statusPessoa, String rgPessoa,
			String observacaoPessoa, String login, String senha, Integer tipoPessoa) {
		super();
		this.idPessoa = idPessoa;
		this.nomePessoa = nomePessoa;
		this.cpfCnpjPessoa = cpfCnpjPessoa;
		this.telefonePessoa = telefonePessoa;
		this.emailPessoa = emailPessoa;
		this.dtaNascimentoPessoa = dtaNascimentoPessoa;
		this.enderecoPessoa = enderecoPessoa;
		this.statusPessoa = statusPessoa;
		this.rgPessoa = rgPessoa;
		this.observacaoPessoa = observacaoPessoa;
		this.login = login;
		this.senha = senha;
		TipoPessoa = tipoPessoa;
	}	
	
}
