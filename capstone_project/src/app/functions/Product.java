package app.functions;

import java.awt.Dimension;
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
		button.setPreferredSize(new Dimension(121,100));
		button.addActionListener(this);
	}
	
	
	//Setters
	public void setTransactionSource(Transaction t) {
		this.transaction = t;
	}
	
	
	//Getters
	public int getId() {
		return this.productId;
	}
	public String getName() {
		return this.productName;
	}
	public double getPrice() {
		return this.productPrice;
	}
	public int getProductQuantity() {
		return this.productQuantity;
	}
	
	public JButton getButton() {
		return button;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button) {
			System.out.println("ButtonClicked");
			String qtty = JOptionPane.showInputDialog("Enter Quantity");
			if(qtty.matches(".*[a-zA-Z]+.*") || qtty.isBlank()) {
				System.out.println("> error, invalid input");
			} else {
				transaction.addToCart(this, Integer.parseInt(qtty));
			}
			
		}
		
	}
}

