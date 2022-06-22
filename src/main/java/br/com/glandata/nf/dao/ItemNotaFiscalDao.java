package br.com.glandata.nf.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.glandata.nf.model.ItemNotaFiscal;

public class ItemNotaFiscalDao {

	public ItemNotaFiscalDao(EntityManager em) {
		this.em = em;
	}

	private EntityManager em;

	public void cadastrar(ItemNotaFiscal itemNotaFiscal) {
		em.persist(itemNotaFiscal);
	}

	public void atualizar(ItemNotaFiscal itemNotaFiscal) {
		em.merge(itemNotaFiscal);
	}

	public void remover(ItemNotaFiscal itemNotaFiscal) {
		itemNotaFiscal = em.merge(itemNotaFiscal);
		em.remove(itemNotaFiscal);
	}

	public ItemNotaFiscal buscarPorId(Long id) {
		return em.find(ItemNotaFiscal.class, id);
	}

	public List<ItemNotaFiscal> buscarTodos() {
		return em.createQuery("SELECT inf FROM ItemNotaFiscal inf", ItemNotaFiscal.class).getResultList();
	}

}
