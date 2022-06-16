package com.projeto.syscomn.resources;

import java.net.URI;
import java.time.LocalDate;
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

import com.projeto.syscomn.domain.LoteRacao;
import com.projeto.syscomn.domain.dtos.LoteRacaoDTO;
import com.projeto.syscomn.services.LoteRacaoService;

@RestController
@RequestMapping(value = "loteracao")
public class LoteRacaoResource {
	
	@Autowired
	private LoteRacaoService loteRacaoService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<LoteRacaoDTO> findById(@PathVariable Integer id){
		LoteRacao oLoteRacao = loteRacaoService.findById(id);
				
		return ResponseEntity.ok().body(new LoteRacaoDTO(oLoteRacao));
	}
	
	@GetMapping
	public ResponseEntity<List<LoteRacaoDTO>> findAll(){
		List<LoteRacao> lstLoteRacao = loteRacaoService.findAll();
		List<LoteRacaoDTO> lstLoteRacaoDTO = lstLoteRacao.stream().map(x -> new LoteRacaoDTO(x)).collect(Collectors.toList());
				
		return ResponseEntity.ok().body(lstLoteRacaoDTO);
	}
	
	@GetMapping(value = "producaoEstoque")
	public ResponseEntity<List<LoteRacaoDTO>> findAllEstoque(){
		List<LoteRacao> lstLoteRacao = loteRacaoService.findAll();
		List<LoteRacaoDTO> lstLoteRacaoDTO = lstLoteRacao.stream().map(x -> new LoteRacaoDTO(x)).filter(x -> x.getSaldo() > 0).collect(Collectors.toList());
				
		return ResponseEntity.ok().body(lstLoteRacaoDTO);
	}
	
	@GetMapping(value = "producaoData")
	public ResponseEntity<List<Object>> findAllProducaoPorData(){
		List<Object> lstRetorno = loteRacaoService.findAllProducaoPorData();
						
		return ResponseEntity.ok().body(lstRetorno);
	}
	
	@PostMapping
	public ResponseEntity<LoteRacaoDTO> create(@Valid @RequestBody LoteRacaoDTO pLoteRacaoDTO){
		//Seta a quantidade como saldo no momento do cadastro
		pLoteRacaoDTO.setSaldo(pLoteRacaoDTO.getQuantidade());
		
		//Seta a data atual como fabricação
		pLoteRacaoDTO.setDataFabricacao(LocalDate.now());
		
		LoteRacao oLoteRacao = loteRacaoService.create(pLoteRacaoDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idLoteRacao}").buildAndExpand(oLoteRacao.getIdLoteRacao()).toUri();

		return ResponseEntity.created(uri).build();	
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<LoteRacaoDTO> update(@Valid @RequestBody LoteRacaoDTO pLoteRacaoDTO, @PathVariable Integer id){
		LoteRacao oLoteRacao = loteRacaoService.update(pLoteRacaoDTO, id);
				
		return ResponseEntity.ok().body(new LoteRacaoDTO(oLoteRacao));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<LoteRacaoDTO> delete(@PathVariable Integer id){
		loteRacaoService.delete(id);
				
		return ResponseEntity.noContent().build();
	}

}
