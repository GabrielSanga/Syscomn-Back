package com.projeto.syscomn.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.syscomn.domain.dtos.FuncionarioDTO;
import com.projeto.syscomn.domain.enums.Perfil;
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
	
	private String status;
	
	public Funcionario() {
		super();
		addPerfil(Perfil.FUNCIONARIO);
	}

	public Funcionario(Integer idPessoa, String nomePessoa,
			@CPF(groups = CpfGroup.class) @CNPJ(groups = CnpjGroup.class) String cpfCnpjPessoa, String telefonePessoa,
			String emailPessoa, Date dtaNascimentoPessoa, String enderecoPessoa, String statusPessoa, String rgPessoa,
			String observacaoPessoa, String userName, String senha, Integer tipoPessoa, Set<Integer> perfis,
			byte[] fotoPessoa, Assinante assinante) {
		super(idPessoa, nomePessoa, cpfCnpjPessoa, telefonePessoa, emailPessoa, dtaNascimentoPessoa, enderecoPessoa,
				statusPessoa, rgPessoa, observacaoPessoa, userName, senha, tipoPessoa, perfis, fotoPessoa, assinante);
		addPerfil(Perfil.FUNCIONARIO);
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
		this.dtaAdmissao = pFuncionarioDTO.getDtaAdmissao();
		this.dtaDemissao= pFuncionarioDTO.getDtaDemissao();
		this.NIS = pFuncionarioDTO.getNIS();
		this.status = pFuncionarioDTO.getStatus();
		this.assinante = new Assinante();
		this.perfis = pFuncionarioDTO.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
	}


}	
