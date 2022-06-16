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

import com.projeto.syscomn.domain.Pesagem;
import com.projeto.syscomn.domain.dtos.PesagemDTO;
import com.projeto.syscomn.domain.dtos.UsuarioDTO;
import com.projeto.syscomn.security.JWTUtil;
import com.projeto.syscomn.services.PesagemService;

@PreAuthorize("hasAnyRole('ADMIN', 'FUNCIONARIO')")
@RestController
@RequestMapping(value = "pesagem")
public class PesagemResource {
	
	@Autowired
	private PesagemService pesagemService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PesagemDTO> findById(@PathVariable Integer id){
		Pesagem oPesagem = pesagemService.findById(id);
				
		return ResponseEntity.ok().body(new PesagemDTO(oPesagem));
	}
	
	@GetMapping
	public ResponseEntity<List<PesagemDTO>> findAll(){
		List<Pesagem> lstPesagem = pesagemService.findAll();
		List<PesagemDTO> lstPesagemDTO = lstPesagem.stream().map(x -> new PesagemDTO(x)).collect(Collectors.toList());
				
		return ResponseEntity.ok().body(lstPesagemDTO);
	}
	
	@PostMapping
	public ResponseEntity<PesagemDTO> create(@Valid @RequestBody PesagemDTO pPesagemDTO, HttpServletRequest request){
		adicionaUsuárioLogado(pPesagemDTO, request);

		Pesagem oPesagem = pesagemService.create(pPesagemDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idPesagem}").buildAndExpand(oPesagem.getIdPesagem()).toUri();

		return ResponseEntity.created(uri).build();	
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<PesagemDTO> update(@Valid @RequestBody PesagemDTO pPesagemDTO, @PathVariable Integer id, HttpServletRequest request){
		adicionaUsuárioLogado(pPesagemDTO, request);

		Pesagem oPesagem = pesagemService.update(pPesagemDTO, id);
				
		return ResponseEntity.ok().body(new PesagemDTO(oPesagem));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<PesagemDTO> delete(@PathVariable Integer id){
		pesagemService.delete(id);
				
		return ResponseEntity.noContent().build();
	}
	
	private void adicionaUsuárioLogado(PesagemDTO pPesagemDTO, HttpServletRequest request) {
		UsuarioDTO oUsuarioDTO = jwtUtil.getUsuarioLogado(request);
		
		pPesagemDTO.setIdPessoa(oUsuarioDTO.getIdUsuario());
	}

}
