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

import controller.BSController;
import model.Book;
import model.Cart;
import model.Customer;
import model.LineItem;

@SuppressWarnings("serial")
public class CustomerHomepage extends JPanel {
	
	JFrame view;
	
	JPanel welcomePanel;
	JPanel buttonPanel;
	JPanel cartPanel;
	JScrollPane scrollPane;
	
	LinkedList<JButton> removeButtons;
	
	private BSController controller;
	public JButton profile;
	public JButton logout;
	public JButton startShopping;
	public JButton log;
	public JButton checkOut;
	public JLabel welcome;
	LinkedList<String> items;
	
	public CustomerHomepage() {
		view = View.getInstance();
		this.items = new LinkedList<String>();
		this.removeButtons = new LinkedList<JButton>();
		
		this.profile = new JButton("Profile");
		this.profile.setBounds(50, 30, 120, 50);
		this.startShopping = new JButton("Start Shopping");
		this.startShopping.setBounds(50, 100, 120, 50);
		this.log = new JButton("View Log");
		this.log.setBounds(50, 170, 120, 50);
		this.logout = new JButton("Log out");
		this.logout.setBounds(50, view.getHeight()-150, 120, 50);
		this.checkOut = new JButton("Check out");
		this.checkOut.setBounds(view.getWidth()-150, view.getHeight()-150, 120, 50);
		
		this.welcomePanel = new JPanel();
		this.welcomePanel.setBounds(210, 0, view.getWidth(), 180);
		this.welcomePanel.setBackground(Color.WHITE);
		
		this.buttonPanel = new JPanel();
		this.buttonPanel.setBounds(0, 0, 210, view.getHeight());
		this.buttonPanel.setBackground(Color.DARK_GRAY);
		
		this.cartPanel = new JPanel(){
			@Override
		     public void paintComponent(Graphics g) {
				super.paintComponent(g);
				int y = 50;
		        g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
		        for(String s : items){
		        	g.drawString("    -" + s, 50, y);
		        	y += 30;
		        	g.fillRect(50, y, cartPanel.getWidth(), 7);
		        	y += 40;
		        }
		     }
		};
		this.cartPanel.setBounds(210, 250, view.getWidth(), view.getHeight()-450);
		this.cartPanel.setBackground(Color.WHITE);

		
		this.scrollPane = new JScrollPane(cartPanel);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(cartPanel.getWidth()-50, 250, 50, cartPanel.getHeight());
	}
	
	public void viewPage(BSController controller, Customer customer){
		
		this.controller = controller;
		view.getContentPane().add(this);
		this.setLayout(null);
		this.welcomePanel.setLayout(null);
		this.buttonPanel.setLayout(null);
		this.cartPanel.setLayout(null);

		this.welcome = new JLabel("Welcome " + customer.getFirstname() + " " + customer.getLastname());
		this.welcome.setBounds(500, 20, 500, 100);
		this.welcome.setFont(new Font("TimesRoman", Font.BOLD, 30));
		
		this.buttonPanel.add(profile);
		this.buttonPanel.add(startShopping);
		this.buttonPanel.add(log);
		this.buttonPanel.add(logout);
		this.add(checkOut);
		this.welcomePanel.add(welcome);
		this.add(welcomePanel);
		this.add(buttonPanel);
		this.add(scrollPane);
		
		
		JLabel cartLabel = new JLabel();
		cartLabel.setBounds(250, 200, 700, 30);
		cartLabel.setFont(new Font("TimesRoman", Font.PLAIN, 18));
		final Cart cart = customer.getCart();
		if(cart == null || cart.isEmpty()){
			cartLabel.setText("Your cart is empty ..");
		}
		else{
			cartLabel.setText("Your cart contains the following Items : ");
			int y = 40;
			for(final LineItem item : cart.getItems()){
				Book book = item.getBook();
				final int quantity = item.getQuantity();
				String temp = book.getTitle() + "   " + quantity + "x                         " + book.getSelling_price()*quantity + "$";
				items.add(temp);
				
				JButton remove = new JButton("Remove 1 book");
				remove.setBounds(cartPanel.getWidth()-450, y, 130, 30);
				remove.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						updateCart(cart, item);
					}
				});
				
				this.removeButtons.add(remove);
				y += 70;
			}
			
			
			this.cartPanel.repaint();
			JLabel totalPrice = new JLabel("" + cart.getTotalPrice());
			totalPrice.setBounds(250, view.getHeight()-300, 500, 100);
			totalPrice.setFont(new Font("TimesRoman", Font.BOLD, 30));
			this.add(totalPrice);
		}
		
		
		
		this.add(cartPanel);
		this.add(cartLabel);
		
		for(JButton btn : this.removeButtons){
			this.cartPanel.add(btn);
		}
		
		this.logout.addActionListener(controller);
		this.log.addActionListener(controller);
		this.profile.addActionListener(controller);
		this.startShopping.addActionListener(controller);
		this.checkOut.addActionListener(controller);
		
		view.setVisible(true);
		
	}
	
	private void updateCart(Cart cart, LineItem item) {
		// TODO Auto-generated method stub
		this.controller.updateCart(cart, item);
	}
	
}
