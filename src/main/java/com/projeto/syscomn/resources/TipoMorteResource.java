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

import com.projeto.syscomn.domain.TipoMorte;
import com.projeto.syscomn.domain.dtos.TipoMorteDTO;
import com.projeto.syscomn.services.TipoMorteService;

@RestController
@RequestMapping(value = "tipomorte")
public class TipoMorteResource {
	
	@Autowired
	private TipoMorteService tipoMorteservice;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TipoMorteDTO> findById(@PathVariable Integer id) {
		TipoMorte oTipoMorte = tipoMorteservice.findById(id);
		return ResponseEntity.ok().body(new TipoMorteDTO(oTipoMorte));
	}
	
	@GetMapping
	public ResponseEntity<List<TipoMorteDTO>> findAll(){
		List<TipoMorte> lstTipoMorte = tipoMorteservice.findAll();
		List<TipoMorteDTO> lstTipoMorteDTO = lstTipoMorte.stream().map(x -> new TipoMorteDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(lstTipoMorteDTO);	
	}
	 
	@PostMapping
	public ResponseEntity<TipoMorteDTO> create(@Valid @RequestBody TipoMorteDTO oTipoMorteDTO){
		TipoMorte oTipoMorte =tipoMorteservice.create(oTipoMorteDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idTipoMorte}").buildAndExpand(oTipoMorte.getIdTipoMorte()).toUri();
		
		return ResponseEntity.created(uri).build();	
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<TipoMorteDTO> update(@PathVariable Integer id,@Valid @RequestBody TipoMorteDTO oTipoMorteDTO){
		TipoMorte oTipoMorte = tipoMorteservice.update(id, oTipoMorteDTO);
		return ResponseEntity.ok().body(new TipoMorteDTO(oTipoMorte));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<TipoMorteDTO> delete(@PathVariable Integer id){
		tipoMorteservice.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
