package app.functions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import app.database.Database;

public class Category {
	int category_id;
	
	JPanel categoryPane = new JPanel();
	
	ArrayList<Product> products = new ArrayList<Product>();
	ArrayList<Integer> productsID = new ArrayList<Integer>();
	Database database;
	Transaction transaction;
	
	public Category(int category_id, Database database, Transaction transaction){
		this.category_id = category_id;
		System.out.println("category created");
		
		this.database = database;
		this.transaction = transaction;
		
		setProducts();

	}
	
//	public void setSomething(Database database, Transaction transaction) {
//		this.database = database;
//		this.transaction = transaction;
//	}
	
	//Adds 
	void setProducts(){
		ArrayList<Product> retrieved = database.getProductsInCategory(category_id);
		if(retrieved.isEmpty()) {
			System.out.println("result is empty");
		}
		
		for(Product p: retrieved) {
			p.setTransactionSource(transaction);
			productsID.add(p.getId());
			products.add(p);
		}
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
	
	
	
//	void setProductPane() {
//		for(Product p: products) {
//			categoryPane.add(new ProductButton(p, transaction));
//		}
//	}
	
}


//class ProductButton extends JButton implements ActionListener{
//	Transaction transaction;
//	Product product;
//	
//	public ProductButton(Product prod, Transaction transaction) {
//		this.setText(prod.getProductName());
//		this.addActionListener(this);
//		this.setPreferredSize(new Dimension(121,100));
//		
//		this.transaction = transaction;
//		this.product = prod;
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		if(e.getSource()==this) {
//			System.out.println("ButtonClicked");
//			int qtty = Integer.parseInt(JOptionPane.showInputDialog("Enter Quantity"));
//			transaction.addToCart(product.getProductId(), qtty);
//		}
//		
//	}
//}
