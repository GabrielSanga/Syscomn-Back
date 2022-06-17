package com.projeto.syscomn.resources;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.collections.map.HashedMap;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.projeto.syscomn.domain.MovimentacaoReport;
import com.projeto.syscomn.domain.dtos.LoteDTO;
import com.projeto.syscomn.domain.dtos.UsuarioDTO;
import com.projeto.syscomn.security.JWTUtil;
import com.projeto.syscomn.services.LoteService;
import com.projeto.syscomn.services.reportService;

@RestController
@RequestMapping(value = "lote")
public class LoteResource {
	
	@Autowired
	private LoteService loteService;
	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private reportService reportService;
	
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
	
	@GetMapping(value="/relatorios", produces = "application/text")
	public ResponseEntity<String> dowloadRelatorio(HttpServletRequest request) throws Exception{
		byte[] pdf = reportService.gerarRelatorio("relMovimentacao", new HashMap() , 
				request.getServletContext());
		
		String base64Pdf = "data:application/pdf;base64," + Base64.encodeBase64String(pdf);
		
		return new ResponseEntity<String>(base64Pdf, HttpStatus.OK);
	}
	
	@PostMapping(value="/relatorios", produces = "application/text")
	public ResponseEntity<String> dowloadRelatorioParam(HttpServletRequest request, @RequestBody MovimentacaoReport movimentacaoReport) throws Exception{
		
		Map<String,Object> params = new HashMap<String, Object>();
		
		params.put("DATA_INICIO", movimentacaoReport.getDataInicio());
		params.put("DATA_FIM", movimentacaoReport.getDataFim());
		
		byte[] pdf = reportService.gerarRelatorio("relMovimentacao-param", params, request.getServletContext());
		
		String base64Pdf = "data:application/pdf;base64," + Base64.encodeBase64String(pdf);
		
		return new ResponseEntity<String>(base64Pdf, HttpStatus.OK);
	}

}
