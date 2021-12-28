package com.projeto.syscomn.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.syscomn.services.AdministradorService;

@RestController
@RequestMapping(value = "administrador")
public class AdministradorResource {
	
	@Autowired
	private AdministradorService administradorService;

}
