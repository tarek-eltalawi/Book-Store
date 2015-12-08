package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.BSController;

@SuppressWarnings("serial")
public class Payment extends JFrame {
	
	public JButton buyBtn;
	public JPanel mainPanel;
	public JTextField cardNumField;
	public JTextField expireDateField;
	public JLabel errorLabel;
	public Payment() {
		
		setSize(500, 400);
		setTitle("Payment");
		
		// centering the window
		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation((size.width - getWidth())/2, (size.height -
		getHeight())/2);
		
	}
	
	public void viewPage(BSController controller) {
		this.mainPanel = new JPanel();
		this.getContentPane().add(mainPanel);
		this.mainPanel.setLayout(null);
		
		buyBtn = new JButton("Buy");
		errorLabel = new JLabel();
		errorLabel.setBounds(150, 210, 350, 50);
		errorLabel.setForeground(Color.RED);
		this.mainPanel.add(errorLabel);
		
		JLabel cardNumLabel = new JLabel("Credit Card No : ");
		cardNumLabel.setBounds(50, 70, 100, 40);
		this.mainPanel.add(cardNumLabel);
		
		cardNumField = new JTextField();
		cardNumField.setBounds(150, 70, 300, 40);
		this.mainPanel.add(cardNumField);
		
		JLabel expireDateLabel = new JLabel("Expire Date : ");
		expireDateLabel.setBounds(50, 170, 100, 40);
		this.mainPanel.add(expireDateLabel);
		
		expireDateField = new JTextField();
		expireDateField.setBounds(150, 170, 300, 40);
		this.mainPanel.add(expireDateField);
		
		buyBtn.setBounds(150, 300, 100, 50);
		buyBtn.addActionListener(controller);
		
		this.mainPanel.add(buyBtn);
		
		this.setVisible(true);
	}
	
	public void showPayment(BSController controller){
		JPanel infoPanel = new JPanel(){
			@Override
		     public void paintComponent(Graphics g) {
				super.paintComponent(g);
		        g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
		        g.drawString("Thank you for buying from us ..", 50, 60);
		        g.drawString("    your credit card number is currently being checked", 50, 120);
		        g.drawString("    once it's successfully checked the books will be", 50, 180);
		        g.drawString("    shipped to your address.", 50, 240);
		     }
		};
		this.getContentPane().add(infoPanel);
		infoPanel.setLayout(null);
		infoPanel.repaint();
		
		this.setVisible(true);
		
	}
	
}
