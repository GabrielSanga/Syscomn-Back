package com.projeto.syscomn.repositores;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projeto.syscomn.domain.AnimalChip;

public interface AnimalChipRepository extends JpaRepository<AnimalChip, Integer>{
	
	@Query("Select Count(*) As Qtd, Extract('Month' From A.dtaSaida)"
			+ "From AnimalChip A  "
			+ "Where tipoMorte > 0 "
			+ "And Extract('Year' From A.dtaSaida) = Extract('Year' From Now()) "
			+ "Group By Extract('Month' From A.dtaSaida) "
			+ "Order By Extract('Month' From A.dtaSaida)")
	List<Object> findAllMorteMes();
	
}
