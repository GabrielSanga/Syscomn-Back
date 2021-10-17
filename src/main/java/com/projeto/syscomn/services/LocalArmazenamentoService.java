package com.projeto.syscomn.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.syscomn.services.exceptions.ObjectNotFoundException;
import com.projeto.syscomn.domain.LocalArmazenamento;
import com.projeto.syscomn.domain.dtos.LocalArmazenamentoDTO;
import com.projeto.syscomn.repositores.LocalArmazenamentoRepository;

@Service
public class LocalArmazenamentoService {

	@Autowired
	private LocalArmazenamentoRepository localArmazenamentoRepository;

	public LocalArmazenamento findById(Integer id) {
		Optional<LocalArmazenamento> oLocalArmazenamento = localArmazenamentoRepository.findById(id);

		return oLocalArmazenamento
				.orElseThrow(() -> new ObjectNotFoundException("Local Armazenamento não encontrado! ID: " + id));
	}

	public List<LocalArmazenamento> findAll() {
		return localArmazenamentoRepository.findAll();
	}

	public LocalArmazenamento create(@Valid LocalArmazenamentoDTO oLocalArmazenamentoDTO) {
		oLocalArmazenamentoDTO.setIdLocalArmazenamento(null);

		LocalArmazenamento oLocalArmazenamento = new LocalArmazenamento(oLocalArmazenamentoDTO);

		return localArmazenamentoRepository.save(oLocalArmazenamento);
	}

	public LocalArmazenamento update(Integer id, LocalArmazenamentoDTO oLocalArmazenamentoDTO) {
		oLocalArmazenamentoDTO.setIdLocalArmazenamento(id);

		LocalArmazenamento oLocalArmazenamento = findById(id);

		oLocalArmazenamento = new LocalArmazenamento(oLocalArmazenamentoDTO);

		return localArmazenamentoRepository.save(oLocalArmazenamento);
	}

	public void delete(Integer id) {
		LocalArmazenamento oLocalArmazenamento = findById(id);

		localArmazenamentoRepository.deleteById(oLocalArmazenamento.getIdLocalArmazenamento());
	}

}
