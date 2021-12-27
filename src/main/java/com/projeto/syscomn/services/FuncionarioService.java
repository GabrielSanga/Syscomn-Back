package com.projeto.syscomn.services;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.syscomn.domain.Assinante;
import com.projeto.syscomn.domain.Funcionario;
import com.projeto.syscomn.domain.dtos.FuncionarioDTO;
import com.projeto.syscomn.repositores.FuncionarioRepository;

@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	@Autowired
	private AssinanteService assinanteService;

	public Funcionario create(@Valid FuncionarioDTO pFuncionarioDTO) {
		return funcionarioRepository.save(newFuncionario(pFuncionarioDTO));
	}
	
	private Funcionario newFuncionario(FuncionarioDTO pFuncionarioDTO) {	
		pFuncionarioDTO.setIdPessoa(0);
		
		Assinante oAssinante = assinanteService.findById(pFuncionarioDTO.getIdAssinante());
		
		if (oAssinante == null) { return null; }
				
		Funcionario oFuncionario = new Funcionario(pFuncionarioDTO);
		oFuncionario.setAssinante(oAssinante);
	
		return oFuncionario;			
	}

}
