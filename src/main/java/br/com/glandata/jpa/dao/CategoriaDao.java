package br.com.glandata.jpa.dao;

import javax.persistence.EntityManager;

import br.com.glandata.jpa.model.Categoria;

public class CategoriaDao {

	public CategoriaDao(EntityManager em) {
		this.em = em;
	}

	private EntityManager em;

	public void cadastrar(Categoria categoria) {
		em.persist(categoria);
	}

}
