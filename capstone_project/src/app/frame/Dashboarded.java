package app.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import app.database.Database;
import app.functions.Category;
import app.functions.Product;
import app.functions.Transaction;
import app.functions.Category_control;
import app.misc.Colors;
import app.misc.FontSize;
import app.misc.PopUp;
import app.misc.Screen;

public class Dashboarded extends JFrame implements ActionListener, MouseListener{
	private Database db = new Database();
	private Transaction t = new Transaction(db, this);
	private Colors colors = new Colors();
	private FontSize font = new FontSize();
	private PopUp popup = new PopUp();
	private Screen sc = new Screen();
	private KeyBind key = new KeyBind(this);
	private Category_control categoryControl = new Category_control(db, t);
	
	//NorthPaneContents
	String operator_username; //id of operator
	String operator_name; //name of operator
	
	JLabel time;
	JLabel date;
	
	String day;
	String month;
	String year;
	String week;
	
	//JMenuOptions
	JMenuItem view_toggleDarkMode;
	JMenuItem opt_exit;
	
	//West Pane contents
	double total,received,change;
	public JLabel totalValue, receivedValue, changeValue;
	
	JButton wp_1;
	JButton wp_2;
	JButton wp_3;
	JButton wp_4;
	JButton wp_5;
	
	DefaultTableModel model;
	JTable table;
	
	//eastPaneN
	JPanel eastPaneN;
	JLabel category;
	
	
	//eastPaneS Contents
	JPanel productContainer;
	
	//southPaneWW Components
	JButton sww_1;
	JButton sww_2;
	JButton sww_3;
	JButton sww_4;
	JButton sww_5;
	JButton sww_6;
	
	//southPaneWE Components
	JButton end;
	
	//southPaneE Components
	JPanel previous;
	JPanel next;
	
	public Dashboarded(String username){
		setOperator(username);
		setupFrame();
		setupPanels();
		setupMenuBars();
		dateTimeDisplay(); 
		this.setVisible(true);
	}

	// Display date and time
	private void dateTimeDisplay() {
		LocalDateTime myDateObj = LocalDateTime.now();
		
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh-mm");
		date.setText(myDateObj.format(dateFormat));
		time.setText(myDateObj.format(timeFormat));
	}
	
	public void updateMoney() {
		this.totalValue.setText("₱"+ t.getCurrentTotal());
		this.receivedValue.setText("₱"+ t.getCurrentReceived());
		this.changeValue.setText("₱"+ t.getCurrentChange());
		
		System.out.println("> money opdated");
	}
	
	//Set operator
	public void setOperator(String username) {
		this.operator_username = username;
		this.operator_name = db.getEmployeeName(this.operator_username);
	}
	
	
	
