package com.projeto.syscomn.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.syscomn.domain.Fornecedor;
import com.projeto.syscomn.domain.Vacina;
import com.projeto.syscomn.domain.dtos.VacinaDTO;
import com.projeto.syscomn.repositores.VacinaRepository;
import com.projeto.syscomn.services.exceptions.ObjectNotFoundException;

@Service
public class VacinaService {
	
	@Autowired
	private VacinaRepository vacinaRepository;
	@Autowired
	private FornecedorService fornecedorService;

	public Vacina findById(Integer id) {
		Optional<Vacina> oVacina = vacinaRepository.findById(id);
		
		return oVacina.orElseThrow(() -> new ObjectNotFoundException("Vacina não encontrada! ID: " + id));
	}

	public List<Vacina> findAll() {	
		return vacinaRepository.findAll();
	}

	public Vacina create(VacinaDTO oVacinaDTO) {
		return vacinaRepository.save(newVacina(oVacinaDTO));
	}

	public Vacina update(Integer id, @Valid VacinaDTO oVacinaDTO) {
		oVacinaDTO.setIdVacina(id);
		Vacina oldVacina = findById(id);
		oldVacina = newVacina(oVacinaDTO);
		return vacinaRepository.save(oldVacina);
	}

	public void delete(Integer id) {
		Vacina oVacina = findById(id);
		
		
		vacinaRepository.deleteById(oVacina.getIdVacina());
	}
	
	private Vacina newVacina(VacinaDTO oVacinaDTO) {
		Fornecedor oFornecedor = fornecedorService.findById(oVacinaDTO.getFornecedor());
		Vacina oVacina = new Vacina(oVacinaDTO);
		
		if(oVacinaDTO.getIdVacina() != null) {
			oVacina.setIdVacina(oVacinaDTO.getIdVacina());
		}
		
		oVacina.setFornecedor(oFornecedor);

		return oVacina;
	}
}

