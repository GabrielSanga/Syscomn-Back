package com.projeto.syscomn.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projeto.syscomn.domain.Pessoa;
import com.projeto.syscomn.repositores.PessoaRepository;
import com.projeto.syscomn.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		/* Busca um usuário com o login informado */
		Optional<Pessoa> user = pessoaRepository.findByUserName(username);
		
		/* Caso encontrado o funcionário */
		if (user.isPresent()) {
			/* Retorna um objeto com todas as informações para authenticação do usuário */
			return new UserSS(user.get().getIdPessoa(), user.get().getUserName(), user.get().getSenha(), user.get().getPerfis());
		}
		
		throw new UsernameNotFoundException(username);
	}

}
