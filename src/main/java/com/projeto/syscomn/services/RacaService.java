package com.projeto.syscomn.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.syscomn.services.exceptions.ObjectNotFoundException;

import com.projeto.syscomn.domain.Raca;
import com.projeto.syscomn.domain.dtos.RacaDTO;
import com.projeto.syscomn.repositores.RacaRepository;

@Service
public class RacaService {
 
	@Autowired
	private RacaRepository racaRepository;

	public Raca findById(Integer id) {
		Optional<Raca> oRaca = racaRepository.findById(id);

		return oRaca.orElseThrow(() -> new ObjectNotFoundException("Raça não encontrada! ID: " + id));
	}

	public List<Raca> findAll() {
		return racaRepository.findAll();
	}

	public Raca create(@Valid RacaDTO oRacaDTO) {
		oRacaDTO.setIdRaca(null);

		Raca oRaca = new Raca(oRacaDTO);

		return racaRepository.save(oRaca);
	}

	public Raca update(Integer id, RacaDTO oRacaDTO) {
		oRacaDTO.setIdRaca(id);

		Raca oRaca = findById(id);

		oRaca = new Raca(oRacaDTO);

		return racaRepository.save(oRaca);
	}

	public void delete(Integer id) {
		Raca oRaca = findById(id);

		racaRepository.deleteById(oRaca.getIdRaca());
	}

}