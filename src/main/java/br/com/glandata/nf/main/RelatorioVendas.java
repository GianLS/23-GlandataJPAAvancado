package br.com.glandata.nf.main;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.glandata.nf.dao.NotaFiscalDao;
import br.com.glandata.nf.util.JPAUtil;
import br.com.glandata.nf.vo.RelatorioFaturamentoVo;

public class RelatorioVendas {

	public static void main(String[] args) {
		EntityManager em = JPAUtil.getEntityManager();

		NotaFiscalDao notaFiscalDao = new NotaFiscalDao(em);

		List<RelatorioFaturamentoVo> relatorio = notaFiscalDao.relatorioFaturamento();

		System.out.println("ITEM;\t\t\t\tQUANTIDADE VENDIDA;\tÚLTIMA VENDA");
		for (RelatorioFaturamentoVo item : relatorio) {
			System.out.println(
					item.getNomeProduto() + ";\t\t\t\t" + item.getQuantidade() + ";\t\t\t" + item.getUltimaVenda());
		}

		em.close();
	}

}
