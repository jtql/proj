package com.fdmgroup.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import com.fdmgroup.payment.Cart;
import com.fdmgroup.products.Bag;

public class BagJPAImpl {
	private EntityManagerFactory emf;
	// private static final Logger logger = LogManager.getLogger(PenJPAImpl.class);

	public BagJPAImpl(EntityManagerFactory emfPassed) {
		emf = emfPassed;
	}

	public void addItem(Bag c) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		em.close();
		// logger to inform add item accomplished

	}

	public void removeItem(Bag c) {
		EntityManager em = emf.createEntityManager();
		Bag u = em.find(Bag.class, c.getId());
		em.getTransaction().begin();
		em.remove(u);
		em.getTransaction().commit();
		em.close();

	}
	
	public void removeItemById(int id) {
		EntityManager em = emf.createEntityManager();
		Bag u = em.find(Bag.class, id);
		em.getTransaction().begin();
		em.remove(u);
		em.getTransaction().commit();
		em.close();

	}

	public void addToCart(Bag bag, Cart cart) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Bag b = em.find(Bag.class, bag.getId());
		b.setCart(cart);
		em.getTransaction().commit();
		em.close();

	}
	
	public void removeFromCart(Bag bag, Cart cart) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Bag b = em.find(Bag.class, bag.getId());
		b.setCart(null);
		em.getTransaction().commit();
		em.close();

	}

	public Bag getItem(int id) {
		EntityManager em = emf.createEntityManager();
		Bag u = em.find(Bag.class, id);
		em.close();
		return u;
	}

	public List<Bag> returnAllItems(int id) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Bag> query = em.createQuery("Select b from Bags b Where cart_id = "+id, Bag.class);
		List<Bag> addAllBag = query.getResultList();
		em.close();
		return addAllBag;
	}
	
	public List<Bag> returnAllItemsByAdminId(int id) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Bag> query = em.createQuery("Select b from Bags b Where admin_id = "+id, Bag.class);
		List<Bag> addAllBag = query.getResultList();
		em.close();
		return addAllBag;
	}
}
