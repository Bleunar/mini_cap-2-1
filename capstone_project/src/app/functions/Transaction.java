package app.functions;

import java.text.DecimalFormat;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import app.database.Database;
import app.frame.Dashboarded;
import app.misc.PopUp;

public class Transaction extends receiptPreview{
	
	HashMap<Product, Integer> cart = new HashMap<Product, Integer>();
	
	PopUp popup = new PopUp();
	DefaultTableModel model;
	private double totalPrice;
	private double received;
	private double change;
	
	// format for double
	DecimalFormat df = new DecimalFormat("0.00");
	
	private Database database;
	Dashboarded parent; //parent
	
	public Transaction(Database db, Dashboarded parent){
		this.database = db;
		this.parent = parent;
	}
	
	public void setModel(DefaultTableModel model) {
		this.model = model;
	}
	
	public void removeFromCart(Object id) {
		int id2 = Integer.parseInt(String.valueOf(id));
		Product p = this.findProduct(id2);
		this.totalPrice -= p.getPrice() * cart.get(p);
		cart.remove(p);

		popup.message("Product "+ p.getName() +" deleted");
		
		parent.updateMoney();
	}
	
	void addToCart(Product product, int quantity) {
		if(cart.containsKey(product)) {
			
			//Subtracts the total price of previous quantity
			this.totalPrice -= product.getPrice() * cart.get(product); 
			
			int updatedQtty = cart.get(product) + quantity;
			cart.put(product, updatedQtty);
			updateRow(model, product, updatedQtty);
			
			//add to total price of the quantity
			this.totalPrice += product.getPrice() * updatedQtty;
			
			parent.updateMoney();
 		} else {
			cart.put(product, quantity);
			this.addToReceipt(model, product, quantity);
			
			this.totalPrice += product.getPrice() * quantity;
			
			parent.updateMoney();
		}
		printCart();
	}
	
	
	public String getCurrentTotal() {
		return df.format(this.totalPrice);
	}
	
	public String getCurrentReceived() {
		return df.format(this.received);
	}
	
	public String getCurrentChange() {
		return df.format(this.change);
	}
	
	
	// find product on cart from the table using its id
	public Product findProduct(int id) {
		for(Product p: cart.keySet()) {
			System.out.println(id);
			if(p.getId() == id) {
				return p;
			}
		}
		return null;
	}
	
	
	public void updateProductQuantity(Object id) {
		int id2 = Integer.parseInt(String.valueOf(id));
		Product prod = this.findProduct(id2);
		String qtty = JOptionPane.showInputDialog("Enter updated quantity for " + cart.get(this.findProduct(id2).getName()));
		
		if(qtty.matches(".*[a-zA-Z]+.*") || qtty.isBlank() || qtty.isEmpty()) {
			System.out.println("> Error, input is invalid or empty");
		} else {
			//Subtracts the total price of previous quantity
			this.totalPrice -= prod.getPrice() * cart.get(prod); 
			
			//updates the receipt table
			int qttyx = this.updateRow(model, prod, Integer.parseInt(qtty));
			
			//update the content of the cart
			cart.put(prod, qttyx);
			
			//add to total price of the quantity to the receipt
			this.totalPrice += this.findProduct(id2).getPrice() * qttyx;
			parent.updateMoney();
		}
	}
	
	public void clearCart() {
		int response = popup.popUpPrompt(null, "Confirm clearing the cart?");
		if(response == 0) {
			cart.clear();
			this.totalPrice = 0;
			this.received = 0;
			this.change = 0;
			
			model.setRowCount(0);
		} else {
		}
	}
	
	public void addOne(Object id) {
		int id_parsed = Integer.parseInt(String.valueOf(id));
		Product prod = this.findProduct(id_parsed); 	// finds the product from cart based on its id
		
		int currentQtty = cart.get(prod); // current qtty
		currentQtty++; // add 1
		cart.put(prod, currentQtty); // update the quantity of product on cart
		
		this.updateRow(model, prod, currentQtty);
		this.totalPrice += prod.getPrice(); 
		parent.updateMoney();
	}
	
	public void lessOne(Object id, int row) {
		int id_parsed = Integer.parseInt(String.valueOf(id));
		Product prod = this.findProduct(id_parsed);
		
		int currentQtty = cart.get(prod);
		if(currentQtty == 1) {
			removeFromCart(id);
			model.removeRow(row);
		} else {
			currentQtty--; //less 1
			cart.put(prod, currentQtty);
			this.totalPrice -= prod.getPrice(); 
		}
		
		this.updateRow(model, prod, currentQtty);
		parent.updateMoney();
	}
	
	void printCart() {
		System.out.println("=============================");
		for(Product p: cart.keySet()) {
			System.out.println("Name: " + p.getName());
			System.out.println("Price: " + p.getPrice());
			System.out.println("Qtty: " + cart.get(p));
			System.out.println("--------------------");
		}
		System.out.println("=============================");
	}
}


// Displays the content of the cart in the dashboard with jtable
class receiptPreview{
	DecimalFormat df = new DecimalFormat("0.00");
	int counter = 0;
	
	JTable table;
	JScrollPane sp;
	JPanel container;
	
	JPanel getReceipt() {
		container.setVisible(true);
		return container;
	}
	
	public void addToReceipt(DefaultTableModel model, Product product, int quantity) {
		model.addRow(new Object[]{product.getId(), product.getName(),"₱" +  df.format(product.getPrice()), quantity});
	}
	
	public void removeFromReceipt(DefaultTableModel model, Product product) {
		for(int i = 0; i < model.getRowCount(); i++) {
			if(model.getValueAt(i, 0).toString().equals("" + product.getId())) {
				model.removeRow(i);
			}
		}
	}
	
	public int updateRow(DefaultTableModel table, Product product, int quantity) {
		for(int i = 0; i < table.getRowCount(); i++) {
			if(table.getValueAt(i, 0).toString().equals("" + product.getId())) {
				table.setValueAt(quantity, i, 3);
				return quantity;
			}
		}
		return 0;
	}
}