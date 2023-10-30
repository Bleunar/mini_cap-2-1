package app.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import app.functions.Product;

public class Database extends Databased{
	
	public Database(String source){
		System.out.println(source);
	}
	
	public boolean authenticate_account(String username, String password) {
		boolean verified = auth_account2(username, password);
		
		if(verified) {
			return true;
		} else {
			return false;
		}
	}
	
	public String getEmployeeName(String username) {
		String name = super.getEmployeeName(username);
		return name;
	}
	
	
	public ResultSet getProducts() {
		return getAvailableProducts();
	}
	
	public HashMap<Integer, String> getCategories(){
		return getCategoriesDB();
	}
	
	
	public void updateProduct(int id, int quantity) {
		this.updateProductQtty(id, quantity);
	}
	
	public boolean isAdmin(String username) {
		if(this.isAdminAccount(username)) {
			return true;
		} else {
			return false;
		}
	}
	
	public ResultSet getProductsPerCategory(int category_id) {
		return getProdPerCateg(category_id);
	}
	
	public void close() {
		closeDB();
	}
	
	public ArrayList<Product> getProductsInCategory(int categ) {
		return getProductsInCateg(categ);
	}
}

//Abstract class to avoid object creation
abstract class Databased {
	private String url = "jdbc:mysql://localhost:3306/coffee37";
	private String usernameDB = "root";
	private String passwordDB = "";
	Connection conn;
	
	Databased(){
		startDB();
	}
	
	
	private boolean startDB(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, usernameDB, passwordDB);
			System.out.println("> Database Connection Success");
			return true;
		} catch (NullPointerException e) {
			System.out.println("Error, Database is offline");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//Authentication prone to sql inject 
	protected boolean auth_account(String username, String password) {
		String query = String.format("SELECT emp_username FROM employee where emp_username = '%S' and emp_password = '%s'", username, password);
		System.out.println(query);
		ResultSet res = db_query(query);
		try {
			if(res.next()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	protected boolean auth_account2(String username, String password) {
		String query = String.format("SELECT * FROM employee");
		ResultSet res = db_query(query);
		try {
			while(res.next()) {
				if(username.equals(res.getString("emp_username"))) {
					if(password.equals(res.getString("emp_password"))) {
						return true;
					}
				} 
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	protected String getEmployeeName(String username) {
		String query = "SELECT * FROM employee WHERE emp_username = '"+username+"'";
		
		try {
			ResultSet res = db_query(query);
			String full_name = null;
			int id = -1;
			
			while(res.next()) {
				id = res.getInt("emp_id");
				full_name = res.getString("emp_fullName");
				break;
			}
			return full_name;
			
		} catch (Exception e) {
			System.out.println("> Error, " + e);
			return null;
		}
	}
	
	protected ResultSet getAvailableProducts(){
		String query = "select * from products where prod_isAvailable;";
		ResultSet res = db_query(query);
		return res;
	}
	
	//general query
	
	protected ResultSet db_query(String query) {
		try {
			Statement stmnt = conn.createStatement();
			ResultSet res = stmnt.executeQuery(query);
			
			return res;
			
		} catch (NullPointerException e) {
			System.out.println("Database is offline");
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return null;
	}
	
	protected void closeDB() {
		try {
			conn.close();
			System.out.println("> Database Closed");
		} catch (SQLException e) {
			System.out.println("> Error, closing database");
		}
	}
	
	protected void updateProductQtty(int id, int quantity) {
		String query = "UPDATE products; SET product_quantity = '"+quantity+"' WHERE product_id = '"+id+"';";
		this.db_query(query);
	}
	
	protected boolean isAdminAccount(String username) {
		try {
			String query = "select * from employee where emp_username = '"+ username+"' and emp_isAdmin;";
			ResultSet res = db_query(query);
			if(res.next()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	protected HashMap<Integer, String> getCategoriesDB() {
		String query = "select * from product_category";
		ResultSet res = db_query(query);
		HashMap<Integer, String> retorn = new HashMap<Integer, String>();
		try {
			while(res.next()) {
				retorn.put(res.getInt("category_id"), res.getString("category_name"));
			}
			return retorn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected ResultSet getProdPerCateg(int category_id) {
		String query = "select * from products where category_id = " + category_id + ";";
		return db_query(query);
	}
	
	protected ArrayList<Product> getProductsInCateg(int category){
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
			return null;
		}
	}
}
