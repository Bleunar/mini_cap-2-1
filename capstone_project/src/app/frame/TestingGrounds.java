package app.frame;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import app.function.Transaction;
import app.function.WheelPanel;

public class TestingGrounds extends JFrame implements KeyListener{
	WheelPanel wp = new WheelPanel(new Transaction());
	JPanel x;
	
	public TestingGrounds(){
		this.setBounds(0,0,500,500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		this.setFocusable(true);
		this.requestFocus();
		
		x = new JPanel();
		x.add(wp.getPane());
		
		this.add(x);
		this.setVisible(true);
		this.addKeyListener(this);

	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			wp.updatePane(x);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
