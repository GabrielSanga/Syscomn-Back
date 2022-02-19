package com.projeto.syscomn.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {
	
	@Value("${jwt.expiration}")
	private Long experation; //-> Tempo de expiração definida nas properties
	
	@Value("${jwt.secret}")
	private String secret; //-> Chave do Token definida nas properties
	
	/*Método responsável por gerar o Token que será enviado no retorno da requisição*/
	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username) // -> Setando o Login nas informações do token
				.setExpiration(new Date(System.currentTimeMillis() + experation)) // -> Setando a data de expiração do token, data atual + expiração definida
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()) // -> Setando algoritimo para assinatura do token e passando a chave secreta para ser embaralhada
				.compact();
	}
	
	public boolean tokenValido(String token) {
		//Buscando as Claims presentes no Token
		Claims claims = getClaims(token);
		
		//Caso encontrado
		if(claims != null) {
			String username = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			
			//Verificando se o login foi encontrado e se o token não está expirado
			if(username != null && expirationDate != null && now.before(expirationDate)) {
				return true;
			}
		}
		return false;
	}
	
	//Método responsável por descriptografar o token e devolver os Claims
	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			return null;
		}
	}

	//Método responsável por devolver o Login do usuário presente no token
	public String getUserName(String token) {
		Claims claims = getClaims(token);
		
		if(claims != null) {
			return claims.getSubject();
		}
		
		return null;
	}

}
