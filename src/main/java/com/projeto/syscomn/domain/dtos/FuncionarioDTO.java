package com.projeto.syscomn.domain.dtos;

import java.time.LocalDate;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.projeto.syscomn.domain.Funcionario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncionarioDTO extends PessoaDTO{
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Data de Admissão é campo de preenchimento obrigatório!")
	private LocalDate dtaAdmissao;

	private LocalDate dtaDemissao;
	
	private String NIS;
	
	private String status;
	
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
		this.userName = pFuncionario.getUserName();
		this.senha = pFuncionario.getSenha();
		this.tipoPessoa = pFuncionario.getTipoPessoa();
		this.fotoPessoa = pFuncionario.getFotoPessoa();
		this.dtaAdmissao = pFuncionario.getDtaAdmissao();
		this.dtaDemissao= pFuncionario.getDtaDemissao();
		this.NIS = pFuncionario.getNIS();
		this.status = pFuncionario.getStatus();
		this.perfis = pFuncionario.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());

	}
	
}
