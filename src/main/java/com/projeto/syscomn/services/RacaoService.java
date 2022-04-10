package com.projeto.syscomn.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.syscomn.domain.Racao;
import com.projeto.syscomn.domain.dtos.RacaoDTO;
import com.projeto.syscomn.repositores.RacaoRepository;
import com.projeto.syscomn.services.exceptions.ObjectNotFoundException;

@Service
public class RacaoService {
	
	@Autowired
	private RacaoRepository racaoRepository;

	public Racao findById(Integer id) {
		Optional<Racao> oRacao = racaoRepository.findById(id);

		return oRacao.orElseThrow(() -> new ObjectNotFoundException("Ração não encontrada! ID: " + id));
	}

	public List<Racao> findAll() {
		return racaoRepository.findAll();
	}

	public Racao create(@Valid RacaoDTO pRacaoDTO) {
		pRacaoDTO.setIdRacao(0);
		
		Racao oRacao = new Racao(pRacaoDTO);
		
		return racaoRepository.save(oRacao);
	}

	public Racao update(Integer id, @Valid RacaoDTO pRacaoDTO) {
		pRacaoDTO.setIdRacao(id);

		Racao oRacao = findById(id);

		oRacao = new Racao(pRacaoDTO);

		return racaoRepository.save(oRacao);
	}

	public void delete(Integer id) {
		Racao oRacao = findById(id);

		racaoRepository.deleteById(oRacao.getIdRacao());
	}

}
