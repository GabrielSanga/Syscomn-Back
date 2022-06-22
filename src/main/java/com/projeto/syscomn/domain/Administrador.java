package com.projeto.syscomn.domain;

import java.time.LocalDate;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.syscomn.domain.dtos.AdministradorDTO;
import com.projeto.syscomn.domain.enums.Perfil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Administrador extends Pessoa{
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern = "MM/dd/yyyy")
	private LocalDate dtaAdmissao;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dtaDemissao;
	
	private String status;

	public Administrador() {
		super();
		super.addPerfil(Perfil.DIRETOR);
		super.addPerfil(Perfil.ADMIN);
		super.addPerfil(Perfil.FUNCIONARIO);
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
		this.fotoPessoa = pAdministadorDTO.getFotoPessoa();
		this.tipoPessoa = pAdministadorDTO.getTipoPessoa();
		this.dtaAdmissao = pAdministadorDTO.getDtaAdmissao();
		this.dtaDemissao= pAdministadorDTO.getDtaDemissao();
		this.status = pAdministadorDTO.getStatus();
		this.perfis = pAdministadorDTO.getPerfis();
		super.addPerfil(Perfil.DIRETOR);
		super.addPerfil(Perfil.ADMIN);
		super.addPerfil(Perfil.FUNCIONARIO);
	}
		
}
