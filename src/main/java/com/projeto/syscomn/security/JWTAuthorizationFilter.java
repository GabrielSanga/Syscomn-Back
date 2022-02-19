package com.projeto.syscomn.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter{

	private JWTUtil jwtUtil;
	private UserDetailsService userDetailsService;
	
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//Recuperando o Token que foi enviado por Parâmetro na requisição
		String token = recuperaToken(request);
		
		//Verificando se o token foi encontrado
		if(token != null) {		
			UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(token);
			
			//Caso o token seja válido
			if(authenticationToken != null) {
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}		
		}
		
		//Continua a rotina de autorização do Spring
		chain.doFilter(request, response);
	}

	private String recuperaToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return token.substring(7, token.length());
	}

	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		//Verifica se o token é válido
		if(jwtUtil.tokenValido(token)) {
			//Pegando o login do usuário que está presente no token
			String userName = jwtUtil.getUserName(token);
			
			//Buscando um usuário que possua esse login
			UserDetails details = userDetailsService.loadUserByUsername(userName);
			
			return new UsernamePasswordAuthenticationToken(details.getUsername(), null,  details.getAuthorities());
		}
		return null;
	}
	
}
