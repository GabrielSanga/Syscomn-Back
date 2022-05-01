package com.projeto.syscomn.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.syscomn.domain.Pesagem;

public interface PesagemRepository extends JpaRepository<Pesagem, Integer>{ }
