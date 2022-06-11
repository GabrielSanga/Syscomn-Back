package com.projeto.syscomn.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.syscomn.domain.Alimentacao;

public interface AlimentacaoRepository extends JpaRepository<Alimentacao, Integer>{ }
