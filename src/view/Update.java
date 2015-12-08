package view;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import controller.BSController;


@SuppressWarnings("serial")
public class Update extends JPanel {
	
	JFrame view;
	
	JPanel mainPanel;
	JPanel buttonPanel;
	
	public JButton home;
	public JButton update;
	
	public JTextField username_Field;
	public JTextField password_Field;
	public JTextField email_Field;
	public JTextField phonenumber_Field;
	public JTextField firstname_Field;
	public JTextField lastname_Field;
	public JTextField address_Field;
	
	
	
	public JLabel username_Label;
	public JLabel password_Label;
	public JLabel email_Label;
	public JLabel phonenumber_Label;
	public JLabel firstname_Label;
	public JLabel lastname_Label;
	public JLabel address_Label;
	
	
	public Update() {
		
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
		username_Field = new JTextField(10);
		password_Field = new JTextField(10);
		email_Field = new JTextField(10);
		phonenumber_Field = new JTextField(10);
		firstname_Field = new JTextField(10);
		lastname_Field = new JTextField(10);
		address_Field = new JTextField(10);
		
		username_Field.setBounds(560, 25, 500, 30);
		password_Field.setBounds(560, 95, 500, 30);
		email_Field.setBounds(560, 165, 500, 30);
		phonenumber_Field.setBounds(560, 235, 500, 30);
		firstname_Field.setBounds(560, 305, 500, 30);
		lastname_Field.setBounds(560, 375, 500, 30);
		address_Field.setBounds(560, 445, 500, 30);
		
		// buttons initialization
		this.update = new JButton("Update");
		this.update.setBounds(400, 600, 120, 40);
		
		// Labels initialization
		username_Label = new JLabel("username : ");
		password_Label = new JLabel("password : ");
		email_Label = new JLabel("email : ");
		phonenumber_Label = new JLabel("phone number : ");
		firstname_Label = new JLabel("first name : ");
		lastname_Label = new JLabel("last name : ");
		address_Label = new JLabel("address : ");
		
		username_Label.setBounds(400, 30, 200, 15);
		password_Label.setBounds(400, 100, 200, 15);
		email_Label.setBounds(400, 170, 200, 15);
		phonenumber_Label.setBounds(400, 240, 300, 15);
		firstname_Label.setBounds(400, 310, 200, 15);
		lastname_Label.setBounds(400, 380, 200, 15);
		address_Label.setBounds(400, 450, 200, 15);

	}
	
	public void viewPage(BSController controller) {
		
		// using panel to put button on the window
		view.getContentPane().add(this);
		this.setLayout(null);
		this.mainPanel.setLayout(null);
		this.buttonPanel.setLayout(null);
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
		
		update.addActionListener(controller);
		this.mainPanel.add(update);
		
		home.addActionListener(controller);
		this.buttonPanel.add(home);
		
		this.add(mainPanel);
		this.add(buttonPanel);
		
		view.setVisible(true);
		
	}
}
