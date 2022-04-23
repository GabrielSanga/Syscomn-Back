package com.projeto.syscomn.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.projeto.syscomn.domain.dtos.AnimalChipDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class AnimalChip implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAnimalChip;
	
	private String chip;
	
	private String codigo;
	
	private String nome;
	
	private LocalDate dtaNascimento;
	
	private LocalDate dtaEntrada;
	
	private LocalDate dtaSaida;
	
	private String pai;
	
	private String mae;
	
	private Double custoAquisicao;
	
	private Double custoFinal;
	
	private Double pesoEntrada;
	
    private String motivoSaida;
    
    private Integer status;
    
    private Double ganhoMedioDiario;
    
	@ManyToOne
	@JoinColumn(name = "idLote")
    private Lote lote;
    
	@ManyToOne
	@JoinColumn(name = "idSexoAnimal")
    private SexoAnimal sexoAnimal;
    
	@ManyToOne
	@JoinColumn(name = "idRaca")
    private Raca raca;
    
	@ManyToOne
	@JoinColumn(name = "idEstadoAnimal")
    private EstadoAnimal estadoAnimal;
    
	@ManyToOne
	@JoinColumn(name = "idTipoMorte")
    private TipoMorte tipoMorte;

	public AnimalChip() {
		super();
	}
	
	public AnimalChip(AnimalChipDTO pAnimalChipDTO) {
		this.idAnimalChip = pAnimalChipDTO.getIdAnimalChip();
		this.chip = pAnimalChipDTO.getChip();
		this.codigo = pAnimalChipDTO.getCodigo();
		this.nome = pAnimalChipDTO.getNome();
		this.dtaNascimento = pAnimalChipDTO.getDtaNascimento();
		this.dtaEntrada = pAnimalChipDTO.getDtaEntrada();
		this.dtaSaida = pAnimalChipDTO.getDtaSaida();
		this.pai = pAnimalChipDTO.getPai();
		this.mae = pAnimalChipDTO.getMae();
		this.custoAquisicao = pAnimalChipDTO.getCustoAquisicao();
		this.custoFinal = pAnimalChipDTO.getCustoFinal();
		this.pesoEntrada = pAnimalChipDTO.getPesoEntrada();
		this.motivoSaida = pAnimalChipDTO.getMotivoSaida();
		this.status = pAnimalChipDTO.getStatus();
		this.ganhoMedioDiario = pAnimalChipDTO.getGanhoMedioDiario();
	}	
	
}
