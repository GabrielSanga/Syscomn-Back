package com.projeto.syscomn.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projeto.syscomn.domain.dtos.LoteDTO;
import com.projeto.syscomn.domain.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Lote implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idLote;
	
	private String descricao;
	
	private Integer nroLote;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate dataInicio = LocalDate.now();
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate dataFinal = LocalDate.now();
	
	private Double pesoEntrada;
	
	private Double custoLote;
	
	private Integer qtdeCabecasEntrada;
	
	private Integer qtdeCabecasMorte;
	
	private Integer status;
	
	@ManyToOne
	@JoinColumn(name = "idCurralPiquete")
	private CurralPiquete curralPiquete;
	
	@ManyToOne
	@JoinColumn(name = "idRegimeEngorda")
	private RegimeEngorda regimeEngorda;
	
	@JsonIgnore
	@OneToMany(mappedBy = "lote")
	private List<Movimentacao> lstMovimentacao = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "lote")
	private List<AnimalChip> lstAnimais = new ArrayList<>();

	public Lote() {
		super();
	}
	
	public Lote(LoteDTO pLoteDTO) {
		super();
		this.idLote = pLoteDTO.getIdLote();
		this.descricao = pLoteDTO.getDescricao();
		this.nroLote = pLoteDTO.getNroLote();
		this.dataInicio = pLoteDTO.getDataInicio();
		this.dataFinal = pLoteDTO.getDataFinal();
		this.pesoEntrada = pLoteDTO.getPesoEntrada();
		this.custoLote = pLoteDTO.getCustoLote();
		this.qtdeCabecasEntrada = pLoteDTO.getQtdeCabecasEntrada();
		this.qtdeCabecasMorte = pLoteDTO.getQtdeCabecasMorte();
		this.status = pLoteDTO.getStatus();
		this.lstMovimentacao = pLoteDTO.getLstMovimentacao().stream().map(x -> new Movimentacao(x)).collect(Collectors.toList());
		this.lstAnimais = pLoteDTO.getLstAnimais().stream().map(x -> new AnimalChip(x)).collect(Collectors.toList());
	}

}
