package com.projeto.syscomn.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.syscomn.domain.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer>{

}
