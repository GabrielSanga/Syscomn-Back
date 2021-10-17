package com.projeto.syscomn.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.syscomn.domain.Propriedade;
import com.projeto.syscomn.domain.dtos.PropriedadeDTO;
import com.projeto.syscomn.repositores.PropriedadeRepository;
import com.projeto.syscomn.services.exceptions.ObjectNotFoundException;

@Service
public class PropriedadeService {
	
	@Autowired
	private PropriedadeRepository propriedadeRepository;

	public Propriedade findById(Integer id) {
		Optional<Propriedade> oPropriedade = propriedadeRepository.findById(id);
		return oPropriedade.orElseThrow(() -> new ObjectNotFoundException("Propriedade não encontrada! ID: " + id));
	}

	public List<Propriedade> findAll() {	
		return propriedadeRepository.findAll();
	}

	public Propriedade create(@Valid PropriedadeDTO oPropriedadeDTO) {
		oPropriedadeDTO.setIdPropriedade(null);
		
		Propriedade oPropriedade = new Propriedade(oPropriedadeDTO);
		
		return propriedadeRepository.save(oPropriedade);
	}

	public Propriedade update(Integer id,@Valid PropriedadeDTO oPropriedadeDTO) {
		oPropriedadeDTO.setIdPropriedade(id);
		
		Propriedade oPropriedade  = findById(id);
		
		oPropriedade = new Propriedade(oPropriedadeDTO);
		
		return propriedadeRepository.save(oPropriedade);
	}

	public void delete(Integer id) {
		Propriedade oPropriedade = findById(id);
		propriedadeRepository.deleteById(oPropriedade.getIdPropriedade());
	}
	
}
