package br.com.glandata.jpa.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
	private BigDecimal valorTotal;

	@Getter
	@Setter
	@Column(name = "data_emissao")
	private LocalDate dataEmissao = LocalDate.now();

	@Getter
	@Setter
	@ManyToOne
	private Cliente cliente;
	
	@Getter
	@Setter
	@OneToMany(mappedBy = "notaFiscal", cascade = CascadeType.ALL)
	private List<ItemNotaFiscal> itens = new ArrayList<>();
	
	public void adicionarItem(ItemNotaFiscal item) {
		//item.setNotaFiscal(this);
		itens.add(item);
	}
}
