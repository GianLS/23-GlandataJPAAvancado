package br.com.glandata.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.glandata.jpa.model.NotaFiscal;

public class NotaFiscalDao {

	public NotaFiscalDao(EntityManager em) {
		this.em = em;
	}

	private EntityManager em;

	public void cadastrar(NotaFiscal notaFiscal) {
		em.persist(notaFiscal);
	}

	public void atualizar(NotaFiscal notaFiscal) {
		em.merge(notaFiscal);
	}

	public void remover(NotaFiscal notaFiscal) {
		notaFiscal = em.merge(notaFiscal);
		em.remove(notaFiscal);
	}

	public NotaFiscal buscarPorId(Long id) {
		return em.find(NotaFiscal.class, id);
	}

	public List<NotaFiscal> listarTudo() {
		return em.createQuery("SELECT nf FROM NotaFiscal nf", NotaFiscal.class).getResultList();
	}

}
