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

import com.projeto.syscomn.domain.FormulaRacao;
import com.projeto.syscomn.domain.dtos.FormulaRacaoDTO;
import com.projeto.syscomn.services.FormulaService;

@RestController
@RequestMapping(value = "formula")
public class FormulaResource {
	
	@Autowired
	private FormulaService formulaService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<FormulaRacaoDTO> findById(@PathVariable Integer id){
		FormulaRacao oFormulaRacao = formulaService.findById(id);
				
		return ResponseEntity.ok().body(new FormulaRacaoDTO(oFormulaRacao));
	}
	
	@GetMapping
	public ResponseEntity<List<FormulaRacaoDTO>> findAll(){
		List<FormulaRacao> lstFormulas = formulaService.findAll();
		List<FormulaRacaoDTO> lstFormulasDTO = lstFormulas.stream().map(x -> new FormulaRacaoDTO(x)).collect(Collectors.toList());
				
		return ResponseEntity.ok().body(lstFormulasDTO);
	}
	
	@PostMapping
	public ResponseEntity<FormulaRacaoDTO> create(@Valid @RequestBody FormulaRacaoDTO pFormulaRacaoDTO){
		FormulaRacao oFormulaRacao = formulaService.create(pFormulaRacaoDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idFormulaRacao}").buildAndExpand(oFormulaRacao.getIdFormulaRacao()).toUri();

		return ResponseEntity.created(uri).build();	
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<FormulaRacaoDTO> update(@Valid @RequestBody FormulaRacaoDTO pFormulaRacaoDTO, @PathVariable Integer id){
		FormulaRacao oFormulaRacao = formulaService.update(pFormulaRacaoDTO, id);
				
		return ResponseEntity.ok().body(new FormulaRacaoDTO(oFormulaRacao));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<FormulaRacaoDTO> delete(@PathVariable Integer id){
		formulaService.delete(id);
				
		return ResponseEntity.noContent().build();
	}

}
