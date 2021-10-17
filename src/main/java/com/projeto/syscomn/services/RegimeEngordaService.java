package com.projeto.syscomn.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.syscomn.services.exceptions.ObjectNotFoundException;

import com.projeto.syscomn.domain.RegimeEngorda;
import com.projeto.syscomn.domain.dtos.RegimeEngordaDTO;
import com.projeto.syscomn.repositores.RegimeEngordaRepository;

@Service
public class RegimeEngordaService {

	@Autowired
	private RegimeEngordaRepository regimeEngordaRepository;

	public RegimeEngorda findById(Integer id) {
		Optional<RegimeEngorda> oRegimeEngorda = regimeEngordaRepository.findById(id);

		return oRegimeEngorda.orElseThrow(() -> new ObjectNotFoundException("Regime Engorda n�o encontrado! ID: " + id));
	}

	public List<RegimeEngorda> findAll() {
		return regimeEngordaRepository.findAll();
	}

	public RegimeEngorda create(@Valid RegimeEngordaDTO oRegimeEngordaDTO) {
		oRegimeEngordaDTO.setIdRegimeEngorda(null);

		RegimeEngorda oRegimeEngorda = new RegimeEngorda(oRegimeEngordaDTO);

		return regimeEngordaRepository.save(oRegimeEngorda);
	}
 
	public RegimeEngorda update(Integer id, RegimeEngordaDTO oRegimeEngordaDTO) {
		oRegimeEngordaDTO.setIdRegimeEngorda(id);

		RegimeEngorda oRegimeEngorda = findById(id);

		oRegimeEngorda = new RegimeEngorda(oRegimeEngordaDTO);

		return regimeEngordaRepository.save(oRegimeEngorda);
	}

	public void delete(Integer id) {
		RegimeEngorda oRegimeEngorda = findById(id);

		regimeEngordaRepository.deleteById(oRegimeEngorda.getIdRegimeEngorda());
	}

}
