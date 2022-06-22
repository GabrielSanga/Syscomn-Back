package com.projeto.syscomn.domain;

import java.time.LocalDate;

import javax.persistence.Entity;

import com.projeto.syscomn.domain.dtos.FuncionarioDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Funcionario extends Pessoa {
	private static final long serialVersionUID = 1L;

	private LocalDate dtaAdmissao;

	private LocalDate dtaDemissao;

	private String NIS;
	
	private String status;
	
	public Funcionario() {
		super();
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
		this.userName = pFuncionarioDTO.getUserName();
		this.senha = pFuncionarioDTO.getSenha();
		this.tipoPessoa = pFuncionarioDTO.getTipoPessoa();
		this.fotoPessoa = pFuncionarioDTO.getFotoPessoa();
		this.dtaAdmissao = pFuncionarioDTO.getDtaAdmissao();
		this.dtaDemissao= pFuncionarioDTO.getDtaDemissao();
		this.NIS = pFuncionarioDTO.getNIS();
		this.status = pFuncionarioDTO.getStatus();
		this.perfis = pFuncionarioDTO.getPerfis();
	}


}	
