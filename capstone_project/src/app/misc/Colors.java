package app.misc;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Colors {
	private boolean darkMode = false;

	public Color app1 = new Color(77, 170, 167);
	public Color app1L = new Color(107, 200, 197);
	public Color app2 = new Color(185, 197, 232);
	public Color app3 = new Color(51,51,51);
	public Color app4 = new Color(135, 182, 214);
	
	public Color app_white = new Color(230,230,230);
	
	public Color test1 = new Color(230,0,0);
	public Color test2 = new Color(0,230,0);
	
	
	public Color colorBG = new Color(77, 170, 167);
	public Color colorFont = new Color(10,10,10);
	public Color colorFontLight = new Color(50,50,50);
	public Color notThatWhite = new Color(230,230,230);
	public Color medyoBlue = new Color(230,230,255);
	public Color maPinkPink = new Color(255,240,240);
	public Color maPulaPula = new Color(255,150,150);
	public Color itom = new Color(0,0,0);

	
	public Colors(){
		if(darkMode) {
			// modify values of colors to darker shades
		} else {
			// modify values of colors to lighter shades
		}
	}
	
	// Extra feature
	public void setDarkMode(Boolean val) {
		darkMode = val;
	}
}
