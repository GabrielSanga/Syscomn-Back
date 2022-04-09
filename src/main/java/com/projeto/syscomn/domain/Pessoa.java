package com.projeto.syscomn.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

import com.projeto.syscomn.domain.enums.Perfil;
import com.projeto.syscomn.interfaces.CnpjGroup;
import com.projeto.syscomn.interfaces.CpfGroup;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy= InheritanceType.JOINED)
@DiscriminatorColumn(name="Tipo")
@GroupSequenceProvider(PessoaGroupSequenceProvider.class)
public abstract class Pessoa implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer idPessoa;
	
	protected String nomePessoa;
	
	@Column(unique = true)
	@CPF(groups = CpfGroup.class)
	@CNPJ(groups = CnpjGroup.class)
	protected String cpfCnpjPessoa;
	
	protected String telefonePessoa;
	
	protected String emailPessoa;
	
	protected LocalDate dtaNascimentoPessoa;
	
	protected String enderecoPessoa;
	
	protected String statusPessoa;
	
	protected String rgPessoa;
	
	protected String observacaoPessoa;
	
	protected String userName;	
	
	protected String senha;
	
	protected Integer tipoPessoa;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	protected Set<Integer> perfis = new HashSet<>();
	
    @Column
    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    protected byte[] fotoPessoa;
	
	
	public Pessoa() {
		super();
	}

	public Pessoa(Integer idPessoa, String nomePessoa,
			@CPF(groups = CpfGroup.class) @CNPJ(groups = CnpjGroup.class) String cpfCnpjPessoa, String telefonePessoa,
			String emailPessoa, LocalDate dtaNascimentoPessoa, String enderecoPessoa, String statusPessoa, String rgPessoa,
			String observacaoPessoa, String userName, String senha, Integer tipoPessoa, Set<Integer> perfis,
			byte[] fotoPessoa) {
		super();
		this.idPessoa = idPessoa;
		this.nomePessoa = nomePessoa;
		this.cpfCnpjPessoa = cpfCnpjPessoa;
		this.telefonePessoa = telefonePessoa;
		this.emailPessoa = emailPessoa;
		this.dtaNascimentoPessoa = dtaNascimentoPessoa;
		this.enderecoPessoa = enderecoPessoa;
		this.statusPessoa = statusPessoa;
		this.rgPessoa = rgPessoa;
		this.observacaoPessoa = observacaoPessoa;
		this.userName = userName;
		this.senha = senha;
		this.tipoPessoa = tipoPessoa;
		this.perfis = perfis;
		this.fotoPessoa = fotoPessoa;
	}
	
	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil) {
		this.perfis.add(perfil.getCodigo());
	}
	
}
