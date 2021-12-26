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

import com.projeto.syscomn.domain.Propriedade;
import com.projeto.syscomn.domain.dtos.PropriedadeDTO;
import com.projeto.syscomn.services.PropriedadeService;

@RestController
@RequestMapping(value = "propriedade")
public class PropriedadeResource {
	
	@Autowired
	private PropriedadeService propriedadeService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PropriedadeDTO> findById(@PathVariable Integer id){
		
		Propriedade oPropriedade = propriedadeService.findById(id);
		
		return ResponseEntity.ok().body(new PropriedadeDTO(oPropriedade));
	}
	 
	@GetMapping
	public ResponseEntity<List<PropriedadeDTO>> findAll(){
		List<Propriedade> lstPropriedade = propriedadeService.findAll();
		List<PropriedadeDTO> lstPropriedadeDTO = lstPropriedade.stream().map(x -> new PropriedadeDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(lstPropriedadeDTO);
	}
	
	@PostMapping
	public ResponseEntity<PropriedadeDTO> create(@Valid @RequestBody PropriedadeDTO oPropriedadeDTO){
		Propriedade oPropriedade = propriedadeService.create(oPropriedadeDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idPropriedade}").buildAndExpand(oPropriedade.getIdPropriedade()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<PropriedadeDTO> update(@PathVariable Integer id,@Valid @RequestBody PropriedadeDTO oPropriedadeDTO){
		Propriedade oPropriedade = propriedadeService.update(id, oPropriedadeDTO);
		
		return ResponseEntity.ok().body(new PropriedadeDTO(oPropriedade));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<PropriedadeDTO> delete(@PathVariable Integer id){
		propriedadeService.delete(id);
		
		return ResponseEntity.noContent().build();
	}

}
