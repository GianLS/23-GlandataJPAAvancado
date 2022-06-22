package br.com.glandata.nf.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "notas_fiscais")
public class NotaFiscal {

	public NotaFiscal() {
	}

	public NotaFiscal(Long id) {
		this.id = id;
	}

	public NotaFiscal(Cliente cliente) {
		this.cliente = cliente;
	}

	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Getter
	@Setter
	@Column(name = "valor_total")
	private BigDecimal valorTotal = BigDecimal.ZERO;

	@Getter
	@Setter
	@Column(name = "data_emissao")
	private LocalDate dataEmissao = LocalDate.now();

	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;

	@Getter
	@Setter
	@OneToMany(mappedBy = "notaFiscal", cascade = CascadeType.ALL)
	private List<ItemNotaFiscal> itens = new ArrayList<>();

	public void adicionarItem(ItemNotaFiscal item) {
		itens.add(item);
		atualizarValorNota(item.getPrecoUnitario(), item.getQuantidade());
	}

	private void atualizarValorNota(BigDecimal valor, Integer quantidade) {
		this.setValorTotal(this.valorTotal.add(valor.multiply(new BigDecimal(quantidade))));
	}

	public void removerItem(ItemNotaFiscal item) {
		itens.remove(item);
		atualizarValorNota(item.getPrecoUnitario().negate(), item.getQuantidade());
	}

	public void calcularValorTotal() {
		this.itens.forEach(i -> this
				.setValorTotal(this.valorTotal.add(i.getPrecoUnitario().multiply(new BigDecimal(i.getQuantidade())))));
	}
}
