package app.function;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import app.database.Database;

//Temporarily stores the products from the database
public class ProductMain{
	Database db = new Database();
	
	//Every Category
	HashMap<Integer, Product> productMap = new HashMap<Integer, Product>(); 
	//   Product ID : Product Object
	
	// List of categories available (category_id and category_name)
	HashMap<Integer, String> categoryList = db.getCategories();
	
	// List of categories that stores products according to its category
	LinkedList<Category> categorized = new LinkedList<Category>();
	
	
	public ProductMain(){
//		setupProducts();
		setupCategories();
	}
	
	
	LinkedList<Category> getCategorizedPanes(){
		return categorized;
	}
	
	void setupCategories() {
		for(int id: categoryList.keySet()) {
			Category categ = new Category(id, categoryList.get(id));
			
			ResultSet res = db.getProductsPerCategory(id);
			
			try {
				while(res.next()) {
					Product prod = new Product();
					int prod_id = res.getInt("prod_id");
					String prod_name = res.getString("prod_name");
					double prod_price = res.getDouble("prod_price");
					int prod_quantity = res.getInt("prod_quantity");
					prod.setProductDetails(prod_id, prod_name, prod_price, prod_quantity);
					
					categ.addProduct(prod);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			categorized.add(categ);
		}
	}
	
	// Gathers products from the database and puts it in the program
	public void setupProducts(){
		ResultSet rs = db.getProducts(); // Gets the products from the database
		
		//loops through the result set and adds every result to the products object
		try {
			while(rs.next()) {
				Product prod = new Product();
				
				int id = rs.getInt("prod_id");
				String name = rs.getString("prod_name");
				double price = rs.getDouble("prod_price");
				int quantity = rs.getInt("prod_quantity");
				
				prod.setProductDetails(id, name, price, quantity);
				
				productMap.put(id, prod);
			}
			System.out.println("> Successfuly retreived products from database");
		} catch (SQLException e) {
			System.err.println("Error: ProductTemp.setupProducts");
			e.printStackTrace();
		}
	}
	
	
//	public void updateDatabase(int[] prodUpdated){
//		for(int i: productMap.keySet()) {
//			Product prod = productMap.get(i);
//			db.updateProduct(i, prod.getProductQuantity());
//		}
//		System.out.println("> Database Updated");
//	}
	

	
	public String getProdName(int id) {
		Product prod = productMap.get(id);
		return prod.getProductName();		
	}
	
	public double getPrice(int id) {
		return productMap.get(id).getProductPrice();
	}
	
	public ArrayList<Integer> getAllProductIDs() {
		ArrayList<Integer> keys = new ArrayList<Integer>();
		for(int i: productMap.keySet()) {
			keys.add(i);
		}
		return keys;
	}
	
	
	
	
	
	
	public void testPrint() {
		for(int i: productMap.keySet()) {
			Product prod = productMap.get(i);
			
			System.out.println("Key: " + i);
			System.out.println("Name: " + prod.getProductName());
			System.out.println("Qtty: " + prod.getProductQuantity());
			System.out.println("Price: $" + prod.getProductPrice());
			System.out.println("-----------------------------------------");
		}
	}
	
	
}

