package app.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import app.misc.Colors;
import app.misc.FontSize;
import app.misc.PopUp;



public class Loading_frame extends JFrame{
	PopUp pop_up = new PopUp();
	Colors colors = new Colors();
	
	FontSize fonts = new FontSize();
	
	private JLabel title = new JLabel();
	
	public Loading_frame() throws InterruptedException{
		this.setUndecorated(true);
		this.setBounds(0,0,500,300);
		this.getContentPane().setBackground(new Color(0,0,0));
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		
		java.net.URL imgURL = Loading_frame.class.getResource("src/resources/loading-gif.gif");
		
		ImageIcon x = new ImageIcon(imgURL);
		Image image = x.getImage(); // transform it 
		Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		x = new ImageIcon(newimg);  // transform it back
		
		JLabel label = new JLabel("Hello World");
		label.setIcon(x);
		
		this.add(label);
		
		this.setVisible(true);
		
		TimeUnit.SECONDS.sleep(2);
		System.exit(1);
	}
}

