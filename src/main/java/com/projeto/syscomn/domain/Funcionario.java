package com.projeto.syscomn.domain;

import java.time.LocalDate;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.syscomn.domain.dtos.FuncionarioDTO;
import com.projeto.syscomn.interfaces.CnpjGroup;
import com.projeto.syscomn.interfaces.CpfGroup;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Funcionario extends Pessoa {
	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dtaAdmissao;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dtaDemissao;

	private String NIS;
	
	public Funcionario() {
		super();
	}

	public Funcionario(Integer idPessoa, String nomePessoa,
			@CPF(groups = CpfGroup.class) @CNPJ(groups = CnpjGroup.class) String cpfCnpjPessoa, String telefonePessoa,
			String emailPessoa, LocalDate dtaNascimentoPessoa, String enderecoPessoa, String statusPessoa,
			String rgPessoa, String observacaoPessoa, String login, String senha, Integer tipoPessoa,
			Assinante assinante) {
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
		this.observacaoPessoa = pFuncionarioDTO.getObservacaoPessoa();
		this.login = pFuncionarioDTO.getLogin();
		this.senha = pFuncionarioDTO.getSenha();
		this.tipoPessoa = pFuncionarioDTO.getTipoPessoa();
		this.dtaAdmissao = pFuncionarioDTO.getDtaAdmissao();
		this.dtaDemissao= pFuncionarioDTO.getDtaDemissao();
		this.NIS = pFuncionarioDTO.getNIS();
		this.assinante = new Assinante();
	}

}	
