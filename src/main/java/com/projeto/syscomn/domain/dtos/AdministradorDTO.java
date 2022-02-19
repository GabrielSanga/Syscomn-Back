package com.projeto.syscomn.domain.dtos;

import java.time.LocalDate;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.syscomn.domain.Administrador;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministradorDTO extends PessoaDTO{
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Data de Admissão é campo de preenchimento obrigatório!")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dtaAdmissao;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dtaDemissao;
	
	private String status;
	
	public AdministradorDTO() {
		super();
	}

	public AdministradorDTO(Administrador pAdministador) {
		super();
		this.idPessoa = pAdministador.getIdPessoa();
		this.nomePessoa = pAdministador.getNomePessoa();
		this.cpfCnpjPessoa = pAdministador.getCpfCnpjPessoa();
		this.telefonePessoa = pAdministador.getTelefonePessoa();
		this.emailPessoa = pAdministador.getEmailPessoa();
		this.dtaNascimentoPessoa = pAdministador.getDtaNascimentoPessoa();;
		this.enderecoPessoa = pAdministador.getEnderecoPessoa();
		this.statusPessoa = pAdministador.getStatusPessoa();
		this.rgPessoa = pAdministador.getRgPessoa();
		this.observacaoPessoa = pAdministador.getObservacaoPessoa();
		this.userName = pAdministador.getUserName();
		this.senha = pAdministador.getSenha();
		this.tipoPessoa = pAdministador.getTipoPessoa();
		this.idAssinante = pAdministador.getAssinante().getIdAssinante();
		this.nomeAssinante = pAdministador.getAssinante().getNomeAssinante();
		this.cpfCnpjAssinante = pAdministador.getAssinante().getCpfCnpj();
		this.dtaAdmissao = pAdministador.getDtaAdmissao();
		this.dtaDemissao= pAdministador.getDtaDemissao();
		this.status = pAdministador.getStatus();
		this.perfis = pAdministador.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
	}
	
}