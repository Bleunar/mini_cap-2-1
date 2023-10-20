package app.frame;

import javax.swing.JFrame;
import javax.swing.JLabel;

import app.misc.Colors;
import app.misc.PopUp;



public class Loading_frame extends JFrame{
	PopUp pop_up = new PopUp();
	Colors colors = new Colors();
	
	private JLabel title = new JLabel();
	
	
	
	
	
	public Loading_frame(){
		this.setUndecorated(true);
		this.setBounds(0,0,500,300);
		this.setBackground(colors.colorBG);
		this.setLocationRelativeTo(null);
		
		
		this.setVisible(true);
	}
	
	
}
