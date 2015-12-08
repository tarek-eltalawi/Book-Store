package view;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Book;
import controller.BSController;


@SuppressWarnings("serial")
public class Modify extends JPanel {
	
	JFrame view;
	
	JPanel mainPanel;
	JPanel buttonPanel;
	
	public JButton home;
	public JButton update;
	
	public JTextField titleField;
	public JTextField stockQuantityField;
	public JTextField minQuantityField;
	public JTextField categoryField;
	public JTextField priceField;
	public JTextField yearField;
	public JTextField publisherField;
	
	
	
	public JLabel titleLabel;
	public JLabel stockQuantityLabel;
	public JLabel minQuantityLabel;
	public JLabel categoryLabel;
	public JLabel priceLabel;
	public JLabel yearLabel;
	public JLabel publisherLabel;
	
	private Book book;
	public Modify(Book book) {
		this.book = book;
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
		titleField = new JTextField(10);
		stockQuantityField = new JTextField(10);
		minQuantityField = new JTextField(10);
		categoryField = new JTextField(10);
		priceField = new JTextField(10);
		yearField = new JTextField(10);
		publisherField = new JTextField(10);
		
		titleField.setBounds(560, 25, 500, 30);
		stockQuantityField.setBounds(560, 95, 500, 30);
		minQuantityField.setBounds(560, 165, 500, 30);
		categoryField.setBounds(560, 235, 500, 30);
		priceField.setBounds(560, 305, 500, 30);
		yearField.setBounds(560, 375, 500, 30);
		publisherField.setBounds(560, 445, 500, 30);
		
		// buttons initialization
		this.update = new JButton("Update");
		this.update.setBounds(400, 600, 120, 40);
		
		// Labels initialization
		titleLabel = new JLabel("Title : ");
		stockQuantityLabel = new JLabel("stock quantity : ");
		minQuantityLabel = new JLabel("min quantity : ");
		categoryLabel = new JLabel("category : ");
		priceLabel = new JLabel("selling price : ");
		yearLabel = new JLabel("publication year : ");
		publisherLabel = new JLabel("publisher name : ");
		
		titleLabel.setBounds(400, 30, 200, 15);
		stockQuantityLabel.setBounds(400, 100, 200, 15);
		minQuantityLabel.setBounds(400, 170, 200, 15);
		categoryLabel.setBounds(400, 240, 300, 15);
		priceLabel.setBounds(400, 310, 200, 15);
		yearLabel.setBounds(400, 380, 200, 15);
		publisherLabel.setBounds(400, 450, 200, 15);

	}
	
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public void viewPage(BSController controller) {
		
		// using panel to put button on the window
		view.getContentPane().add(this);
		this.setLayout(null);
		this.mainPanel.setLayout(null);
		this.buttonPanel.setLayout(null);
		this.mainPanel.add(titleLabel);
		this.mainPanel.add(titleField);
		this.mainPanel.add(stockQuantityLabel);
		this.mainPanel.add(stockQuantityField);
		this.mainPanel.add(minQuantityLabel);
		this.mainPanel.add(minQuantityField);
		this.mainPanel.add(categoryLabel);
		this.mainPanel.add(categoryField);
		this.mainPanel.add(priceLabel);
		this.mainPanel.add(priceField);
		this.mainPanel.add(yearLabel);
		this.mainPanel.add(yearField);
		this.mainPanel.add(publisherLabel);
		this.mainPanel.add(publisherField);
		
		update.addActionListener(controller);
		this.mainPanel.add(update);
		
		home.addActionListener(controller);
		this.buttonPanel.add(home);
		
		this.add(mainPanel);
		this.add(buttonPanel);
		
		view.setVisible(true);
		
	}
}
