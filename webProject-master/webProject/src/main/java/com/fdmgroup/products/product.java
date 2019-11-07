package com.fdmgroup.products;

public abstract class product {

	private int stock;
	private double price;

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public product() {
		super();
	
	}

}
