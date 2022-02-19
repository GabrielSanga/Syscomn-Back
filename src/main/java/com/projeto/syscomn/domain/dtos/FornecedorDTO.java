package com.projeto.syscomn.domain.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.projeto.syscomn.domain.Fornecedor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FornecedorDTO extends PessoaDTO{
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Razão Social é campo de preenchimento obrigatório!")
    @Size(min = 5, max = 100, message = "Razão Social deve ter entre 5 e 100 letras!")
	private String razaoSocial;
	
	@NotBlank(message = "Nome Fantasía é campo de preenchimento obrigatório!")	
    @Size(min = 5, max = 100, message = "Nome Fantasía deve ter entre 5 e 100 letras!")
	private String nomeFantasia;
	
	@NotBlank(message = "Inscricao Estadual é campo de preenchimento obrigatório!")	
	private String inscricaoEstadual;
	
	private String cnae;
	
	private String status;
	
	@NotNull(message = "Tipo Forncedor é campo de preenchimento obrigatório!")
	private Integer idTipoFornecedor;
	private String descricaoTipoFornecedor;
	
	public FornecedorDTO() {
		super();
	}
	
	public FornecedorDTO(Fornecedor pFornecedor) {
		super();
		this.idPessoa = pFornecedor.getIdPessoa();
		this.nomePessoa = pFornecedor.getNomePessoa();
		this.cpfCnpjPessoa = pFornecedor.getCpfCnpjPessoa();
		this.telefonePessoa = pFornecedor.getTelefonePessoa();
		this.emailPessoa = pFornecedor.getEmailPessoa();
		this.dtaNascimentoPessoa = pFornecedor.getDtaNascimentoPessoa();;
		this.enderecoPessoa = pFornecedor.getEnderecoPessoa();
		this.statusPessoa = pFornecedor.getStatusPessoa();
		this.rgPessoa = pFornecedor.getRgPessoa();
		this.observacaoPessoa = pFornecedor.getObservacaoPessoa();
		this.userName = pFornecedor.getUserName();
		this.senha = pFornecedor.getSenha();
		this.tipoPessoa = pFornecedor.getTipoPessoa();
		this.idAssinante = pFornecedor.getAssinante().getIdAssinante();
		this.nomeAssinante = pFornecedor.getAssinante().getNomeAssinante();
		this.cpfCnpjAssinante = pFornecedor.getAssinante().getCpfCnpj();
		this.razaoSocial = pFornecedor.getRazaoSocial();
		this.nomeFantasia = pFornecedor.getNomeFantasia();
		this.inscricaoEstadual = pFornecedor.getInscricaoEstadual();
		this.cnae = pFornecedor.getCnae();
		this.status = pFornecedor.getStatus();
		this.idTipoFornecedor = pFornecedor.getTipoFornecedor().getIdTipoFornecedor();
		this.descricaoTipoFornecedor = pFornecedor.getTipoFornecedor().getDescricao();
	}
	
}
