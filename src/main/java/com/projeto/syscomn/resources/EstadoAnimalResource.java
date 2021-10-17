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

import com.projeto.syscomn.domain.EstadoAnimal;
import com.projeto.syscomn.domain.dtos.EstadoAnimalDTO;
import com.projeto.syscomn.domain.dtos.MateriaPrimaDTO;
import com.projeto.syscomn.services.EstadoAnimalService;

@RestController
@RequestMapping(value = "estadoanimal")
public class EstadoAnimalResource {
	
	@Autowired
	private EstadoAnimalService estadoAnimalService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<EstadoAnimalDTO> findById(@PathVariable Integer id){
		
		EstadoAnimal oEstadoAnimal = estadoAnimalService.findById(id);
		
		return ResponseEntity.ok().body(new EstadoAnimalDTO(oEstadoAnimal));
	}
	 
	@GetMapping
	public ResponseEntity<List<EstadoAnimalDTO>> findAll(){
		List<EstadoAnimal> lstEstadoAnimal = estadoAnimalService.findAll();
		List<EstadoAnimalDTO> lstEstadoAnimalDTO = lstEstadoAnimal.stream().map(x -> new EstadoAnimalDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(lstEstadoAnimalDTO);
	}
	
	@PostMapping
	public ResponseEntity<MateriaPrimaDTO> create(@Valid @RequestBody EstadoAnimalDTO oEstadoAnimalDTO){
		EstadoAnimal oEstadoAnimal = estadoAnimalService.create(oEstadoAnimalDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idEstadoAnimal}").buildAndExpand(oEstadoAnimal.getIdEstadoAnimal()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	 
	@PutMapping(value = "/{id}")
	public ResponseEntity<EstadoAnimalDTO> update(@PathVariable Integer id,@Valid @RequestBody EstadoAnimalDTO oEstadoAnimalDTO){
		EstadoAnimal oEstadoAnimal = estadoAnimalService.update(id, oEstadoAnimalDTO);
		
		return ResponseEntity.ok().body(new EstadoAnimalDTO(oEstadoAnimal));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<EstadoAnimalDTO> delete(@PathVariable Integer id){
		estadoAnimalService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
}
