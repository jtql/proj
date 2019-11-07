package com.fdmgroup.products;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fdmgroup.payment.Cart;
import com.fdmgroup.users.Admin;

@Entity
@Table(name = "Bags")
public class Bag extends product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private double price;
	private int stock;
	private String description;

	@ManyToOne
	@JoinColumn(name = "cart_id")
	private Cart cart;

	@ManyToOne
	@JoinColumn(name = "admin_id")
	private Admin admin;

	public Bag(double price, int stock, String description) {
		super();
		this.price = price;
		this.stock = stock;
		this.description = description;
	}

	public Bag(int id) {
		super();
		this.id = id;
	}

	public Bag() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin a) {
		this.admin = a;

	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Bag [price=" + price + ", stock=" + stock + ", description=" + description + "]";
	}

	public int getId() {
		return id;
	}

}
