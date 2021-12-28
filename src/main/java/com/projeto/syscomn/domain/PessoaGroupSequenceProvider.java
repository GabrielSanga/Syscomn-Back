package com.projeto.syscomn.domain;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import com.projeto.syscomn.domain.enums.TipoPessoa;

public class PessoaGroupSequenceProvider implements DefaultGroupSequenceProvider<Pessoa>{

	@Override
	public List<Class<?>> getValidationGroups(Pessoa pPessoa) {
		List<Class<?>> groups = new ArrayList<>();
		groups.add(Pessoa.class);
		
		if(pPessoa != null) {
			groups.add(TipoPessoa.toEnum(pPessoa.getTipoPessoa()).getGroup());		
		}
				
		return groups;
	}

}
