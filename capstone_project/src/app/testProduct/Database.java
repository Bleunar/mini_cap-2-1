package app.testProduct;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {
	private String url = "jdbc:mysql://localhost:3306/coffee37";
	private String usernameDB = "root";
	private String passwordDB = "";
	Connection conn;
	
	
	Database(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, usernameDB, passwordDB);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	ArrayList<Product> getProductsInCategory(int category){
		ArrayList<Product> products = new ArrayList<Product>();
		try {
			String query = "SELECT * FROM products WHERE category_id = '"+category+"' AND prod_isAvailable";
			System.out.println(query);
			Statement stmnt = conn.createStatement();
			ResultSet result = stmnt.executeQuery(query);
			
			while(result.next()) {
				products.add(new Product(result.getInt("prod_id"), result.getString("prod_name"), result.getDouble("prod_price"), result.getInt("prod_quantity")));
				System.out.println("> Product added - " + result.getString("prod_name"));
			}
			return products;
		} catch (Exception e) {
			e.printStackTrace();
			return products;
		}
	}
}
