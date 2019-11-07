package com.fdmgroup.dao;

import java.sql.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.fdmgroup.users.Admin;

public class LoginDao {
	public static boolean validate(String name, String pass) {
		boolean status = false;
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("CustomerDB");
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			TypedQuery<Admin> query = em.createQuery(
					"Select a from Admin a WHERE a.username =:name and a.password =:pass", Admin.class);
			query.setParameter("name", name);
			query.setParameter("pass", pass);
			Admin admin = query.getSingleResult();
			System.out.println(admin);
			if (admin != null) {
				status = true;
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
	public static boolean validateId(String name) {
		boolean status = false;
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("CustomerDB");
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			TypedQuery<Admin> query = em.createQuery(
					"Select a from Admin a WHERE a.username =:name", Admin.class);
			query.setParameter("name", name);
			System.out.println(query.getSingleResult());
			Admin admin = query.getSingleResult();
			System.out.println(admin);
			if (admin != null) {
				status = true;
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
	
}
