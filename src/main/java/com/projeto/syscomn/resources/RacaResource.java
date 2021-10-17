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

import com.projeto.syscomn.domain.Raca;
import com.projeto.syscomn.domain.dtos.RacaDTO;
import com.projeto.syscomn.services.RacaService;

@RestController
@RequestMapping(value = "raca")
public class RacaResource {

	@Autowired
	private RacaService racaService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<RacaDTO> findById(@PathVariable Integer id) {
		Raca oRaca = racaService.findById(id);
		return ResponseEntity.ok().body(new RacaDTO(oRaca));
	}

	@GetMapping
	public ResponseEntity<List<RacaDTO>> findAll() {
		List<Raca> lstRaca = racaService.findAll();
		List<RacaDTO> lstRacaDTO = lstRaca.stream().map(x -> new RacaDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(lstRacaDTO);
	}
 
	@PostMapping
	public ResponseEntity<RacaDTO> create(@Valid @RequestBody RacaDTO oRacaDTO) {
		Raca oRaca = racaService.create(oRacaDTO);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idRaca}").buildAndExpand(oRaca.getIdRaca()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<RacaDTO> update(@PathVariable Integer id,@Valid @RequestBody RacaDTO oRacaDTO) {
		Raca oRaca = racaService.update(id, oRacaDTO);
		return ResponseEntity.ok().body(new RacaDTO(oRaca));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<RacaDTO> delete(@PathVariable Integer id) {
		racaService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
