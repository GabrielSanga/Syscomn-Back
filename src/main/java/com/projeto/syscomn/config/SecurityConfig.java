package com.projeto.syscomn.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.projeto.syscomn.security.JWTAuthorizationFilter;
import com.projeto.syscomn.security.JWTAuthenticationFilter;
import com.projeto.syscomn.security.JWTUtil;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	//Configurações de Autenticação
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//userDetailsService diz para o spring qual é a classe que possui a lógica da autenticação
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	//Configuraçõe de Autorização
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//Para todas as requisições será necessário a autenticação 
		http.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/login").permitAll() //Especifica uma URL em específico, bloqueando ou liberando
			.antMatchers(HttpMethod.POST, "/administrador").permitAll()
			.anyRequest().authenticated() //Qualquer outra requisição precisa ser autenticada
			.and().csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //Desabilitando a criação de sessão de usuário ao efetuar login
		
		//Adicionando nosso tratamento personalizado para o security
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
		
		//Adicionando tratamento personálizado para verificar se o usuário está autorizado
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));

	}
	
	//Configurações de recursos estáticos - JS, CSS, Imagens etc...
	@Override
	public void configure(WebSecurity web) throws Exception {
	}
	
	//Liberação dos CORS
	@Bean
	CorsConfigurationSource configurationSource() {
		/*Liberando as requisições de outros locais - Liberando Cors*/
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		
		/*Todas as fontes receberão as configurações acima*/
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		
		return source;
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}

