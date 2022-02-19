package com.projeto.syscomn.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.syscomn.domain.dtos.AdministradorDTO;
import com.projeto.syscomn.domain.enums.Perfil;
import com.projeto.syscomn.interfaces.CnpjGroup;
import com.projeto.syscomn.interfaces.CpfGroup;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Administrador extends Pessoa{
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dtaAdmissao;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dtaDemissao;
	
	private String status;

	public Administrador() {
		super();
		super.addPerfil(Perfil.ADMIN);
	}
	
	public Administrador(Integer idPessoa, String nomePessoa,
			@CPF(groups = CpfGroup.class) @CNPJ(groups = CnpjGroup.class) String cpfCnpjPessoa, String telefonePessoa,
			String emailPessoa, Date dtaNascimentoPessoa, String enderecoPessoa, String statusPessoa, String rgPessoa,
			String observacaoPessoa, String userName, String senha, Integer tipoPessoa, Set<Integer> perfis,
			byte[] fotoPessoa, Assinante assinante) {
		super(idPessoa, nomePessoa, cpfCnpjPessoa, telefonePessoa, emailPessoa, dtaNascimentoPessoa, enderecoPessoa,
				statusPessoa, rgPessoa, observacaoPessoa, userName, senha, tipoPessoa, perfis, fotoPessoa, assinante);
		super.addPerfil(Perfil.ADMIN);
	}
	
	public Administrador(AdministradorDTO pAdministadorDTO) {
		super();
		this.idPessoa = pAdministadorDTO.getIdPessoa();
		this.nomePessoa = pAdministadorDTO.getNomePessoa();
		this.cpfCnpjPessoa = pAdministadorDTO.getCpfCnpjPessoa();
		this.telefonePessoa = pAdministadorDTO.getTelefonePessoa();
		this.emailPessoa = pAdministadorDTO.getEmailPessoa();
		this.dtaNascimentoPessoa = pAdministadorDTO.getDtaNascimentoPessoa();;
		this.enderecoPessoa = pAdministadorDTO.getEnderecoPessoa();
		this.statusPessoa = pAdministadorDTO.getStatusPessoa();
		this.rgPessoa = pAdministadorDTO.getRgPessoa();
		this.observacaoPessoa = pAdministadorDTO.getObservacaoPessoa();
		this.userName = pAdministadorDTO.getUserName();
		this.senha = pAdministadorDTO.getSenha();
		this.tipoPessoa = pAdministadorDTO.getTipoPessoa();
		this.dtaAdmissao = pAdministadorDTO.getDtaAdmissao();
		this.dtaDemissao= pAdministadorDTO.getDtaDemissao();
		this.status = pAdministadorDTO.getStatus();
		this.assinante = new Assinante();
		this.perfis = pAdministadorDTO.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
	}
		
}
