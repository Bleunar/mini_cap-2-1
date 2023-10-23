package app.function;

import java.util.HashMap;

/*
 * 
 * Called for every transaction of customers
 * The transaction details(date, total sale, list of products purchased and its quantity) is saved on a database
 * Cleared after every transaction/ after the customer has paid/ after the transaction has concluded
 * 
 * Stores the products that a customer wishes to purchase in a cart
 * The cart stores the id of the product and its quantity
 * 
 * C - Ability to add products to the cart
 * R - Read every products in the cart
 * U - Update the quantity of a product in a cart
 * D - Delete products from the cart
 * 
 * Ability to apply discounts, promos for the customers sale
 * 
 */

public class Transaction {
	ProductMain prod = new ProductMain();
	HashMap<Integer, Integer> cart = new HashMap<Integer, Integer>();
	// Stores the product id and the quantity
	
	double total_sale;
	double received;
	double change;
	
	public Transaction(){
		
	}
	
	public void addProduct(int id, int quantity){
		//if product already exists on the cart, it adds its quantity to the recent quantity
		if(cart.containsKey(id)) {
			int temp = cart.get(id);
			cart.put(id, temp + quantity);
		} else { // it only adds the id and quantity to the cart
			cart.put(id, quantity);
		}
	}
	
	public int getProductQtty(int id){
		return cart.get(id);
	}
	
	public int[] getProductIDs() {
		int[] products = new int[cart.size()];
		int counter = 0;
		for(int id: cart.keySet()) {
			products[counter] = id;
			counter++;
		}
		return products;
	}
	
	
	// Received bill from the customer
	public boolean setCustomerPayment(double payment) {
		if(payment > total_sale) {
			received = payment;
			return true;
		} else if (payment < total_sale) {
			return false;
		} else {
			return false;
		}
	}
	
	
	// Display to the dashboard
	public double getTotalSale() {
		return total_sale;
	}
	
	public double getCustomerPayment() {
		return received;
	}
	
	public double getChange() {
		return received - total_sale;
	}
	
	
	public void removeProduct(int id) {
		cart.remove(id);
	}
	
	public void changeProductQtty(int id,int newQtty) {
		cart.remove(id);
		cart.put(id, newQtty);
	}
	
	public void clearCart() {
		cart.clear();
	}
	
	
	void computeTotalSale() {
		for(int id: cart.keySet()) {
			double price = prod.getPrice(id);
			total_sale = total_sale +(cart.get(id) * price);
		}
	}
	
	
	

}
