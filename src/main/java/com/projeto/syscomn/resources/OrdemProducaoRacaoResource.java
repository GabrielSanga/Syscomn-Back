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

import com.projeto.syscomn.domain.OrdemProducaoRacao;
import com.projeto.syscomn.domain.dtos.OrdemProducaoRacaoDTO;
import com.projeto.syscomn.domain.dtos.RacaoProduzirDTO;
import com.projeto.syscomn.domain.dtos.UsuarioDTO;
import com.projeto.syscomn.security.JWTUtil;
import com.projeto.syscomn.services.OrdemProducaoRacaoService;
import com.projeto.syscomn.services.RacaoProduzirService;

@RestController
@RequestMapping(value = "ordemproducao")
public class OrdemProducaoRacaoResource {
	
	@Autowired
	private OrdemProducaoRacaoService ordemProducaoService;
	
	@Autowired
	private RacaoProduzirService racaoProduzirService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<OrdemProducaoRacaoDTO> findById(@PathVariable Integer id){
		
		OrdemProducaoRacao oOrdemProducaoRacao = ordemProducaoService.findById(id);
		
		return ResponseEntity.ok().body(new OrdemProducaoRacaoDTO(oOrdemProducaoRacao));
	}
	
	@GetMapping
	public ResponseEntity<List<OrdemProducaoRacaoDTO>> findAll(){
		List<OrdemProducaoRacao> lstOrdemProducaoRacao = ordemProducaoService.findAll();
		List<OrdemProducaoRacaoDTO> lstOrdemProducaoRacaoDTO = lstOrdemProducaoRacao.stream().map(x -> new OrdemProducaoRacaoDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(lstOrdemProducaoRacaoDTO);
	}
	 
	@PostMapping
	public ResponseEntity<OrdemProducaoRacaoDTO> create(@Valid @RequestBody OrdemProducaoRacaoDTO pOrdemProducaoRacaoDTO, HttpServletRequest request){
		adicionaUsuárioLogado(pOrdemProducaoRacaoDTO, request);
		
		OrdemProducaoRacao oOrdemProducaoRacao = ordemProducaoService.create(pOrdemProducaoRacaoDTO);
		
		//Cadastrando a produção da Ordem
		for (RacaoProduzirDTO oRacaoProduzirDTO : pOrdemProducaoRacaoDTO.getLstRacaoProduzir()) {
			oRacaoProduzirDTO.setIdRacaoProduzir(0);
			oRacaoProduzirDTO.setIdOrdemProducaoRacao(oOrdemProducaoRacao.getIdOrdemProducaoRacao());
			racaoProduzirService.create(oRacaoProduzirDTO);
		}
			
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idOrdemProducaoRacao}").buildAndExpand(oOrdemProducaoRacao.getIdOrdemProducaoRacao()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<OrdemProducaoRacaoDTO> update(@PathVariable Integer id, @Valid @RequestBody OrdemProducaoRacaoDTO pOrdemProducaoRacaoDTO, HttpServletRequest request){
		adicionaUsuárioLogado(pOrdemProducaoRacaoDTO, request);
		
		OrdemProducaoRacao oOrdemProducaoRacao = ordemProducaoService.update(id, pOrdemProducaoRacaoDTO);
		
		return ResponseEntity.ok().body(new OrdemProducaoRacaoDTO(oOrdemProducaoRacao));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<OrdemProducaoRacaoDTO> delete(@PathVariable Integer id){
		ordemProducaoService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	private void adicionaUsuárioLogado(OrdemProducaoRacaoDTO pOrdemProducaoRacaoDTO, HttpServletRequest request) {
		UsuarioDTO oUsuarioDTO = jwtUtil.getUsuarioLogado(request);
		
		pOrdemProducaoRacaoDTO.setIdFuncionario(oUsuarioDTO.getIdUsuario());
	}

}