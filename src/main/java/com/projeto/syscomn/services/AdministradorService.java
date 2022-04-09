package com.projeto.syscomn.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projeto.syscomn.domain.Administrador;
import com.projeto.syscomn.domain.dtos.AdministradorDTO;
import com.projeto.syscomn.repositores.AdministradorRepository;
import com.projeto.syscomn.services.exceptions.ObjectNotFoundException;

@Service
public class AdministradorService {
	
	@Autowired
	private AdministradorRepository administradorRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public Administrador findById(Integer id) {
		Optional<Administrador> oAdministrador = administradorRepository.findById(id);

		return oAdministrador.orElseThrow(() -> new ObjectNotFoundException("Administrador não encontrado! ID: " + id));
	}
	
	public List<Administrador> findAll() {
		return administradorRepository.findAll();
	}

	public Administrador create(@Valid AdministradorDTO pAdministradorDTO) {
		return administradorRepository.save(newAdministrador(pAdministradorDTO));
	}
	
	public Administrador update(@Valid AdministradorDTO oAdministradorDTO, Integer id) {
		oAdministradorDTO.setIdPessoa(id);
		
		Administrador oAdministrador = findById(oAdministradorDTO.getIdPessoa());
		
		oAdministrador = newAdministrador(oAdministradorDTO);
		
		return administradorRepository.save(oAdministrador);
	}
	
	public void delete(Integer id) {
		Administrador oAdministrador = findById(id);
		
		administradorRepository.deleteById(oAdministrador.getIdPessoa());
	}
	
	private Administrador newAdministrador(AdministradorDTO pAdministradorDTO) {					
		Administrador oAdministrador = new Administrador(pAdministradorDTO);
		//Realizando a criptografia da senha
		oAdministrador.setSenha(encoder.encode(oAdministrador.getSenha()));
	
		return oAdministrador;			
	}
	
}
