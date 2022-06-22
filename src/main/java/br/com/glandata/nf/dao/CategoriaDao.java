package br.com.glandata.nf.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.glandata.nf.model.Categoria;

public class CategoriaDao {

	public CategoriaDao(EntityManager em) {
		this.em = em;
	}

	private EntityManager em;

	public void cadastrar(Categoria categoria) {
		em.persist(categoria);
	}

	public void atualizar(Categoria categoria) {
		em.merge(categoria);
	}

	public void remover(Categoria categoria) {
		categoria = em.merge(categoria);
		em.remove(categoria);
	}

	public Categoria buscarPorId(Long id) {
		return em.find(Categoria.class, id);
	}

	public List<Categoria> buscarTodos() {
		return em.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();
	}

}
