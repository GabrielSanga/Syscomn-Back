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

import com.projeto.syscomn.domain.TipoFornecedor;
import com.projeto.syscomn.domain.dtos.TipoFornecedorDTO;
import com.projeto.syscomn.services.TipoFornecedorService;

@RestController
@RequestMapping(value = "tipofornecedor")
public class TipoFornecedorResource {
	
	@Autowired
	private TipoFornecedorService tipoForncedorService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TipoFornecedorDTO> findById(@PathVariable Integer id) {
		
		TipoFornecedor oTipoFornecedor = tipoForncedorService.findById(id);
		return ResponseEntity.ok().body(new TipoFornecedorDTO(oTipoFornecedor));		
	}
	 
	@GetMapping
	public ResponseEntity<List<TipoFornecedorDTO>> findAll() {
		List<TipoFornecedor> lstTipoFornecedor = tipoForncedorService.findAll();
		List<TipoFornecedorDTO> lstTipoFornecedorDTO = lstTipoFornecedor.stream().map(x -> new TipoFornecedorDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(lstTipoFornecedorDTO);
	}

	@PostMapping
	public ResponseEntity<TipoFornecedorDTO> create(@Valid @RequestBody TipoFornecedorDTO oTipoFornecedorDTO) {
		TipoFornecedor oTipoForncedor = tipoForncedorService.create(oTipoFornecedorDTO);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idTipoFornecedor}").buildAndExpand(oTipoForncedor.getIdTipoFornecedor()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<TipoFornecedorDTO> update(@PathVariable Integer id,@Valid @RequestBody TipoFornecedorDTO oTipoFornecedorDTO) {
		TipoFornecedor oTipoFornecedor = tipoForncedorService.update(id, oTipoFornecedorDTO);
		return ResponseEntity.ok().body(new TipoFornecedorDTO(oTipoFornecedor));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<TipoFornecedorDTO> delete(@PathVariable Integer id) {
		tipoForncedorService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
