package com.projeto.syscomn.repositores;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.syscomn.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{
		
	Optional<Pessoa> findByUserName(String username);

}
