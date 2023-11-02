package app.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import app.database.Database;
import app.misc.Colors;

public class Dashboarded_admin extends JFrame implements ActionListener, MouseListener{
	Colors colors = new Colors();
	Database db = new Database();
	
	//Top Components
	JPanel topBorder = new JPanel();
	
	
	
	public Dashboarded_admin(){
		System.out.println("> Dashboard for admin created");
		
		this.setLocationRelativeTo(null);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.getContentPane().setBackground(colors.colorBG);
		this.setLayout(new BorderLayout());
		
		topBorder.setPreferredSize(new Dimension(0,80));
		topBorder.setBackground(colors.maPinkPink);
		
		this.add(topBorder, BorderLayout.NORTH);
		
		this.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	// For buttons
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
