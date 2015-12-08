package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.Customer;

import controller.BSController;

@SuppressWarnings("serial")
public class Customers extends JPanel {
	
	JFrame view;
	JPanel buttonPanel;
	JPanel customerPanel;
	JScrollPane scrollPane;
	
	BSController controller;
	
	LinkedList<String> customerNames;
	LinkedList<JButton> promoteButtons;
	
	public JButton profile;
	public JButton home;
	
	public JButton topBooks;
	
	public Customers() {
		view = View.getInstance();
		this.customerNames = new LinkedList<String>();
		this.promoteButtons = new LinkedList<JButton>();
		
		this.buttonPanel = new JPanel();
		this.buttonPanel.setBounds(0, 0, 210, view.getHeight());
		this.buttonPanel.setBackground(Color.DARK_GRAY);
		
		this.customerPanel = new JPanel(){
			@Override
		     public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
				int y = 50;
				for(String s : customerNames){
					g.drawString("    -" + s, 50, y);
		        	y += 30;
		        	g.fillRect(0, y, customerPanel.getWidth()-50, 7);
		        	y += 40;
				}
				
			}
		};
		this.customerPanel.setBounds(210, 0, view.getWidth(), view.getHeight());
		this.customerPanel.setBackground(Color.WHITE);
		
		this.profile = new JButton("Profile");
		this.profile.setBounds(50, 30, 120, 50);
		
		this.home = new JButton("Home");
		this.home.setBounds(50, 100, 120, 50);
		
		this.scrollPane = new JScrollPane(customerPanel);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(customerPanel.getWidth()-50, 0, 50, customerPanel.getHeight());
	}
	
	public void viewPage(BSController controller, LinkedList<Customer> customers){
		this.controller = controller;
		view.getContentPane().add(this);
		this.setLayout(null);
		this.customerPanel.setLayout(null);
		this.buttonPanel.setLayout(null);
		this.buttonPanel.add(profile);
		this.buttonPanel.add(home);
		this.add(buttonPanel);
		this.add(scrollPane);
		
		if(customers == null || customers.isEmpty()){
			JLabel emptyLabel = new JLabel();
			emptyLabel.setBounds(250, 200, 700, 30);
			emptyLabel.setFont(new Font("TimesRoman", Font.PLAIN, 18));
			emptyLabel.setText("You haven't made any customers in the past months ..");
		}
		else{
			int y = 40;
			for(final Customer customer : customers){
				String temp = customer.getUsername() + " " + customer.getFirstname() + " " + customer.getLastname()
						+ " " + customer.getEmail() + " " + customer.getAddress() + " " + customer.getPhonenumber();
				customerNames.add(temp);
				JButton promote = new JButton("Promote Customer");
				promote.setBounds(customerPanel.getWidth()-450, y, 160, 30);
				promote.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						promoteCustomer(customer);
					}
				});
				this.promoteButtons.add(promote);
				y += 70;
			}
			this.customerPanel.repaint();
		}
		
		this.add(customerPanel);
		for(JButton btn : this.promoteButtons){
			this.customerPanel.add(btn);
		}
		
		this.home.addActionListener(controller);
		this.profile.addActionListener(controller);
		view.setVisible(true);
	}

	protected void promoteCustomer(Customer customer) {
		this.controller.promoteCustomer(customer);
	}
	
}
