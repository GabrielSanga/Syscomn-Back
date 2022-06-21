package com.projeto.syscomn.domain.dtos;

import java.io.Serializable;
import java.util.Set;

import com.projeto.syscomn.domain.Pessoa;
import com.projeto.syscomn.domain.enums.Perfil;

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
    private Set<Perfil> roles;
    
    public UsuarioDTO() {}
   
	public UsuarioDTO(Pessoa pPessoa) {
		super();
		this.idUsuario = pPessoa.getIdPessoa();
		this.nomeUsuario = pPessoa.getNomePessoa();
		this.foto = pPessoa.getFotoPessoa();
		this.roles = pPessoa.getPerfis();
	}
    
}
