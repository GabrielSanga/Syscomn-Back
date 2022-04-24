package com.projeto.syscomn.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.syscomn.domain.CurralPiquete;
import com.projeto.syscomn.domain.Lote;
import com.projeto.syscomn.domain.Movimentacao;
import com.projeto.syscomn.domain.Pessoa;
import com.projeto.syscomn.domain.RegimeEngorda;
import com.projeto.syscomn.domain.dtos.LoteDTO;
import com.projeto.syscomn.repositores.LoteRepository;
import com.projeto.syscomn.repositores.MovimentacaoRepository;
import com.projeto.syscomn.repositores.PessoaRepository;
import com.projeto.syscomn.services.exceptions.ObjectNotFoundException;

@Service
public class LoteService {

	@Autowired
	private LoteRepository loteRepository;
	@Autowired
	private CurralPiqueteService curralPiqueteService;
	@Autowired
	private RegimeEngordaService regimeEngordaService;
	@Autowired
	private MovimentacaoService movimentacaoService;
	@Autowired
	private MovimentacaoRepository movimentacaoRepository;
	@Autowired
	private PessoaRepository pessoaRespoRepository;

	public Lote findById(Integer id) {
		Optional<Lote> oLote = loteRepository.findById(id);

		return oLote.orElseThrow(() -> new ObjectNotFoundException("Lote não encontrada! ID: " + id));
	}

	public List<Lote> findAll() {
		return loteRepository.findAll();
	}

	public Lote create(LoteDTO oLoteDTO, Integer pnIdPessoa) {
		//Adicionando o objeto do Funcionário na Movimentação
	    Pessoa oPessoa = pessoaRespoRepository.findById(pnIdPessoa).get();
		
		Lote oLote = loteRepository.save(newLote(oLoteDTO, false, 0));

		Movimentacao oMovimentacao = new Movimentacao(oLote, oLote.getCurralPiquete(), oPessoa, 0);
		movimentacaoService.create(oMovimentacao);

		return oLote;
	}

	public Lote update(Integer id, @Valid LoteDTO oLoteDTO, Integer pnIdPessoa) {
		oLoteDTO.setIdLote(id);

		Lote oldLote = findById(id);

		oldLote = newLote(oLoteDTO, true, pnIdPessoa);

		return loteRepository.save(oldLote);
	}

	public void delete(Integer id) {
		Lote oLote = findById(id);

		loteRepository.deleteById(oLote.getIdLote());
	}

	private Lote newLote(LoteDTO oLoteDTO, boolean pbIsUpdate, Integer pnIdPessoa) {
		
		CurralPiquete oCurralPiquete = curralPiqueteService.findById(oLoteDTO.getCurralPiquete());
		RegimeEngorda oRegimeEngorda = regimeEngordaService.findById(oLoteDTO.getRegimeEngorda());		

		if (pbIsUpdate) {
			//Adicionando o objeto do Funcionário na Movimentação
		    Pessoa oPessoa = pessoaRespoRepository.findById(pnIdPessoa).get();
		    
			Lote oLoteAntigo = findById(oLoteDTO.getIdLote());

			if (oLoteAntigo.getCurralPiquete().getIdCurralPiquete() != oCurralPiquete.getIdCurralPiquete()) {

				for (Movimentacao mov : oLoteAntigo.getLstMovimentacao()) {
					mov.setSituacao(1);
					movimentacaoRepository.save(mov);

				}

				Movimentacao oMovimentacao = new Movimentacao(new Lote(oLoteDTO), oCurralPiquete, oPessoa, 0);

				movimentacaoService.create(oMovimentacao);
			}
		}

		Lote oLote = new Lote(oLoteDTO);

		if (oLoteDTO.getIdLote() != null) {
			oLote.setIdLote(oLoteDTO.getIdLote());
		}

		oLote.setCurralPiquete(oCurralPiquete);
		oLote.setRegimeEngorda(oRegimeEngorda);

		return oLote;
	}
}
