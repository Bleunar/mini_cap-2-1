package app.testProduct;

import java.util.HashMap;

public class Transaction{
	HashMap<Integer, Integer> cart = new HashMap<Integer, Integer>();
	private double totalPrice;
	private double received;
	private double change;
	
	Transaction(){
		
	}
	
	
	
	void addToCart(int product_id, int quantity) {
		if(cart.containsKey(product_id)) {
			int updatedQtty = cart.get(product_id) + quantity;
			cart.put(product_id, updatedQtty);
		} else {
			cart.put(product_id, quantity);
		}
	}
}