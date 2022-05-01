package com.projeto.syscomn.repositores;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projeto.syscomn.domain.LoteRacao;

public interface LoteRacaoRepository extends JpaRepository<LoteRacao, Integer>{
	
	
	@Query("Select L.racaoProduzir.racao.idRacao, L.racaoProduzir.racao.descricao, L.racaoProduzir.racao.unidade,"
			+ " L.dataFabricacao, L.dataValidade,"
			+ " Sum(L.saldo) As Saldo"
			+ " From LoteRacao L"
			+ " Where L.saldo > 0"
			+ " Group By L.racaoProduzir.racao.idRacao, L.racaoProduzir.racao.descricao, L.racaoProduzir.racao.unidade, L.dataFabricacao, L.dataValidade"
			+ " Order By L.dataFabricacao, L.dataValidade, L.racaoProduzir.racao.idRacao, L.racaoProduzir.racao.descricao, L.racaoProduzir.racao.unidade")
	List<Object> findAllProducaoPorData();
	
}
