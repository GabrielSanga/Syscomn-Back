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

import com.projeto.syscomn.domain.Vacina;
import com.projeto.syscomn.domain.dtos.VacinaDTO;
import com.projeto.syscomn.services.VacinaService;

@RestController
@RequestMapping(value = "vacina")
public class VacinaResource {
	
	@Autowired
	private VacinaService vacinaService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<VacinaDTO> findById(@PathVariable Integer id){
		
		Vacina oVacina = vacinaService.findById(id);
		
		return ResponseEntity.ok().body(new VacinaDTO(oVacina));
	}
	 
	@GetMapping
	public ResponseEntity<List<VacinaDTO>> findAll(){
		List<Vacina> lstVacina = vacinaService.findAll();
		List<VacinaDTO> lstVacinaDTO = lstVacina.stream().map(x -> new VacinaDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(lstVacinaDTO);
	}
	
	@PostMapping
	public ResponseEntity<VacinaDTO> create(@Valid @RequestBody VacinaDTO oVacinaDTO){
		Vacina oVacina = vacinaService.create(oVacinaDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idCurralPiquete}").buildAndExpand(oVacina.getIdVacina()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<VacinaDTO> update(@PathVariable Integer id,@Valid @RequestBody VacinaDTO oVacinaDTO){
		Vacina oVacina = vacinaService.update(id, oVacinaDTO);
		
		return ResponseEntity.ok().body(new VacinaDTO(oVacina));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<VacinaDTO> delete(@PathVariable Integer id){
		vacinaService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
}
