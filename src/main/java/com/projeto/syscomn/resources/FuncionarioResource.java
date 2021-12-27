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

import com.projeto.syscomn.domain.Funcionario;
import com.projeto.syscomn.domain.dtos.FuncionarioDTO;
import com.projeto.syscomn.services.FuncionarioService;

@RestController
@RequestMapping(value = "funcionario")
public class FuncionarioResource {
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<FuncionarioDTO> findById(@PathVariable Integer id){
		Funcionario oFuncionario = funcionarioService.findById(id);
				
		return ResponseEntity.ok().body(new FuncionarioDTO(oFuncionario));
	}
	
	@GetMapping
	public ResponseEntity<List<FuncionarioDTO>> findAll(){
		List<Funcionario> lstFuncionarios = funcionarioService.findAll();
		List<FuncionarioDTO> lstFuncionariosDTO = lstFuncionarios.stream().map(x -> new FuncionarioDTO(x)).collect(Collectors.toList());
				
		return ResponseEntity.ok().body(lstFuncionariosDTO);
	}
	
	@PostMapping
	public ResponseEntity<FuncionarioDTO> create(@Valid @RequestBody FuncionarioDTO oFuncionarioDTO){
		Funcionario oFuncionario = funcionarioService.create(oFuncionarioDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idFuncionario}").buildAndExpand(oFuncionario.getIdPessoa()).toUri();

		return ResponseEntity.created(uri).build();	
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<FuncionarioDTO> update(@Valid @RequestBody FuncionarioDTO oFuncionarioDTO, @PathVariable Integer id){
		Funcionario oFuncionario = funcionarioService.update(oFuncionarioDTO, id);
				
		return ResponseEntity.ok().body(new FuncionarioDTO(oFuncionario));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<FuncionarioDTO> delete(@PathVariable Integer id){
		funcionarioService.delete(id);
				
		return ResponseEntity.noContent().build();
	}
}
