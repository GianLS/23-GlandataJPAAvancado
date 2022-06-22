package br.com.glandata.nf.main;

import javax.persistence.EntityManager;

import br.com.glandata.nf.dao.NotaFiscalDao;
import br.com.glandata.nf.model.NotaFiscal;
import br.com.glandata.nf.util.JPAUtil;

public class PerformanceDeConsultas {

	public static void main(String[] args) {
		EntityManager em = JPAUtil.getEntityManager();

		NotaFiscalDao notaFiscalDao = new NotaFiscalDao(em);
		
		NotaFiscal nf = em.find(NotaFiscal.class, 1L);

		System.out.println(nf.getDataEmissao());

		System.out.println(nf.getCliente().getNome());
		
		nf = notaFiscalDao.buscaNotaFiscalComCliente(1L);
		
		System.out.println(nf.getCliente().getNome());
	}

}
