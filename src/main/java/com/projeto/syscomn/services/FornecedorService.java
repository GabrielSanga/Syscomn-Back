package com.projeto.syscomn.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.syscomn.domain.Fornecedor;
import com.projeto.syscomn.domain.TipoFornecedor;
import com.projeto.syscomn.domain.dtos.FornecedorDTO;
import com.projeto.syscomn.repositores.FornecedorRepository;
import com.projeto.syscomn.services.exceptions.ObjectNotFoundException;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@Autowired
	private TipoFornecedorService tipoFornecedorService;
	
	public Fornecedor findById(Integer id) {
		Optional<Fornecedor> oFornecedor = fornecedorRepository.findById(id);

		return oFornecedor.orElseThrow(() -> new ObjectNotFoundException("Fornecedor não encontrado! ID: " + id));
	}
	
	public List<Fornecedor> findAll() {
		return fornecedorRepository.findAll();
	}

	public Fornecedor create(@Valid FornecedorDTO pFornecedorDTO) {
		return fornecedorRepository.save(newFornecedor(pFornecedorDTO));
	}
	
	public Fornecedor update(@Valid FornecedorDTO pFornecedorDTO, Integer id) {
		pFornecedorDTO.setIdPessoa(id);
		
		Fornecedor oFornecedor = findById(pFornecedorDTO.getIdPessoa());
		
		oFornecedor = newFornecedor(pFornecedorDTO);
		
		return fornecedorRepository.save(oFornecedor);
	}
	
	public void delete(Integer id) {
		Fornecedor oFornecedor = findById(id);
		
		fornecedorRepository.deleteById(oFornecedor.getIdPessoa());
	}
	
	private Fornecedor newFornecedor(FornecedorDTO pFornecedorDTO) {			
		TipoFornecedor oTipoFornecedor = tipoFornecedorService.findById(pFornecedorDTO.getIdTipoFornecedor());
			
		if (oTipoFornecedor == null) { return null; }
				
		Fornecedor oFornecedor = new Fornecedor(pFornecedorDTO);
		oFornecedor.setTipoFornecedor(oTipoFornecedor);
	
		return oFornecedor;	
	}
	
}