package com.projeto.syscomn.domain.dtos;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.syscomn.domain.Funcionario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncionarioDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	protected Integer idPessoa;
	
	@NotBlank(message = "Nome é campo de preenchimento obrigatório!")
	protected String nomePessoa;

	@NotBlank(message = "CPF/CNPJ é campo de preenchimento obrigatório!")
	protected String cpfCnpjPessoa;

	protected String telefonePessoa;

	@NotBlank(message = "E-mail é campo de preenchimento obrigatório!")
	protected String emailPessoa;

	@NotNull(message = "Data de Nascimento é campo de preenchimento obrigatório!")
	@JsonFormat(pattern = "dd/MM/yyyy")
	protected Date dtaNascimentoPessoa;

	@NotBlank(message = "Endereço é campo de preenchimento obrigatório!")
	protected String enderecoPessoa;

	@NotBlank(message = "Status é campo de preenchimento obrigatório!")
	protected String statusPessoa;

	@NotBlank(message = "RG é campo de preenchimento obrigatório!")
	protected String rgPessoa;
	
	protected String observacaoPessoa;

	@NotBlank(message = "Login é campo de preenchimento obrigatório!")
	protected String login;	

	@NotBlank(message = "Senha é campo de preenchimento obrigatório!")
	protected String senha;

	@NotNull(message = "Tipo da Pessoa é campo de preenchimento obrigatório!")
	protected Integer tipoPessoa;

	@NotNull(message = "Assinante é campo de preenchimento obrigatório!")
	protected Integer assinante;
	
	public FuncionarioDTO() {}
	
	public FuncionarioDTO(Funcionario pFuncionario){
		this.idPessoa = pFuncionario.getIdPessoa();
		this.nomePessoa = pFuncionario.getNomePessoa();
		this.cpfCnpjPessoa = pFuncionario.getCpfCnpjPessoa();
		this.telefonePessoa = pFuncionario.getTelefonePessoa();
		this.emailPessoa = pFuncionario.getEmailPessoa();
		this.dtaNascimentoPessoa = pFuncionario.getDtaNascimentoPessoa();;
		this.enderecoPessoa = pFuncionario.getEnderecoPessoa();
		this.statusPessoa = pFuncionario.getStatusPessoa();
		this.login = pFuncionario.getLogin();
		this.senha = pFuncionario.getSenha();
		this.assinante = pFuncionario.getAssinante().getIdAssinante();
	}
}
