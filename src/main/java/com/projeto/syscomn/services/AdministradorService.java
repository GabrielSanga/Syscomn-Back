package com.projeto.syscomn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.syscomn.repositores.AdministradorRepository;

@Service
public class AdministradorService {
	
	@Autowired
	private AdministradorRepository administradorRepository;

}
