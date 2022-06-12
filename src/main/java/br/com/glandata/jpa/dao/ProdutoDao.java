package br.com.glandata.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.glandata.jpa.model.Produto;

public class ProdutoDao {

	public ProdutoDao(EntityManager em) {
		this.em = em;
	}

	private EntityManager em;

	public void cadastrar(Produto produto) {
		em.persist(produto);
	}

	public void atualizar(Produto produto) {
		em.merge(produto);
	}

	public void remover(Produto produto) {
		produto = em.merge(produto);
		em.remove(produto);
	}
	
	public Produto buscarPorId(Long id) {
		return em.find(Produto.class, id);
	}
	
	public List<Produto> listarTudo(){
		return em.createQuery("SELECT p FROM Produto p", Produto.class).getResultList();
	}

}
