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

import com.projeto.syscomn.domain.AnimalChip;
import com.projeto.syscomn.domain.dtos.AnimalChipDTO;
import com.projeto.syscomn.services.AnimalChipService;

@RestController
@RequestMapping(value = "animalchip")
public class AnimalChipResource {
	
	@Autowired
	private AnimalChipService animalChipService;
	

	@GetMapping(value = "/{id}")
	public ResponseEntity<AnimalChipDTO> findById(@PathVariable Integer id){
		AnimalChip oAnimalChip = animalChipService.findById(id);
				
		return ResponseEntity.ok().body(new AnimalChipDTO(oAnimalChip));
	}
	
	@GetMapping
	public ResponseEntity<List<AnimalChipDTO>> findAll(){
		List<AnimalChip> lstAnimalChip = animalChipService.findAll();
		List<AnimalChipDTO> lstAnimalChipDTO = lstAnimalChip.stream().map(x -> new AnimalChipDTO(x)).collect(Collectors.toList());
				
		return ResponseEntity.ok().body(lstAnimalChipDTO);
	}
	
	@PostMapping
	public ResponseEntity<AnimalChipDTO> create(@Valid @RequestBody AnimalChipDTO pAnimalChipDTO){
		AnimalChip oAnimalChip = animalChipService.create(pAnimalChipDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idRacaoProduzir}").buildAndExpand(oAnimalChip.getIdAnimalChip()).toUri();
 
		return ResponseEntity.created(uri).build();	
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<AnimalChipDTO> update(@Valid @RequestBody AnimalChipDTO pAnimalChipDTO, @PathVariable Integer id){
		AnimalChip oAnimalChip = animalChipService.update(pAnimalChipDTO, id);
				
		return ResponseEntity.ok().body(new AnimalChipDTO(oAnimalChip));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<AnimalChipDTO> delete(@PathVariable Integer id){
		animalChipService.delete(id);
				
		return ResponseEntity.noContent().build();
	}

}
