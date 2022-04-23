package com.projeto.syscomn.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
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

import com.projeto.syscomn.domain.Lote;
import com.projeto.syscomn.domain.dtos.LoteDTO;
import com.projeto.syscomn.domain.dtos.UsuarioDTO;
import com.projeto.syscomn.security.JWTUtil;
import com.projeto.syscomn.services.LoteService;

@RestController
@RequestMapping(value = "lote")
public class LoteResource {
	
	@Autowired
	private LoteService loteService;
	@Autowired
	private JWTUtil jwtUtil;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<LoteDTO> findById(@PathVariable Integer id){
		
		Lote oLote = loteService.findById(id);
		
		return ResponseEntity.ok().body(new LoteDTO(oLote));
	}
	 
	@GetMapping
	public ResponseEntity<List<LoteDTO>> findAll(){
		List<Lote> lstLote = loteService.findAll();
		List<LoteDTO> lstLoteDTO = lstLote.stream().map(x -> new LoteDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(lstLoteDTO);
	}
	
	@PostMapping
	public ResponseEntity<LoteDTO> create(@Valid @RequestBody LoteDTO oLoteDTO, HttpServletRequest request){
		UsuarioDTO oUsuarioDTO = jwtUtil.getUsuarioLogado(request);
		
		Lote oLote = loteService.create(oLoteDTO, oUsuarioDTO.getIdUsuario());
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idLote}").buildAndExpand(oLote.getIdLote()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<LoteDTO> update(@PathVariable Integer id, @Valid @RequestBody LoteDTO oLoteDTO, HttpServletRequest request){
		UsuarioDTO oUsuarioDTO = jwtUtil.getUsuarioLogado(request);
		
		Lote oLote = loteService.update(id, oLoteDTO, oUsuarioDTO.getIdUsuario());
		
		return ResponseEntity.ok().body(new LoteDTO(oLote));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<LoteDTO> delete(@PathVariable Integer id){
		loteService.delete(id);
		
		return ResponseEntity.noContent().build();
	}

}
