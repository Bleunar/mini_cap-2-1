package app.functions;

import java.util.ArrayList;

import javax.swing.JPanel;

import app.database.Database;

public class Category {
	//Category properties
	int category_id;
	String category_name;
	JPanel categoryPane = new JPanel();
	
	
	ArrayList<Product> products = new ArrayList<Product>();		//Stores the products in a category
	
	// Allows the category to communicate with the database and transaction
	Database database;
	Transaction transaction;
	
	public Category(int category_id, String category_name){
		this.category_id = category_id;
		this.category_name = category_name;
	}
	
	// Sets up the connection to the database and transaction objects
	public void setSource(Database database, Transaction transaction) {
		this.database = database;
		this.transaction = transaction;
		setProducts(); 
	}
	
	//Retrieves all the products related to the category
	void setProducts(){
		ArrayList<Product> retrieved = database.getProductsInCategory(category_id);
		
		if(retrieved.isEmpty()) System.out.println("> Category " + this.category_name + "  is empty");
		else {
			for(Product p: retrieved) {
				p.setTransactionSource(transaction);
				products.add(p);
			}
		}
		retrieved.clear();
		
	}
	
	public ArrayList<Product> getProducts(){
		return products;
	}
	
	void testPrint() {
		for(Product p: products) {
			System.out.println("ID: "+ p.getId());
			System.out.println("Name: "+ p.getName());
			System.out.println("Price: "+ p.getPrice());
			System.out.println("------------------------");
		}
	}
}