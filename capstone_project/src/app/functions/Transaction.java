package app.functions;

import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import app.database.Database;

public class Transaction extends receiptPreview{
	HashMap<Product, Integer> cart = new HashMap<Product, Integer>();
	DefaultTableModel model;
	private double totalPrice;
	private double received;
	private double change;
	
	private Database database;
	
	
	public Transaction(Database db){
		this.database = db;
		this.model = model;
		
	}
	
	public void setModel(DefaultTableModel model) {
		this.model = model;
	}
	
	void addToCart(Product product, int quantity) {
		if(cart.containsKey(product)) {
			int updatedQtty = cart.get(product) + quantity;
			cart.put(product, updatedQtty);
			System.out.println("prod exists");
		} else {
			cart.put(product, quantity);
			this.addToReceipt(model, product, quantity);
			System.out.println("prod added");
		}
		System.out.println(cart.get(product));
	}
	
	public JPanel getReceiptPane() {
		return new receiptPreview().getReceipt();
	}
	

}

class receiptPreview{
	int counter = 0;
	
	String[] columnNames = {
			"Quantity",
            "Product Name",
            "Price",
            "Total"
            };
	
	Object[][] data = {
		{1,"2", 3, 4},
		{1,"2", 3, 4},
		{1,"2", 3, 4}
	};
	
	JTable table;
	JScrollPane sp;
	JPanel container;
	
//	receiptPreview(){
//				
//				
//		table = new JTable(data, columnNames);
//		
//		sp = new JScrollPane(table);
//		
//		container.add(sp);
//	}
	
	JPanel getReceipt() {
		container.setVisible(true);
		return container;
	}
	
	public void addToReceipt(DefaultTableModel model, Product product, int quantity) {
		model.addRow(new Object[]{product.getId(), product.getName(), product.getPrice(), quantity});

	}
	
	public void removeFromReceipt(DefaultTableModel model, Product product) {
//		model.removeRow();
	}
	
	
}