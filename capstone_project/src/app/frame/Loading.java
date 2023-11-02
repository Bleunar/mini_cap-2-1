package app.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import app.misc.Colors;
import app.misc.FontSize;

public class Loading extends JFrame{
	Colors colors = new Colors();
	FontSize fonts = new FontSize();
	
	public Loading() throws IOException{
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setBounds(0,0,400,200);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		
		ImageIcon img = new ImageIcon(getClass().getResource("/resources/eee.jpg"));
		Image imgGet = img.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
		JLabel lab = new JLabel();
		img = new ImageIcon(imgGet);
		lab.setLayout(new BorderLayout());
		lab.setIcon(img);
		lab.setBorder(BorderFactory.createLineBorder(colors.app1L, 5));
		this.setContentPane(lab);
		this.setBackground(colors.app_black);
		
		this.setVisible(true);
		
		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());
		container.setSize(400,100);
		container.setOpaque(false);
		
		JLabel title = new JLabel("P.O.S. Software", SwingConstants.LEADING);
		title.setFont(fonts.mediumBold);
		title.setSize(400,50);
		title.setForeground(colors.app_white);
		title.setBorder(new EmptyBorder(10, 10, 0, 10));
		
		JLabel subTitle = new JLabel("created by PHINMA UI Students", SwingConstants.LEADING);
		subTitle.setFont(fonts.regularPlain);
		subTitle.setSize(400,30);
		subTitle.setForeground(colors.app_white);
		subTitle.setBorder(new EmptyBorder(0, 10, 0, 10));
		
		container.add(title, BorderLayout.NORTH);
		container.add(subTitle, BorderLayout.SOUTH);
		
		this.add(container, BorderLayout.NORTH);
		
		JPanel loading = new JPanel();
		loading.setPreferredSize(new Dimension(0,30));
		loading.setLayout(new FlowLayout(FlowLayout.LEADING));
		loading.setBackground(colors.app1);
		
		
		JLabel loadingTXT = new JLabel("Loading");
		loadingTXT.setFont(fonts.regularPlain);
		loadingTXT.setForeground(colors.app_black);
		
		loading.add(loadingTXT);
		
		this.add(loading, BorderLayout.SOUTH);
		
		this.setVisible(true);
		
		int iter = 0;
		while(true) {
			loadingTXT.setText("Loading");
			try {
				
				
				String x = "Loading";
				int counter = 1;
				for(int i = 0; i < 4; i++) {
					TimeUnit.SECONDS.sleep(1);
					x = x + " .";
					loadingTXT.setText(x);
					
				}
				if(iter == 1) {
					break;
				}
				iter++;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.setVisible(false);
		new Login_frame();
	
	}
}