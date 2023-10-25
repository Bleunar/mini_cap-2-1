package app.testProduct;

import java.util.ArrayList;

public class Category {
	int category_id;
	
	ArrayList<Product> products = new ArrayList<Product>();
	Database database;
	Transaction transaction;
	
	Category(int category_id){
		this.category_id = category_id;

	}
	
	void setSomething(Database database, Transaction transaction) {
		this.database = database;
		this.transaction = transaction;
	}
	
	//Adds 
	void setProducts(){
		ArrayList<Product> retrieved = database.getProductsInCategory(category_id);
		if(retrieved.isEmpty()) {
			System.out.println("result is empty");
		}
		
		for(Product p: retrieved) {
			p.setTransactionSource(transaction);
			products.add(p);
		}
	}
	
	void testPrint() {
		for(Product p: products) {
			System.out.println("ID: "+ p.getProductId());
			System.out.println("Name: "+ p.getProductName());
			System.out.println("Price: "+ p.getProductPrice());
			System.out.println("------------------------");
		}
	}
	
	
}
