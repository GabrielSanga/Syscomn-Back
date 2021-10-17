package com.projeto.syscomn.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.syscomn.services.exceptions.DataIntegrityViolationException;
import com.projeto.syscomn.domain.MateriaPrima;
import com.projeto.syscomn.domain.dtos.MateriaPrimaDTO;
import com.projeto.syscomn.repositores.MateriaPrimaRepository;
import com.projeto.syscomn.services.exceptions.ObjectNotFoundException;

@Service
public class MateriaPrimaService {
	
	@Autowired
	private MateriaPrimaRepository materiaPrimaRepository;

	public MateriaPrima findById(Integer id) {
		Optional<MateriaPrima> oMateriaPrima = materiaPrimaRepository.findById(id);
		
		return oMateriaPrima.orElseThrow(() -> new ObjectNotFoundException("Matéria Prima não encontrada! ID: " + id));
	}

	public List<MateriaPrima> findAll() {	
		return materiaPrimaRepository.findAll();
	}

	public MateriaPrima create(@Valid MateriaPrimaDTO oMateriaPrimaDTO) {
		oMateriaPrimaDTO.setIdMateriaPrima(null);
		
		MateriaPrima oMateriaPrima = new MateriaPrima(oMateriaPrimaDTO);
		
		return materiaPrimaRepository.save(oMateriaPrima);
	}

	public MateriaPrima update(Integer id, MateriaPrimaDTO oMateriaPrimaDTO) {
		oMateriaPrimaDTO.setIdMateriaPrima(id);
		
		MateriaPrima oMateriaPrima = findById(id);
		
		oMateriaPrima = new MateriaPrima(oMateriaPrimaDTO);
		
		return materiaPrimaRepository.save(oMateriaPrima);
	}

	public void delete(Integer id) {
		MateriaPrima oMateriaPrima = findById(id);
		
		if(oMateriaPrima.getLstFormulaRacao().size() > 0) {
			throw new DataIntegrityViolationException("Matéria Prima Está Vinculada a uma Fórmula, Não é Possível Exclui-la!");
		}
		
		materiaPrimaRepository.deleteById(oMateriaPrima.getIdMateriaPrima());
	}

}
