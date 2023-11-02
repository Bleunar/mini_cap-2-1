package app.main;

import app.frame.Dashboarded;
import app.frame.Loading;
import app.functions.Category_control;
import javax.swing.JOptionPane;

import com.mysql.cj.jdbc.exceptions.*;

public class Main {
	static int num = 0;

	public static void main(String[] args) {
		try {
			new Loading();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		
//		new Login_frame();
		
//		new Loading();
		
//		new TestingGrounds();
		
//		new Category_control();
		
		
		
	}

}
