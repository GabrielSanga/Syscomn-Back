package com.projeto.syscomn.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@PostMapping
	public ResponseEntity<SexoAnimalDTO> create(@Valid @RequestBody SexoAnimalDTO oSexoAnimalDTO) {
		SexoAnimal oSexoAnimal = sexoAnimalService.create(oSexoAnimalDTO);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idSexoAnimal}")
				.buildAndExpand(oSexoAnimal.getIdSexoAnimal()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<SexoAnimalDTO> update(@PathVariable Integer id, @RequestBody SexoAnimalDTO oSexoAnimalDTO) {
		SexoAnimal oSexoAnimal = sexoAnimalService.update(id, oSexoAnimalDTO);
		return ResponseEntity.ok().body(new SexoAnimalDTO(oSexoAnimal));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<SexoAnimalDTO> delete(@PathVariable Integer id) {
		sexoAnimalService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
