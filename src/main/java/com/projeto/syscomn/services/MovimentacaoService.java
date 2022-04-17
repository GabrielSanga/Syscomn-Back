package com.projeto.syscomn.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.syscomn.domain.Movimentacao;
import com.projeto.syscomn.domain.dtos.MovimentacaoDTO;
import com.projeto.syscomn.repositores.MovimentacaoRepository;
import com.projeto.syscomn.services.exceptions.ObjectNotFoundException;

@Service
public class MovimentacaoService {
	
	@Autowired
	private MovimentacaoRepository movimentacaoRepository;
	
	public Movimentacao findById(Integer id) {
		Optional<Movimentacao> oMovimentacao = movimentacaoRepository.findById(id);
		
		return oMovimentacao.orElseThrow(() -> new ObjectNotFoundException("Movimentacao não encontrado! ID: " + id));
	}

	public List<Movimentacao> findAll() {
		return movimentacaoRepository.findAll();
	}

	public Integer create(Movimentacao pMovimentacao) {
		pMovimentacao.setIdMovimentacao(null);
				
		return movimentacaoRepository.save(pMovimentacao).getIdMovimentacao();
	}

	public Movimentacao update(MovimentacaoDTO oMovimentacaoDTO, Integer id) {
		oMovimentacaoDTO.setIdMovimentacao(id);
		
		Movimentacao oMovimentacao = this.findById(id);
		
		oMovimentacao = new Movimentacao(oMovimentacaoDTO);
		
		return movimentacaoRepository.save(oMovimentacao);
	}

	public void delete(Integer id) {
		Movimentacao oMovimentacao = this.findById(id);
		
		movimentacaoRepository.deleteById(oMovimentacao.getIdMovimentacao());
	}

}
