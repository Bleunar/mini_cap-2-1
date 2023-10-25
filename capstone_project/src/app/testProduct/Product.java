package app.testProduct;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Product implements ActionListener{
	private String productName;
	private int productId;
	private double productPrice;
	private int productQuantity;
	
	JFrame frame;
	
	private JButton button;
	
	private Transaction transaction;
	
	
	public Product(int id, String name, double price, int quantity) {
		this.productId = id;
		this.productName = name;
		this.productPrice = price;
		this.productQuantity = quantity;
		
		//Creates the button
		button = new JButton(this.productName);
		button.addActionListener(this);
	}
	
	
	//Setters
	public void setTransactionSource(Transaction t) {
		this.transaction = t;
	}
	
	
	//Getters
	int getProductId() {
		return this.productId;
	}
	String getProductName() {
		return this.productName;
	}
	double getProductPrice() {
		return this.productPrice;
	}
	int getProductQuantity() {
		return this.productQuantity;
	}
	
	JButton getButton() {
		return button;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button) {
			//Takes the quantity of the product
			
			//transaction.addToCart(id, )
		}
		
	}
}

