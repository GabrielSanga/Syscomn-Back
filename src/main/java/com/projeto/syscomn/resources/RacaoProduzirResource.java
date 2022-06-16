package com.projeto.syscomn.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.projeto.syscomn.domain.RacaoProduzir;
import com.projeto.syscomn.domain.dtos.RacaoProduzirDTO;
import com.projeto.syscomn.services.RacaoProduzirService;

@PreAuthorize("hasAnyRole('ADMIN', 'FUNCIONARIO')")
@RestController
@RequestMapping(value = "racaoproduzir")
public class RacaoProduzirResource {
	
	@Autowired
	private RacaoProduzirService racaoProduzirService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<RacaoProduzirDTO> findById(@PathVariable Integer id){
		RacaoProduzir oRacaoProduzir = racaoProduzirService.findById(id);
				
		return ResponseEntity.ok().body(new RacaoProduzirDTO(oRacaoProduzir));
	}
	
	@GetMapping
	public ResponseEntity<List<RacaoProduzirDTO>> findAll(){
		List<RacaoProduzir> lstRacaoProduzir = racaoProduzirService.findAll();
		List<RacaoProduzirDTO> lstRacaoProduzirDTO = lstRacaoProduzir.stream().map(x -> new RacaoProduzirDTO(x)).collect(Collectors.toList());
				
		return ResponseEntity.ok().body(lstRacaoProduzirDTO);
	}
	
	@PostMapping
	public ResponseEntity<RacaoProduzirDTO> create(@Valid @RequestBody RacaoProduzirDTO pRacaoProduzirDTO){
		RacaoProduzir oRacaoProduzir = racaoProduzirService.create(pRacaoProduzirDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idRacaoProduzir}").buildAndExpand(oRacaoProduzir.getIdRacaoProduzir()).toUri();
 
		return ResponseEntity.created(uri).build();	
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<RacaoProduzirDTO> update(@Valid @RequestBody RacaoProduzirDTO pRacaoProduzirDTO, @PathVariable Integer id){
		RacaoProduzir oRacaoProduzir = racaoProduzirService.update(pRacaoProduzirDTO, id);
				
		return ResponseEntity.ok().body(new RacaoProduzirDTO(oRacaoProduzir));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<RacaoProduzirDTO> delete(@PathVariable Integer id){
		racaoProduzirService.delete(id);
				
		return ResponseEntity.noContent().build();
	}
	
}