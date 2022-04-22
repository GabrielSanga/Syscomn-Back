package com.projeto.syscomn.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.syscomn.domain.LocalArmazenamento;
import com.projeto.syscomn.domain.LoteRacao;
import com.projeto.syscomn.domain.RacaoProduzir;
import com.projeto.syscomn.domain.dtos.LoteRacaoDTO;
import com.projeto.syscomn.repositores.LoteRacaoRepository;
import com.projeto.syscomn.services.exceptions.ObjectNotFoundException;

@Service
public class LoteRacaoService {
	
	@Autowired
	private LoteRacaoRepository loteRacaoRepository;
	
	@Autowired
	private RacaoProduzirService produzirService;
	@Autowired
	private LocalArmazenamentoService armazenamentoService;

	public LoteRacao findById(Integer id) {
		Optional<LoteRacao> oLoteRacao = loteRacaoRepository.findById(id);

		return oLoteRacao.orElseThrow(() -> new ObjectNotFoundException("Lote de Ração não encontrado! ID: " + id));
	}

	public List<LoteRacao> findAll() {
		return loteRacaoRepository.findAll();
	}

	public LoteRacao create(@Valid LoteRacaoDTO pLoteRacaoDTO) {
		return loteRacaoRepository.save(newLoteRacao(pLoteRacaoDTO));
	}

	public LoteRacao update(@Valid LoteRacaoDTO pLoteRacaoDTO, Integer id) {
		pLoteRacaoDTO.setIdLoteRacao(id);
		
		LoteRacao oLoteRacao = findById(pLoteRacaoDTO.getIdLoteRacao());
		
		oLoteRacao = newLoteRacao(pLoteRacaoDTO);
		
		return loteRacaoRepository.save(oLoteRacao);
	}

	public void delete(Integer id) {
		LoteRacao oLoteRacao = findById(id);
		
		loteRacaoRepository.deleteById(oLoteRacao.getIdLoteRacao());	
	}
	
	private LoteRacao newLoteRacao(LoteRacaoDTO pLoteRacaoDTO) {			
		RacaoProduzir oRacaoProduzir = produzirService.findById(pLoteRacaoDTO.getIdRacaoProduzir());
		LocalArmazenamento oLocalArmazenamento = armazenamentoService.findById(pLoteRacaoDTO.getIdLocalArmazenamento());
			
		if (oRacaoProduzir == null || oLocalArmazenamento == null) { return null; }
				
		LoteRacao oLoteRacao = new LoteRacao(pLoteRacaoDTO);
		oLoteRacao.setRacao(oRacaoProduzir);
		oLoteRacao.setLocalArmazenamento(oLocalArmazenamento);
		oLoteRacao.setDataValidade(oLoteRacao.getDataFabricacao().plusDays(oRacaoProduzir.getRacao().getDiasValidade()));
	
		return oLoteRacao;	
	}

}
