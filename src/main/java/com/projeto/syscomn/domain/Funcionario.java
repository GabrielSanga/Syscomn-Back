package com.projeto.syscomn.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.projeto.syscomn.domain.dtos.FuncionarioDTO;

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
	
	public Funcionario() {
		super();
	}

	public Funcionario(Integer idPessoa, String nomePessoa, String cpfCnpjPessoa, String telefonePessoa,
			String emailPessoa, Date dtaNascimentoPessoa, String enderecoPessoa, String statusPessoa, String rgPessoa,
			String observacaoPessoa, String login, String senha, Integer tipoPessoa, Assinante assinante) {
		super(idPessoa, nomePessoa, cpfCnpjPessoa, telefonePessoa, emailPessoa, dtaNascimentoPessoa, enderecoPessoa,
				statusPessoa, rgPessoa, observacaoPessoa, login, senha, tipoPessoa, assinante);
	}
	
	public Funcionario(FuncionarioDTO pFuncionarioDTO){
		this.idPessoa = pFuncionarioDTO.getIdPessoa();
		this.nomePessoa = pFuncionarioDTO.getNomePessoa();
		this.cpfCnpjPessoa = pFuncionarioDTO.getCpfCnpjPessoa();
		this.telefonePessoa = pFuncionarioDTO.getTelefonePessoa();
		this.emailPessoa = pFuncionarioDTO.getEmailPessoa();
		this.dtaNascimentoPessoa = pFuncionarioDTO.getDtaNascimentoPessoa();;
		this.enderecoPessoa = pFuncionarioDTO.getEnderecoPessoa();
		this.statusPessoa = pFuncionarioDTO.getStatusPessoa();
		this.rgPessoa = pFuncionarioDTO.getRgPessoa();
		this.login = pFuncionarioDTO.getLogin();
		this.senha = pFuncionarioDTO.getSenha();
		this.tipoPessoa = pFuncionarioDTO.getTipoPessoa();
	}

}	
