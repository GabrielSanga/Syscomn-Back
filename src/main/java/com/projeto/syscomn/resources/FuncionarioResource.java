package com.projeto.syscomn.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping
	public ResponseEntity<FuncionarioDTO> create(@Valid @RequestBody FuncionarioDTO oFuncionarioDTO){
		Funcionario oFuncionario = funcionarioService.create(oFuncionarioDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idFuncionario}").buildAndExpand(oFuncionario.getIdPessoa()).toUri();

		return ResponseEntity.created(uri).build();	
	}
}
