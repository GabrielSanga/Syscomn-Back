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

import com.projeto.syscomn.domain.Assinante;
import com.projeto.syscomn.domain.dtos.AssinanteDTO;
import com.projeto.syscomn.services.AssinanteService;

@RestController
@RequestMapping(value = "assinante")
public class AssinanteResource {
	
	@Autowired
	private AssinanteService assinanteService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<AssinanteDTO> findById(@PathVariable Integer id){		
		
		Assinante oAssinante = assinanteService.findById(id);
		
		return ResponseEntity.ok().body(new AssinanteDTO(oAssinante));
	}
	 
	@GetMapping
	public ResponseEntity<List<AssinanteDTO>> findAll(){
		List<Assinante> lstAssinante = assinanteService.findAll();	
		List<AssinanteDTO> lstAssinanteDTO = lstAssinante.stream().map(x -> new AssinanteDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(lstAssinanteDTO);
	}
	
	@PostMapping
	public ResponseEntity<AssinanteDTO> create(@Valid @RequestBody AssinanteDTO oAssinanteDTO){
		Assinante oAssinante = assinanteService.create(oAssinanteDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idAssinante}").buildAndExpand(oAssinante.getIdAssinante()).toUri();

		return ResponseEntity.created(uri).build();	
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<AssinanteDTO> update(@Valid @RequestBody AssinanteDTO oAssinanteDTO, @PathVariable Integer id){
		Assinante oAssinante = assinanteService.update(oAssinanteDTO, id);
		
		return ResponseEntity.ok().body(new AssinanteDTO(oAssinante));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<AssinanteDTO> delete(@PathVariable Integer id){
		assinanteService.delete(id);
		
		return ResponseEntity.noContent().build();
	}

}
