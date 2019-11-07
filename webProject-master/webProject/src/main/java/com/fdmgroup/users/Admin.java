package com.fdmgroup.users;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Table;

import com.fdmgroup.jpa.AdminJPAImpl;
import com.fdmgroup.jpa.BagJPAImpl;
import com.fdmgroup.products.Bag;

@Entity
@Table(name = "Admin")
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String username;
	private String password;
	private String name;

	@OneToMany(mappedBy = "admin", cascade = CascadeType.MERGE)
	List<Bag> bags = new ArrayList<>();

	public List<Bag> getBags() {
		return new BagJPAImpl(Persistence.createEntityManagerFactory("Bags")).returnAllItemsByAdminId(id);
	}

	public void addBag(Bag bag) {
		if (bag != null) {
			if (bags.contains(bag)) {
				return;
			}
			Bag b = bag;
			b.setAdmin(this);
			bags.add(b);
			new BagJPAImpl(Persistence.createEntityManagerFactory("Bags")).addItem(b);
		}
	}

	public void removeBag(Bag bag) {
		if (!bags.contains(bag)) {
			return;
		}
		bags.remove(bag);
		bag.setAdmin(null);
		new BagJPAImpl(Persistence.createEntityManagerFactory("Bags")).removeItem(bag);
	}

	public void removeBagById(int bagId) {
		new BagJPAImpl(Persistence.createEntityManagerFactory("Bags")).removeItemById(bagId);
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Admin() {
		super();
	}

	public void register(Admin a) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Admin");
		AdminJPAImpl aJPA = new AdminJPAImpl(emf);
		aJPA.addItem(a);
	}

	public Admin login() {
		Admin tempAdmin = null;
		List<Admin> temp = new ArrayList<>();
		temp = new AdminJPAImpl(Persistence.createEntityManagerFactory("Admin")).returnAllItems();
		for (Admin admin : temp) {
			if (admin.getUsername().equals(this.getUsername())) {
				tempAdmin = admin;
			}
		}
		return tempAdmin;
	}

	@Override
	public String toString() {
		return "Admin [username=" + username + ", password=" + password + ", name=" + name + "]";
	}

}
