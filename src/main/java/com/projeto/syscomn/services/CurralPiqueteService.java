package com.projeto.syscomn.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.syscomn.domain.CurralPiquete;
import com.projeto.syscomn.domain.Propriedade;
import com.projeto.syscomn.domain.dtos.CurralPiqueteDTO;
import com.projeto.syscomn.repositores.CurralPiqueteRepository;
import com.projeto.syscomn.services.exceptions.DataIntegrityViolationException;
import com.projeto.syscomn.services.exceptions.ObjectNotFoundException;

@Service
public class CurralPiqueteService {
	
	@Autowired
	private CurralPiqueteRepository curralPiqueteRepository;
	@Autowired
	private PropriedadeService propriedadeService;

	public CurralPiquete findById(Integer id) {
		Optional<CurralPiquete> oCurralPiquete = curralPiqueteRepository.findById(id);
		
		return oCurralPiquete.orElseThrow(() -> new ObjectNotFoundException("Curral ou Piquete não encontrada! ID: " + id));
	}

	public List<CurralPiquete> findAll() {	
		return curralPiqueteRepository.findAll();
	}

	public CurralPiquete create(CurralPiqueteDTO oCurralPiqueteDTO) {
		return curralPiqueteRepository.save(newCurralPiquete(oCurralPiqueteDTO));
	}

	public CurralPiquete update(Integer id, @Valid CurralPiqueteDTO oCurralPiqueteDTO) {
		oCurralPiqueteDTO.setIdCurralPiquete(id);
		CurralPiquete oldCurralPiquete = findById(id);
		oldCurralPiquete = newCurralPiquete(oCurralPiqueteDTO);
		return curralPiqueteRepository.save(oldCurralPiquete);
	}

	public void delete(Integer id) {
		CurralPiquete oCurralPiquete = findById(id);
		
		if(oCurralPiquete.getLstLote().size() > 0) {
			throw new DataIntegrityViolationException("Curral ou Piquete Está Vinculada a um Lote, Não é Possível Exclui-la!");
		}
		
		curralPiqueteRepository.deleteById(oCurralPiquete.getIdCurralPiquete());
	}
	
	private CurralPiquete newCurralPiquete(CurralPiqueteDTO oCurralPiqueteDTO) {
		Propriedade oPropriedade = propriedadeService.findById(oCurralPiqueteDTO.getPropriedade());
		CurralPiquete oCurralPiquete = new CurralPiquete(oCurralPiqueteDTO);
		
		if(oCurralPiqueteDTO.getIdCurralPiquete() != null) {
			oCurralPiquete.setIdCurralPiquete(oCurralPiqueteDTO.getIdCurralPiquete());
		}
		
		oCurralPiquete.setPropriedade(oPropriedade);

		return oCurralPiquete;
	}

}