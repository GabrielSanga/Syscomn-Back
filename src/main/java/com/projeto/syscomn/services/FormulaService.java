package com.projeto.syscomn.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.syscomn.domain.FormulaRacao;
import com.projeto.syscomn.domain.MateriaPrima;
import com.projeto.syscomn.domain.Racao;
import com.projeto.syscomn.domain.dtos.FormulaRacaoDTO;
import com.projeto.syscomn.repositores.FormulaRepository;
import com.projeto.syscomn.services.exceptions.ObjectNotFoundException;

@Service
public class FormulaService {
	
	@Autowired
	private FormulaRepository formulaRepository;
	
	@Autowired
	private RacaoService racaoService;
	
	@Autowired
	private MateriaPrimaService materiaPrimaService;

	public FormulaRacao findById(Integer id) {
		Optional<FormulaRacao> oFormulaRacao = formulaRepository.findById(id);

		return oFormulaRacao.orElseThrow(() -> new ObjectNotFoundException("Fórmula não encontrada! ID: " + id));
	}

	public List<FormulaRacao> findAll() {
		return formulaRepository.findAll();
	}

	public FormulaRacao create(@Valid FormulaRacaoDTO pFormulaRacaoDTO) {
		return formulaRepository.save(newFormulaRacao(pFormulaRacaoDTO));
	}

	public FormulaRacao update(@Valid FormulaRacaoDTO pFormulaRacaoDTO, Integer id) {
		pFormulaRacaoDTO.setIdFormulaRacao(id);
		
		FormulaRacao oFormulaRacao = findById(pFormulaRacaoDTO.getIdFormulaRacao());
		
		oFormulaRacao = newFormulaRacao(pFormulaRacaoDTO);
		
		return formulaRepository.save(oFormulaRacao);
	}

	public void delete(Integer id) {
		FormulaRacao oFormulaRacao = findById(id);
		
		formulaRepository.deleteById(oFormulaRacao.getIdFormulaRacao());	
	}
	
	private FormulaRacao newFormulaRacao(FormulaRacaoDTO pFormulaRacaoDTO) {			
		Racao oRacao = racaoService.findById(pFormulaRacaoDTO.getIdRacao());
		MateriaPrima oMateriaPrima = materiaPrimaService.findById(pFormulaRacaoDTO.getIdMateriaPrima());
			
		if (oRacao == null || oMateriaPrima == null) { return null; }
				
		FormulaRacao oFormulaRacao = new FormulaRacao(pFormulaRacaoDTO);
		oFormulaRacao.setRacao(oRacao);
		oFormulaRacao.setMateriaPrima(oMateriaPrima);
	
		return oFormulaRacao;	
	}

}