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
public class Login extends JPanel {
	
	JFrame view;
	public JTextField username_Field;
	public JTextField password_Field;
	
	public JButton login_customer;
	public JButton login_manager;
	public JButton register;
	
	JPanel mainPanel;
	
	public JLabel username_Label;
	public JLabel password_Label;
	
	public Login() {
		view = View.getInstance();
		
		this.setBackground(Color.DARK_GRAY);
		
		// fields initialization
		username_Field = new JTextField(10);
		password_Field = new JTextField(10);
		
		mainPanel = new JPanel();
		mainPanel.setBounds(50, 50, 1250, 470);
		mainPanel.setBackground(Color.WHITE);
		
		// buttons initialization
		login_customer = new JButton("Login as Customer");
		login_manager = new JButton("Login as Manager");
		register = new JButton("go to register page");
		
		login_customer.setBounds(300, 550, 700, 40);
		login_manager.setBounds(300, 600, 700, 40);
		register.setBounds(300, 650, 700, 40);
		
		// Labels initialization
		username_Label = new JLabel("username : ");
		password_Label = new JLabel("password : ");
		
	}
	
	
	public void viewPage(BSController controller) {
		
		// using panel to put button on the window
		view.getContentPane().add(this);
		this.setLayout(null);
		this.add(mainPanel);
		this.mainPanel.add(username_Label);
		this.mainPanel.add(username_Field);
		this.mainPanel.add(password_Label);
		this.mainPanel.add(password_Field);
		this.mainPanel.setLayout(null);
		
		JLabel login = new JLabel("Welcome to Login Page");
		login.setFont(new Font("TimesRoman", Font.BOLD, 35));
		login.setBounds(200, 70, 700, 50);
		
		username_Field.setBounds((this.getWidth()+500), (this.getHeight()+200), 500, 30);
		password_Field.setBounds((this.getWidth()+500), (this.getHeight()+270), 500, 30);
		
		username_Label.setBounds(this.getWidth()+400, (this.getHeight()+200), 200, 15);
		password_Label.setBounds(this.getWidth()+400, (this.getHeight()+270), 200, 15);
		
		login_customer.addActionListener(controller);
		this.add(login_customer);
		
		login_manager.addActionListener(controller);
		this.add(login_manager);
		
		register.addActionListener(controller);
		this.add(register);
		
		this.mainPanel.add(login);
		
		view.setVisible(true);
		
	}
}
