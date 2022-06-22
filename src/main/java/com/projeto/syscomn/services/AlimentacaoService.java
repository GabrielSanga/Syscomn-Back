package com.projeto.syscomn.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.syscomn.domain.Alimentacao;
import com.projeto.syscomn.domain.AnimalChip;
import com.projeto.syscomn.domain.LoteRacao;
import com.projeto.syscomn.domain.Pessoa;
import com.projeto.syscomn.domain.dtos.AlimentacaoDTO;
import com.projeto.syscomn.domain.dtos.AnimalChipDTO;
import com.projeto.syscomn.domain.dtos.LoteDTO;
import com.projeto.syscomn.repositores.AlimentacaoRepository;
import com.projeto.syscomn.repositores.AnimalChipRepository;
import com.projeto.syscomn.repositores.LoteRacaoRepository;
import com.projeto.syscomn.repositores.PessoaRepository;
import com.projeto.syscomn.services.exceptions.ObjectNotFoundException;

@Service
public class AlimentacaoService {
	
	@Autowired
	private AlimentacaoRepository alimentacaoRepository;
	
	@Autowired
	private PessoaRepository pessoaRespoRepository;
	
	@Autowired
	private LoteRacaoRepository loteRacaoRepository;
	
	@Autowired
	private AnimalChipRepository animalChipRepository;
	
	@Autowired
	private LoteService loteService;


	public Alimentacao findById(Integer id) {
		Optional<Alimentacao> oAlimentacao = alimentacaoRepository.findById(id);

		return oAlimentacao.orElseThrow(() -> new ObjectNotFoundException("Alimentação não encontrada! ID: " + id));
	}

	public List<Alimentacao> findAll() {
		return alimentacaoRepository.findAll();
	}

	public Alimentacao create(@Valid AlimentacaoDTO pAlimentacaoDTO) {
		Alimentacao oAlimentacao = null;
		
		if(pAlimentacaoDTO.getTipoAlimentacao().equalsIgnoreCase("A")) {
			AnimalChip oAnimal = animalChipRepository.findById(pAlimentacaoDTO.getIdAnimalChip()).get();
			
			oAnimal.setCustoFinal(oAnimal.getCustoFinal() + pAlimentacaoDTO.getCusto());
			
			animalChipRepository.save(oAnimal);
			
			oAlimentacao =  alimentacaoRepository.save(newAlimentacao(pAlimentacaoDTO));
			
		}else if(pAlimentacaoDTO.getTipoAlimentacao().equalsIgnoreCase("L")){
			//Rateio de Peso		
			LoteDTO oLoteDTO = new LoteDTO(loteService.findById(pAlimentacaoDTO.getIdLote()));
			
			Integer qtdAnimaisLote = oLoteDTO.getLstAnimais().size();
			
			Integer QtdAlimentacaoUnica = pAlimentacaoDTO.getQuantidade() / qtdAnimaisLote;
			Double custoRatiado = pAlimentacaoDTO.getCusto() / qtdAnimaisLote;
				
			for (AnimalChipDTO oAnimalChip : oLoteDTO.getLstAnimais()) {
				pAlimentacaoDTO.setIdAnimalChip(oAnimalChip.getIdAnimalChip());
				pAlimentacaoDTO.setQuantidade(QtdAlimentacaoUnica);
				
				oAlimentacao = alimentacaoRepository.save(newAlimentacao(pAlimentacaoDTO));
				
				AnimalChip oAnimal = animalChipRepository.findById(oAnimalChip.getIdAnimalChip()).get();
				
				oAnimal.setCustoFinal(oAnimal.getCustoFinal() + custoRatiado);
				
				animalChipRepository.save(oAnimal);
			}
		}
		
		return oAlimentacao;
	}

	public Alimentacao update(@Valid AlimentacaoDTO pAlimentacaoDTO, Integer id) {
		pAlimentacaoDTO.setIdAlimentacao(id);
		
		Alimentacao oAlimentacao = findById(pAlimentacaoDTO.getIdAlimentacao());
		
		oAlimentacao = newAlimentacao(pAlimentacaoDTO);
		
		return alimentacaoRepository.save(oAlimentacao);
	}

	public void delete(Integer id) {
		Alimentacao oAlimentacao = findById(id);
		
		alimentacaoRepository.deleteById(oAlimentacao.getIdAlimentacao());		
	}
	
	private Alimentacao newAlimentacao(AlimentacaoDTO pAlimentacaoDTO) {
		AnimalChip oAnimalChip = animalChipRepository.findById(pAlimentacaoDTO.getIdAnimalChip()).get();
		LoteRacao oLoteRacao = loteRacaoRepository.findById(pAlimentacaoDTO.getIdLoteRacao()).get();
		Pessoa oPessoa = pessoaRespoRepository.findById(pAlimentacaoDTO.getIdPessoa()).get();
		
		if (oAnimalChip == null || oPessoa == null || oLoteRacao == null ) { return null; }
				
		Alimentacao oAlimentacao = new Alimentacao(pAlimentacaoDTO);
		oAlimentacao.setAnimalChip(oAnimalChip);
		oAlimentacao.setLoteRacao(oLoteRacao);
		oAlimentacao.setPessoa(oPessoa);
		
		//------ Consumindo a quantidade do lote de produção -----
		Double qtdAtual = oLoteRacao.getSaldo() - pAlimentacaoDTO.getQuantidade();	
		oLoteRacao.setSaldo(qtdAtual);

		loteRacaoRepository.save(oLoteRacao);
		//--------------------------------------------------------
	
		return oAlimentacao;
	}
	

}
