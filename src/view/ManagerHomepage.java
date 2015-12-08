package view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.BSController;
import model.Manager;

@SuppressWarnings("serial")
public class ManagerHomepage extends JPanel {
	
	JFrame view;
	
	JPanel welcomePanel;
	JPanel buttonPanel;
	JPanel bookPanel;
	
	public JButton profile;
	public JButton logout;
	public JButton library;
	public JButton log;
	public JButton addBook;
	public JButton viewCustomer;
	public JButton viewOrders; 
	
	public JTextField title_Field;
	public JTextField stockQuantity_Field;
	public JTextField minQuantity_Field;
	public JTextField category_Field;
	public JTextField price_Field;
	public JTextField year_Field;
	public JTextField publisher_Field;

	public JLabel welcome;
	public JLabel title_Label;
	public JLabel stockQuantity_Label;
	public JLabel minQuantity_Label;
	public JLabel category_Label;
	public JLabel price_Label;
	public JLabel year_Label;
	public JLabel publisher_Label;
	
	public ManagerHomepage() {
		view = View.getInstance();
		
		this.profile = new JButton("Profile");
		this.profile.setBounds(50, 30, 120, 50);
		this.library = new JButton("Library");
		this.library.setBounds(50, 100, 120, 50);
		this.log = new JButton("View Log");
		this.log.setBounds(50, 170, 120, 50);
		this.viewCustomer = new JButton("Customers");
		this.viewCustomer.setBounds(50, 240, 120, 50);
		this.viewOrders = new JButton("View Orders");
		this.viewOrders.setBounds(50, 310, 120, 50);
		this.logout = new JButton("Log out");
		this.logout.setBounds(50, view.getHeight()-150, 120, 50);
		this.addBook = new JButton("Add Book");
		this.addBook.setBounds(view.getWidth()/2, view.getHeight()-150, 120, 50);
		
		this.welcomePanel = new JPanel();
		this.welcomePanel.setBounds(210, 0, view.getWidth(), 180);
		this.welcomePanel.setBackground(Color.WHITE);
		
		this.buttonPanel = new JPanel();
		this.buttonPanel.setBounds(0, 0, 210, view.getHeight());
		this.buttonPanel.setBackground(Color.DARK_GRAY);
		
		this.bookPanel = new JPanel();
		this.bookPanel.setBounds(210, 250, view.getWidth(), view.getHeight()-420);
		this.bookPanel.setBackground(Color.WHITE);

	}
	
	public void viewPage(BSController controller, Manager manager){
		
		view.getContentPane().add(this);
		this.setLayout(null);
		this.welcomePanel.setLayout(null);
		this.buttonPanel.setLayout(null);
		this.bookPanel.setLayout(null);

		this.welcome = new JLabel("Welcome " + manager.getFirstname() + " " + manager.getLastname());
		this.welcome.setBounds(500, 20, 500, 100);
		this.welcome.setFont(new Font("TimesRoman", Font.BOLD, 30));
		
		// text fields initialization
		title_Field = new JTextField(10);
		stockQuantity_Field = new JTextField(10);
		minQuantity_Field = new JTextField(10);
		category_Field = new JTextField(10);
		price_Field = new JTextField(10);
		year_Field = new JTextField(10);
		publisher_Field = new JTextField(10);
		
		title_Field.setBounds(360, 50, 500, 30);
		stockQuantity_Field.setBounds(360, 85, 500, 30);
		minQuantity_Field.setBounds(360, 120, 500, 30);
		category_Field.setBounds(360, 155, 500, 30);
		price_Field.setBounds(360, 190, 500, 30);
		year_Field.setBounds(360, 225, 500, 30);
		publisher_Field.setBounds(360, 260, 500, 30);
		
		title_Label = new JLabel("Title : ");
		stockQuantity_Label = new JLabel("stock quantity : ");
		minQuantity_Label = new JLabel("min quantity : ");
		category_Label = new JLabel("category : ");
		price_Label = new JLabel("price : ");
		year_Label = new JLabel("publication year : ");
		publisher_Label = new JLabel("publisher name : ");
		
		title_Label.setBounds(230, 50, 500, 30);
		stockQuantity_Label.setBounds(230, 85, 500, 30);
		minQuantity_Label.setBounds(230, 120, 500, 30);
		category_Label.setBounds(230, 155, 500, 30);
		price_Label.setBounds(230, 190, 500, 30);
		year_Label.setBounds(230, 225, 500, 30);
		publisher_Label.setBounds(230, 260, 500, 30);
		
		this.bookPanel.add(title_Field);
		this.bookPanel.add(title_Label);
		this.bookPanel.add(stockQuantity_Field);
		this.bookPanel.add(stockQuantity_Label);
		this.bookPanel.add(minQuantity_Field);
		this.bookPanel.add(minQuantity_Label);
		this.bookPanel.add(category_Field);
		this.bookPanel.add(category_Label);
		this.bookPanel.add(price_Field);
		this.bookPanel.add(price_Label);
		this.bookPanel.add(year_Field);
		this.bookPanel.add(year_Label);
		this.bookPanel.add(publisher_Field);
		this.bookPanel.add(publisher_Label);
		
		
		this.buttonPanel.add(profile);
		this.buttonPanel.add(library);
		this.buttonPanel.add(log);
		this.buttonPanel.add(viewCustomer);
		this.buttonPanel.add(viewOrders);
		this.buttonPanel.add(logout);
		this.add(addBook);
		this.welcomePanel.add(welcome);
		this.add(welcomePanel);
		this.add(buttonPanel);
		
		
		
		
		this.add(bookPanel);
		
		this.logout.addActionListener(controller);
		this.log.addActionListener(controller);
		this.viewCustomer.addActionListener(controller);
		this.viewOrders.addActionListener(controller);
		this.profile.addActionListener(controller);
		this.library.addActionListener(controller);
		this.addBook.addActionListener(controller);
		
		view.setVisible(true);
		
	}
	
}
