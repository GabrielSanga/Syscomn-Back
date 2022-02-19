package com.projeto.syscomn.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.syscomn.domain.dtos.CredenciaisDTO;

/* OBS: Ao extender essa classe o spring irá entender que esse filtro vai interceptar todas as requisições POST no Endpoint /Login */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	/*OBS: Esse obj é a principal interface de autenticação. Se o login e senha (Principal) for verificado
	 * o método Authenticate irá retornar TRUE, do contrário NULL */
	private AuthenticationManager authenticationManager;
	private JWTUtil jwtUtil;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}
	
	/*Toda tentativa de autenticação será executado esse método*/
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			/* Cria um objeto de credenciais com os valores que vieram na requisição do /Login (login e senha) */
			CredenciaisDTO creds = new ObjectMapper().readValue(request.getInputStream(), CredenciaisDTO.class);
			
			/*Objeto que recebe as informações de login, senha e autorização que serão validados*/
			UsernamePasswordAuthenticationToken authenticationToken = 
					new UsernamePasswordAuthenticationToken(creds.getUserName(), creds.getSenha(), new ArrayList<>());
			
			/*Envia o objeto acima para o método Authenticate que irá tentar realizar a autenticação*/
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			
			/*Retorna se o usuário está autenticado ou não*/
			return authentication;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/*Caso tenha sucesso na autenticação entra nesse método*/
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		//Obs: AuthResult é o resultado da autenticação
		
		//Pegando o login que foi autenticado
		String username = ((UserSS) authResult.getPrincipal()).getUsername();
		//Criando o token que será retornado para o usuário
		String token = jwtUtil.generateToken(username);
		
		//Passando a resposta da requisição junto com o token
		response.setHeader("access-control-expose-headers", "Authorization");
		response.setHeader("Authorization", "Bearer " + token);
	}
	
	/*Caso não tenha sucesso na autenticação entra nesse método*/
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		//Em caso de não autenticação devolve um status 401 junto com mensagem de falha na autenticação
		response.setStatus(401);
		response.setContentType("application/json");
		response.getWriter().append(json());
	}

	//Retorna o JSON com informações de falha na autenticação
	private CharSequence json() {
		long date = new Date().getTime();
		return "{"
				+ "\"timestamp\": " + date + ", "
				+ "\"status\": 401, "
				+ "\"error\": \"Não Autorizado\", "
				+ "\"message\": \"Login ou Senha Inválidos\", "
				+ "\"path\": \"/login\"}, ";
	}
	
}
