package com.fdmgroup.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import com.fdmgroup.payment.Order;

public class OrderJPA {
	private EntityManagerFactory emf;
	// private static final Logger logger = LogManager.getLogger(PenJPAImpl.class);

	public OrderJPA(EntityManagerFactory emfPassed) {
		emf = emfPassed;
	}

	public void addItem(Order order) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(order);
		em.getTransaction().commit();
		em.close();
		// logger to inform add item accomplished

	}

	public void removeItem(Order order) {
		EntityManager em = emf.createEntityManager();
		Order u = em.find(Order.class, order.getId());
		em.getTransaction().begin();
		em.remove(u);
		em.getTransaction().commit();
		em.close();

	}

	public void updateItem(Order order) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(order);
		em.getTransaction().commit();
		em.close();

	}

	public Order getItem(Order order) {
		EntityManager em = emf.createEntityManager();
		Order u = em.find(Order.class, order.getId());
		em.close();
		return u;
	}

	public List<Order> returnAllItems(int id) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Order> query = em.createQuery("Select o from Orders o Where cart_id = "+id, Order.class);
		List<Order> addAllOrder = query.getResultList();
		em.close();
		return addAllOrder;
	}
}
