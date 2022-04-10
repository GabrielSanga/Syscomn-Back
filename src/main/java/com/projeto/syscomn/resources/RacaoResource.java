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

import com.projeto.syscomn.domain.Racao;
import com.projeto.syscomn.domain.dtos.RacaoDTO;
import com.projeto.syscomn.services.RacaoService;

@RestController
@RequestMapping(value = "racao")
public class RacaoResource {
	
	@Autowired
	private RacaoService racaoService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<RacaoDTO> findById(@PathVariable Integer id) {
		Racao oRacao = racaoService.findById(id);
		return ResponseEntity.ok().body(new RacaoDTO(oRacao));
	}

	@GetMapping
	public ResponseEntity<List<RacaoDTO>> findAll() {
		List<Racao> lstRacao = racaoService.findAll();
		List<RacaoDTO> lstRacaoDTO = lstRacao.stream().map(x -> new RacaoDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(lstRacaoDTO);
	}
 
	@PostMapping
	public ResponseEntity<RacaoDTO> create(@Valid @RequestBody RacaoDTO pRacaoDTO) {
		Racao oRacao = racaoService.create(pRacaoDTO);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idRacao}").buildAndExpand(oRacao.getIdRacao()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<RacaoDTO> update(@PathVariable Integer id, @Valid @RequestBody RacaoDTO pRacaoDTO) {
		Racao oRacao = racaoService.update(id, pRacaoDTO);
		
		return ResponseEntity.ok().body(new RacaoDTO(oRacao));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<RacaoDTO> delete(@PathVariable Integer id) {
		racaoService.delete(id);
		
		return ResponseEntity.noContent().build();
	}

}
