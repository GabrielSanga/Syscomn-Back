package com.projeto.syscomn.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.projeto.syscomn.domain.enums.Perfil;

public class UserSS implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String userName;
	private String senha;
	private Collection<? extends GrantedAuthority> authorities;
		
	public UserSS(Integer id, String userName, String senha, Set<Perfil> perfis) {
		super();
		this.id = id;
		this.userName = userName;
		this.senha = senha;
		this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toSet());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	/*Retorna o ID do Usuário*/
	public Integer getId() {
		return id;
	}
	
	/*Retorna o login do Usuário*/
	@Override
	public String getUsername() {
		return userName;
	}

	/*Retorna a senha do Usuário*/
	@Override
	public String getPassword() {
		return senha;
	}

	/*Busca se a conta está expirada - Software não ira validar isso, retorna TRUE*/
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/*Busca se a conta está bloqueado - Software não ira validar isso, retorna TRUE*/
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/*Busca se a senha não está expirada - Software não ira validar isso, retorna TRUE*/
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/*Busca se usuário está habilitado - Software não ira validar isso, retorna TRUE*/
	@Override
	public boolean isEnabled() {
		return true;
	}

}
