package br.com.glandata.nf.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.glandata.nf.model.NotaFiscal;
import br.com.glandata.nf.vo.RelatorioFaturamentoVo;

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

	public List<NotaFiscal> buscarTodos() {
		return em.createQuery("SELECT nf FROM NotaFiscal nf", NotaFiscal.class).getResultList();
	}

	public BigDecimal totalFaturado() {
		return em.createQuery("SELECT SUM(nf.valorTotal) FROM NotaFiscal nf", BigDecimal.class).getSingleResult();
	}

	public List<RelatorioFaturamentoVo> relatorioFaturamento() {
		String jpql = "SELECT new br.com.glandata.nf.vo.RelatorioFaturamentoVo(p.nome, " + " SUM(i.quantidade), "
				+ " MAX(nf.dataEmissao)) " + "FROM NotaFiscal nf " + "JOIN nf.itens i " + "JOIN i.produto p "
				+ "GROUP BY p.nome ";

		return em.createQuery(jpql, RelatorioFaturamentoVo.class).getResultList();
	}

	public NotaFiscal buscaNotaFiscalComCliente(Long id) {
		String jpql = "SELECT nf FROM NotaFiscal nf JOIN FETCH nf.cliente c WHERE nf.id = :id";
		return em.createQuery(jpql, NotaFiscal.class).setParameter("id", id).getSingleResult();
	}

}
