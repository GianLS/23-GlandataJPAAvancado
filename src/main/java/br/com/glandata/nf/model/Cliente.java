package br.com.glandata.nf.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clientes")
public class Cliente {
	public Cliente() {
	}

	public Cliente(Long id) {
		this.id = id;
	}

	public Cliente(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
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
	private String cpf;
}
