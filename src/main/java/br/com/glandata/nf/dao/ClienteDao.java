package br.com.glandata.nf.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.glandata.nf.model.Cliente;

public class ClienteDao {

	public ClienteDao(EntityManager em) {
		this.em = em;
	}

	private EntityManager em;

	public void cadastrar(Cliente cliente) {
		em.persist(cliente);
	}

	public void atualizar(Cliente cliente) {
		em.merge(cliente);
	}

	public void remover(Cliente cliente) {
		cliente = em.merge(cliente);
		em.remove(cliente);
	}

	public Cliente buscarPorId(Long id) {
		return em.find(Cliente.class, id);
	}

	public List<Cliente> buscarTodos() {
		return em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
	}

}
