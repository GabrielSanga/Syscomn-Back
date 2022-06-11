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

import com.projeto.syscomn.domain.Vacinacao;
import com.projeto.syscomn.domain.dtos.UsuarioDTO;
import com.projeto.syscomn.domain.dtos.VacinacaoDTO;
import com.projeto.syscomn.security.JWTUtil;
import com.projeto.syscomn.services.VacinacaoService;

@RestController
@RequestMapping(value = "vacinacao")
public class VacinacaoResource {
	
	@Autowired
	private VacinacaoService vacinacaoService;
	
	@Autowired
	private JWTUtil jwtUtil;
	

	@GetMapping(value = "/{id}")
	public ResponseEntity<VacinacaoDTO> findById(@PathVariable Integer id){
		Vacinacao oVacinacao = vacinacaoService.findById(id);
				
		return ResponseEntity.ok().body(new VacinacaoDTO(oVacinacao));
	}
	
	@GetMapping
	public ResponseEntity<List<VacinacaoDTO>> findAll(){
		List<Vacinacao> lstVacinacao = vacinacaoService.findAll();
		List<VacinacaoDTO> lstVacinacaoDTO = lstVacinacao.stream().map(x -> new VacinacaoDTO(x)).collect(Collectors.toList());
				
		return ResponseEntity.ok().body(lstVacinacaoDTO);
	}
	
	@PostMapping
	public ResponseEntity<VacinacaoDTO> create(@Valid @RequestBody VacinacaoDTO pVacinacaoDTO, HttpServletRequest request){
		adicionaUsuárioLogado(pVacinacaoDTO, request);

		Vacinacao oVacinacao = vacinacaoService.create(pVacinacaoDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idVacinacao}").buildAndExpand(oVacinacao.getIdVacinacao()).toUri();

		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<VacinacaoDTO> update(@Valid @RequestBody VacinacaoDTO pVacinacaoDTO, @PathVariable Integer id, HttpServletRequest request){
		adicionaUsuárioLogado(pVacinacaoDTO, request);

		Vacinacao oVacinacao = vacinacaoService.update(pVacinacaoDTO, id);
				
		return ResponseEntity.ok().body(new VacinacaoDTO(oVacinacao));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<VacinacaoDTO> delete(@PathVariable Integer id){
		vacinacaoService.delete(id);
				
		return ResponseEntity.noContent().build();
	}
	
	private void adicionaUsuárioLogado(VacinacaoDTO pVacinacaoDTO, HttpServletRequest request) {
		UsuarioDTO oUsuarioDTO = jwtUtil.getUsuarioLogado(request);
		
		pVacinacaoDTO.setIdPessoa(oUsuarioDTO.getIdUsuario());
	}

}
