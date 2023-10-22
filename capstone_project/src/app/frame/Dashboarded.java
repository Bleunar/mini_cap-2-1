package app.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import app.database.Database;
import app.misc.Colors;
import app.misc.FontSize;
import app.misc.PopUp;
import app.misc.Screen;

public class Dashboarded extends JFrame implements ActionListener, MouseListener{
	private Database db = new Database();
	private Colors colors = new Colors();
	private FontSize font = new FontSize();
	private PopUp popup = new PopUp();
	private Screen sc = new Screen();
	private KeyBind key = new KeyBind();
	
	//JMenuOptions
	JMenuItem view_toggleDarkMode;
	JMenuItem opt_exit;
	
	//southPaneWW Components
	JButton a;
	JButton b;
	JButton c;
	JButton d;
	JButton ee;
	JButton f;
	
	//southPaneWE Components
	JButton pay;
	
	//southPaneE Components
	JPanel previous;
	JPanel next;
	
	public Dashboarded(){
		setupFrame();
		setupPanels();
		setupMenuBars();
		
		this.setVisible(true);
	}

	
	private void setupFrame() {
		this.setTitle("P.O.S.  |  Coffee37");
//		this.setSize(new Dimension(sc.getScreenWidth(), sc.getScreenHeight()));
		this.setUndecorated(true);
		this.setLocationRelativeTo(null); //center frame relative to screen
		this.setLayout(new BorderLayout());
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); //forda full screen;
//		this.getContentPane().setBackground(colors.itom);
		this.setFocusable(true);
		this.requestFocus();
	    this.addKeyListener(key);
	    JFrame frame = this;
	    this.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {}
            public void focusLost(FocusEvent e) {
            	frame.requestFocus();}
    });
	}
	
	private void setupPanels() {
		JPanel northPane = new JPanel();
		JPanel westPane = new JPanel();
		JPanel eastPane = new JPanel();
		JPanel southPane = new JPanel();
		
		northPane.setPreferredSize(new Dimension(0,100));
		northPane.setLayout(new BorderLayout());
		northPane.setBackground(colors.itom);
		northPane.setBorder(BorderFactory.createLineBorder(Color.black));
		northPaneContents(northPane);
		
		westPane.setPreferredSize(new Dimension(sc.getScreenWidth()/2,0));
		westPane.setLayout(new BorderLayout());
		westPane.setBackground(colors.itom);
		westPane.setBorder(BorderFactory.createLineBorder(Color.black));
		westPaneContents(westPane);
		
		eastPane.setPreferredSize(new Dimension(sc.getScreenWidth()/2,0));
		eastPane.setLayout(new BorderLayout());
		eastPane.setBackground(colors.itom);
		eastPane.setBorder(BorderFactory.createLineBorder(Color.black));
		eastPaneContents(eastPane);
		
		southPane.setPreferredSize(new Dimension(0,100));
		southPane.setLayout(new BorderLayout());
		southPane.setBackground(colors.itom);
		southPane.setBorder(BorderFactory.createLineBorder(Color.black));
		southPaneContents(southPane);
		
		this.add(northPane, BorderLayout.NORTH);
		this.add(westPane, BorderLayout.WEST);
		this.add(eastPane, BorderLayout.EAST);
		this.add(southPane, BorderLayout.SOUTH);
	}
	
	
	//Contains the panels for the north pane
	private void northPaneContents(JPanel parent) {
		JPanel northPaneW = new JPanel();
		northPaneW.setPreferredSize(new Dimension(sc.getScreenWidth()/2,0));
		northPaneW.setLayout(new BorderLayout());
		northPaneW.setBackground(colors.app3);
		
		
		JPanel northPaneE = new JPanel();
		northPaneE.setPreferredSize(new Dimension(sc.getScreenWidth()/2,0));
		northPaneE.setLayout(new BorderLayout());
		northPaneE.setBackground(colors.app3);
		
		parent.add(northPaneW, BorderLayout.WEST);
		parent.add(northPaneE, BorderLayout.EAST);
	}
	
	
	//Contains the panels for the west pane
	private void westPaneContents(JPanel parent) {
		JPanel westPaneN = new JPanel();
		int n = (int) ((int)(sc.getScreenHeight()-200)*0.33);
		westPaneN.setPreferredSize(new Dimension(0,n));
		westPaneN.setLayout(null); 									// Revisit layout
		westPaneN.setBackground(colors.app1L);
		westPaneN.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JPanel westPaneW = new JPanel();
		int w = (int) ((int) (sc.getScreenWidth()/2)*0.75); // calculates the size of the pane in relation to screen width  
		westPaneW.setPreferredSize(new Dimension(w,0));
		westPaneW.setLayout(null); 									// Revisit layout
		westPaneW.setBackground(colors.app1);
		westPaneW.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JPanel westPaneE = new JPanel();
		int e = (int) ((int) (sc.getScreenWidth()/2)*0.25); // calculates the size of the pane in relation to screen width 
		westPaneE.setPreferredSize(new Dimension(e,0));
		westPaneE.setLayout(new GridLayout(5,1,0,0)); 									// Revisit layout
		westPaneE.setBackground(colors.app1);
		westPaneE.setBorder(BorderFactory.createLineBorder(Color.black));
		
		parent.add(westPaneN, BorderLayout.NORTH);
		parent.add(westPaneW, BorderLayout.WEST);
		parent.add(westPaneE, BorderLayout.EAST);
	}
	
	
	private void eastPaneContents(JPanel parent) {
		
		JPanel eastPaneN = new JPanel();
		int n = (int)((sc.getScreenHeight()-200) * 0.1);
		eastPaneN.setPreferredSize(new Dimension(0, n));
		eastPaneN.setLayout(new FlowLayout(FlowLayout.LEADING));
		eastPaneN.setBackground(colors.app4);
		;
		
		JPanel eastPaneS = new JPanel();
		int s = (int)((sc.getScreenHeight()-200) * 0.9);
		eastPaneS.setPreferredSize(new Dimension(0,s));
		eastPaneS.setLayout(new BorderLayout());
		eastPaneS.setBackground(colors.app4);
		
		parent.add(eastPaneN, BorderLayout.NORTH);
		parent.add(eastPaneS, BorderLayout.SOUTH);
		
	}
	
	private void southPaneContents(JPanel parent) {
		JPanel southPaneW = new JPanel();
		southPaneW.setPreferredSize(new Dimension(sc.getScreenWidth()/2,0));
		southPaneW.setLayout(new BorderLayout());
		southPaneW.setBackground(colors.itom);
		southPaneWContents(southPaneW);
		
		
		JPanel southPaneE = new JPanel();
		southPaneE.setPreferredSize(new Dimension(sc.getScreenWidth()/2,0));
		southPaneE.setLayout(new BorderLayout());
		southPaneE.setBackground(colors.itom);
		southPaneEContents(southPaneE);
		
		parent.add(southPaneW, BorderLayout.WEST);
		parent.add(southPaneE, BorderLayout.EAST);
	}
	
	private void southPaneWContents(JPanel parent) {
		JPanel southPaneWW = new JPanel();
		int w = (int)((int)(sc.getScreenWidth()/2)*0.75);
		southPaneWW.setPreferredSize(new Dimension(w,0));
		southPaneWW.setLayout(new GridLayout(2,3,0,0));
		southPaneWW.setBackground(colors.itom);
		southPaneWWContents(southPaneWW);
		
		JPanel southPaneWE = new JPanel();
		int e = (int)((int)(sc.getScreenWidth()/2)*0.25);
		southPaneWE.setPreferredSize(new Dimension(e,0));
		southPaneWE.setLayout(new GridLayout(1,1));
		southPaneWE.setBackground(colors.itom);
		southPaneWEContents(southPaneWE);
		
		parent.add(southPaneWW, BorderLayout.WEST);
		parent.add(southPaneWE, BorderLayout.EAST);
	}
	
	private void southPaneWWContents(JPanel parent){
		a= new JButton("Promos");
		a.addActionListener(this);
		b = new JButton("Discounts");
		b.addActionListener(this);
		c = new JButton("Payment Method");
		c.addActionListener(this);
		d = new JButton("Search Product");
		d.addActionListener(this);
		ee = new JButton("-----------");
		ee.addActionListener(this);
		f = new JButton("Help");
		f.addActionListener(this);
		
		
		parent.add(a);
		parent.add(b);
		parent.add(c);
		parent.add(d);
		parent.add(ee);
		parent.add(f);
	}
	
	private void southPaneWEContents(JPanel parent) {
		pay = new JButton("Pay");
		pay.addActionListener(this);
		parent.add(pay);
	}
	
	private void southPaneEContents(JPanel parent) {
		JLabel prev = new JLabel("Previous");
		
		previous = new JPanel();
		int w = (sc.getScreenWidth()/2)/2;
		previous.setPreferredSize(new Dimension(w,0));
		previous.setLayout(new GridBagLayout());
		previous.addMouseListener(this); 						//Add mosue listener
		previous.setBackground(colors.app2);
		previous.setBorder(BorderFactory.createLineBorder(Color.black));
		previous.add(prev);
		
		
		
		JLabel nxt = new JLabel("Next");
		
		next = new JPanel();
		int e = (sc.getScreenWidth()/2)/2;
		next.setPreferredSize(new Dimension(e,0));
		next.setLayout(new GridBagLayout());
		next.addMouseListener(this); 
		next.setBackground(colors.app2);
		next.setBorder(BorderFactory.createLineBorder(Color.black));
		next.add(nxt);
		
		parent.add(previous, BorderLayout.WEST);
		parent.add(next, BorderLayout.EAST);
	}
	
	
	
	/*
	 * 
	 * Dashboard Menu Bar
	 * Add the following:
	 * 		Credits
	 * 		Call Admin
	 * 		Report Issue
	 * 
	 */
	private void setupMenuBars() {
	     JMenuBar menuBar;
	 
	    menuBar = new JMenuBar();
        
        JMenu view = new JMenu("View");
        JMenu options = new JMenu("Options");
        
        view_toggleDarkMode = new JMenuItem("Toggle Dark Mode");
        opt_exit = new JMenuItem("Exit");
 
        view_toggleDarkMode.addActionListener(this);
        opt_exit.addActionListener(this);
        
        options.add(opt_exit);
        view.add(view_toggleDarkMode);
        
        menuBar.add(options);
        menuBar.add(view);
 
        this.setJMenuBar(menuBar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source== view_toggleDarkMode) System.out.println("Going Dark");
		if(source==opt_exit) {
			if(popup.popUpPrompt(null, "Exit Program?")==0) {
				System.exit(0);
			}
		}
		
		if(e.getSource()==a) System.out.println("A");
		if(e.getSource()==b) System.out.println("B");
		if(e.getSource()==c) System.out.println("C");
		if(e.getSource()==d) System.out.println("D");
		if(e.getSource()==ee) System.out.println("E");
		if(e.getSource()==f) System.out.println("F");
		
		if(e.getSource()==pay) System.out.println("Pay Button Pressed");
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==next) {
			popup.message("Next Button Pressed");
		} else if (e.getSource()==previous){
			popup.message("Previous Button Pressed");
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
}


class KeyBind implements KeyListener{
	private PopUp popup = new PopUp();
	
	@Override
    public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
       
       	if(key==KeyEvent.VK_ESCAPE) {
    	   	if(popup.popUpPrompt(null, "Exit Program?")==0) System.exit(0);
       	}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
}
