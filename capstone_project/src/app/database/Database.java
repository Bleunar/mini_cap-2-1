package app.database;

import java.sql.ResultSet;

public class Database extends Databased{
	public Database(){
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
		return this.getAvailableProducts();
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
	
	public void close() {
		closeDB();
	}

}
