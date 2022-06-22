package br.com.glandata.nf.main;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.glandata.nf.dao.ProdutoDao;
import br.com.glandata.nf.model.Produto;
import br.com.glandata.nf.util.JPAUtil;

public class TestaCriteria {

	public static void main(String[] args) {
		LocalDate data = LocalDate.of(2022, 6, 21);

		EntityManager em = JPAUtil.getEntityManager();

		ProdutoDao produtoDao = new ProdutoDao(em);

		List<Produto> produtos = produtoDao.buscarPorParametroComCriteria(null, new BigDecimal("50"), data);

		produtos.forEach(p -> System.out.println(p.getNome() + " - " + p.getPreco()));
	}

}
