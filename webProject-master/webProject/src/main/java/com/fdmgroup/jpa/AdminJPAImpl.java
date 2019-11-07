package com.fdmgroup.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.fdmgroup.products.Bag;
import com.fdmgroup.users.Admin;

public class AdminJPAImpl {
	private EntityManagerFactory emf;
	// private static final Logger logger = LogManager.getLogger(PenJPAImpl.class);

	public AdminJPAImpl(EntityManagerFactory emfPassed) {
		emf = emfPassed;
	}

	public void addItem(Admin user) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
//		Bag bag = new Bag(user.getId());
//		new BagJPAImpl(Persistence.createEntityManagerFactory("Bags")).addItem(bag);
		// logger to inform add item accomplished

	}

	public void removeItem(Admin user) {
		EntityManager em = emf.createEntityManager();
		Admin u = em.find(Admin.class, user.getId());
		em.getTransaction().begin();
		em.remove(u);
		em.getTransaction().commit();
		em.close();

	}

	public void updateItem(Admin user) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(user);
		em.getTransaction().commit();
		em.close();

	}

	public Admin getItem(int id) {
		EntityManager em = emf.createEntityManager();
		Admin u = em.find(Admin.class, id);
		em.close();
		return u;
	}

	public List<Admin> returnAllItems() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Admin> query = em.createQuery("Select a from Admin a", Admin.class);
		List<Admin> addAllUser = query.getResultList();
		em.close();
		return addAllUser;
	}
}
