package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.LinkedList;

import controller.BSController;

public class BookOrder {
	
	String username;
	int isbn;
	String paymentType;
	String shippingStatus;
	Timestamp paymentDate;
	
	private static Connection conn = BSController.getConn();
	
	public BookOrder(String username, int isbn, String paymentType,
			String shippingStatus, Timestamp paymentDate) {
		this.username = username;
		this.isbn = isbn;
		this.paymentType = paymentType;
		this.shippingStatus = shippingStatus;
		this.paymentDate = paymentDate;
	}
	
	public void insertNewOrder(Connection conn){
		LinkedList<Object> values = new LinkedList<Object>();
		LinkedList<String> columns = new LinkedList<String>();
		columns.add("Customer_username");
		columns.add("Book_ISBN");
		columns.add("payment_type");
		columns.add("shipping_status");
		columns.add("date_of_payment");
		String command = SQLStatements.insertInto("bookorder", values, columns);
		command = command.substring(0, command.length()-2);
		command += " (?, ?, ?, ?, ?);";
		try {
			java.sql.PreparedStatement stmt = conn.prepareStatement(command);
			stmt.setString(1, username);
			stmt.setInt(2, isbn);
			stmt.setString(3, paymentType);
			stmt.setString(4, shippingStatus);
			stmt.setTimestamp(5, paymentDate);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static LinkedList<BookOrder> getBookOrders(String username, Connection conn){
		LinkedList<BookOrder> orders = new LinkedList<BookOrder>();
		
		String condition = "Customer_username = \"" + username + "\"";
		String command = SQLStatements.selectFromTable("bookorder", condition);
		try {
			ResultSet result = SQLStatements.executeQuery(conn, command);
			while(result.next()){
				int isbn = result.getInt("Book_ISBN");
				String paymentType = result.getString("payment_type");
				String shippingStatus = result.getString("shipping_status");
				Timestamp paymentDate = result.getTimestamp("date_of_payment");
				BookOrder order = new BookOrder(username, isbn, paymentType, shippingStatus, paymentDate);
				orders.add(order);
			}
		} catch (SQLException e) {}
		
		return orders;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getShippingStatus() {
		return shippingStatus;
	}

	public void setShippingStatus(String shippingStatus) {
		this.shippingStatus = shippingStatus;
	}

	public Timestamp getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Book getBook() {
		return Book.findByISBN(isbn);
	}

	public static LinkedList<BookOrder> getOrdersByDate(Timestamp ts) throws ParseException {
		LinkedList<BookOrder> orders = new LinkedList<BookOrder>();
		
		String condition = "date_of_payment >= ?";
		String command = SQLStatements.selectFromTable("bookorder", condition);
		try {
			PreparedStatement stmt = conn.prepareStatement(command);
			stmt.setTimestamp(1, ts);
			ResultSet result = stmt.executeQuery();
			while(result.next()){
				String username = result.getString("Customer_username");
				int isbn = result.getInt("Book_ISBN");
				String paymentType = result.getString("payment_type");
				String shippingStatus = result.getString("shipping_status");
				Timestamp paymentDate = result.getTimestamp("date_of_payment");
				BookOrder order = new BookOrder(username, isbn, paymentType, shippingStatus, paymentDate);
				orders.add(order);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		return orders;
	}
	
	
}
