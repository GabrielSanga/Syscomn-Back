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

import com.projeto.syscomn.domain.Fornecedor;
import com.projeto.syscomn.domain.dtos.FornecedorDTO;
import com.projeto.syscomn.domain.dtos.FuncionarioDTO;
import com.projeto.syscomn.services.FornecedorService;

@RestController
@RequestMapping(value = "fornecedor")
public class FornecedorResource {

	@Autowired
	private FornecedorService fornecedorService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<FornecedorDTO> findById(@PathVariable Integer id){
		Fornecedor oFornecedor = fornecedorService.findById(id);
				
		return ResponseEntity.ok().body(new FornecedorDTO(oFornecedor));
	}
	
	@GetMapping
	public ResponseEntity<List<FornecedorDTO>> findAll(){
		List<Fornecedor> lstFornecedores = fornecedorService.findAll();
		List<FornecedorDTO> lstFornecedoresDTO = lstFornecedores.stream().map(x -> new FornecedorDTO(x)).collect(Collectors.toList());
				
		return ResponseEntity.ok().body(lstFornecedoresDTO);
	}
	
	@PostMapping
	public ResponseEntity<FornecedorDTO> create(@Valid @RequestBody FornecedorDTO oFornecedorDTO){
		Fornecedor oFornecedor = fornecedorService.create(oFornecedorDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idFornecedor}").buildAndExpand(oFornecedor.getIdPessoa()).toUri();

		return ResponseEntity.created(uri).build();	
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<FornecedorDTO> update(@Valid @RequestBody FornecedorDTO oFornecedorDTO, @PathVariable Integer id){
		Fornecedor oFornecedor = fornecedorService.update(oFornecedorDTO, id);
				
		return ResponseEntity.ok().body(new FornecedorDTO(oFornecedor));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<FuncionarioDTO> delete(@PathVariable Integer id){
		fornecedorService.delete(id);
				
		return ResponseEntity.noContent().build();
	}
	
}