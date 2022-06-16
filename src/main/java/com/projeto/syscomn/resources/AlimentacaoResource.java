package com.projeto.syscomn.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
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

import com.projeto.syscomn.domain.Alimentacao;
import com.projeto.syscomn.domain.dtos.AlimentacaoDTO;
import com.projeto.syscomn.domain.dtos.UsuarioDTO;
import com.projeto.syscomn.security.JWTUtil;
import com.projeto.syscomn.services.AlimentacaoService;

@PreAuthorize("hasAnyRole('ADMIN', 'FUNCIONARIO')")
@RestController
@RequestMapping(value = "alimentacao")
public class AlimentacaoResource {
	
	@Autowired
	private AlimentacaoService alimentacaoService;
	
	@Autowired
	private JWTUtil jwtUtil;
	

	@GetMapping(value = "/{id}")
	public ResponseEntity<AlimentacaoDTO> findById(@PathVariable Integer id){
		Alimentacao oAlimentacao = alimentacaoService.findById(id);
				
		return ResponseEntity.ok().body(new AlimentacaoDTO(oAlimentacao));
	}
	
	@GetMapping
	public ResponseEntity<List<AlimentacaoDTO>> findAll(){
		List<Alimentacao> lstAlimentacao = alimentacaoService.findAll();
		List<AlimentacaoDTO> lstAlimentacaoDTO = lstAlimentacao.stream().map(x -> new AlimentacaoDTO(x)).collect(Collectors.toList());
				
		return ResponseEntity.ok().body(lstAlimentacaoDTO);
	}
	
	@PostMapping
	public ResponseEntity<AlimentacaoDTO> create(@Valid @RequestBody AlimentacaoDTO pAlimentacaoDTO, HttpServletRequest request){
		adicionaUsuárioLogado(pAlimentacaoDTO, request);

		Alimentacao oAlimentacao = alimentacaoService.create(pAlimentacaoDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idAlimentacao}").buildAndExpand(oAlimentacao.getIdAlimentacao()).toUri();

		return ResponseEntity.created(uri).build();	
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<AlimentacaoDTO> update(@Valid @RequestBody AlimentacaoDTO pAlimentacaoDTO, @PathVariable Integer id, HttpServletRequest request){
		adicionaUsuárioLogado(pAlimentacaoDTO, request);

		Alimentacao oAlimentacao = alimentacaoService.update(pAlimentacaoDTO, id);
				
		return ResponseEntity.ok().body(new AlimentacaoDTO(oAlimentacao));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<AlimentacaoDTO> delete(@PathVariable Integer id){
		alimentacaoService.delete(id);
				
		return ResponseEntity.noContent().build();
	}
	
	private void adicionaUsuárioLogado(AlimentacaoDTO pAlimentacaoDTO, HttpServletRequest request) {
		UsuarioDTO oUsuarioDTO = jwtUtil.getUsuarioLogado(request);
		
		pAlimentacaoDTO.setIdPessoa(oUsuarioDTO.getIdUsuario());
	}

}
