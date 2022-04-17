package com.projeto.syscomn.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.syscomn.domain.Movimentacao;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Integer>{

}
