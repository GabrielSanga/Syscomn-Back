package com.projeto.syscomn.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.syscomn.domain.Pessoa;

import com.projeto.syscomn.domain.dtos.UsuarioDTO;
import com.projeto.syscomn.domain.enums.Perfil;
import com.projeto.syscomn.repositores.PessoaRepository;
import com.projeto.syscomn.security.JWTUtil;

@RestController
@RequestMapping(value = "usuario")
public class UsuarioResource {

	@Autowired
	private JWTUtil jwt;

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@PostMapping
	public ResponseEntity<UsuarioDTO> findByToken(@RequestBody UsuarioDTO oUsuarioDTO, HttpServletRequest request) {
		return ResponseEntity.ok().body(jwt.getUsuarioLogado(request));
	}

	@PutMapping("{idUsuario}/foto")
	public void addPhoto(@PathVariable("idUsuario") Integer idUsuario, @RequestParam("foto") Part arquivo) {
		Optional<Pessoa> oPessoa = pessoaRepository.findById(idUsuario);

		try {
			InputStream inputStream = arquivo.getInputStream();
			byte[] bytes = new byte[(int) arquivo.getSize()];
			IOUtils.readFully(inputStream, bytes);

			oPessoa.get().setFotoPessoa(bytes);
			pessoaRepository.save(oPessoa.get());

			inputStream.close();

		} catch (IOException ex) {
		}
	}

	@PostMapping(value = "password")
	public ResponseEntity<UsuarioDTO> updatePassword(@RequestBody UsuarioDTO pUsuarioDTO, HttpServletRequest request) {
		Integer idUsuario = 0;

		idUsuario = pUsuarioDTO.getIdUsuarioAlteracao();

		// Caso seja alteração da própria senha realiza as validações, caso contrário
		// não (ADMINISTRADOR que alterou)
		if (idUsuario == 0) {
			UsuarioDTO oUsuarioLogado = jwt.getUsuarioLogado(request);

			idUsuario = oUsuarioLogado.getIdUsuario();

			// Pega a senha antiga (CRIPTOGRAFADA)
			String sSenhaBanco = pessoaRepository.findById(oUsuarioLogado.getIdUsuario()).get().getSenha();

			// Senha informada na aplicação
			String sSenhaInformada = pUsuarioDTO.getSenhaAntiga();

			// Verifica se as senhas são iguais (Compara a senha em plain-text com o hash do
			// banco)
			if (!encoder.matches(sSenhaInformada, sSenhaBanco)) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}

		Pessoa oPessoa = pessoaRepository.findById(idUsuario).get();

		// CRIPTOGRAFANDO e setando a nova senha para o usuário
		oPessoa.setSenha(encoder.encode(pUsuarioDTO.getSenha()));

		// Update
		pessoaRepository.save(oPessoa);

		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "rolesUsuario")
	public ResponseEntity<String[]> getRolesUsuario(HttpServletRequest request){		
		UsuarioDTO oUsuarioDTO = jwt.getUsuarioLogado(request);
		
		String lstRoles[] = new String[3];
		Integer i = 0;
		
		for (Perfil perfil : oUsuarioDTO.getRoles()) {
			lstRoles[i] = perfil.getDescricao();
			i++;			
		}
		return ResponseEntity.ok().body(lstRoles);
	}

}
