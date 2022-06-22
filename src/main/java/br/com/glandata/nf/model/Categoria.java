package br.com.glandata.nf.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "categorias")
public class Categoria {
	public Categoria() {
	}

	public Categoria(Long id) {
		this.id = id;
	}

	public Categoria(String nome) {
		this.nome = nome;
	}

	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Getter
	@Setter
	private String nome;

}
