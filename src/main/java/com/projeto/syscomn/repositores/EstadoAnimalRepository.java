package com.projeto.syscomn.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.syscomn.domain.EstadoAnimal;

public interface EstadoAnimalRepository extends JpaRepository<EstadoAnimal, Integer>{

}
