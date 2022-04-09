package com.projeto.syscomn.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.syscomn.domain.Assinante;
import com.projeto.syscomn.domain.dtos.AssinanteDTO;
import com.projeto.syscomn.services.AssinanteService;

@RestController
@RequestMapping(value = "assinante")
public class AssinanteResource {
	
	@Autowired
	private AssinanteService assinanteService;
	
	@GetMapping
	public ResponseEntity<List<AssinanteDTO>> findAll(){
		List<Assinante> lstAssinantes = assinanteService.findAll();
		List<AssinanteDTO> lstAssinantesDTO = lstAssinantes.stream().map(x -> new AssinanteDTO(x)).collect(Collectors.toList());
				
		return ResponseEntity.ok().body(lstAssinantesDTO);
	}

}
