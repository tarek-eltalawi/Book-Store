package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.User;

import controller.BSController;

@SuppressWarnings("serial")
public class Profile extends JPanel{
	
	JFrame view;
	JPanel buttonPanel;
	JPanel mainPanel;
	
	public JButton home;
	public JButton edit;
	
	public Profile() {
		view = View.getInstance();
		
		this.buttonPanel = new JPanel();
		this.buttonPanel.setBounds(0, 0, 210, view.getHeight());
		this.buttonPanel.setBackground(Color.DARK_GRAY);
		
		this.mainPanel = new JPanel();
		this.mainPanel.setBounds(210, 0, view.getWidth(), view.getHeight());
		this.mainPanel.setBackground(Color.WHITE);
		
		this.edit = new JButton("Edit info");
		this.edit.setBounds(50, 30, 120, 50);
		
		this.home = new JButton("Home");
		this.home.setBounds(50, 100, 120, 50);
	}
	
	public void viewPage(BSController controller, User user){
		view.getContentPane().add(this);
		this.setLayout(null);
		this.buttonPanel.setLayout(null);
		this.mainPanel.setLayout(null);
		
		this.buttonPanel.add(edit);
		this.buttonPanel.add(home);
		
		this.edit.addActionListener(controller);
		this.home.addActionListener(controller);
		
		JLabel username = new JLabel("Username : ");
		username.setBounds(220, 60, 100, 30);
		username.setFont(new Font("TimesRoman", Font.PLAIN, 18));
		
		JLabel usernameText = new JLabel();
		usernameText.setBounds(420, 60, 700, 30);
		usernameText.setFont(new Font("TimesRoman", Font.PLAIN, 18));
		usernameText.setText(user.getUsername());
		
		JLabel password = new JLabel("password : ");
		password.setBounds(220, 120, 100, 30);
		password.setFont(new Font("TimesRoman", Font.PLAIN, 18));
		
		JLabel passwordText = new JLabel();
		passwordText.setBounds(420, 120, 700, 30);
		passwordText.setFont(new Font("TimesRoman", Font.PLAIN, 18));
		passwordText.setText(user.getPassword());
		
		JLabel email = new JLabel("email : ");
		email.setBounds(220, 180, 100, 30);
		email.setFont(new Font("TimesRoman", Font.PLAIN, 18));
		
		JLabel emailText = new JLabel();
		emailText.setBounds(420, 180, 700, 30);
		emailText.setFont(new Font("TimesRoman", Font.PLAIN, 18));
		emailText.setText(user.getEmail());
		
		JLabel phonenumber = new JLabel("phonenumber : ");
		phonenumber.setBounds(220, 240, 100, 30);
		phonenumber.setFont(new Font("TimesRoman", Font.PLAIN, 18));
		
		JLabel phonenumberText = new JLabel();
		phonenumberText.setBounds(420, 240, 700, 30);
		phonenumberText.setFont(new Font("TimesRoman", Font.PLAIN, 18));
		phonenumberText.setText(user.getPhonenumber());
		
		JLabel firstname = new JLabel("firstname : ");
		firstname.setBounds(220, 300, 100, 30);
		firstname.setFont(new Font("TimesRoman", Font.PLAIN, 18));
		
		JLabel firstnameText = new JLabel();
		firstnameText.setBounds(420, 300, 700, 30);
		firstnameText.setFont(new Font("TimesRoman", Font.PLAIN, 18));
		firstnameText.setText(user.getFirstname());
		
		JLabel lastname = new JLabel("lastname : ");
		lastname.setBounds(220, 360, 100, 30);
		lastname.setFont(new Font("TimesRoman", Font.PLAIN, 18));
		
		JLabel lastnameText = new JLabel();
		lastnameText.setBounds(420, 360, 700, 30);
		lastnameText.setFont(new Font("TimesRoman", Font.PLAIN, 18));
		lastnameText.setText(user.getLastname());
		
		JLabel address = new JLabel("address : ");
		address.setBounds(220, 420, 100, 30);
		address.setFont(new Font("TimesRoman", Font.PLAIN, 18));
		
		JLabel addressText = new JLabel();
		addressText.setBounds(420, 420, 700, 30);
		addressText.setFont(new Font("TimesRoman", Font.PLAIN, 18));
		addressText.setText(user.getAddress());
		
		this.mainPanel.add(username);
		this.mainPanel.add(password);
		this.mainPanel.add(email);
		this.mainPanel.add(firstname);
		this.mainPanel.add(lastname);
		this.mainPanel.add(phonenumber);
		this.mainPanel.add(address);
		this.mainPanel.add(usernameText);
		this.mainPanel.add(passwordText);
		this.mainPanel.add(emailText);
		this.mainPanel.add(firstnameText);
		this.mainPanel.add(lastnameText);
		this.mainPanel.add(phonenumberText);
		this.mainPanel.add(addressText);
		
		this.add(mainPanel);
		this.add(buttonPanel);
		
		view.setVisible(true);
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
