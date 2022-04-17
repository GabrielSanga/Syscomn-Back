package com.projeto.syscomn.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.projeto.syscomn.domain.Funcionario;
import com.projeto.syscomn.domain.OrdemProducaoRacao;
import com.projeto.syscomn.domain.dtos.OrdemProducaoRacaoDTO;
import com.projeto.syscomn.repositores.OrdemProducaoRacaoRepository;
import com.projeto.syscomn.services.exceptions.ObjectNotFoundException;

@Service
public class OrdemProducaoRacaoService {
	
	@Autowired
	private OrdemProducaoRacaoRepository ordemProducaoRepository;
	
	@Autowired
	private RacaoProduzirService racaoProduzirService;
	
	@Autowired
	private FuncionarioService funcionarioService;

	public OrdemProducaoRacao findById(Integer id) {
		Optional<OrdemProducaoRacao> OrdemProducaoRacao = ordemProducaoRepository.findById(id);

		return OrdemProducaoRacao.orElseThrow(() -> new ObjectNotFoundException("Ordem de Produção não encontrada! ID: " + id));
	}
	
	public List<OrdemProducaoRacao> findByData(LocalDate dataOrdem) {
		Optional<List<OrdemProducaoRacao>> OrdemProducaoRacao = ordemProducaoRepository.findByData(dataOrdem);

		return OrdemProducaoRacao.orElseThrow(() -> new ObjectNotFoundException("Não encontrado Ordem de Produção para a data! Data: " + dataOrdem));
	}

	public List<OrdemProducaoRacao> findAll() {
		return ordemProducaoRepository.findAll(Sort.by(Sort.Direction.DESC, "data"));
	}

	public OrdemProducaoRacao create(@Valid OrdemProducaoRacaoDTO pOrdemProducaoRacaoDTO) {
		pOrdemProducaoRacaoDTO.setIdOrdemProducaoRacao(0);
		
		return ordemProducaoRepository.save(newOrdemProducao(pOrdemProducaoRacaoDTO));
	}

	public OrdemProducaoRacao update(Integer id, @Valid OrdemProducaoRacaoDTO pOrdemProducaoRacaoDTO) {
		pOrdemProducaoRacaoDTO.setIdOrdemProducaoRacao(id);

		OrdemProducaoRacao oOrdemProducaoRacao = findById(id);
		
		//deleta todas as produções do banco
		racaoProduzirService.deleteAll(oOrdemProducaoRacao.getLstRacaoProduzir());
		
		//Adiciona as que vieram da tela
		racaoProduzirService.createAll(pOrdemProducaoRacaoDTO.getLstRacaoProduzir(), oOrdemProducaoRacao.getIdOrdemProducaoRacao());

		oOrdemProducaoRacao = newOrdemProducao(pOrdemProducaoRacaoDTO);
		
		return ordemProducaoRepository.save(oOrdemProducaoRacao);
	}

	public void delete(Integer id) {
		OrdemProducaoRacao oOrdemProducaoRacao = findById(id);

		ordemProducaoRepository.deleteById(oOrdemProducaoRacao.getIdOrdemProducaoRacao());
	}
	
	//Converte o DTO em Entity e seta o usuário logado
	private OrdemProducaoRacao newOrdemProducao(OrdemProducaoRacaoDTO pOrdemProducaoRacaoDTO) {
		//Adicionando o objeto do Funcionário na Ordem de Produção
		Funcionario oFuncionario = funcionarioService.findById(pOrdemProducaoRacaoDTO.getIdFuncionario());
		
		OrdemProducaoRacao oOrdemProducaoRacao = new OrdemProducaoRacao(pOrdemProducaoRacaoDTO);
		
		oOrdemProducaoRacao.setFuncionario(oFuncionario);
		
		return oOrdemProducaoRacao;
	}

}
