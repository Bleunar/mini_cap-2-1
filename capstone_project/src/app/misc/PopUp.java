package app.misc;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PopUp {
	
	
	
	public void message(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	
	public void popUp(JFrame parent,String message) {
		JOptionPane.showMessageDialog(parent, message);
	}
	
	public int popUpPrompt(JFrame parent, String message) {
		int x = JOptionPane.showConfirmDialog(parent, message);
		return x;
	}

}
