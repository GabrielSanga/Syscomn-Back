package com.projeto.syscomn.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	protected Integer idPessoa;
	
	@NotBlank(message = "Nome é campo de preenchimento obrigatório!")
    @Size(min = 4, max = 100, message = "Nome deve ter entre 4 e 100 letras!")
	protected String nomePessoa;

	@NotBlank(message = "CPF/CNPJ é campo de preenchimento obrigatório!")
	protected String cpfCnpjPessoa;

	protected String telefonePessoa;

	@NotBlank(message = "E-mail é campo de preenchimento obrigatório!")
	protected String emailPessoa;

	@NotNull(message = "Data de Nascimento é campo de preenchimento obrigatório!")
	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate dtaNascimentoPessoa;

	@NotBlank(message = "Endereço é campo de preenchimento obrigatório!")
    @Size(min = 10, max = 100, message = "Nome deve ter entre 10 e 100 letras!")
	protected String enderecoPessoa;

	@NotBlank(message = "Status é campo de preenchimento obrigatório!")
    @Size(max = 1, message = "Status aceitáveis: 'A' - Ativo / 'I' - Inativo")
	protected String statusPessoa;

	@NotBlank(message = "RG é campo de preenchimento obrigatório!")
    @Size(max = 10, message = "RG Incorreto!")
	protected String rgPessoa;
	
	protected String observacaoPessoa;

	@NotBlank(message = "Login é campo de preenchimento obrigatório!")
    @Size(min = 5, max = 20, message = "Login deve ter entre 5 e 20 letras!")
	protected String login;	

	@NotBlank(message = "Senha é campo de preenchimento obrigatório!")
    @Size(min = 8, max = 30, message = "Senha deve ter entre 8 e 30 letras!")
	protected String senha;

	@NotNull(message = "Tipo da Pessoa é campo de preenchimento obrigatório!")
	protected Integer tipoPessoa;
	
    private byte[] fotoPessoa;

	@NotNull(message = "Assinante é campo de preenchimento obrigatório!")
	protected Integer idAssinante;
	protected String nomeAssinante;
	protected String cpfCnpjAssinante;

}
