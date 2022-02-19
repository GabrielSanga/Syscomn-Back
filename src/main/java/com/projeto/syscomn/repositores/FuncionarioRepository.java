package com.projeto.syscomn.repositores;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.syscomn.domain.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer>{

	Optional<Funcionario> findByUserName(String username);
}
