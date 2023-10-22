package app.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import app.database.Database;
import app.function.ProductFunc;
import app.misc.Colors;
import app.misc.Dashboard_functions;
import app.misc.PopUp;

/*
 * 
 * This is an old version of dashboard
 * 
 * Shit version
 * 
 */


public class Dashboard extends JFrame implements ActionListener, MouseListener, KeyListener{
	Colors colors = new Colors();
	PopUp pop_up = new PopUp();
	Database database = new Database();
	
	//frame dimensions
	private int frameWidth = 1100;
	private int frameHeight = 700;
	
	//Panels for every border
	private JPanel top = new JPanel();
	private JPanel left = new JPanel();
	
	private JPanel center = new JPanel();
	private JPanel right = new JPanel();
	private JPanel bottom = new JPanel();
	
	
	private JPanel topL = new JPanel(); 
	private JPanel topR = new JPanel(); 
	
	//Bottom panel components
	JLabel logoutButton;
	JLabel checkoutButton;
	
	
	public Dashboard(String employeeId) {
		String name = database.getEmployeeName(employeeId);
		this.setTitle("P.O.S.  |  Coffee37  |  " + name);
		this.setBounds(0,0,frameWidth,frameHeight); // way pulos
		this.setUndecorated(true);
		this.setLocationRelativeTo(null); //center frame relative to screen
		this.setLayout(new BorderLayout());
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); //forda full screen
		
		
		//Panels
		top.setBackground(colors.maPinkPink);
		top.setPreferredSize(new Dimension(frameWidth, 69));
		top.setLayout(new BorderLayout());
		
		left.setBackground(colors.notThatWhite);
		left.setPreferredSize(new Dimension(600, 0));
		
		center.setBackground(colors.medyoBlue);
		center.setLayout(new BorderLayout());
		center.setPreferredSize(new Dimension(500, 800));
		
		
		right.setBackground(colors.notThatWhite);
		right.setPreferredSize(new Dimension(150, frameHeight));
		
		bottom.setBackground(colors.colorBG);
		bottom.setPreferredSize(new Dimension(frameWidth, 80));
		bottom.setLayout(new GridLayout(0,8));
		
		
		//Top Components
		topL.setBackground(colors.colorBG);
		topL.setLayout(new BorderLayout());
		topL.setPreferredSize(new Dimension(500, 0));
		
		
 		topR.setLayout(new FlowLayout(FlowLayout.TRAILING));
		topR.setPreferredSize(new Dimension(500, 0));
		topR.setBackground(colors.colorBG);
		
		top.add(topL, BorderLayout.WEST);
		top.add(topR, BorderLayout.EAST);
		
		
		
		
		Dashboard_functions g = new Dashboard_functions();
		ArrayList<Integer> keys = new ArrayList<Integer>();
		ProductFunc xx = new ProductFunc();
		keys = xx.getProdIds();
		
		JPanel panel = new JPanel();
//		panel.setBounds(0,0,800,500);
		
		panel.setSize(new Dimension(800,11500));
		panel.setLayout(new GridLayout(0,8,5,5));
		panel.setOpaque(false);
		
		g.productDisplay(xx.getProdName(1), panel, 1);
		
		for(int i: keys) {
			g.productDisplay(xx.getProdName(i), panel, i);
		}
		
		JScrollPane sp = new JScrollPane(panel);
		sp.setSize(new Dimension(800,1500));
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sp.getVerticalScrollBar().setUnitIncrement(20);
		sp.setBackground(colors.maPinkPink);
		sp.setOpaque(true);
//		sp.setViewportView(panel);
//		sp.add(panel);
		
		panel.setSize(panel.getParent().getSize());
		center.add(sp);
		
		
		//Bottom components
		for(int i = 1; i <= 7; i++) {
			String x = "" + i;
			g.testButton(x, bottom);
		}
		
		
		//Buttons at the bottom of the dashboard
		// Login, ,Checkout, ...
		logoutButton = new JLabel("Logout", SwingConstants.CENTER);
		logoutButton.setBounds(0,0,300,100);
		logoutButton.setOpaque(true);
		logoutButton.setBackground(colors.maPulaPula);
		logoutButton.addMouseListener(this);
		
		bottom.add(logoutButton);
		
		
		
		
		this.add(top, BorderLayout.NORTH);
		this.add(left, BorderLayout.WEST);
		this.add(center, BorderLayout.CENTER);
		this.add(right, BorderLayout.EAST);
		this.add(bottom, BorderLayout.SOUTH);
		
		this.setVisible(true);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals("a")) {
			System.exit(1);
		}
		
	}

	
	//MouseListener
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==logoutButton) {
			int response = pop_up.popUpPrompt(this, "Are you sure to quit");
			if(response==0) {
				System.exit(0);
			} else {
				// pass
			}
		}
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent e) {	
	}


	@Override
	public void keyPressed(KeyEvent e) {
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
