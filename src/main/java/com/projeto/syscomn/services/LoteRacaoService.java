package com.projeto.syscomn.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.syscomn.domain.LocalArmazenamento;
import com.projeto.syscomn.domain.LoteRacao;
import com.projeto.syscomn.domain.OrdemProducaoRacao;
import com.projeto.syscomn.domain.RacaoProduzir;
import com.projeto.syscomn.domain.dtos.LoteRacaoDTO;
import com.projeto.syscomn.repositores.LoteRacaoRepository;
import com.projeto.syscomn.repositores.OrdemProducaoRacaoRepository;
import com.projeto.syscomn.services.exceptions.ObjectNotFoundException;

@Service
public class LoteRacaoService {
	
	@Autowired
	private LoteRacaoRepository loteRacaoRepository;
	
	@Autowired
	private RacaoProduzirService produzirService;
	@Autowired
	private LocalArmazenamentoService armazenamentoService;
	@Autowired
	private OrdemProducaoRacaoRepository ordemProducaoRacaoRepository;
	

	public LoteRacao findById(Integer id) {
		Optional<LoteRacao> oLoteRacao = loteRacaoRepository.findById(id);

		return oLoteRacao.orElseThrow(() -> new ObjectNotFoundException("Lote de Ração não encontrado! ID: " + id));
	}

	public List<LoteRacao> findAll() {
		return loteRacaoRepository.findAll();
	}

	public LoteRacao create(@Valid LoteRacaoDTO pLoteRacaoDTO) {
		LoteRacao oLoteRacao =  loteRacaoRepository.save(newLoteRacao(pLoteRacaoDTO));
		
		//Encontra a ordem de produção desse lote
		OrdemProducaoRacao oOrdemProducao = oLoteRacao.getRacaoProduzir().getOrdemProducaoRacao();	
		
		//Encontra a quantidade programada da ordem de produção
		Double qtdProgramadaOP = oOrdemProducao.getLstRacaoProduzir().stream().mapToDouble(RacaoProduzir::getQuantidade).sum();
			
		//Calcula a quantidade produzida da ordem de produção
		Double qtdProduzidaOP = 0.0;
		for (RacaoProduzir oRacaoProduzir : oOrdemProducao.getLstRacaoProduzir()) {
			qtdProduzidaOP += oRacaoProduzir.getLstLoteRacao().stream().mapToDouble(LoteRacao::getQuantidade).sum();
		}
				
		//Caso a Ordem de Produção não tenha mais saldo a produzir então FINALIZA
		if (qtdProduzidaOP >= qtdProgramadaOP) {
			oOrdemProducao.setStatus(3);
			ordemProducaoRacaoRepository.save(oOrdemProducao);
		}
		
		return oLoteRacao;
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
		
		//Volta o status da Ordem de Produção para andamento
		OrdemProducaoRacao oOrdemProducao = oLoteRacao.getRacaoProduzir().getOrdemProducaoRacao();
		oOrdemProducao.setStatus(2);
		ordemProducaoRacaoRepository.save(oOrdemProducao);
	}
	
	private LoteRacao newLoteRacao(LoteRacaoDTO pLoteRacaoDTO) {			
		RacaoProduzir oRacaoProduzir = produzirService.findById(pLoteRacaoDTO.getIdRacaoProduzir());
		LocalArmazenamento oLocalArmazenamento = armazenamentoService.findById(pLoteRacaoDTO.getIdLocalArmazenamento());
			
		if (oRacaoProduzir == null || oLocalArmazenamento == null) { return null; }
						
		LoteRacao oLoteRacao = new LoteRacao(pLoteRacaoDTO);
		oLoteRacao.setRacaoProduzir(oRacaoProduzir);
		oLoteRacao.setLocalArmazenamento(oLocalArmazenamento);
		oLoteRacao.setDataValidade(oLoteRacao.getDataFabricacao().plusDays(oRacaoProduzir.getRacao().getDiasValidade()));

		//Se o status da Ordem de Produção ainda não for ANDAMENTO então altera
		if (oRacaoProduzir.getOrdemProducaoRacao().getStatus() != 2) {
			OrdemProducaoRacao oOrdemProducao = oRacaoProduzir.getOrdemProducaoRacao();
			oOrdemProducao.setStatus(2);
			ordemProducaoRacaoRepository.save(oOrdemProducao);
		}

		return oLoteRacao;	
	}

}
