package app.function;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class ProductButton extends JButton implements ActionListener{
	Transaction trans;
	Product prod;
	
	public ProductButton(Product prod, Transaction trans) {
		this.setText(prod.getProductName());
		this.addActionListener(this);
		this.setPreferredSize(new Dimension(121,100));
		
		this.trans = trans;
		this.prod = prod;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this) {
			System.out.println("ButtonClicked");
			int qtty = Integer.parseInt(JOptionPane.showInputDialog("Enter Quantity"));
			trans.addProduct(prod.getProductId(), qtty);
		}
		
	}
}
