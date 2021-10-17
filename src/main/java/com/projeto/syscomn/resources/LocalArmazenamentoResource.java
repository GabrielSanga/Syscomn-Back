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

import com.projeto.syscomn.domain.LocalArmazenamento;
import com.projeto.syscomn.domain.dtos.LocalArmazenamentoDTO;
import com.projeto.syscomn.services.LocalArmazenamentoService;

@RestController
@RequestMapping(value = "localarmazenamento")
public class LocalArmazenamentoResource {

	@Autowired
	private LocalArmazenamentoService localArmazenamentoService;
 
	@GetMapping(value = "/{id}")
	public ResponseEntity<LocalArmazenamentoDTO> findById(@PathVariable Integer id) {
		LocalArmazenamento oLocalArmazenamento = localArmazenamentoService.findById(id);
		
		return ResponseEntity.ok().body(new LocalArmazenamentoDTO(oLocalArmazenamento));
	}

	@GetMapping
	public ResponseEntity<List<LocalArmazenamentoDTO>> findAll() {
		List<LocalArmazenamento> lstLocalArmazenamento = localArmazenamentoService.findAll();
		List<LocalArmazenamentoDTO> lstLocalArmazenamentoDTO = lstLocalArmazenamento.stream().map(x -> new LocalArmazenamentoDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(lstLocalArmazenamentoDTO);
	}

	@PostMapping
	public ResponseEntity<LocalArmazenamentoDTO> create(@Valid @RequestBody LocalArmazenamentoDTO oLocalArmazenamentoDTO) {
		LocalArmazenamento oLocalArmazenamento = localArmazenamentoService.create(oLocalArmazenamentoDTO);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idTipoMorte}").buildAndExpand(oLocalArmazenamento.getIdLocalArmazenamento()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<LocalArmazenamentoDTO> update(@PathVariable Integer id,@Valid @RequestBody LocalArmazenamentoDTO oLocalArmazenamentoDTO) {
		LocalArmazenamento oLocalArmazenamento = localArmazenamentoService.update(id, oLocalArmazenamentoDTO);
		
		return ResponseEntity.ok().body(new LocalArmazenamentoDTO(oLocalArmazenamento));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<LocalArmazenamentoDTO> delete(@PathVariable Integer id) {
		localArmazenamentoService.delete(id);
		
		return ResponseEntity.noContent().build();
	}

}
