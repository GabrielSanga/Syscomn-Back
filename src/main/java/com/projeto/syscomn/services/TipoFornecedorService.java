package com.projeto.syscomn.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.syscomn.domain.TipoFornecedor;
import com.projeto.syscomn.domain.dtos.TipoFornecedorDTO;
import com.projeto.syscomn.repositores.TipoFornecedorRepository;
import com.projeto.syscomn.services.exceptions.ObjectNotFoundException;
 
@Service
public class TipoFornecedorService {
	
	@Autowired
	private TipoFornecedorRepository tipoFornecedorRepository;
	
	public TipoFornecedor findById(Integer id) {
		Optional<TipoFornecedor> oTipoFornecedor = tipoFornecedorRepository.findById(id);
		
		return oTipoFornecedor.orElseThrow(() -> new ObjectNotFoundException("Tipo Fornecedor não encontrado! ID: " + id));
	}
	
	public List<TipoFornecedor> findAll() {
		return tipoFornecedorRepository.findAll();
	}

	public TipoFornecedor create(@Valid TipoFornecedorDTO oTipoFornecedorDTO) {
		oTipoFornecedorDTO.setIdTipoFornecedor(null);

		TipoFornecedor oTipoFornecedor = new TipoFornecedor(oTipoFornecedorDTO);

		return tipoFornecedorRepository.save(oTipoFornecedor);
	}

	public TipoFornecedor update(Integer id, TipoFornecedorDTO oTipoFornecedorDTO) {
		oTipoFornecedorDTO.setIdTipoFornecedor(id);

		TipoFornecedor oTipoFornecedor = findById(id);

		oTipoFornecedor = new TipoFornecedor(oTipoFornecedorDTO);

		return tipoFornecedorRepository.save(oTipoFornecedor);
	}

	public void delete(Integer id) {
		TipoFornecedor oTipoFornecedor = findById(id);

		tipoFornecedorRepository.deleteById(oTipoFornecedor.getIdTipoFornecedor());
	}

}
