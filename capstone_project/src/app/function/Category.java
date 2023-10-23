package app.function;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.Stack;

import javax.swing.JPanel;

public class Category {
	
	LinkedList<Product> products = new LinkedList<Product>();
	private int category_id;
	private String category_name;
	
	public Category(int category_id, String category_name) {
		this.category_id = category_id;
		this.category_name = category_name;
	}
	
	
	void addProduct(Product prod){
		products.add(prod);
	}
	
	public String getCategoryName() {
		return this.category_name;
	}
	
	public JPanel getCategoryPane(Transaction trans) {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		for(Product prod: products) {
			panel.add(new ProductButton(prod, trans));
		}
		return panel;
	}
}
