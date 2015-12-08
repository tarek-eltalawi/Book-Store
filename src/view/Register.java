package view;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import controller.BSController;


@SuppressWarnings("serial")
public class Register extends JPanel {
	
	JFrame view;
	JPanel mainPanel;
	public JTextField username_Field;
	public JTextField password_Field;
	public JTextField email_Field;
	public JTextField phonenumber_Field;
	public JTextField firstname_Field;
	public JTextField lastname_Field;
	public JTextField address_Field;
	
	
	public JButton login;
	public JButton register_customer;
	public JButton register_manager;
	
	public JLabel username_Label;
	public JLabel password_Label;
	public JLabel email_Label;
	public JLabel phonenumber_Label;
	public JLabel firstname_Label;
	public JLabel lastname_Label;
	public JLabel address_Label;
	
	
	public Register() {
		
		view = View.getInstance();
		this.setBackground(Color.DARK_GRAY);
		
		mainPanel = new JPanel();
		mainPanel.setBounds(50, 50, 1250, 480);
		mainPanel.setBackground(Color.WHITE);
		
		// text fields initialization
		username_Field = new JTextField(10);
		password_Field = new JTextField(10);
		email_Field = new JTextField(10);
		phonenumber_Field = new JTextField(10);
		firstname_Field = new JTextField(10);
		lastname_Field = new JTextField(10);
		address_Field = new JTextField(10);
		
		username_Field.setBounds(360, 185, 500, 30);
		password_Field.setBounds(360, 220, 500, 30);
		email_Field.setBounds(360, 255, 500, 30);
		phonenumber_Field.setBounds(360, 290, 500, 30);
		firstname_Field.setBounds(360, 325, 500, 30);
		lastname_Field.setBounds(360, 360, 500, 30);
		address_Field.setBounds(360, 395, 500, 30);
		
		// Labels initialization
		username_Label = new JLabel("username : ");
		password_Label = new JLabel("password : ");
		email_Label = new JLabel("email : ");
		phonenumber_Label = new JLabel("phone number : ");
		firstname_Label = new JLabel("first name : ");
		lastname_Label = new JLabel("last name : ");
		address_Label = new JLabel("address : ");
		
		username_Label.setBounds(250, 185, 200, 15);
		password_Label.setBounds(250, 220, 200, 15);
		email_Label.setBounds(250, 255, 200, 15);
		phonenumber_Label.setBounds(250, 290, 200, 15);
		firstname_Label.setBounds(250, 325, 200, 15);
		lastname_Label.setBounds(250, 360, 200, 15);
		address_Label.setBounds(250, 395, 200, 15);
		
		// buttons initialization
		register_customer = new JButton("Register as Customer");
		register_manager = new JButton("Register as Manager");
		login = new JButton("go to login page");
		
		register_customer.setBounds(300, 550, 700, 40);
		register_manager.setBounds(300, 600, 700, 40);
		login.setBounds(300, 650, 700, 40);
		

	}
	
	public void viewPage(BSController controller) {
		
		// using panel to put button on the window
		view.getContentPane().add(this);
		this.setLayout(null);
		this.mainPanel.setLayout(null);
		this.mainPanel.add(username_Label);
		this.mainPanel.add(username_Field);
		this.mainPanel.add(password_Label);
		this.mainPanel.add(password_Field);
		this.mainPanel.add(email_Label);
		this.mainPanel.add(email_Field);
		this.mainPanel.add(phonenumber_Label);
		this.mainPanel.add(phonenumber_Field);
		this.mainPanel.add(firstname_Label);
		this.mainPanel.add(firstname_Field);
		this.mainPanel.add(lastname_Label);
		this.mainPanel.add(lastname_Field);
		this.mainPanel.add(address_Label);
		this.mainPanel.add(address_Field);
		
		JLabel register = new JLabel("Welcome to Register Page");
		register.setFont(new Font("TimesRoman", Font.BOLD, 35));
		register.setBounds(200, 70, 700, 50);
		
		register_customer.addActionListener(controller);
		this.add(register_customer);
		
		register_manager.addActionListener(controller);
		this.add(register_manager);
		
		login.addActionListener(controller);
		this.add(login);
		
		this.mainPanel.add(register);
		this.add(mainPanel);
		view.setVisible(true);
		
	}
}
