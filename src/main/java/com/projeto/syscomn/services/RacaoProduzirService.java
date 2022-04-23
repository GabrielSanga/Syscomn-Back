package com.projeto.syscomn.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.syscomn.domain.OrdemProducaoRacao;
import com.projeto.syscomn.domain.Racao;
import com.projeto.syscomn.domain.RacaoProduzir;
import com.projeto.syscomn.domain.dtos.RacaoProduzirDTO;
import com.projeto.syscomn.repositores.RacaoProduzirRepository;
import com.projeto.syscomn.services.exceptions.ObjectNotFoundException;

@Service
public class RacaoProduzirService {
	
	@Autowired
	private RacaoProduzirRepository produzirRepository;
	
	@Autowired
	private RacaoService racaoService;
	@Autowired
	private OrdemProducaoRacaoService ordemProducaoRacaoService;
	
	public RacaoProduzir findById(Integer id) {
		Optional<RacaoProduzir> oRacaoProduzir = produzirRepository.findById(id);

		return oRacaoProduzir.orElseThrow(() -> new ObjectNotFoundException("Produção não encontrada! ID: " + id));
	}

	public List<RacaoProduzir> findAll() {
		return produzirRepository.findAll();
	}

	public RacaoProduzir create(@Valid RacaoProduzirDTO pRacaoProduzirDTO) {
		return produzirRepository.save(newProducao(pRacaoProduzirDTO));
	}

	public RacaoProduzir update(@Valid RacaoProduzirDTO pRacaoProduzirDTO, Integer id) {
		pRacaoProduzirDTO.setIdRacaoProduzir(id);
		
		RacaoProduzir oRacaoProduzir = findById(pRacaoProduzirDTO.getIdRacaoProduzir());
		
		oRacaoProduzir = newProducao(pRacaoProduzirDTO);
		
		return produzirRepository.save(oRacaoProduzir);	
	}

	public void delete(Integer id) {
		RacaoProduzir oRacaoProduzir = findById(id);
		
		produzirRepository.deleteById(oRacaoProduzir.getIdRacaoProduzir());	
	}
	
	public void deleteAll(List<RacaoProduzir> lstRacaoProduzir) {			
		produzirRepository.deleteAll(lstRacaoProduzir);	
	}
	
	public void createAll(List<RacaoProduzirDTO> lstRacaoProduzirDTO, Integer idOrdemProducaoRacao) {	
		OrdemProducaoRacao oOrdemProducaoRacao = ordemProducaoRacaoService.findById(idOrdemProducaoRacao);

		List<RacaoProduzir> lstRacaoProduzir = lstRacaoProduzirDTO.stream().map(x -> new RacaoProduzir(x, oOrdemProducaoRacao,  racaoService.findById(x.getIdRacao()))).collect(Collectors.toList());	
		
		produzirRepository.saveAll(lstRacaoProduzir);	
	}
	
	private RacaoProduzir newProducao(RacaoProduzirDTO pRacaoProduzirDTO) {		
		Racao oRacao = racaoService.findById(pRacaoProduzirDTO.getIdRacao());
		OrdemProducaoRacao oOrdemProducao = ordemProducaoRacaoService.findById(pRacaoProduzirDTO.getIdOrdemProducaoRacao());
			
		if (oRacao == null || oOrdemProducao == null) { return null; }
				
		RacaoProduzir oRacaoProduzir = new RacaoProduzir(pRacaoProduzirDTO);
		oRacaoProduzir.setRacao(oRacao);
		oRacaoProduzir.setOrdemProducaoRacao(oOrdemProducao);
	
		return oRacaoProduzir;	
	}

}
