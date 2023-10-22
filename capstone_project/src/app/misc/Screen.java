package app.misc;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Screen {
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	double width = screenSize.getWidth();
	double height = screenSize.getHeight();
	
	public int getScreenWidth() {
		return (int) width;
	}
	
	public int getScreenHeight() {
		return (int) height;
	}

}
