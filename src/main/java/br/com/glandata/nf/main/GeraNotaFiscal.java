package br.com.glandata.nf.main;

import javax.persistence.EntityManager;

import br.com.glandata.nf.dao.ClienteDao;
import br.com.glandata.nf.dao.NotaFiscalDao;
import br.com.glandata.nf.dao.ProdutoDao;
import br.com.glandata.nf.model.Cliente;
import br.com.glandata.nf.model.ItemNotaFiscal;
import br.com.glandata.nf.model.NotaFiscal;
import br.com.glandata.nf.util.JPAUtil;

public class GeraNotaFiscal {

	public static void main(String[] args) {
//		PopulaDados.cadastrarDados();

		EntityManager em = JPAUtil.getEntityManager();

		ClienteDao clienteDao = new ClienteDao(em);
		ProdutoDao produtoDao = new ProdutoDao(em);
		NotaFiscalDao notaFiscalDao = new NotaFiscalDao(em);

		// Nota1
		Cliente cliente = clienteDao.buscarPorId(1L);
		NotaFiscal notaFiscal1 = new NotaFiscal(cliente);
		notaFiscal1.adicionarItem(new ItemNotaFiscal(2, notaFiscal1, produtoDao.buscarPorId(3L)));
		notaFiscal1.adicionarItem(new ItemNotaFiscal(4, notaFiscal1, produtoDao.buscarPorId(4L)));

		// Nota 2
		NotaFiscal notaFiscal2 = new NotaFiscal(cliente);
		notaFiscal2.adicionarItem(new ItemNotaFiscal(1, notaFiscal2, produtoDao.buscarPorId(1L)));
		notaFiscal2.adicionarItem(new ItemNotaFiscal(1, notaFiscal2, produtoDao.buscarPorId(2L)));

		try {
			em.getTransaction().begin();

			notaFiscalDao.cadastrar(notaFiscal1);
			notaFiscalDao.cadastrar(notaFiscal2);

			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

}
