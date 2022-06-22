package br.com.glandata.nf.main;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.glandata.nf.dao.NotaFiscalDao;
import br.com.glandata.nf.model.ItemNotaFiscal;
import br.com.glandata.nf.model.NotaFiscal;
import br.com.glandata.nf.util.JPAUtil;

public class ConsultarNotas {

	public static void main(String[] args) {
		EntityManager em = JPAUtil.getEntityManager();

		NotaFiscalDao notaFiscalDao = new NotaFiscalDao(em);

		List<NotaFiscal> notasFiscais = notaFiscalDao.buscarTodos();

		for (NotaFiscal notaFiscal : notasFiscais) {
			System.out.println("Nota Fiscal: " + notaFiscal.getId());
			for (ItemNotaFiscal item : notaFiscal.getItens()) {
				System.out.println(item.getId() + " Item: " + item.getProduto().getNome() + " Preço Unitário: "
						+ item.getPrecoUnitario() + " Quantidade: " + item.getQuantidade() + " Preço Final: "
						+ item.getPrecoUnitario().multiply(new BigDecimal(item.getQuantidade())));
			}
			System.out.println("Valor Total da Nota: " + notaFiscal.getValorTotal());
		}
	}

}
