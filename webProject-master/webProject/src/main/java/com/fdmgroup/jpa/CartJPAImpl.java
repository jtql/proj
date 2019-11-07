package com.fdmgroup.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import com.fdmgroup.payment.Cart;

public class CartJPAImpl {
	private EntityManagerFactory emf;
	// private static final Logger logger = LogManager.getLogger(PenJPAImpl.class);

	public CartJPAImpl(EntityManagerFactory emfPassed) {
		emf = emfPassed;
	}

	public void addItem(Cart c) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		em.close();
		// logger to inform add item accomplished

	}

	public void removeItem(Cart c) {
		EntityManager em = emf.createEntityManager();
		Cart u = em.find(Cart.class, c.getId());
		em.getTransaction().begin();
		em.remove(u);
		em.getTransaction().commit();
		em.close();

	}

	public void updateItem(Cart c) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(c);
		em.getTransaction().commit();
		em.close();

	}

	public Cart getItem(int id) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Cart> query = em.createQuery("Select c from Carts c WHERE Cart.ID = "+ id, Cart.class);
		Cart u = query.getSingleResult();
		em.close();
		return u;
	}

	public List<Cart> returnAllItems() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Cart> query = em.createQuery("Select c from Carts c", Cart.class);
		List<Cart> addAllUser = query.getResultList();
		em.close();
		return addAllUser;
	}
}
