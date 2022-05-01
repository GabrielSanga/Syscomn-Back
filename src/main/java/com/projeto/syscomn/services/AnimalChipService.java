package com.projeto.syscomn.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.syscomn.domain.AnimalChip;
import com.projeto.syscomn.domain.EstadoAnimal;
import com.projeto.syscomn.domain.Lote;
import com.projeto.syscomn.domain.Raca;
import com.projeto.syscomn.domain.SexoAnimal;
import com.projeto.syscomn.domain.TipoMorte;
import com.projeto.syscomn.domain.dtos.AnimalChipDTO;
import com.projeto.syscomn.repositores.AnimalChipRepository;
import com.projeto.syscomn.services.exceptions.ObjectNotFoundException;

@Service
public class AnimalChipService {
	
	@Autowired
	private AnimalChipRepository animalChipRepository;
	
	@Autowired
	private LoteService loteService;
	@Autowired
	private SexoAnimalService sexoAnimalService;
	@Autowired
	private RacaService racaService;
	@Autowired
	private EstadoAnimalService estadoAnimalService;
	@Autowired
	private TipoMorteService tipoMorteService;

	public AnimalChip findById(Integer id) {
		Optional<AnimalChip> oAnimalChip = animalChipRepository.findById(id);

		return oAnimalChip.orElseThrow(() -> new ObjectNotFoundException("Animal não encontrado! ID: " + id));
	}

	public List<AnimalChip> findAll() {
		return animalChipRepository.findAll();
	}
	
	public List<Object> findAllMortos() {
		return animalChipRepository.findAllMorteMes();
	}

	public AnimalChip create(@Valid AnimalChipDTO pAnimalChipDTO) {
		return animalChipRepository.save(newAnimal(pAnimalChipDTO, false));
	}

	public AnimalChip update(@Valid AnimalChipDTO pAnimalChipDTO, Integer id) {
		pAnimalChipDTO.setIdAnimalChip(id);
		
		AnimalChip oAnimalChip = findById(pAnimalChipDTO.getIdAnimalChip());
		
		oAnimalChip = newAnimal(pAnimalChipDTO, true);
		
		return animalChipRepository.save(oAnimalChip);	
	}

	public void delete(Integer id) {
		AnimalChip oAnimalChip = findById(id);
		
		animalChipRepository.deleteById(oAnimalChip.getIdAnimalChip());	
	}
	
	private AnimalChip newAnimal(AnimalChipDTO pAnimalChipDTO, Boolean pbIsUpdate) {
		Lote oLote = loteService.findById(pAnimalChipDTO.getIdLote());
		SexoAnimal oSexoAnimal = sexoAnimalService.findById(pAnimalChipDTO.getIdSexoAnimal());
		Raca oRaca = racaService.findById(pAnimalChipDTO.getIdRaca());
		EstadoAnimal oEstadoAnimal = estadoAnimalService.findById(pAnimalChipDTO.getIdEstadoAnimal());
		
		TipoMorte oTipoMorte = null;
				
		if(pbIsUpdate && pAnimalChipDTO.getIdTipoMorte() != null) {	
			oTipoMorte = tipoMorteService.findById(pAnimalChipDTO.getIdTipoMorte());
		}
			
		if (oLote == null || oSexoAnimal == null || oRaca == null || oEstadoAnimal == null) { return null; }
				
		AnimalChip oAnimalChip = new AnimalChip(pAnimalChipDTO);
		oAnimalChip.setLote(oLote);
		oAnimalChip.setSexoAnimal(oSexoAnimal);
		oAnimalChip.setRaca(oRaca);
		oAnimalChip.setEstadoAnimal(oEstadoAnimal);
		
		if(pbIsUpdate) {
			oAnimalChip.setTipoMorte(oTipoMorte);
			
			AnimalChip oAnimalChipOld = findById(oAnimalChip.getIdAnimalChip());
		
			if(oAnimalChipOld.getStatus() != 1 && oAnimalChip.getStatus() == 1) {
				oAnimalChip.setDtaSaida(LocalDate.now());
			}else if(oAnimalChipOld.getStatus() == 1 && oAnimalChip.getStatus() != 1) {
				oAnimalChip.setDtaSaida(null);
				oAnimalChip.setMotivoSaida("");
			}
			
			if(oAnimalChipOld.getTipoMorte() == null && oTipoMorte != null) {
				oAnimalChip.setDtaSaida(LocalDate.now());
			}else if(oAnimalChipOld.getTipoMorte() != null &&  oTipoMorte == null) {
				oAnimalChip.setDtaSaida(null);
			}
			
		}
		
		return oAnimalChip;	
	}

}
