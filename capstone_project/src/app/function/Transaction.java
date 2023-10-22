package app.function;

import java.util.HashMap;

// Called for every transaction of customers
public class Transaction {
	ProductFunc prod = new ProductFunc();
	double totalPrice;
	double receivedCash;
	double change;
	
	
	HashMap<Integer, Integer> cartContents = new HashMap<Integer, Integer>();
	//   Product Id : Quantity
	
	Transaction(){
		
	}
	
	void addToCart(int id, int qtty) {
		cartContents.put(id, qtty);
		
		totalPrice = prod.getProductPrice(id) * qtty;
		
		System.out.println("Product Added");
	}
	
	void modifyProductQuantity(int id, int new_qtty) {
		cartContents.replace(id, null, new_qtty);
	}
	
	
	void checkout() {
		//Update product quantities on the database
		// clear the contents of cart, total price
		
		// Maybe add the transaction logs to the database
	}
	
	void getCartContents(){
		
	}
	
	void closeObj() {
		
	}
}
