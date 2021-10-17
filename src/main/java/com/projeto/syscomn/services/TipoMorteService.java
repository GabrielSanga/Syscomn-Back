package com.projeto.syscomn.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.syscomn.services.exceptions.ObjectNotFoundException;

import com.projeto.syscomn.domain.TipoMorte;
import com.projeto.syscomn.domain.dtos.TipoMorteDTO;
import com.projeto.syscomn.repositores.TipoMorteRepository;

@Service
public class TipoMorteService {
	
	@Autowired
	private TipoMorteRepository tipoMorteRepository;
	
	public TipoMorte findById(Integer id) {
		Optional<TipoMorte> oTipoMorte = tipoMorteRepository.findById(id);
		
		return oTipoMorte.orElseThrow(() -> new ObjectNotFoundException("Tipo Morte não encontrada! ID: " + id));
	}
	
	public List<TipoMorte> findAll() {	
		return tipoMorteRepository.findAll();
	}

	public TipoMorte create(@Valid TipoMorteDTO oTipoMorteDTO) {
		oTipoMorteDTO.setIdTipoMorte(null);
		
		TipoMorte oTipoMorte = new TipoMorte(oTipoMorteDTO);
		
		return tipoMorteRepository.save(oTipoMorte);		
	}

	public TipoMorte update(Integer id, TipoMorteDTO oTipoMorteDTO) {
		oTipoMorteDTO.setIdTipoMorte(id);
		
		TipoMorte oTipoMorte = findById(id);
		
		oTipoMorte = new TipoMorte(oTipoMorteDTO);
		
		return tipoMorteRepository.save(oTipoMorte);
	}

	public void delete(Integer id) {
		TipoMorte oTipoMorte = findById(id);
				
		tipoMorteRepository.deleteById(oTipoMorte.getIdTipoMorte());	
	}

}
