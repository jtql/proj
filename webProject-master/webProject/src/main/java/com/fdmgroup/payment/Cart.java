package com.fdmgroup.payment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Persistence;
import javax.persistence.Table;

import com.fdmgroup.jpa.BagJPAImpl;
import com.fdmgroup.jpa.OrderJPA;
import com.fdmgroup.products.Bag;
import com.fdmgroup.users.Customer;

@Entity
@Table(name = "Carts")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private double total;

	@OneToOne(mappedBy = "cart")
	private Customer customer;

	@OneToMany(mappedBy = "cart")
	List<Bag> bags;

	@OneToMany(mappedBy = "cart")
	List<Order> orders;

	public List<Order> getOrders() {
		return new OrderJPA(Persistence.createEntityManagerFactory("Orders")).returnAllItems(id);
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<Bag> getBags() {
		return new BagJPAImpl(Persistence.createEntityManagerFactory("Bags")).returnAllItems(id);
	}

	public void setBags(List<Bag> bags) {
		this.bags = bags;
	}

	public double getTotal() {
		List<Bag> allBags = new ArrayList<>();
		allBags = this.getBags();
		for (Bag b : allBags) {
			total += b.getPrice();
		}
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getId() {
		return id;
	}

	public Cart() {
		super();
	}

	public void addItem(Bag bag) {
		bags.add(bag);

	}
	
	public void addToCart(Bag bag) {
		new BagJPAImpl(Persistence.createEntityManagerFactory("Bags")).addToCart(bag, this);
	}
	
	public void removeFromCart(Bag bag) {
		new BagJPAImpl(Persistence.createEntityManagerFactory("Bags")).removeFromCart(bag, this);
	}

	public void checkOut() {
		Order order = new Order();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		order.setDate(formatter.format(date));
		for (Bag b : bags) {
			total += b.getPrice();
		}
		order.setUsername(customer.getUsername());
		order.setTotal(this.getTotal());
		order.toString();
	}

}
