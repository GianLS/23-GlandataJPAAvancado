package br.com.glandata.nf.vo;

import java.time.LocalDate;

import lombok.Getter;

public class RelatorioFaturamentoVo {

	
	public RelatorioFaturamentoVo(String nomeProduto, Long quantidade, LocalDate ultimaVenda) {
		this.nomeProduto = nomeProduto;
		this.quantidade = quantidade;
		this.ultimaVenda = ultimaVenda;
	}

	@Getter
	private String nomeProduto;

	@Getter
	private Long quantidade;

	@Getter
	private LocalDate ultimaVenda;
}
