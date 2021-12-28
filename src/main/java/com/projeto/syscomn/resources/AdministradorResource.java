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

import com.projeto.syscomn.domain.Administrador;
import com.projeto.syscomn.domain.dtos.AdministradorDTO;
import com.projeto.syscomn.services.AdministradorService;

@RestController
@RequestMapping(value = "administrador")
public class AdministradorResource {
	
	@Autowired
	private AdministradorService administradorService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<AdministradorDTO> findById(@PathVariable Integer id){
		Administrador oAdministrador = administradorService.findById(id);
				
		return ResponseEntity.ok().body(new AdministradorDTO(oAdministrador));
	}
	
	@GetMapping
	public ResponseEntity<List<AdministradorDTO>> findAll(){
		List<Administrador> lstAdministrador = administradorService.findAll();
		List<AdministradorDTO> lstAdministradorDTO = lstAdministrador.stream().map(x -> new AdministradorDTO(x)).collect(Collectors.toList());
				
		return ResponseEntity.ok().body(lstAdministradorDTO);
	}
	
	@PostMapping
	public ResponseEntity<AdministradorDTO> create(@Valid @RequestBody AdministradorDTO oAdministradorDTO){
		Administrador oAdministrador = administradorService.create(oAdministradorDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idAdministrador}").buildAndExpand(oAdministrador.getIdPessoa()).toUri();

		return ResponseEntity.created(uri).build();	
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<AdministradorDTO> update(@Valid @RequestBody AdministradorDTO oAdministradorDTO, @PathVariable Integer id){
		Administrador oAdministrador = administradorService.update(oAdministradorDTO, id);
		
		return ResponseEntity.ok().body(new AdministradorDTO(oAdministrador));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<AdministradorDTO> delete(@PathVariable Integer id){
		administradorService.delete(id);
				
		return ResponseEntity.noContent().build();
	}

}
