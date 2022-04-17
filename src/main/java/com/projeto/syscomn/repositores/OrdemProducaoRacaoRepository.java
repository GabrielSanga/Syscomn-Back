package com.projeto.syscomn.repositores;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.syscomn.domain.OrdemProducaoRacao;

public interface OrdemProducaoRacaoRepository extends JpaRepository<OrdemProducaoRacao, Integer> {
	
	Optional<List<OrdemProducaoRacao>> findByData(LocalDate dataOrdem);
	
}
