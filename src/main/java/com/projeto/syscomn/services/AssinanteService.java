package com.projeto.syscomn.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.syscomn.domain.Assinante;
import com.projeto.syscomn.domain.dtos.AssinanteDTO;
import com.projeto.syscomn.repositores.AssinanteRepository;
import com.projeto.syscomn.services.exceptions.ObjectNotFoundException;

@Service
public class AssinanteService {
	
	@Autowired
	private AssinanteRepository assinanteRepository;
	
	public Assinante findById(Integer id) {
		Optional<Assinante> oAssinante = assinanteRepository.findById(id);
		
		return oAssinante.orElseThrow(() -> new ObjectNotFoundException("Assinante não encontrado! ID: " + id));
	}

	public List<Assinante> findAll() {
		return assinanteRepository.findAll();
	}

	public Assinante create(AssinanteDTO oAssinanteDTO) {
		oAssinanteDTO.setIdAssinante(null);
		
		Assinante oAssinante = new Assinante(oAssinanteDTO);
		
		return assinanteRepository.save(oAssinante);
	}

	public Assinante update(AssinanteDTO oAssinanteDTO, Integer id) {
		oAssinanteDTO.setIdAssinante(id);
		
		Assinante oAssinante = this.findById(id);
		
		oAssinante = new Assinante(oAssinanteDTO);
		
		return assinanteRepository.save(oAssinante);
	}

	public void delete(Integer id) {
		Assinante oAssinante = this.findById(id);
		
		assinanteRepository.deleteById(oAssinante.getIdAssinante());
	}

}
