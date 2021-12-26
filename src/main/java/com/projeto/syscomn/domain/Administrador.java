package com.projeto.syscomn.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Administrador extends Pessoa{

	private Integer idAdministrador;
	private Date dtaAdmissao;
	private Date dtaDemissao;
	private String status;
	
	public Administrador() {
		super();
	}
	
	public Administrador(Integer idPessoa, String nomePessoa, String cpfCnpjPessoa, String telefonePessoa,
			String emailPessoa, Date dtaNascimentoPessoa, String enderecoPessoa, String statusPessoa, String rgPessoa,
			String observacaoPessoa, String login, String senha, Integer tipoPessoa) {
		super(idPessoa, nomePessoa, cpfCnpjPessoa, telefonePessoa, emailPessoa, dtaNascimentoPessoa, enderecoPessoa,
				statusPessoa, rgPessoa, observacaoPessoa, login, senha, tipoPessoa);
	}	
	
}
