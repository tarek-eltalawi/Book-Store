package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.BookOrder;

import controller.BSController;

@SuppressWarnings("serial")
public class Log extends JPanel {
	
	JFrame view;
	JPanel buttonPanel;
	JPanel orderPanel;
	JScrollPane scrollPane;
	
	BSController controller;
	
	LinkedList<String> orderNames;
	
	public JButton profile;
	public JButton home;
	
	public JButton topBooks;
	
	public Log() {
		view = View.getInstance();
		this.orderNames = new LinkedList<String>();
		
		this.buttonPanel = new JPanel();
		this.buttonPanel.setBounds(0, 0, 210, view.getHeight());
		this.buttonPanel.setBackground(Color.DARK_GRAY);
		
		this.orderPanel = new JPanel(){
			@Override
		     public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
				int y = 50;
				for(String s : orderNames){
					g.drawString("    -" + s, 50, y);
		        	y += 30;
		        	g.fillRect(0, y, orderPanel.getWidth()-50, 7);
		        	y += 40;
				}
				
			}
		};
		this.orderPanel.setBounds(210, 0, view.getWidth(), view.getHeight());
		this.orderPanel.setBackground(Color.WHITE);
		
		this.profile = new JButton("Profile");
		this.profile.setBounds(50, 30, 120, 50);
		
		this.home = new JButton("Home");
		this.home.setBounds(50, 100, 120, 50);
		
		this.scrollPane = new JScrollPane(orderPanel);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(orderPanel.getWidth()-50, 0, 50, orderPanel.getHeight());
	}
	
	public void viewPage(BSController controller, LinkedList<BookOrder> orders, String user){
		this.controller = controller;
		view.getContentPane().add(this);
		this.setLayout(null);
		this.orderPanel.setLayout(null);
		this.buttonPanel.setLayout(null);
		this.buttonPanel.add(profile);
		this.buttonPanel.add(home);
		this.add(buttonPanel);
		this.add(scrollPane);
		
		if(user.equals("manager")){
			
			topBooks = new JButton("Statistics");
			topBooks.setBounds(220, orderPanel.getHeight()-110, 140, 40);
			topBooks.addActionListener(controller);
			orderPanel.add(topBooks);
			
		}
		
		if(orders == null || orders.isEmpty()){
			JLabel emptyLabel = new JLabel();
			emptyLabel.setBounds(250, 200, 700, 30);
			emptyLabel.setFont(new Font("TimesRoman", Font.PLAIN, 18));
			emptyLabel.setText("You haven't made any orders in the past months ..");
		}
		else{
			for(final BookOrder order : orders){
				String temp = order.getUsername() + " " + order.getBook().getTitle() + " " + order.getPaymentType() + " " 
						+ order.getShippingStatus() + " " +order.getPaymentDate();
				orderNames.add(temp);
			}
			this.orderPanel.repaint();
		}
		
		this.add(orderPanel);
		
		this.home.addActionListener(controller);
		this.profile.addActionListener(controller);
		view.setVisible(true);
	}
	
}
