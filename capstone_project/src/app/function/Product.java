package app.function;

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
	
	
	public Product() {
		createButton();
	}
	
	//Setters
	void setProductDetails(int id, String name, double price, int quantity){
		this.productId = id;
		this.productName = name;
		this.productPrice = price;
		this.productQuantity = quantity;
	}
	
	private void createButton() {
		button = new JButton(this.productName);
		button.addActionListener(this);
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
			System.out.println("Clicked "+ this.productName);
			Transaction t = new Transaction();
			String qtty = JOptionPane.showInputDialog("Enter Quantity");
			try {
				int qttyNum = Integer.parseInt(qtty);
				t.addProduct(this.productId, qttyNum);
			} catch(Exception expt) {
				expt.printStackTrace();
			}
			
		}
	}
}

