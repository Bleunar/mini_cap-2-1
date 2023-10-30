package app.frame;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TestingGrounds extends JFrame{
    JFrame f = new JFrame();  
	
	public TestingGrounds(){
		init();
		addTable();
	}
	
	void init(){
		this.setBounds(0,0,500,400);
		this.setLocationRelativeTo(null);
		
		this.setVisible(true);
	}
	
	void addTable() {
		DefaultTableModel model = new DefaultTableModel(); 
		JTable table = new JTable(model); 

		// insert columns
		model.addColumn("id"); 
		model.addColumn("name"); 
		model.addColumn("quantity"); 
		model.addColumn("price"); 


		// add rows
		model.addRow(new Object[]{"1", "Dimsum Panda", "13", "35.00",});
		model.addRow(new Object[]{"1", "Dimsum Panda", "13", "35.00",});
		model.addRow(new Object[]{"1", "Dimsum Panda", "13", "35.00",});
		
		
		//Focus on rows
		table.requestFocus();
		table.changeSelection(0,0,false, false);
	        
		table.setBounds(30,40,200,300);
		JScrollPane sp=new JScrollPane(table);
		this.add(sp);
		this.setSize(300,400);    
	    this.setVisible(true);
	    
	}

}
