package app.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

import app.misc.Colors;
import app.misc.FontSize;
import app.misc.PopUp;

public class TestingGrounds extends JFrame{
	FontSize font = new FontSize();
	Colors colors = new Colors();
	
	public TestingGrounds(){
		
		PopUp x = new PopUp();
		this.setBounds(0,0,500,500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		

		
		JPanel pan = new JPanel();
		pan.setBounds(0,0,1000,500);
		pan.setLayout(new GridLayout(5,5));
		pan.setBackground(new Color(50,50,50));
		
		JButton butt = new JButton("Tae");
		butt.setBounds(0,0,150,50);
		
		pan.add(butt);
		
		JScrollPane sp = new JScrollPane(pan);
		sp.setPreferredSize(new Dimension(500,500));
		sp.setLayout(new ScrollPaneLayout());
		
//		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sp.getVerticalScrollBar().setUnitIncrement(20);
		
		
//		sp.setViewportView(pan);
//		sp.add(pan);
//		sp.setVisible(true);
		this.add(sp, BorderLayout.CENTER);
		this.setVisible(true);
		
		
	}
}
