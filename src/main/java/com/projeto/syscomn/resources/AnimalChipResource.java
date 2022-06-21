package com.projeto.syscomn.resources;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

import com.projeto.syscomn.domain.AnimalChip;
import com.projeto.syscomn.domain.AnimalReport;
import com.projeto.syscomn.domain.MovimentacaoReport;
import com.projeto.syscomn.domain.dtos.AnimalChipDTO;
import com.projeto.syscomn.services.AnimalChipService;
import com.projeto.syscomn.services.reportService;

@RestController
@RequestMapping(value = "animalchip")
public class AnimalChipResource {
	
	@Autowired
	private AnimalChipService animalChipService;
	@Autowired
	private reportService reportService;
	

	@GetMapping(value = "/{id}")
	public ResponseEntity<AnimalChipDTO> findById(@PathVariable Integer id){
		AnimalChip oAnimalChip = animalChipService.findById(id);
				
		return ResponseEntity.ok().body(new AnimalChipDTO(oAnimalChip));
	}
	
	@GetMapping
	public ResponseEntity<List<AnimalChipDTO>> findAll(){
		List<AnimalChip> lstAnimalChip = animalChipService.findAll();
		List<AnimalChipDTO> lstAnimalChipDTO = lstAnimalChip.stream().map(x -> new AnimalChipDTO(x)).collect(Collectors.toList());
				
		return ResponseEntity.ok().body(lstAnimalChipDTO);
	}
	
	@GetMapping(value = "mortos")
	public ResponseEntity<List<Object>> findAllQtdMortosMes(){
		List<Object> lstQtdMorteMes = animalChipService.findAllMortos();
				
		return ResponseEntity.ok().body(lstQtdMorteMes);
	}
	
	@PostMapping
	public ResponseEntity<AnimalChipDTO> create(@Valid @RequestBody AnimalChipDTO pAnimalChipDTO){
		AnimalChip oAnimalChip = animalChipService.create(pAnimalChipDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idRacaoProduzir}").buildAndExpand(oAnimalChip.getIdAnimalChip()).toUri();
 
		return ResponseEntity.created(uri).build();	
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<AnimalChipDTO> update(@Valid @RequestBody AnimalChipDTO pAnimalChipDTO, @PathVariable Integer id){
		AnimalChip oAnimalChip = animalChipService.update(pAnimalChipDTO, id);
				
		return ResponseEntity.ok().body(new AnimalChipDTO(oAnimalChip));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<AnimalChipDTO> delete(@PathVariable Integer id){
		animalChipService.delete(id);
				
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping(value="/relatorios", produces = "application/text")
	public ResponseEntity<String> dowloadRelatorioParam(HttpServletRequest request, @RequestBody AnimalReport animalReport) throws Exception{
		
		Map<String,Object> params = new HashMap<String, Object>();
		
		params.put("RACA", animalReport.getRaca());
		
		
		byte[] pdf = reportService.gerarRelatorio("relAnimal", params, request.getServletContext());
		
		String base64Pdf = "data:application/pdf;base64," + Base64.encodeBase64String(pdf);
		
		return new ResponseEntity<String>(base64Pdf, HttpStatus.OK);
	}

}
