package app.function;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import app.database.Database;

//Temporarily stores the products from the database
public class ProductTemp{
	Database db = new Database();
	HashMap<Integer, Product> productMap = new HashMap<Integer, Product>(); 
	//   Product ID : Product Object
	
	public ProductTemp(){
		setupProducts();
	}
	
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
	
	
	public void updateDatabase(int[] prodUpdated){
		for(int i: productMap.keySet()) {
			Product prod = productMap.get(i);
			
			db.updateProduct(i, prod.getProductQuantity());
		}
		System.out.println("> Database Updated");
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
	
	public String getProdName(int id) {
		Product prod = productMap.get(id);
		return prod.getProductName();		
	}
	
	public ArrayList<Integer> getProdIds() {
		ArrayList<Integer> keys = new ArrayList<Integer>();
		for(int i: productMap.keySet()) {
			keys.add(i);
		}
		return keys;
	}
	
	public double getProductPrice(int id) {
		return productMap.get(id).getProductPrice();
	}
}

