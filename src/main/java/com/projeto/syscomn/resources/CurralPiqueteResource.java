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

import com.projeto.syscomn.domain.CurralPiquete;
import com.projeto.syscomn.domain.dtos.CurralPiqueteDTO;
import com.projeto.syscomn.services.CurralPiqueteService;

@RestController
@RequestMapping(value = "curralpiquete")
public class CurralPiqueteResource {
	
	@Autowired
	private CurralPiqueteService curralPiqueteService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CurralPiqueteDTO> findById(@PathVariable Integer id){
		
		CurralPiquete oCurralPiquete = curralPiqueteService.findById(id);
		
		return ResponseEntity.ok().body(new  CurralPiqueteDTO(oCurralPiquete));
	}
	 
	@GetMapping
	public ResponseEntity<List<CurralPiqueteDTO>> findAll(){
		List<CurralPiquete> lstCurralPiquete = curralPiqueteService.findAll();
		List<CurralPiqueteDTO> lstCurralPiqueteDTO = lstCurralPiquete.stream().map(x -> new CurralPiqueteDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(lstCurralPiqueteDTO);
	}
	
	@PostMapping
	public ResponseEntity<CurralPiqueteDTO> create(@Valid @RequestBody CurralPiqueteDTO oCurralPiqueteDTO){
		CurralPiquete oCurralPiquete = curralPiqueteService.create(oCurralPiqueteDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idCurralPiquete}").buildAndExpand(oCurralPiquete.getIdCurralPiquete()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<CurralPiqueteDTO> update(@PathVariable Integer id,@Valid @RequestBody CurralPiqueteDTO oCurralPiqueteDTO){
		CurralPiquete oCurralPiquete = curralPiqueteService.update(id, oCurralPiqueteDTO);
		
		return ResponseEntity.ok().body(new CurralPiqueteDTO(oCurralPiquete));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<CurralPiqueteDTO> delete(@PathVariable Integer id){
		curralPiqueteService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
}