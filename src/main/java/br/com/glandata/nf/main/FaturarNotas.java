package br.com.glandata.nf.main;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.glandata.nf.dao.NotaFiscalDao;
import br.com.glandata.nf.dao.ProdutoDao;
import br.com.glandata.nf.model.Produto;
import br.com.glandata.nf.util.JPAUtil;
import br.com.glandata.nf.vo.RelatorioFaturamentoVo;

public class FaturarNotas {

	public static void main(String[] args) {
		EntityManager em = JPAUtil.getEntityManager();

		NotaFiscalDao notaFiscalDao = new NotaFiscalDao(em);
		ProdutoDao produtoDao = new ProdutoDao(em);

		System.out.println("O Total Faturado até o momento é: " + notaFiscalDao.totalFaturado());

		List<RelatorioFaturamentoVo> relatorio = notaFiscalDao.relatorioFaturamento();

		System.out.println("\nITEM;\t\t\t\tQUANTIDADE VENDIDA;\tÚLTIMA VENDA");
		for (RelatorioFaturamentoVo item : relatorio) {
			System.out.println(
					item.getNomeProduto() + ";\t\t\t\t" + item.getQuantidade() + ";\t\t\t" + item.getUltimaVenda());
		}

		List<Produto> produtosPorCategoria = produtoDao.buscarPorNomeCategoria("Alimentos");

		System.out.println("\nPRODUTOS POR CATEGORIA");
		produtosPorCategoria.forEach(p -> {
			System.out.println("Nome: " + p.getNome() + " - Descrição: " + p.getDescricao() + " - Preço: R$" + p.getPreco());
		});

	}

}
