package com.projeto.syscomn.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.syscomn.domain.CurralPiquete;
import com.projeto.syscomn.domain.Lote;
import com.projeto.syscomn.domain.Movimentacao;
import com.projeto.syscomn.domain.dtos.LoteDTO;
import com.projeto.syscomn.repositores.LoteRepository;
import com.projeto.syscomn.repositores.MovimentacaoRepository;
import com.projeto.syscomn.services.exceptions.ObjectNotFoundException;

@Service
public class LoteService {

	@Autowired
	private LoteRepository loteRepository;
	@Autowired
	private CurralPiqueteService curralPiqueteService;
	@Autowired
	private MovimentacaoService movimentacaoService;
	@Autowired
	private FuncionarioService funcionarioService;
	@Autowired
	private MovimentacaoRepository movimentacaoRepository;

	public Lote findById(Integer id) {
		Optional<Lote> oLote = loteRepository.findById(id);
		
		return oLote.orElseThrow(() -> new ObjectNotFoundException("Lote não encontrada! ID: " + id));
	}

	public List<Lote> findAll() {	
		return loteRepository.findAll();
	}

	public Lote create(LoteDTO oLoteDTO) {
	   Lote oLote = loteRepository.save(newLote(oLoteDTO, false));
		
	   Movimentacao oMovimentacao = new Movimentacao(oLote, oLote.getCurralPiquete(), 0);	
	   movimentacaoService.create(oMovimentacao);	
	   
	   return oLote;
	}

	public Lote update(Integer id, @Valid LoteDTO oLoteDTO) {
		oLoteDTO.setIdLote(id);
		
		Lote oldLote = findById(id);
		
		oldLote = newLote(oLoteDTO, true);
		
		return loteRepository.save(oldLote);
	}
	
	public void delete(Integer id) {
		Lote oLote = findById(id);
		
		loteRepository.deleteById(oLote.getIdLote());
	}
	
	private Lote newLote(LoteDTO oLoteDTO, boolean pbIsUpdate) {
		CurralPiquete oCurralPiquete = curralPiqueteService.findById(oLoteDTO.getCurralPiquete());
	
		if (pbIsUpdate) {
			Lote oLoteAntigo = findById(oLoteDTO.getIdLote());
									
			if(oLoteAntigo.getCurralPiquete().getIdCurralPiquete() != oCurralPiquete.getIdCurralPiquete()) {
				
				for (Movimentacao mov : oLoteAntigo.getLstMovimentacao()) {
					mov.setSituacao(1);
					movimentacaoRepository.save(mov);
					
				}									
				
				Movimentacao oMovimentacao = new Movimentacao(new Lote(oLoteDTO), oCurralPiquete, 0);
				
				movimentacaoService.create(oMovimentacao);		
			}	
		}
					
		Lote oLote = new Lote(oLoteDTO);
		
		if(oLoteDTO.getIdLote() != null) {
			oLote.setIdLote(oLoteDTO.getIdLote());
		}
		
		oLote.setCurralPiquete(oCurralPiquete);
		
		return oLote;
	}
}
