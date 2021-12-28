package com.projeto.syscomn.domain.dtos;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.syscomn.domain.Funcionario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncionarioDTO extends PessoaDTO{
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "NIS é campo de preenchimento obrigatório!")
	private String NIS;
	
	@NotNull(message = "Data de Admissão é campo de preenchimento obrigatório!")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dtaAdmissao;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dtaDemissao;
	
	public FuncionarioDTO() { 
		super();
	}
	
	public FuncionarioDTO(Funcionario pFuncionario){
		super();
		this.idPessoa = pFuncionario.getIdPessoa();
		this.nomePessoa = pFuncionario.getNomePessoa();
		this.cpfCnpjPessoa = pFuncionario.getCpfCnpjPessoa();
		this.telefonePessoa = pFuncionario.getTelefonePessoa();
		this.emailPessoa = pFuncionario.getEmailPessoa();
		this.dtaNascimentoPessoa = pFuncionario.getDtaNascimentoPessoa();;
		this.enderecoPessoa = pFuncionario.getEnderecoPessoa();
		this.statusPessoa = pFuncionario.getStatusPessoa();
		this.rgPessoa = pFuncionario.getRgPessoa();
		this.observacaoPessoa = pFuncionario.getObservacaoPessoa();
		this.login = pFuncionario.getLogin();
		this.senha = pFuncionario.getSenha();
		this.tipoPessoa = pFuncionario.getTipoPessoa();
		this.idAssinante = pFuncionario.getAssinante().getIdAssinante();
		this.nomeAssinante = pFuncionario.getAssinante().getNomeAssinante();
		this.cpfCnpjAssinante = pFuncionario.getAssinante().getCpfCnpj();
		this.dtaAdmissao = pFuncionario.getDtaAdmissao();
		this.dtaDemissao= pFuncionario.getDtaDemissao();
		this.NIS = pFuncionario.getNIS();
	}
	
}
