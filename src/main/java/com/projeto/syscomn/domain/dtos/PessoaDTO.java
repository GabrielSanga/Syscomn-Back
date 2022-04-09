package com.projeto.syscomn.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.projeto.syscomn.domain.enums.Perfil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	protected Integer idPessoa;

	@NotNull(message = "Nome é campo de preenchimento obrigatório!")
	@Size(min = 4, max = 100, message = "Nome deve ter entre 4 e 100 letras!")
	protected String nomePessoa;

	@NotNull(message = "CPF/CNPJ é campo de preenchimento obrigatório!")
	@Column(unique = true)
	protected String cpfCnpjPessoa;

	protected String telefonePessoa;

	@NotBlank(message = "E-mail é campo de preenchimento obrigatório!")
	protected String emailPessoa;

	@NotNull(message = "Data de Nascimento é campo de preenchimento obrigatório!")
	protected LocalDate dtaNascimentoPessoa;

	protected String enderecoPessoa;

	@NotNull(message = "Status é campo de preenchimento obrigatório!")
	@Size(max = 1, message = "Status aceitáveis: 'A' - Ativo / 'I' - Inativo")
	protected String statusPessoa;

	@NotNull(message = "RG é campo de preenchimento obrigatório!")
	@Size(max = 15, message = "RG Incorreto!")
	protected String rgPessoa;

	protected String observacaoPessoa;

	@NotNull(message = "Login é campo de preenchimento obrigatório!")
	@Size(min = 5, max = 20, message = "Login deve ter entre 5 e 20 letras!")
	protected String userName;

	@NotNull(message = "Senha é campo de preenchimento obrigatório!")
	@Size(min = 8, message = "Senha deve ter mais de 8 dígitos!")
	protected String senha;

	@NotNull(message = "Tipo da Pessoa é campo de preenchimento obrigatório!")
	protected Integer tipoPessoa;
	
	protected Set<Integer> perfis = new HashSet<>();

	protected byte[] fotoPessoa;

	protected Integer idAssinante;
	protected String nomeAssinante;
	protected String cpfCnpjAssinante;
	
	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil) {
		this.perfis.add(perfil.getCodigo());
	}

}
