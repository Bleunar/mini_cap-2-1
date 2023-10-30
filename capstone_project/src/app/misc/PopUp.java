package app.misc;

import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PopUp {
	
	public PopUp(){
		Queue x = new LinkedList();
	}
	
	
	
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
