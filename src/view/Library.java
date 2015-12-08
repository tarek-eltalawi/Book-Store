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

import controller.BSController;

@SuppressWarnings("serial")
public class Library extends JPanel {
	
	JFrame view;
	JPanel buttonPanel;
	JPanel bookPanel;
	JScrollPane scrollPane;
	
	BSController controller;
	
	LinkedList<JButton> modifyBook;
	LinkedList<String> bookNames;
	
	public JButton profile;
	public JButton home;
	public JButton search;
	
	public Library() {
		view = View.getInstance();
		this.modifyBook = new LinkedList<JButton>();
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
		
		if(books == null || books.isEmpty()){
			JLabel emptyLabel = new JLabel();
			emptyLabel.setBounds(250, 200, 700, 30);
			emptyLabel.setFont(new Font("TimesRoman", Font.PLAIN, 18));
			emptyLabel.setText("There are no books in the store ..");
		}
		else{
			int y = 40;
			for(final Book book : books){
				String temp = book.getTitle() + " " + book.getCategory() + " " + book.getSelling_price() + " " +book.getPublication_year() 
						+ "         books remaining = " + book.getStock_quantity();
				bookNames.add(temp);
				
				JButton modify = new JButton("Modify Book");
				modify.setBounds(bookPanel.getWidth()-450, y, 130, 30);
				modify.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						modifyBook(book);
					}
				});
				this.modifyBook.add(modify);
				y += 70;
			}
			this.bookPanel.repaint();
		}
		
		this.add(bookPanel);
		for(JButton btn : this.modifyBook){
			this.bookPanel.add(btn);
		}
		
		this.home.addActionListener(controller);
		this.profile.addActionListener(controller);
		view.setVisible(true);
	}
	
	
	private void modifyBook(Book book) {
		
		this.controller.modifyBook(book);
		
	}
	
	

}
