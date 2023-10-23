package app.function;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

import javax.swing.JLabel;
import javax.swing.JPanel;

import app.misc.FontSize;

public class WheelPanel {
	ProductMain pf = new ProductMain();
	FontSize font = new FontSize();
	HashMap<String, JPanel> categoryPanes = new HashMap<String, JPanel>();
	Stack<String> stack = new Stack<String>();
	
	int paneCount = 0;
	int currentPane = 0;
	
	Transaction trans;
	
	public WheelPanel(Transaction trans){
		setCategoryPanes();
		setPaneWheel();
		
		this.trans = trans;
		
		
	}
	private void setPaneWheel() {
		for(String i: categoryPanes.keySet()) {
			stack.addLast(i);
		}
	}
	
	private void setCategoryPanes() {
		LinkedList<Category> x = pf.getCategorizedPanes();
		
		for(Category cat: x) {
			categoryPanes.put(cat.getCategoryName(), cat.getCategoryPane(this.trans));
		}
	}
	
	public JPanel getPane() {
		return categoryPanes.get(getCurrentCategory());
	}
	
	public void updatePane(JPanel panel) {
		panel = categoryPanes.get(getCurrentCategory());
	}
	
	public void nextButton() {
		stack.addLast(stack.removeFirst());
	}
	
	public void previousButton() {
		stack.addFirst(stack.removeLast());
	}
	
	public String getCurrentCategory() {
		return stack.peek();
	}
	
	public void updateJLabel(JLabel label) {
		label.setFont(font.regularBold);
		label.setText(this.getCurrentCategory());
	}
	
	public void testPrint() {
		for(String x: stack) {
			System.out.println(x);
			
		}
	}
	
	
}
