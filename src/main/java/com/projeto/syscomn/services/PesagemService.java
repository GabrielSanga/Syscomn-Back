package com.projeto.syscomn.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.syscomn.domain.AnimalChip;
import com.projeto.syscomn.domain.Lote;
import com.projeto.syscomn.domain.Pesagem;
import com.projeto.syscomn.domain.Pessoa;
import com.projeto.syscomn.domain.dtos.AnimalChipDTO;
import com.projeto.syscomn.domain.dtos.PesagemDTO;
import com.projeto.syscomn.repositores.PesagemRepository;
import com.projeto.syscomn.repositores.PessoaRepository;
import com.projeto.syscomn.services.exceptions.ObjectNotFoundException;

@Service
public class PesagemService {
	
	@Autowired
	private PesagemRepository pesagemRepository;
	
	@Autowired
	private PessoaRepository pessoaRespoRepository;
	
	@Autowired
	private AnimalChipService animalChipService;
	
	@Autowired
	private LoteService loteService;

	public Pesagem findById(Integer id) {
		Optional<Pesagem> oPesagem = pesagemRepository.findById(id);

		return oPesagem.orElseThrow(() -> new ObjectNotFoundException("Pesagem não encontrada! ID: " + id));
	}

	public List<Pesagem> findAll() {
		return pesagemRepository.findAll();
	}

	public Pesagem create(@Valid PesagemDTO pPesagemDTO) {
		Pesagem oPesagem = null;
		
		if(pPesagemDTO.getTipoPesagem().equalsIgnoreCase("A")) {
			
			oPesagem =  pesagemRepository.save(newPesagem(pPesagemDTO));
			
		}else if(pPesagemDTO.getTipoPesagem().equalsIgnoreCase("L")){
			//Rateio de Peso		
			Lote oLote = loteService.findById(pPesagemDTO.getIdLote());
			
			Integer qtdAnimaisLote = oLote.getLstAnimais().size();
			
			Double pesoAnimalUnico = pPesagemDTO.getPeso() / qtdAnimaisLote;
				
			for (AnimalChip oAnimalChip : oLote.getLstAnimais()) {
				pPesagemDTO.setIdAnimalChip(oAnimalChip.getIdAnimalChip());
				pPesagemDTO.setPeso(pesoAnimalUnico);
				
				oPesagem = pesagemRepository.save(newPesagem(pPesagemDTO));
			}
		}
		
		return oPesagem;
	}

	public Pesagem update(@Valid PesagemDTO pPesagemDTO, Integer id) {
		pPesagemDTO.setIdPesagem(id);
		
		Pesagem oPesagem = findById(pPesagemDTO.getIdPesagem());
		
		oPesagem = newPesagem(pPesagemDTO);
		
		return pesagemRepository.save(oPesagem);
	}

	public void delete(Integer id) {
		Pesagem oPesagem = findById(id);
		
		pesagemRepository.deleteById(oPesagem.getIdPesagem());	
	}
	
	private Pesagem newPesagem(PesagemDTO pPesagemDTO) {
		AnimalChip oAnimalChip = animalChipService.findById(pPesagemDTO.getIdAnimalChip());
		Pessoa oPessoa = pessoaRespoRepository.findById(pPesagemDTO.getIdPessoa()).get();
		
		//Atualizando os dados do animal
		oAnimalChip.setDtaHoraUltimaPesagem(pPesagemDTO.getData());
		oAnimalChip.setPesoAtual(pPesagemDTO.getPeso());
		
		oAnimalChip = animalChipService.update(new AnimalChipDTO(oAnimalChip), oAnimalChip.getIdAnimalChip());			
		
		if (oAnimalChip == null || oPessoa == null) { return null; }
				
		Pesagem oPesagem = new Pesagem(pPesagemDTO);
		oPesagem.setAnimalChip(oAnimalChip);
		oPesagem.setPessoa(oPessoa);
	
		return oPesagem;
	}

}
