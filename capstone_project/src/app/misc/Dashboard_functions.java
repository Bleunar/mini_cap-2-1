package app.misc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Dashboard_functions{
	public Dashboard_functions(){
		
	}
	
	
	// Display all products onto the dashboard
	public void productDisplay(String name, JPanel container, int id) {
		JButton x = new JButton(name);
		x.setBounds(0,0,100,50);
		x.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent evt) {
		        System.out.println("Clicked " + name);
		    }
		});
		container.add(x);
	}
	
	public void testButton(String iter, JPanel parent) {
		JLabel btt = new JLabel("Button ", SwingConstants.CENTER);
		btt.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		btt.setOpaque(true);
		parent.add(btt);
	}
	
	
}
