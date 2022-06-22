package br.com.glandata.nf.main;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.glandata.nf.dao.ProdutoDao;
import br.com.glandata.nf.model.Produto;
import br.com.glandata.nf.util.JPAUtil;

public class ConsultasAvancadas2 {

	public static void main(String[] args) {
		EntityManager em = JPAUtil.getEntityManager();

		ProdutoDao produtoDao = new ProdutoDao(em);

		String nomeCategoria = "GAMES";
		String nomeProduto = "";
		BigDecimal valorInicial = new BigDecimal("50");
		BigDecimal valorFinal = new BigDecimal("500");

		List<Produto> produtosPorParametro = produtoDao.buscarPorParametros(nomeCategoria, nomeProduto, valorInicial, valorFinal);
		
		produtosPorParametro.forEach(p -> System.out.println(p));
	}

}
