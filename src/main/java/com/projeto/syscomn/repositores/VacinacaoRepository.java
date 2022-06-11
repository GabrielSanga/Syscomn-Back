package com.projeto.syscomn.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.syscomn.domain.Vacinacao;

public interface VacinacaoRepository extends JpaRepository<Vacinacao, Integer>{ }
