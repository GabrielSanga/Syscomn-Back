package com.projeto.syscomn.domain;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import com.projeto.syscomn.domain.dtos.FornecedorDTO;
import com.projeto.syscomn.interfaces.CnpjGroup;
import com.projeto.syscomn.interfaces.CpfGroup;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Fornecedor extends Pessoa{
	private static final long serialVersionUID = 1L;
	
	private String razaoSocial;
	
	private String nomeFantasia;
	
	private String inscricaoEstadual;
	
	private String cnae;
	
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "idTipoForncedor")
	private TipoFornecedor tipoFornecedor;

	public Fornecedor() {
		super();
	}
	
	public Fornecedor(Integer idPessoa, String nomePessoa,
			@CPF(groups = CpfGroup.class) @CNPJ(groups = CnpjGroup.class) String cpfCnpjPessoa, String telefonePessoa,
			String emailPessoa, LocalDate dtaNascimentoPessoa, String enderecoPessoa, String statusPessoa, String rgPessoa,
			String observacaoPessoa, String userName, String senha, Integer tipoPessoa, Set<Integer> perfis,
			byte[] fotoPessoa) {
		super(idPessoa, nomePessoa, cpfCnpjPessoa, telefonePessoa, emailPessoa, dtaNascimentoPessoa, enderecoPessoa,
				statusPessoa, rgPessoa, observacaoPessoa, userName, senha, tipoPessoa, perfis, fotoPessoa);
	}
		
	public Fornecedor(FornecedorDTO pFornecedorDTO) {
		super();
		this.idPessoa = pFornecedorDTO.getIdPessoa();
		this.nomePessoa = pFornecedorDTO.getNomePessoa();
		this.cpfCnpjPessoa = pFornecedorDTO.getCpfCnpjPessoa();
		this.telefonePessoa = pFornecedorDTO.getTelefonePessoa();
		this.emailPessoa = pFornecedorDTO.getEmailPessoa();
		this.dtaNascimentoPessoa = pFornecedorDTO.getDtaNascimentoPessoa();;
		this.enderecoPessoa = pFornecedorDTO.getEnderecoPessoa();
		this.statusPessoa = pFornecedorDTO.getStatusPessoa();
		this.rgPessoa = pFornecedorDTO.getRgPessoa();
		this.observacaoPessoa = pFornecedorDTO.getObservacaoPessoa();
		this.userName = pFornecedorDTO.getUserName();
		this.senha = pFornecedorDTO.getSenha();
		this.tipoPessoa = pFornecedorDTO.getTipoPessoa();
		this.razaoSocial = pFornecedorDTO.getRazaoSocial();
		this.nomeFantasia = pFornecedorDTO.getNomeFantasia();
		this.inscricaoEstadual = pFornecedorDTO.getInscricaoEstadual();
		this.cnae = pFornecedorDTO.getCnae();
		this.status = pFornecedorDTO.getStatus();
		this.tipoFornecedor = new TipoFornecedor();
	}

}
