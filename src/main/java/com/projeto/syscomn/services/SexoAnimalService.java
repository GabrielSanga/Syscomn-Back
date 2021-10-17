package com.projeto.syscomn.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.projeto.syscomn.domain.SexoAnimal;
import com.projeto.syscomn.domain.dtos.SexoAnimalDTO;
import com.projeto.syscomn.repositores.SexoAnimalRepository;
import com.projeto.syscomn.services.exceptions.ObjectNotFoundException;

@Service
public class SexoAnimalService {
	
	private SexoAnimalRepository sexoAnimalRepository;

	public SexoAnimal findById(Integer id) {
		Optional<SexoAnimal> oSexoAnimal = sexoAnimalRepository.findById(id);
		
		return oSexoAnimal.orElseThrow(() -> new ObjectNotFoundException("Sexo Animal não encontrado! ID: " + id));
	}

	public List<SexoAnimal> findAll() {	
		return sexoAnimalRepository.findAll();
	}

	public SexoAnimal create(@Valid SexoAnimalDTO oSexoAnimalDTO) {
		oSexoAnimalDTO.setIdSexoAnimal(null);
		
		SexoAnimal oSexoAnimal = new SexoAnimal(oSexoAnimalDTO);
		
		return sexoAnimalRepository.save(oSexoAnimal);
	}

	public SexoAnimal update(Integer id,SexoAnimalDTO oSexoAnimalDTO) {
		oSexoAnimalDTO.setIdSexoAnimal(id);;
		
		SexoAnimal oSexoAnimal = findById(id);
		
		oSexoAnimal = new SexoAnimal(oSexoAnimalDTO);
		
		return sexoAnimalRepository.save(oSexoAnimal);
	}

	public void delete(Integer id) {
		SexoAnimal oSexoAnimal = findById(id);
		sexoAnimalRepository.deleteById(oSexoAnimal.getIdSexoAnimal());
	}

}
