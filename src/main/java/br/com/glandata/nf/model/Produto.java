package br.com.glandata.nf.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "produtos")
@ToString
@NamedQuery(name = "Produto.produtosPorCategoria", query = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome")
public class Produto {
	public Produto() {
	}

	public Produto(Long id) {
		this.id = id;
	}

	public Produto(String nome, String descricao, BigDecimal preco, Categoria categoria) {
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.categoria = categoria;
	}

	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Getter
	@Setter
	private String nome;

	@Getter
	@Setter
	private String descricao;

	@Getter
	@Setter
	private BigDecimal preco;

	@Getter
	@Setter
	@ManyToOne
	private Categoria categoria;

	@Getter
	@Column(name = "data_cadastro")
	private LocalDate dataCadastro = LocalDate.now();

//	@Getter
//	@Setter
//	@OneToMany
//	private List<ItemNotaFiscal> itens = new ArrayList<>();
}
