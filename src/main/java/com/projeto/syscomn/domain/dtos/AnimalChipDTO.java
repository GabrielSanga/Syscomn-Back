package com.projeto.syscomn.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.syscomn.domain.AnimalChip;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnimalChipDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer idAnimalChip;
	
	private String chip;
	
	@NotNull(message = "Código é campo de preenchimento obrigatório!")
	private String codigo;
	
	private String nome;
	
	private LocalDate dtaNascimento;
	
	@NotNull(message = "Data da Entrada é campo de preenchimento obrigatório!")
	private LocalDate dtaEntrada;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dtaSaida;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dtaHoraUltimaPesagem;
	
	private String pai;
	
	private String mae;
	
	@Range(min = 1, message = "Custo da Aquisição é campo de preenchimento obrigatório!")
	private Double custoAquisicao;
	
	private Double custoFinal;
	
	@NotNull(message = "Peso de Entrada é campo de preenchimento obrigatório!")
	private Double pesoEntrada;
	
	@JsonFormat(pattern = "###.000,000")
	private Double pesoAtual;
	
    private String motivoSaida;
    
    private Integer status;
    
    private Double ganhoMedioDiario;

	@Range(min = 1, message = "Lote é campo de preenchimento obrigatório!")
    private Integer idLote;
	private Integer nroLote;
	private String descrLote;
    
	@Range(min = 1, message = "Sexo é campo de preenchimento obrigatório!")
    private Integer idSexoAnimal;
	private String descrSexoAnimal;
    
	@Range(min = 1, message = "Raça é campo de preenchimento obrigatório!")
    private Integer idRaca;
	private String descrRaca;
    
	@Range(min = 1, message = "Estado do Animal é campo de preenchimento obrigatório!")
    private Integer idEstadoAnimal;
	private String descrEstadoAnimal;

    private Integer idTipoMorte;
    private String descrTipoMorte;
    
	private List<AlimentacaoDTO> lstAlimentacao = new ArrayList<>();
	
	private List<PesagemDTO> lstPesagem = new ArrayList<>();
	
	private List<VacinacaoDTO> lstVacinacao = new ArrayList<>();

	public AnimalChipDTO() {
		super();
	}

	public AnimalChipDTO(AnimalChip pAnimalChip) {
		this.idAnimalChip = pAnimalChip.getIdAnimalChip();
		this.chip = pAnimalChip.getChip();
		this.codigo = pAnimalChip.getCodigo();
		this.nome = pAnimalChip.getNome();
		this.dtaNascimento = pAnimalChip.getDtaNascimento();
		this.dtaEntrada = pAnimalChip.getDtaEntrada();
		this.dtaSaida = pAnimalChip.getDtaSaida();
		this.dtaHoraUltimaPesagem = pAnimalChip.getDtaHoraUltimaPesagem();
		this.pai = pAnimalChip.getPai();
		this.mae = pAnimalChip.getMae();
		this.custoAquisicao = pAnimalChip.getCustoAquisicao();
		this.custoFinal = pAnimalChip.getCustoFinal();
		this.pesoEntrada = pAnimalChip.getPesoEntrada();
		this.pesoAtual = pAnimalChip.getPesoAtual();
		this.motivoSaida = pAnimalChip.getMotivoSaida();
		this.status = pAnimalChip.getStatus();
		this.ganhoMedioDiario = pAnimalChip.getGanhoMedioDiario();
		this.idLote = pAnimalChip.getLote().getIdLote();
		this.nroLote = pAnimalChip.getLote().getNroLote();
		this.descrLote = pAnimalChip.getLote().getDescricao();
		this.idSexoAnimal = pAnimalChip.getSexoAnimal().getIdSexoAnimal();
		this.descrSexoAnimal = pAnimalChip.getSexoAnimal().getDescricao();
		this.idRaca = pAnimalChip.getRaca().getIdRaca();
		this.descrRaca = pAnimalChip.getRaca().getDescricao();
		this.idEstadoAnimal = pAnimalChip.getEstadoAnimal().getIdEstadoAnimal();
		this.descrEstadoAnimal = pAnimalChip.getEstadoAnimal().getDescricao();
		
		if (pAnimalChip.getTipoMorte() != null) {
			this.idTipoMorte = pAnimalChip.getTipoMorte().getIdTipoMorte();
			this.descrTipoMorte = pAnimalChip.getTipoMorte().getDescricao();
		}
		
		this.lstAlimentacao = pAnimalChip.getLstAlimentacao().stream().map(x -> new AlimentacaoDTO(x)).collect(Collectors.toList());
		this.lstPesagem = pAnimalChip.getLstPesagem().stream().map(x -> new PesagemDTO(x)).collect(Collectors.toList());
		this.lstVacinacao = pAnimalChip.getLstVacinacao().stream().map(x -> new VacinacaoDTO(x)).collect(Collectors.toList());
	}

}
