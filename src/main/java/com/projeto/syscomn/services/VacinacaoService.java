package com.projeto.syscomn.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.syscomn.domain.AnimalChip;
import com.projeto.syscomn.domain.Pessoa;
import com.projeto.syscomn.domain.Vacina;
import com.projeto.syscomn.domain.Vacinacao;
import com.projeto.syscomn.domain.dtos.VacinacaoDTO;
import com.projeto.syscomn.repositores.AnimalChipRepository;
import com.projeto.syscomn.repositores.PessoaRepository;
import com.projeto.syscomn.repositores.VacinaRepository;
import com.projeto.syscomn.repositores.VacinacaoRepository;
import com.projeto.syscomn.services.exceptions.ObjectNotFoundException;

@Service
public class VacinacaoService {
	
	@Autowired
	private VacinacaoRepository vacinacaoRepository;
	
	@Autowired
	private PessoaRepository pessoaRespoRepository;
	
	@Autowired
	private VacinaRepository vacinaRepository;
	
	@Autowired
	private AnimalChipRepository animalChipRepository;

	public Vacinacao findById(Integer id) {
		Optional<Vacinacao> oVacinacao = vacinacaoRepository.findById(id);

		return oVacinacao.orElseThrow(() -> new ObjectNotFoundException("Vacinação não encontrada! ID: " + id));
	}

	public List<Vacinacao> findAll() {
		return vacinacaoRepository.findAll();
	}

	public Vacinacao create(@Valid VacinacaoDTO pVacinacaoDTO) {
		return vacinacaoRepository.save(newVacinacao(pVacinacaoDTO));
	}

	public Vacinacao update(@Valid VacinacaoDTO pVacinacaoDTO, Integer id) {
		pVacinacaoDTO.setIdVacinacao(id);
		
		Vacinacao oVacinacao = findById(pVacinacaoDTO.getIdVacinacao());
		
		oVacinacao = newVacinacao(pVacinacaoDTO);
		
		return vacinacaoRepository.save(oVacinacao);
	}

	public void delete(Integer id) {
		Vacinacao oVacinacao = findById(id);
		
		vacinacaoRepository.deleteById(oVacinacao.getIdVacinacao());		
	}
	
	private Vacinacao newVacinacao(VacinacaoDTO pVacinacaoDTO) {
		AnimalChip oAnimalChip = animalChipRepository.findById(pVacinacaoDTO.getIdAnimalChip()).get();
		Vacina oVacina = vacinaRepository.findById(pVacinacaoDTO.getIdVacina()).get();
		Pessoa oPessoa = pessoaRespoRepository.findById(pVacinacaoDTO.getIdPessoa()).get();
		
		if (oAnimalChip == null || oPessoa == null || oVacina == null) { return null; }
				
		Vacinacao oVacinacao = new Vacinacao(pVacinacaoDTO);
		oVacinacao.setAnimalChip(oAnimalChip);
		oVacinacao.setVacina(oVacina);
		oVacinacao.setPessoa(oPessoa);

		return oVacinacao;
	}
	
}
