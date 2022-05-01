package com.projeto.syscomn.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.syscomn.domain.AnimalChip;
import com.projeto.syscomn.domain.Lote;
import com.projeto.syscomn.domain.LoteRacao;
import com.projeto.syscomn.domain.Movimentacao;
import com.projeto.syscomn.domain.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Setter
public class LoteDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Getter
	private Integer idLote;
	
    @Getter
    @NotNull(message = "Descrição do Lote é campo de preenchimento obrigatório!")
    private String descricao;
	
	@Getter
	@NotNull(message = "Número do Lote é campo de preenchimento obrigatório!")
	private Integer nroLote;
	
	@Getter
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataInicio = LocalDate.now();
	
	@Getter
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataFinal = LocalDate.now();
	
	@Getter
	private Double pesoEntrada;
	
	@Getter
	private Double pesoAtual;
	
	@Getter
	private Double custoLote;
	
	@Getter
	private Integer qtdeCabecasEntrada;
	
	@Getter
	private Integer qtdeCabecasMorte;
	
	@Getter
	private Integer qtdeCabecasAtual;

	@Getter
	@NotNull(message = "Curral Piquete é campo de preenchimento obrigatório!")
	private Integer curralPiquete;
	
	@Getter 
	private String descricaoCurralPiquete;
	
	@Getter
	private Integer regimeEngorda; 
	
	@Getter 
	private String descricaoRegimeEngorda;
	
	@NotNull(message = "Status é campo de preenchimento obrigatório!")
	private Integer status;
	
	@Getter
	private List<MovimentacaoDTO> lstMovimentacao = new ArrayList<>();
	
	@Getter
	private List<AnimalChipDTO> lstAnimalChip = new ArrayList<>();
	
	public LoteDTO() {
		super();
	}
	
	public LoteDTO(Lote pLote) {
		super();
		this.idLote = pLote.getIdLote();
		this.descricao = pLote.getDescricao();
		this.nroLote = pLote.getNroLote();
		this.dataInicio = pLote.getDataInicio();
		this.dataFinal = pLote.getDataFinal();
		this.pesoEntrada = pLote.getPesoEntrada();
		this.pesoAtual = pLote.getLstAnimalChip().stream().mapToDouble(AnimalChip::getPesoEntrada).sum();
		this.custoLote = pLote.getCustoLote();
		this.qtdeCabecasEntrada = pLote.getQtdeCabecasEntrada();
		this.qtdeCabecasMorte = pLote.getQtdeCabecasMorte();
		this.qtdeCabecasAtual = pLote.getLstAnimalChip().stream().mapToInt(AnimalChip::getIdAnimalChip).sum();
		this.curralPiquete = pLote.getCurralPiquete().getIdCurralPiquete();
		this.descricaoCurralPiquete = pLote.getCurralPiquete().getDescricao();
		this.status = pLote.getStatus().getCodigo();
		this.regimeEngorda = pLote.getRegimeEngorda().getIdRegimeEngorda();
		this.descricaoRegimeEngorda = pLote.getRegimeEngorda().getDescricao();
		this.lstMovimentacao = pLote.getLstMovimentacao().stream().map(x -> new MovimentacaoDTO(x)).collect(Collectors.toList());
	} 
	
	public Status getStatus() {
		return Status.toEnum(this.status);
	}
	
}
