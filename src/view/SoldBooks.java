package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Book;
import model.Customer;
import controller.BSController;

@SuppressWarnings("serial")
public class SoldBooks extends JPanel {
	
	JFrame view;
	JPanel buttonPanel;
	JPanel bookStatsPanel;
	JScrollPane scrollPane;
	
	BSController controller;
	
	LinkedList<String> bookStats;
	LinkedList<String> customerStats;
	
	public JButton profile;
	public JButton home;
	
	public SoldBooks() {
		view = View.getInstance();
		this.bookStats = new LinkedList<String>();
		this.customerStats = new LinkedList<String>();
		
		this.buttonPanel = new JPanel();
		this.buttonPanel.setBounds(0, 0, 210, view.getHeight());
		this.buttonPanel.setBackground(Color.DARK_GRAY);
		
		this.bookStatsPanel = new JPanel(){
			@Override
		     public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
				g.drawString("Total Sold book ordered by most sold ones : ", 50, 40);
				int y = 90;
				for(String s : bookStats){
					g.drawString("    -" + s, 50, y);
		        	y += 30;
		        	g.fillRect(0, y, bookStatsPanel.getWidth()/3, 7);
		        	y += 40;
				}
				g.fillRect((bookStatsPanel.getWidth()/3), 0, 7, bookStatsPanel.getHeight());
				g.drawString("Top 5 Customers in last 3 months : ", (bookStatsPanel.getWidth()/3)+20, 40);
				y = 90;
				for(String s : customerStats){
					g.drawString("    -" + s, (bookStatsPanel.getWidth()/3)+20, y);
		        	y += 30;
		        	g.fillRect((bookStatsPanel.getWidth()/3), y, bookStatsPanel.getWidth(), 7);
		        	y += 40;
				}
			}
		};
		this.bookStatsPanel.setBounds(210, 0, view.getWidth(), view.getHeight());
		this.bookStatsPanel.setBackground(Color.WHITE);
		
		this.profile = new JButton("Profile");
		this.profile.setBounds(50, 30, 120, 50);
		
		this.home = new JButton("Home");
		this.home.setBounds(50, 100, 120, 50);
		
		this.scrollPane = new JScrollPane(bookStatsPanel);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(bookStatsPanel.getWidth()-50, 0, 50, bookStatsPanel.getHeight());
	}
	
	@SuppressWarnings("rawtypes")
	public void viewPage(BSController controller, HashMap<Book, Integer> bookMap, HashMap<Customer, Integer> customerMap){
		this.controller = controller;
		view.getContentPane().add(this);
		this.setLayout(null);
		this.bookStatsPanel.setLayout(null);
		this.buttonPanel.setLayout(null);
		this.buttonPanel.add(profile);
		this.buttonPanel.add(home);
		this.add(buttonPanel);
		this.add(scrollPane);
		
		if(bookMap == null || bookMap.isEmpty()){
			JLabel emptyLabel = new JLabel();
			emptyLabel.setBounds(250, 200, 700, 30);
			emptyLabel.setFont(new Font("TimesRoman", Font.PLAIN, 18));
			emptyLabel.setText("You haven't made any orders in the past months ..");
		}
		else{
			Iterator it = bookMap.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        Book book = (Book) pair.getKey();
		        String temp = book.getTitle() + " => " + pair.getValue() + " copies";
		        it.remove();
		        bookStats.add(temp);
		    }
		    it = customerMap.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        Customer customer = (Customer) pair.getKey();
		        String temp = customer.getFirstname() + " " + customer.getLastname() + " BOUGHT " + pair.getValue() + " books";
		        it.remove();
		        customerStats.add(temp);
		    }
			this.bookStatsPanel.repaint();
		}
		
		this.add(bookStatsPanel);
		
		this.home.addActionListener(controller);
		this.profile.addActionListener(controller);
		view.setVisible(true);
	}
	
}
