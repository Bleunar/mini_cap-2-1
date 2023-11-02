package app.functions;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Product implements ActionListener{
	
	//Product properties
	private String productName;
	private int productId;
	private double productPrice;
	
	// Button component for dashboard
	private JButton button;

	private Transaction transaction;
	
	
	public Product(int id, String name, double price) {
		this.productId = id;
		this.productName = name;
		this.productPrice = price;
		
		//Creates the button
		button = new JButton(this.productName);
		button.setPreferredSize(new Dimension(184,100));
		button.setFont(new Font("Arial", Font.BOLD, 14));
		button.addActionListener(this);
	}
	
	//Sets the source for transaction
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
	
	public JButton getButton() {
		return button;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(e.getSource()==button) {
				String qtty = JOptionPane.showInputDialog("Enter Quantity");
				if(qtty.matches(".*[a-zA-Z]+.*") || qtty.isBlank() || qtty.isEmpty()) {
					System.out.println("> error, input is invalid or empty");
				} else {
					transaction.addToCart(this, Integer.parseInt(qtty));
				}
			}
		} catch (NullPointerException e2) {
			//do nothing haha
		}
	}
}

