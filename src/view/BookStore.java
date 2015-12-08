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

import model.Book;
import model.Cart;
import model.Customer;

import controller.BSController;

@SuppressWarnings("serial")
public class BookStore extends JPanel {
	
	JFrame view;
	JPanel buttonPanel;
	JPanel bookPanel;
	JScrollPane scrollPane;
	
	BSController controller;
	
	LinkedList<JButton> addToCartButtons;
	LinkedList<String> bookNames;
	
	public JButton profile;
	public JButton home;
	public JButton search;
	
	Customer customer;
	
	public BookStore(Customer customer) {
		view = View.getInstance();
		this.customer = customer;
		this.addToCartButtons = new LinkedList<JButton>();
		this.bookNames = new LinkedList<String>();
		
		this.buttonPanel = new JPanel();
		this.buttonPanel.setBounds(0, 0, 210, view.getHeight());
		this.buttonPanel.setBackground(Color.DARK_GRAY);
		
		this.bookPanel = new JPanel(){
			@Override
		     public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
				int y = 50;
				for(String s : bookNames){
					g.drawString("    -" + s, 50, y);
		        	y += 30;
		        	g.fillRect(0, y, bookPanel.getWidth()-50, 7);
		        	y += 40;
				}
				
			}
		};
		this.bookPanel.setBounds(210, 0, view.getWidth(), view.getHeight());
		this.bookPanel.setBackground(Color.WHITE);
		
		this.profile = new JButton("Profile");
		this.profile.setBounds(50, 30, 120, 50);
		
		this.home = new JButton("Home");
		this.home.setBounds(50, 100, 120, 50);
		
		this.scrollPane = new JScrollPane(bookPanel);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(bookPanel.getWidth()-50, 0, 50, bookPanel.getHeight());
	}
	
	public void viewPage(BSController controller, LinkedList<Book> books){
		this.controller = controller;
		view.getContentPane().add(this);
		this.setLayout(null);
		this.bookPanel.setLayout(null);
		this.buttonPanel.setLayout(null);
		this.buttonPanel.add(profile);
		this.buttonPanel.add(home);
		this.add(buttonPanel);
		this.add(scrollPane);
		
		search = new JButton("Search");
		search.setBounds(bookPanel.getWidth()-450, bookPanel.getHeight()-100, 150, 50);
		this.bookPanel.add(search);
		search.addActionListener(controller);
		
		final Cart cart = customer.getCart();
		if(books == null || books.isEmpty()){
			JLabel emptyLabel = new JLabel();
			emptyLabel.setBounds(250, 200, 700, 30);
			emptyLabel.setFont(new Font("TimesRoman", Font.PLAIN, 18));
			emptyLabel.setText("There are no books in the store ..");
		}
		else{
			int y = 40;
			for(final Book book : books){
				String temp = book.getTitle() + ", Category :" + book.getCategory() + ", Price :" + book.getSelling_price() + ", Publication Year : " +book.getPublication_year();
				bookNames.add(temp);
				
				JButton addToCart = new JButton("Add to Cart");
				addToCart.setBounds(bookPanel.getWidth()-450, y, 130, 30);
				addToCart.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						addBooks(cart, book);
					}
				});
				this.addToCartButtons.add(addToCart);
				y += 70;
			}
			this.bookPanel.repaint();
		}
		
		this.add(bookPanel);
		for(JButton btn : this.addToCartButtons){
			this.bookPanel.add(btn);
		}
		
		this.home.addActionListener(controller);
		this.profile.addActionListener(controller);
		view.setVisible(true);
	}
	
	
	
	
	
	
	
	
	private void addBooks(Cart cart, Book book) {
		
		this.controller.addBooks(book);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
