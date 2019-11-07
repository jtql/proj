package com.fdmgroup.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import com.fdmgroup.users.Customer;

public class CustomerJPAImpl {
	private EntityManagerFactory emf;
	// private static final Logger logger = LogManager.getLogger(PenJPAImpl.class);

	public CustomerJPAImpl(EntityManagerFactory emfPassed) {
		emf = emfPassed;
	}

	public void addItem(Customer user) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		System.out.println("adding customer");
		em.close();
		// logger to inform add item accomplished

	}

	public void removeItem(Customer user) {
		EntityManager em = emf.createEntityManager();
		Customer u = em.find(Customer.class, user.getId());
		em.getTransaction().begin();
		em.remove(u);
		em.getTransaction().commit();
		em.close();

	}

	public void updateItem(Customer user) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(user);
		em.getTransaction().commit();
		em.close();

	}

	public Customer getItem(int id) {
		EntityManager em = emf.createEntityManager();
		Customer u = em.find(Customer.class, id);
		em.close();
		return u;
	}

	public List<Customer> returnAllItems() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Customer> query = em.createQuery("Select u from User u", Customer.class);
		List<Customer> addAllUser = query.getResultList();
		em.close();
		return addAllUser;
	}
}
