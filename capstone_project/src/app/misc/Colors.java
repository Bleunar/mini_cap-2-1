package app.misc;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Colors {
	private boolean darkMode = false;

	public Color colorBG = new Color(100, 150, 150);
	public Color colorFont = new Color(10,10,10);
	public Color colorFontLight = new Color(50,50,50);
	public Color notThatWhite = new Color(230,230,230);
	public Color medyoBlue = new Color(230,230,255);
	public Color maPinkPink = new Color(255,240,240);
	public Color maPulaPula = new Color(255,150,150);
	
	public Colors(){
		if(darkMode) {
			// modify values of colors to darker shades
		} else {
			// modify values of colors to lighter shades
		}
	}
	
	public void setDarkMode(Boolean val) {
		darkMode = val;
	}
}
