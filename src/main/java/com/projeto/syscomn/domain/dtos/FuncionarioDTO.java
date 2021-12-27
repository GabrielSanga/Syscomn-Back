package com.projeto.syscomn.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.syscomn.domain.Funcionario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncionarioDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer idPessoa;
	
	@NotBlank(message = "Nome é campo de preenchimento obrigatório!")
    @Size(min = 4, max = 100, message = "Nome deve ter entre 4 e 100 letras!")
	private String nomePessoa;

	@NotBlank(message = "CPF/CNPJ é campo de preenchimento obrigatório!")
	private String cpfCnpjPessoa;

	private String telefonePessoa;

	@NotBlank(message = "E-mail é campo de preenchimento obrigatório!")
	private String emailPessoa;

	@NotNull(message = "Data de Nascimento é campo de preenchimento obrigatório!")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dtaNascimentoPessoa;

	@NotBlank(message = "Endereço é campo de preenchimento obrigatório!")
    @Size(min = 10, max = 100, message = "Nome deve ter entre 10 e 100 letras!")
	private String enderecoPessoa;

	@NotBlank(message = "Status é campo de preenchimento obrigatório!")
    @Size(max = 1, message = "Status aceitáveis: 'A' - Ativo / 'I' - Inativo")
	private String statusPessoa;

	@NotBlank(message = "RG é campo de preenchimento obrigatório!")
    @Size(max = 10, message = "RG Incorreto!")
	private String rgPessoa;
	
	private String observacaoPessoa;

	@NotBlank(message = "Login é campo de preenchimento obrigatório!")
    @Size(min = 5, max = 20, message = "Login deve ter entre 5 e 20 letras!")
	private String login;	

	@NotBlank(message = "Senha é campo de preenchimento obrigatório!")
    @Size(min = 8, max = 30, message = "Senha deve ter entre 8 e 30 letras!")
	private String senha;

	@NotNull(message = "Tipo da Pessoa é campo de preenchimento obrigatório!")
	private Integer tipoPessoa;

	@NotNull(message = "Assinante é campo de preenchimento obrigatório!")
	private Integer idAssinante;
	private String nomeAssinante;
	private String cpfCnpjAssinante;
	
	@NotBlank(message = "NIS é campo de preenchimento obrigatório!")
	private String NIS;
	
	@NotNull(message = "Data de Admissão é campo de preenchimento obrigatório!")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dtaAdmissao;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dtaDemissao;
	
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
