package br.com.glandata.nf.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.google.common.base.Strings;

import br.com.glandata.nf.model.Produto;

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

	public List<Produto> buscarTodos() {
		return em.createQuery("SELECT p FROM Produto p", Produto.class).getResultList();
	}

	public List<Produto> buscarPorNomeCategoria(String nome) {
		return em.createNamedQuery("Produto.produtosPorCategoria", Produto.class).setParameter("nome", nome)
				.getResultList();
	}

	public List<Produto> buscarPorParametros(String nomeCategoria, String nomeProduto, BigDecimal valorInicial,
			BigDecimal valorFinal) {

		String jpql = "SELECT p FROM Produto p WHERE 1 = 1 ";

		if (!Strings.isNullOrEmpty(nomeProduto))
			jpql += " AND LOWER(p.nome) like :nomeProduto ";
		if (!Strings.isNullOrEmpty(nomeCategoria))
			jpql += " AND LOWER(p.categoria.nome) like :nomeCategoria ";
		if (!Strings.isNullOrEmpty(valorInicial.toString()))
			jpql += " AND p.preco >= :valorInicial ";
		if (!Strings.isNullOrEmpty(valorFinal.toString()))
			jpql += " AND p.preco <= :valorFinal ";

		TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
		if (!Strings.isNullOrEmpty(nomeProduto))
			query.setParameter("nomeProduto", "%" + nomeProduto.toLowerCase() + "%");
		if (!Strings.isNullOrEmpty(nomeCategoria))
			query.setParameter("nomeCategoria", "%" + nomeCategoria.toLowerCase() + "%");
		if (!Strings.isNullOrEmpty(valorInicial.toString()))
			query.setParameter("valorInicial", valorInicial);
		if (!Strings.isNullOrEmpty(valorFinal.toString()))
			query.setParameter("valorFinal", valorFinal);

		return query.getResultList();
	}

	public List<Produto> buscarPorParametroComCriteria(String nome, BigDecimal preco, LocalDate dataCadastro) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);

		Root<Produto> from = query.from(Produto.class);

		Predicate filtros = builder.and();

		if (!Strings.isNullOrEmpty(nome))
			filtros = builder.and(filtros, builder.like(from.get("nome"), nome));

		if (preco != null)
			filtros = builder.and(filtros, builder.greaterThan(from.get("preco"), preco));

		if (dataCadastro != null)
			filtros = builder.and(filtros, builder.equal(from.get("dataCadastro"), dataCadastro));

		query.where(filtros);
		
		return em.createQuery(query).getResultList();
	}

}
