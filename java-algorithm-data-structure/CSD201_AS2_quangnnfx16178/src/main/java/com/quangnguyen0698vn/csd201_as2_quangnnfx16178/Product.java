
package com.quangnguyen0698vn.csd201_as2_quangnnfx16178;

/**
 * @author quang
 */

public class Product implements Comparable<Product> {

	private String bcode;
	private String title;
	private int quantity;
	private double price;
	
	/**
	 * 
	 * Default constructor
	 * 
	 */

	public Product() {

	}

	/**
	 * 
	 * Constructor method to initialize a product
	 * @param bcode Product's bar code
	 * @param title Product's title
	 * @param quantity Product's quantity
	 * @param price Product's price
	 * 
	 */

	public Product(String bcode, String title, Integer quantity, double price) {
		this.bcode = bcode;
		this.title = title;
		this.quantity = quantity;
		this.price = price;
	}

	/**
	 * 
	 * Convert this product to String for printing
	 * 
	 */

	@Override

	public String toString() {
//		15+30+15+15+5-2
		return "|" + MyLogger.paddingLeft(this.bcode, 15) + "|" + MyLogger.paddingLeft(title, 30) + "|" 
				+ MyLogger.paddingRight(Integer.toString(quantity), 15) + "|" + MyLogger.paddingRight(Double.toString(
						price), 15) + "|";
//		return this.bcode + " | " + this.title + " | " + this.quantity + " | " + this.price;
	}

	/*
	Overide compareTo to compare product by ID
	*/
	@Override
	public int compareTo(Product t) {
		return this.getBcode().compareTo(t.getBcode());
	}

	public String getBcode() {
		return bcode;
	}

	public void setBcode(String bcode) {
		this.bcode = bcode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	

}