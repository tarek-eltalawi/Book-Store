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
import model.PlacedOrders;

import controller.BSController;

@SuppressWarnings("serial")
public class Orders extends JPanel {
	
	JFrame view;
	JPanel buttonPanel;
	JPanel orderPanel;
	JScrollPane scrollPane;
	
	BSController controller;
	
	LinkedList<JButton> deleteOrderButtons;
	LinkedList<String> orderNames;
	
	public JButton profile;
	public JButton home;
	public JButton search;
	
	public Orders() {
		view = View.getInstance();
		this.deleteOrderButtons = new LinkedList<JButton>();
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
	
	public void viewPage(BSController controller, LinkedList<PlacedOrders> orders){
		this.controller = controller;
		view.getContentPane().add(this);
		this.setLayout(null);
		this.orderPanel.setLayout(null);
		this.buttonPanel.setLayout(null);
		this.buttonPanel.add(profile);
		this.buttonPanel.add(home);
		this.add(buttonPanel);
		this.add(scrollPane);
		
		if(orders == null || orders.isEmpty()){
			JLabel emptyLabel = new JLabel();
			emptyLabel.setBounds(250, 200, 700, 30);
			emptyLabel.setFont(new Font("TimesRoman", Font.PLAIN, 18));
			emptyLabel.setText("There are no orders in the store ..");
		}
		else{
			int y = 40;
			for(final PlacedOrders order : orders){
				Book book = Book.findByISBN(order.getIsbn());
				String temp = book.getTitle() + "  needs  " + order.getQuantity() + " copies";
				orderNames.add(temp);
				
				JButton deleteOrder = new JButton("Delete Order");
				deleteOrder.setBounds(orderPanel.getWidth()-450, y, 130, 30);
				deleteOrder.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						deleteOrders(order);
					}
				});
				this.deleteOrderButtons.add(deleteOrder);
				y += 70;
			}
			this.orderPanel.repaint();
		}
		
		this.add(orderPanel);
		for(JButton btn : this.deleteOrderButtons){
			this.orderPanel.add(btn);
		}
		
		this.home.addActionListener(controller);
		this.profile.addActionListener(controller);
		view.setVisible(true);
	}
	
	private void deleteOrders(PlacedOrders order) {
		this.controller.deletePlacedOrders(order);
	}
	
}
