package com.projeto.syscomn.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.projeto.syscomn.domain.dtos.VacinaDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Vacina implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idVacina;
	
	private String nomeVacina;
	
	private String fabricante;

	@ManyToOne
	@JoinColumn(name = "idPessoa")
	private Fornecedor fornecedor;
	

	public Vacina() {
		super();
	}
	
	public Vacina(VacinaDTO pVacinaDTO) {
		super();
		this.idVacina = pVacinaDTO.getIdVacina();
		this.nomeVacina = pVacinaDTO.getNomeVacina();
		this.fabricante = pVacinaDTO.getFabricante();
		}
}
