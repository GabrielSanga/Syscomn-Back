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

import com.projeto.syscomn.domain.MateriaPrima;
import com.projeto.syscomn.domain.dtos.MateriaPrimaDTO;
import com.projeto.syscomn.services.MateriaPrimaService;

@RestController
@RequestMapping(value = "materiaprima")
public class MateriaPrimaResource {
	
	@Autowired
	private MateriaPrimaService materiaPrimaService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<MateriaPrimaDTO> findById(@PathVariable Integer id){
		
		MateriaPrima oMateriaPrima = materiaPrimaService.findById(id);
		
		return ResponseEntity.ok().body(new MateriaPrimaDTO(oMateriaPrima));
	}
	
	@GetMapping
	public ResponseEntity<List<MateriaPrimaDTO>> findAll(){
		List<MateriaPrima> lstMateriaPrima = materiaPrimaService.findAll();
		List<MateriaPrimaDTO> lstMateriaPrimaDTO = lstMateriaPrima.stream().map(x -> new MateriaPrimaDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(lstMateriaPrimaDTO);
	}
	 
	@PostMapping
	public ResponseEntity<MateriaPrimaDTO> create(@Valid @RequestBody MateriaPrimaDTO oMateriaPrimaDTO){
		MateriaPrima oMateriaPrima =materiaPrimaService.create(oMateriaPrimaDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idMateriaPrima}").buildAndExpand(oMateriaPrima.getIdMateriaPrima()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<MateriaPrimaDTO> update(@PathVariable Integer id,@Valid @RequestBody MateriaPrimaDTO oMateriaPrimaDTO){
		MateriaPrima oMateriaPrima = materiaPrimaService.update(id, oMateriaPrimaDTO);
		
		return ResponseEntity.ok().body(new MateriaPrimaDTO(oMateriaPrima));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<MateriaPrimaDTO> delete(@PathVariable Integer id){
		materiaPrimaService.delete(id);
		
		return ResponseEntity.noContent().build();
	}

}
