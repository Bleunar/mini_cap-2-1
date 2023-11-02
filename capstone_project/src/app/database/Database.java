package app.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.ArrayList;

import app.misc.PopUp;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import app.functions.Category;
import app.functions.Product;

public class Database extends Databased{
	
	
	public Database(){
		//Nu uhh
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
	
	
	public boolean isAdmin(String username) {
		if(this.isAdminAccount(username)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void close() {
		closeDB();
	}
	
	public ArrayList<Product> getProductsInCategory(int categ) {
		return getProductsInCateg(categ);
	}
	
	public ArrayList<Category> getAllCategories(){
		return getAllCategoriesDB();
	}
}



//Abstract class to avoid object creation
abstract class Databased {
	PopUp pop = new PopUp();
	private String url = "jdbc:mysql://localhost:3306/coffee37";
	private String usernameDB = "root";
	private String passwordDB = "";
	private Connection conn;
	
	Databased(){
		System.out.println("> Attempting to connect...");
		startDB();
	}
	
	
	private boolean startDB(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, usernameDB, passwordDB);
			System.out.println("> Database Connection Success");
			return true;
		} catch (NullPointerException e) {
			System.out.println("Error, database");
			pop.message("Database startup failed");
			System.exit(0);
		} catch (CommunicationsException e){
			System.out.println("> Error, database is offline");
			pop.message("Database is offline");
			System.exit(0);
		} catch(SQLSyntaxErrorException e) {
			System.out.println("> Misconfigured database");
			pop.message("Database startup failed, misconfigured");
			System.exit(0);
		}catch (Exception e) {
			pop.message("Database startup failed");
			e.printStackTrace();
			System.exit(0);
		}
		return false;
	}
	
	//Authentication prone to sequel inject
	//NOT USED
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
			System.out.println("> err, auth_account  |  " +  e.getLocalizedMessage());
			return false;
		}
	}
	
	// Authenticate account 2 (no sql inject)
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
			System.out.println("> err, auth_account2  |  " +  e.getLocalizedMessage());
			return false;
		}
	}
	
	// Retrieves an employee's name with its user name
	protected String getEmployeeName(String username) {
		String query = "SELECT emp_fullName FROM employee WHERE emp_username = '"+username+"'";
		
		try {
			ResultSet res = db_query(query);
			String full_name = null;
			if(res.next()){
				return res.getString("emp_fullName");
			} else {
				return null;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("> Error, getEmployeeName | " + e);
			return null;
		}
	}
	
	// General query
	protected ResultSet db_query(String query) {
		try {
			Statement stmnt = conn.createStatement();
			ResultSet res = stmnt.executeQuery(query);
			return res;
			
		} catch (Exception e) {
			System.out.println("> err, db_query  |  " +  e.getLocalizedMessage());
		}
		 return null;
	}
	
	// Close the database connection
	protected void closeDB() {
		try {
			conn.close();
			System.out.println("> Database Closed ");
		} catch (SQLException e) {
			System.out.println("> err, closeDB  |  " +  e.getLocalizedMessage());
		}
	}
	
	
	// Verifies if an account is admin
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
			System.out.println("> err, isAdminAccount  |  " +  e.getLocalizedMessage());
			return false;
		}
	}
	
	// Get products for a specific category
	protected ArrayList<Product> getProductsInCateg(int category){
		ArrayList<Product> products = new ArrayList<Product>();
		try {
			String query = "SELECT * FROM products WHERE category_id = '"+category+"' AND prod_isAvailable";
			Statement stmnt = conn.createStatement();
			ResultSet result = stmnt.executeQuery(query);
			
			while(result.next()) {
				products.add(new Product(result.getInt("prod_id"), result.getString("prod_name"), result.getDouble("prod_price")));
			}
			return products;
		} catch (Exception e) {
			System.out.println("> err, getProductsInCateg  |  " +  e.getLocalizedMessage());
			return null;
		}
	}
	
	// Get all categories from the database
	protected ArrayList<Category> getAllCategoriesDB(){
		ArrayList<Category> retorn = new ArrayList<Category>();
		
		try {
			String query = "SELECT * FROM product_category";
			Statement stmnt = conn.createStatement();
			ResultSet result = stmnt.executeQuery(query);
			
			while(result.next()) {
				retorn.add(new Category(result.getInt("category_id"),result.getString("category_name")));
			}
		} catch (Exception e) {
			System.out.println("> err, getAllCategoriesDB  |  " +  e.getLocalizedMessage());
		}
		return retorn;
	}
}
