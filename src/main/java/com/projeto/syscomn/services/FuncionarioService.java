package com.projeto.syscomn.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projeto.syscomn.domain.Funcionario;
import com.projeto.syscomn.domain.Pessoa;
import com.projeto.syscomn.domain.dtos.FuncionarioDTO;
import com.projeto.syscomn.domain.enums.Perfil;
import com.projeto.syscomn.repositores.FuncionarioRepository;
import com.projeto.syscomn.repositores.PessoaRepository;
import com.projeto.syscomn.services.exceptions.ObjectNotFoundException;

@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public Funcionario findById(Integer id) {
		Optional<Funcionario> oFuncionario = funcionarioRepository.findById(id);

		return oFuncionario.orElseThrow(() -> new ObjectNotFoundException("Funcionário não encontrado! ID: " + id));
	}
	
	public List<Funcionario> findAll() {
		return funcionarioRepository.findAll();
	}

	public Funcionario create(@Valid FuncionarioDTO pFuncionarioDTO) {		
		return funcionarioRepository.save(newFuncionario(pFuncionarioDTO, false));
	}
	
	public Funcionario update(@Valid FuncionarioDTO oFuncionarioDTO, Integer id) {
		oFuncionarioDTO.setIdPessoa(id);
		
		Funcionario oFuncionario = findById(oFuncionarioDTO.getIdPessoa());
		
		oFuncionario = newFuncionario(oFuncionarioDTO, true);
		
		return funcionarioRepository.save(oFuncionario);
	}
	
	public void delete(Integer id) {
		Funcionario oFuncionario = findById(id);
		
		funcionarioRepository.deleteById(oFuncionario.getIdPessoa());
	}
	
	private Funcionario newFuncionario(FuncionarioDTO pFuncionarioDTO, boolean isUpdated) {			
		Funcionario oFuncionario = new Funcionario(pFuncionarioDTO);
			
		if(!isUpdated) {
			oFuncionario.addPerfil(Perfil.FUNCIONARIO);

			//Validando se login já existe no sistema
			Optional<Pessoa> oPessoa = pessoaRepository.findByUserName(pFuncionarioDTO.getUserName());
			
			if(oPessoa.orElse(null) != null) {
				throw new ObjectNotFoundException("Login já cadastrado no sistema!");
			}
			//Realizando a criptografia da senha
			oFuncionario.setSenha(encoder.encode(oFuncionario.getSenha()));
		}

		return oFuncionario;			
	}

}
