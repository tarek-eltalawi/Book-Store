package view;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import controller.BSController;


@SuppressWarnings("serial")
public class Search extends JPanel {
	
	JFrame view;
	
	JPanel mainPanel;
	JPanel buttonPanel;
	
	public JButton home;
	public JButton search;
	
	public JTextField title_Field;
	public JTextField category_Field;
	public JTextField year_Field;
	public JTextField publisher_Field;

	public JLabel title_Label;
	public JLabel category_Label;
	public JLabel year_Label;
	public JLabel publisher_Label;
	
	
	public Search() {
		
		view = View.getInstance();
		this.buttonPanel = new JPanel();
		this.buttonPanel.setBounds(0, 0, 210, view.getHeight());
		this.buttonPanel.setBackground(Color.DARK_GRAY);
		
		this.mainPanel = new JPanel();
		this.mainPanel.setBounds(210, 0, view.getWidth(), view.getHeight());
		this.mainPanel.setBackground(Color.WHITE);
		
		this.home = new JButton("Home");
		this.home.setBounds(50, 30, 120, 50);
		
		// text fields initialization
		title_Field = new JTextField(10);
		category_Field = new JTextField(10);
		year_Field = new JTextField(10);
		publisher_Field = new JTextField(10);
		
		title_Field.setBounds(560, 25, 500, 30);
		category_Field.setBounds(560, 95, 500, 30);
		year_Field.setBounds(560, 165, 500, 30);
		publisher_Field.setBounds(560, 235, 500, 30);
		
		// buttons initialization
		this.search = new JButton("search");
		this.search.setBounds(400, 600, 120, 40);
		
		// Labels initialization
		title_Label = new JLabel("Title : ");
		category_Label = new JLabel("category : ");
		year_Label = new JLabel("publication year : ");
		publisher_Label = new JLabel("publisher name : ");
		
		title_Label.setBounds(400, 30, 200, 15);
		category_Label.setBounds(400, 100, 200, 15);
		year_Label.setBounds(400, 170, 300, 15);
		publisher_Label.setBounds(400, 240, 300, 15);

	}
	
	public void viewPage(BSController controller) {
		
		// using panel to put button on the window
		view.getContentPane().add(this);
		this.setLayout(null);
		this.mainPanel.setLayout(null);
		this.buttonPanel.setLayout(null);
		this.mainPanel.add(title_Label);
		this.mainPanel.add(title_Field);
		this.mainPanel.add(category_Label);
		this.mainPanel.add(category_Field);
		this.mainPanel.add(year_Label);
		this.mainPanel.add(year_Field);
		this.mainPanel.add(publisher_Label);
		this.mainPanel.add(publisher_Field);
		
		search.addActionListener(controller);
		this.mainPanel.add(search);
		
		home.addActionListener(controller);
		this.buttonPanel.add(home);
		
		this.add(mainPanel);
		this.add(buttonPanel);
		
		view.setVisible(true);
		
	}
}
