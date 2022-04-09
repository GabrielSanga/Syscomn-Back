package com.projeto.syscomn.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.syscomn.domain.Pessoa;

import com.projeto.syscomn.domain.dtos.UsuarioDTO;
import com.projeto.syscomn.repositores.PessoaRepository;
import com.projeto.syscomn.security.JWTUtil;

@RestController
@RequestMapping(value = "usuario")
public class UsuarioResource {
	
	@Autowired
	private JWTUtil jwt;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> findByToken(@RequestBody UsuarioDTO oUsuarioDTO, HttpServletRequest request){		
		return ResponseEntity.ok().body(jwt.getUsuarioLogado(request));
	}
	
    @PutMapping("{idUsuario}/foto")
    public void addPhoto(@PathVariable("idUsuario") Integer idUsuario, @RequestParam("foto")Part arquivo) {
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
    
	
}
