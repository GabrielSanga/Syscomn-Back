package com.projeto.syscomn.resources;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.syscomn.domain.SexoAnimal;
import com.projeto.syscomn.domain.dtos.SexoAnimalDTO;
import com.projeto.syscomn.services.SexoAnimalService;

@RestController
@RequestMapping(value = "sexoanimal")
public class SexoAnimalResource {
	
	@Autowired
	private SexoAnimalService sexoAnimalService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SexoAnimalDTO> findById(@PathVariable Integer id){
		
		SexoAnimal oSexoAnimal = sexoAnimalService.findById(id);
		
		return ResponseEntity.ok().body(new  SexoAnimalDTO(oSexoAnimal));
	}
	 
	@GetMapping
	public ResponseEntity<List<SexoAnimalDTO>> findAll(){
		List<SexoAnimal> lstSexoAnimal = sexoAnimalService.findAll();
		List<SexoAnimalDTO> lstSexoAnimalDTO = lstSexoAnimal.stream().map(x -> new SexoAnimalDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(lstSexoAnimalDTO);
	}	

}
