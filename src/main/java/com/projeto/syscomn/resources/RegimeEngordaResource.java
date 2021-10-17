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

import com.projeto.syscomn.domain.RegimeEngorda;
import com.projeto.syscomn.domain.dtos.RegimeEngordaDTO;
import com.projeto.syscomn.services.RegimeEngordaService;

@RestController
@RequestMapping(value = "regimeengorda")
public class RegimeEngordaResource {

	@Autowired
	private RegimeEngordaService regimeEngordaService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<RegimeEngordaDTO> findById(@PathVariable Integer id) {

		RegimeEngorda oRegimeEngorda = regimeEngordaService.findById(id);
		return ResponseEntity.ok().body(new RegimeEngordaDTO(oRegimeEngorda));
	}
  
	@GetMapping
	public ResponseEntity<List<RegimeEngordaDTO>> findAll() {
		List<RegimeEngorda> lstRegimeEngorda = regimeEngordaService.findAll();
		List<RegimeEngordaDTO> lstRegimeEngordaDTO = lstRegimeEngorda.stream().map(x -> new RegimeEngordaDTO(x))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(lstRegimeEngordaDTO);
	}

	@PostMapping
	public ResponseEntity<RegimeEngordaDTO> create(@Valid @RequestBody RegimeEngordaDTO oRegimeEngordaDTO) {
		RegimeEngorda oRegimeEngorda = regimeEngordaService.create(oRegimeEngordaDTO);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idRegimeEngorda}")
				.buildAndExpand(oRegimeEngorda.getIdRegimeEngorda()).toUri();

		return ResponseEntity.created(uri).build();
	} 

	@PutMapping(value = "/{id}")
	public ResponseEntity<RegimeEngordaDTO> update(@PathVariable Integer id, @RequestBody RegimeEngordaDTO oRegimeEngordaDTO) {
		RegimeEngorda oRegimeEngorda = regimeEngordaService.update(id, oRegimeEngordaDTO);
		return ResponseEntity.ok().body(new RegimeEngordaDTO(oRegimeEngorda));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<RegimeEngordaDTO> delete(@PathVariable Integer id) {
		regimeEngordaService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
