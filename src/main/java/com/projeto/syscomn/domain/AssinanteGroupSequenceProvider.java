package com.projeto.syscomn.domain;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import com.projeto.syscomn.domain.enums.TipoPessoa;

public class AssinanteGroupSequenceProvider implements DefaultGroupSequenceProvider<Assinante>{

	@Override
	public List<Class<?>> getValidationGroups(Assinante pAssinante) {
		List<Class<?>> groups = new ArrayList<>();
		groups.add(Assinante.class);
		
		if(pAssinante != null) {
			groups.add(TipoPessoa.toEnum(pAssinante.getTipoPessoa()).getGroup());
		}
		
		return groups;
	}

}
