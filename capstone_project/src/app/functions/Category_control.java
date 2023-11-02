package app.functions;

import java.util.ArrayList;

import app.database.Database;

public class Category_control {
	Database database;
	Transaction transaction;
	
	ArrayList<Category> categories = new ArrayList<Category>();
	public int currentCategory = 0;
	
	public Category_control(Database database, Transaction transaction){
		this.database = database;
		this.transaction = transaction;
		setCategories();
	}
	
	void setCategories() {
		
		for(Category i: database.getAllCategories()) {
			i.setSource(database, transaction);
			categories.add(i);
		}
	}
	
	public ArrayList<Product> getCurrentCategoryProducts() {
		return categories.get(currentCategory%categories.size()).getProducts();
	}
	
	public String getCurrentCategoryName() {
		return categories.get(currentCategory).category_name;
	}
	
	public void categoryNext() {
		currentCategory++;;
		if(currentCategory==categories.size()) currentCategory = 0;
	}
	
	public void categoryPrevious() {
		if(currentCategory==0) currentCategory = categories.size();
		currentCategory--;
	}
}