	private void setupFrame() {
		this.setUndecorated(true);
		this.setLocationRelativeTo(null); //center frame relative to screen, not used
		this.setLayout(new BorderLayout());
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); //forda full screen;
		this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(key);
	    
	    
	    //To refocus back on the frame, to make key binds function
	    JFrame frame = this;
	    this.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {}
            public void focusLost(FocusEvent e) {
            	frame.requestFocus();}
    });
	}
	
	//Setup the main panels
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
		northPaneW.setLayout(new BoxLayout(northPaneW, BoxLayout.PAGE_AXIS));
		northPaneW.setBorder(new EmptyBorder(10, 10, 10, 10));
		northPaneW.setBackground(colors.app3);
		northPaneWContents(northPaneW);
		
		
		JPanel northPaneE = new JPanel();
		northPaneE.setPreferredSize(new Dimension(sc.getScreenWidth()/2,0));
		northPaneE.setLayout(new BoxLayout(northPaneE, BoxLayout.PAGE_AXIS));
		northPaneE.setBorder(new EmptyBorder(10, 10, 10, 10));
		northPaneE.setBackground(colors.app3);
		northPaneEContents(northPaneE);
		
		parent.add(northPaneW, BorderLayout.WEST);
		parent.add(northPaneE, BorderLayout.EAST);
	}
	
	private void northPaneWContents(JPanel parent) {
		
		JLabel title = new JLabel("COFFEE 69");
		title.setForeground(colors.app_white);
		title.setFont(font.titleBold);
		JLabel branch = new JLabel("Molo, Iloilo");
		branch.setForeground(colors.app_white);
		branch.setFont(font.regularPlain);
		
		parent.add(title);
		parent.add(branch);
	}
	
	private void northPaneEContents(JPanel parent) {
		JPanel contain = new JPanel();
		contain.setLayout(new BorderLayout());
		contain.setOpaque(false);
		
		JLabel operatorLabel = new JLabel("Operator: " + this.operator_name, SwingConstants.TRAILING);
		operatorLabel.setForeground(colors.app_white);
		operatorLabel.setFont(font.regularBold);
		operatorLabel.setLayout(new FlowLayout(FlowLayout.TRAILING));
		
		date = new JLabel("", SwingConstants.TRAILING);
		date.setForeground(colors.app_white);
		date.setFont(font.regularPlain);
		date.setLayout(new FlowLayout(FlowLayout.TRAILING));
		
		
		time = new JLabel("", SwingConstants.TRAILING);
		time.setForeground(colors.app_white);
		time.setFont(font.regularPlain);
		time.setLayout(new FlowLayout(FlowLayout.TRAILING));
		
		contain.add(operatorLabel, BorderLayout.NORTH);
		contain.add(date, BorderLayout.EAST);
		contain.add(time, BorderLayout.SOUTH);
		
		parent.add(contain);
	}
	
	
	//Contains the panels for the west pane
	private void westPaneContents(JPanel parent) {
		JPanel westPaneN = new JPanel();
		int n = (int) ((int)(sc.getScreenHeight()-200)*0.25);
		westPaneN.setPreferredSize(new Dimension(0,n));
		westPaneN.setLayout(new BorderLayout());
		westPaneN.setBackground(colors.app4);
		westPaneN.setBorder(BorderFactory.createLineBorder(Color.black));
		westPaneN.setBorder(new EmptyBorder(10, 10, 10, 10));
		westPaneNContents(westPaneN);
		
		JPanel westPaneW = new JPanel();
		int w = (int) ((int) (sc.getScreenWidth()/2)*0.75); // calculates the size of the pane in relation to screen width  
		westPaneW.setPreferredSize(new Dimension(w,0));
		westPaneW.setLayout(new BorderLayout());
		westPaneW.setBackground(colors.app1);
		westPaneW.setBorder(BorderFactory.createLineBorder(Color.black));
		westPaneWContents(westPaneW);
		
		JPanel westPaneE = new JPanel();
		int e = (int) ((int) (sc.getScreenWidth()/2)*0.25); // calculates the size of the pane in relation to screen width 
		westPaneE.setPreferredSize(new Dimension(e,0));
		westPaneE.setLayout(new GridLayout());
		westPaneE.setBackground(colors.app1);
		westPaneE.setBorder(BorderFactory.createLineBorder(Color.black));
		westPaneEContents(westPaneE);
		
		parent.add(westPaneN, BorderLayout.NORTH);
		parent.add(westPaneW, BorderLayout.WEST);
		parent.add(westPaneE, BorderLayout.EAST);
	}
	
	private void westPaneNContents(JPanel parent){
		
		//TODO remove this
		this.total = 450.00;
		this.received = 500.00;
		this.change = 50.00;

		int half = sc.getScreenWidth()/2;
		
		JPanel west = new JPanel();
		west.setPreferredSize(new Dimension(half/2, 0));
		west.setLayout(new GridLayout(3,1));
		west.setOpaque(false);
		
		JLabel total = new JLabel("Total", SwingConstants.LEADING);
		total.setFont(font.regularPlain);
		total.setForeground(colors.app_black);
		west.add(total);

		JLabel received = new JLabel("Received", SwingConstants.LEADING);
		received.setFont(font.regularPlain);
		received.setForeground(colors.app_black);
		west.add(received);
		
		JLabel change = new JLabel("Discount", SwingConstants.LEADING);
		change.setFont(font.regularPlain);
		change.setForeground(colors.app_black);
		west.add(change);
	
		JPanel east = new JPanel();
		east.setPreferredSize(new Dimension(half/2, 0));
		east.setLayout(new GridLayout(3,1));
		east.setOpaque(false);
		
		totalValue = new JLabel("₱" + t.getCurrentTotal() , SwingConstants.TRAILING);
		totalValue.setFont(font.mediumBold);
		totalValue.setForeground(colors.app_black);
		east.add(totalValue);

		receivedValue = new JLabel("₱" + t.getCurrentChange() , SwingConstants.TRAILING);
		receivedValue.setFont(font.mediumBold);
		receivedValue.setForeground(colors.app_black);
		east.add(receivedValue);
		
		changeValue = new JLabel("₱"+ t.getCurrentChange() , SwingConstants.TRAILING);
		changeValue.setFont(font.mediumBold);
		changeValue.setForeground(colors.app_black);
		east.add(changeValue);
	
		parent.add(west, BorderLayout.WEST);
		parent.add(east, BorderLayout.EAST);
	}
	
	private void westPaneWContents(JPanel parent) {
		model = new DefaultTableModel(); 
		table = new JTable(model); 
		
		// insert columns
		model.addColumn("ID"); 
		model.addColumn("Name"); 
		model.addColumn("Price"); 
		model.addColumn("Quantity"); 
		
		table.setFont(new Font("Arial", Font.BOLD, 12));
		
		table.requestFocus(); //Focus on rows
		table.setBounds(30,40,200,300);
		JScrollPane sp = new JScrollPane(table);
		parent.add(sp, BorderLayout.CENTER);
		table.addFocusListener(null);
		t.setModel(model);
	}
	
	private void westPaneEContents(JPanel parent) {
		JPanel container = new JPanel();
		container.setLayout(new GridLayout(5,1));
		container.setBorder(BorderFactory.createLineBorder(Color.black));
		
		wp_1 = new JButton("Delete Product");
		wp_1.addActionListener(this);	
		
		wp_2 = new JButton("Modify Quantity");
		wp_2.addActionListener(this);	
		
		wp_3 = new JButton("Add 1");
		wp_3.addActionListener(this);	
		
		wp_4 = new JButton("Less 1");
		wp_4.addActionListener(this);	
		
		wp_5 = new JButton("Clear Cart");
		wp_5.addActionListener(this);	
		
		container.add(wp_1);
		container.add(wp_2);
		container.add(wp_3);
		container.add(wp_4);
		container.add(wp_5);
		
		parent.add(container);
		
		
	}
	
	private void eastPaneContents(JPanel parent) {
		
		JPanel eastPaneN = new JPanel();
		int n = (int)((sc.getScreenHeight()-200) * 0.06);
		eastPaneN.setPreferredSize(new Dimension(0, n));
		eastPaneN.setLayout(new BorderLayout());
		eastPaneN.setBackground(colors.app4);
		eastPaneN.setBorder(BorderFactory.createLineBorder(Color.black));
		eastPaneNContents(eastPaneN);
		
		JPanel eastPaneS = new JPanel();
		int s = (int)((sc.getScreenHeight()-200) * 0.90);
		eastPaneS.setPreferredSize(new Dimension(0,s));
		eastPaneS.setLayout(new BorderLayout());
		eastPaneS.setBackground(colors.app_white);
		eastPaneSContents(eastPaneS);
		
		parent.add(eastPaneN, BorderLayout.NORTH);
		parent.add(eastPaneS, BorderLayout.SOUTH);
	}
	private void eastPaneNContents(JPanel parent) {
		category = new JLabel(categoryControl.getCurrentCategoryName(), SwingConstants.LEADING);
		category.setFont(font.regularBold);
		category.setForeground(colors.app_black);
		category.setBorder(new EmptyBorder(10, 10, 10, 10));
		category.setText(categoryControl.getCurrentCategoryName().toUpperCase());
		
		parent.add(category, BorderLayout.CENTER);
	}
	
	private void eastPaneSContents(JPanel parent) {
		productContainer = new JPanel();
		productContainer.setLayout(new FlowLayout(FlowLayout.LEADING));
		
		for(Product kkk: categoryControl.getCurrentCategoryProducts()) {
			productContainer.add(kkk.getButton());
		}
		parent.add(productContainer, BorderLayout.CENTER);
	}
	
	private void southPaneContents(JPanel parent) {
		JPanel southPaneW = new JPanel();
		southPaneW.setPreferredSize(new Dimension(sc.getScreenWidth()/2,0));
		southPaneW.setLayout(new BorderLayout());
		southPaneW.setBackground(colors.itom);
		southPaneW.setBorder(BorderFactory.createLineBorder(Color.black));
		southPaneWContents(southPaneW);
		
		
		JPanel southPaneE = new JPanel();
		southPaneE.setPreferredSize(new Dimension(sc.getScreenWidth()/2,0));
		southPaneE.setLayout(new BorderLayout());
		southPaneE.setBackground(colors.itom);
		southPaneE.setBorder(BorderFactory.createLineBorder(Color.black));
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
		sww_1 = new JButton("Apply Promos");
		sww_1.addActionListener(this);	
		
		sww_2 = new JButton("Apply Discounts");
		sww_2.addActionListener(this);
		
		sww_3 = new JButton("Add Product Manualy");
		sww_3.addActionListener(this);
		
		sww_4 = new JButton("Payment Method");
		sww_4.addActionListener(this);
		
		sww_5 = new JButton("-----");
		sww_5.addActionListener(this);
		
		sww_6 = new JButton("-----");
		sww_6.addActionListener(this);
		
		
		parent.add(sww_1);
		parent.add(sww_2);
		parent.add(sww_3);
		parent.add(sww_4);
		parent.add(sww_5);
		parent.add(sww_6);
	}
	
	private void southPaneWEContents(JPanel parent) {
		end = new JButton("Add Payment");
		end.addActionListener(this);
		parent.add(end);
	}
	
	// Navigation for Product Category Panel
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
	
	/*
	 * 
	 * ActionListeners for Buttons and Panels
	 * 	
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source== view_toggleDarkMode) System.out.println("Going Dark");
		if(source==opt_exit) {
			if(popup.popUpPrompt(null, "Exit Program?")==0) {
				System.exit(0);
			}
		}
		
		if(e.getSource()==sww_1) System.out.println("A");
		if(e.getSource()==sww_2) System.out.println("B");
		if(e.getSource()==sww_3) System.out.println("C");
		if(e.getSource()==sww_4) System.out.println("D");
		if(e.getSource()==sww_5) System.out.println("E");
		if(e.getSource()==sww_6) System.out.println("F");
		if(e.getSource()==end) System.out.println("Pay Button Pressed");
		
		// delete row
		if(e.getSource()==wp_1) { 			// Delete the selected Product
			try {
				int row = table.getSelectedRow();
				t.removeFromCart(table.getValueAt(row, 0));
				model.removeRow(row);
				this.updateMoney();
			} catch (ArrayIndexOutOfBoundsException expt) {
				if(model.getRowCount()==0) popup.message("The cart is empty");
				else if(table.getSelectedRow()==-1 && model.getRowCount() > 0) popup.message("Select a product from the cart");
				
			}
			
		} else if (e.getSource()==wp_2) { 	// Modify the quantity of a product
			try {
				int row = table.getSelectedRow();
				t.updateProductQuantity(table.getValueAt(row, 0));
				this.updateMoney();
			} catch (ArrayIndexOutOfBoundsException e2) {
				if(model.getRowCount()==0) popup.message("The cart is empty");
				else if(table.getSelectedRow()==-1 && model.getRowCount() > 0) popup.message("Select a product from the cart");
				}
			
		} else if (e.getSource()==wp_3) {	// Add 1 to the selected Product
			try {
				int row = table.getSelectedRow();
				t.addOne(table.getValueAt(row, 0));
			} catch (ArrayIndexOutOfBoundsException exption) {
				if(model.getRowCount()==0) popup.message("The cart is empty");
				else if(table.getSelectedRow()==-1 && model.getRowCount() > 0) popup.message("Select a product from the cart");
			}
			
		} else if(e.getSource()==wp_4) { 	// Subtract 1 to the selected Product
			try {
				int row = table.getSelectedRow();
				t.lessOne(table.getValueAt(row, 0), row);
			} catch (ArrayIndexOutOfBoundsException exption) {
				if(model.getRowCount()==0) popup.message("The cart is empty");
				else if(table.getSelectedRow()==-1 && model.getRowCount() > 0) popup.message("Select a product from the cart");
			}
			
		} else if (e.getSource()== wp_5) {	//Button to clear the contents of  the cart
			try {
				t.clearCart();
				this.updateMoney();
			} catch (Exception e2) {
				if(model.getRowCount()==0) popup.message("The cart is empty");
				else if(table.getSelectedRow()==-1 && model.getRowCount() > 0) popup.message("Select a product from the cart");
			}
			
		}
	}

	
	//MouseListeners

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==next) {
			productContainer.removeAll();
			categoryControl.categoryNext();
			
			category.setText(categoryControl.getCurrentCategoryName().toUpperCase());
			
			//Adds the previous buttons to the panel
			for(Product kkk: categoryControl.getCurrentCategoryProducts()) {
				productContainer.add(kkk.getButton());
			}
			productContainer.updateUI();
			
			
		} else if (e.getSource()==previous){
			productContainer.removeAll();
			categoryControl.categoryPrevious();
			
			category.setText(categoryControl.getCurrentCategoryName().toUpperCase());
			
			//Adds the previous buttons to the panel
			for(Product kkk: categoryControl.getCurrentCategoryProducts()) {
				productContainer.add(kkk.getButton());
			}
			productContainer.updateUI();
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

// Keyboard Listeners / Key binds
class KeyBind implements KeyListener{
	private PopUp popup = new PopUp();
	ArrayList<JButton> buttons = new ArrayList<JButton>();
	ArrayList<JLabel> labels = new ArrayList<JLabel>();
	
	// to compare imported components
	JLabel label = new JLabel();
	JButton button = new JButton();
	
	
	public KeyBind(Dashboarded dashboarded) {
		// TODO Auto-generated constructor stub
	}
	
	@Override
    public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		//Exit
       	if(key==KeyEvent.VK_ESCAPE) {
       		int x = popup.popUpPrompt(null, "Exit Program?");
       		
    	   	if(x==0) {
    	   		System.exit(0);
    	   	}
       	}
       	
       	if(key==KeyEvent.VK_SPACE) {
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
