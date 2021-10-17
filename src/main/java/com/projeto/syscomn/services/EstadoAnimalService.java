package com.projeto.syscomn.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.syscomn.domain.EstadoAnimal;
import com.projeto.syscomn.domain.dtos.EstadoAnimalDTO;
import com.projeto.syscomn.repositores.EstadoAnimalRepository;
import com.projeto.syscomn.services.exceptions.ObjectNotFoundException;

@Service
public class EstadoAnimalService {
	
	@Autowired
	private EstadoAnimalRepository estadoAnimalRepository;

	public EstadoAnimal findById(Integer id) {
		Optional<EstadoAnimal> oEstadoAnimal = estadoAnimalRepository.findById(id);
		
		return oEstadoAnimal.orElseThrow(() -> new ObjectNotFoundException("Estado Animal não encontrado! ID: " + id));
	}

	public List<EstadoAnimal> findAll() {	
		return estadoAnimalRepository.findAll();
	}

	public EstadoAnimal create(@Valid EstadoAnimalDTO oEstadoAnimalDTO) {
		oEstadoAnimalDTO.setIdEstadoAnimal(null);
		
		EstadoAnimal oEstadoAnimal = new EstadoAnimal(oEstadoAnimalDTO);
		
		return estadoAnimalRepository.save(oEstadoAnimal);
	}

	public EstadoAnimal update(Integer id,EstadoAnimalDTO oEstadoAnimalDTO) {
		oEstadoAnimalDTO.setIdEstadoAnimal(id);;
		
		EstadoAnimal oEstadoAnimal = findById(id);
		
		oEstadoAnimal = new EstadoAnimal(oEstadoAnimalDTO);
		
		return estadoAnimalRepository.save(oEstadoAnimal);
	}

	public void delete(Integer id) {
		EstadoAnimal oEstadoAnimal = findById(id);
		estadoAnimalRepository.deleteById(oEstadoAnimal.getIdEstadoAnimal());
	}

}
