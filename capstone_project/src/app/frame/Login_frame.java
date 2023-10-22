package app.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import app.database.Database;
import app.misc.Colors;
import app.misc.FontSize;
import app.misc.PopUp;

public class Login_frame extends JFrame implements ActionListener, KeyListener{
	Database db = new Database();
	FontSize font = new FontSize();
	
	private JPanel x = new JPanel();
	private JLabel title = new JLabel();
	private JLabel branch = new JLabel();
	private JButton login = new JButton("Login");
	private JButton exit = new JButton("Exit");
	
	private JLabel uname_txt = new JLabel("Username");
	private JTextField uname = new JTextField();
	private JLabel pword_txt = new JLabel("Password");
	private JPasswordField pword = new JPasswordField();
	
	private JPanel uname_container = new JPanel();
	private JPanel pword_container = new JPanel();
	
	private JPanel title_panel = new JPanel();
	private JPanel input_panel = new JPanel();
	private JPanel buttons_panel = new JPanel();
	

	PopUp pop_up = new PopUp();
	Colors colors = new Colors();
	
	public Login_frame(){
		this.setBounds(20, 20, 400, 200);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.setResizable(false);
		
		//For Keyboard listener
		this.addKeyListener(this);
		this.setFocusable(true);
		
		
		title_panel.setLayout(new FlowLayout(FlowLayout.LEADING));
		title_panel.setBackground(colors.colorBG); 	
		
		input_panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		input_panel.setBackground(colors.colorBG);
		
		buttons_panel.setLayout(new FlowLayout());
		buttons_panel.setBackground(colors.colorBG);
		
		
		
		title.setText("COFFEE37");
		title.setFont(font.mediumBold);
		title.setBounds(0,0,400,30);
		title.setForeground(colors.colorFont);
		
		JPanel branchContain = new JPanel();
		branchContain.setPreferredSize(new Dimension(60,30));
		branchContain.setLayout(null);
		branchContain.setBackground(colors.colorBG);
		
		branch.setText("Molo, Iloilo City");
		branch.setVerticalAlignment(JLabel.BOTTOM);
		branch.setFont(font.smallBold);
		branch.setBounds(0,0,150,30);
		branch.setForeground(colors.colorFont);
		branchContain.add(branch);
		
		title_panel.add(title);
		title_panel.add(branchContain);
		
		
		uname_container.setLayout(new FlowLayout());
		uname_container.setBackground(colors.colorBG);
		pword_container.setLayout(new FlowLayout());
		pword_container.setBackground(colors.colorBG);
		
		uname_txt.setForeground(colors.colorFont);
		uname_txt.setFont(font.smallPlain);
		uname.setBounds(0,60,300,30);
		uname.setPreferredSize(new Dimension(300,30));
		uname.setFont(font.regularPlain);
		uname.setForeground(colors.colorFontLight);
		uname.addKeyListener(this);
		
		pword_txt.setForeground(colors.colorFont);
		pword_txt.setFont(font.smallPlain);
		pword.setBounds(0,90,300,30);
		pword.setPreferredSize(new Dimension(300,30));
		pword.setFont(font.regularPlain);
		pword.setForeground(colors.colorFontLight);
		pword.addKeyListener(this);
		
		
		
		uname_container.add(uname_txt);
		uname_container.add(uname);
		pword_container.add(pword_txt);
		pword_container.add(pword);
		
		input_panel.add(uname_container);
		input_panel.add(pword_container);
		
		exit.setFont(font.regularPlain);
		exit.setFocusPainted(false);
		exit.addActionListener(this);
		exit.setFocusPainted(false);
		
		login.setFont(font.regularBold);
		login.setFocusPainted(false);
		login.addActionListener(this);
		login.setFocusPainted(false);
		

		buttons_panel.add(exit);
		buttons_panel.add(login);

		this.add(title_panel, BorderLayout.NORTH);
		this.add(input_panel, BorderLayout.CENTER);
		this.add(buttons_panel, BorderLayout.SOUTH);
		
		
		this.setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(login)) {
			System.out.println("Login pressed");
			String getUname = uname.getText();
			String getPass = String.valueOf(pword.getPassword());
			
			
			
			
			boolean result = db.authenticate_account(getUname, getPass);
			if(result) {
				boolean isAdmin = db.isAdmin(getUname);
				if(isAdmin) {
					pop_up.popUp(null, "Successfuly Logged In, Admin");
					this.setVisible(false); // change to close the frame
					new Dashboarded();
					db.close();
				} else {
					pop_up.popUp(null, "Successfuly Logged In, Employee");
					this.setVisible(false); // change to close the frame
					new Dashboarded();
				}
				
			} else {
				pop_up.popUp(null, "Username or Password is incorrect");
				uname.setText("");
				uname.requestFocus();
				pword.setText("");
			}
		} else if (e.getSource().equals(exit)) {
			System.exit(0);
		}
	}
	
	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

	    if (key == KeyEvent.VK_ENTER) {
	        if(String.valueOf(pword.getPassword()).equals("")) {
	        	pword.requestFocusInWindow();
	        } else {
	        	login.doClick();
	        }
	    } else if (key == KeyEvent.VK_ESCAPE || key == KeyEvent.VK_DELETE) {
	        exit.doClick();
	    }
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
