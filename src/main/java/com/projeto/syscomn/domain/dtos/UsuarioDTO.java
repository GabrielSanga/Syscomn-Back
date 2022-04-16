package com.projeto.syscomn.domain.dtos;

import java.io.Serializable;

import com.projeto.syscomn.domain.Pessoa;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer idUsuario;
	private String nomeUsuario;
    private byte[] foto;
    private String senhaAntiga;
    private String senha;
    private Integer idUsuarioAlteracao;
    
    public UsuarioDTO() {}
   
	public UsuarioDTO(Pessoa pPessoa) {
		super();
		this.idUsuario = pPessoa.getIdPessoa();
		this.nomeUsuario = pPessoa.getNomePessoa();
		this.foto = pPessoa.getFotoPessoa();
	}
    
}
