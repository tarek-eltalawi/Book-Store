package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import controller.BSController;

public class PlacedOrders {
	private int isbn;
	private int quantity;
	public PlacedOrders(int isbn, int quantity) {
		super();
		this.isbn = isbn;
		this.quantity = quantity;
	}
	public int getIsbn() {
		return isbn;
	}
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public static LinkedList<PlacedOrders> getAllPlacedOrders() {
		LinkedList<PlacedOrders> orders = new LinkedList<PlacedOrders>();
		String command = SQLStatements.selectFromTable("orders", "");
		try {
			ResultSet result = SQLStatements.executeQuery(BSController.getConn(), command);
			while(result.next()){
				int isbn = result.getInt("ISBN");
				int quantity = result.getInt("quantity");
				orders.add(new PlacedOrders(isbn, quantity));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orders;
	}
	public void remove() {
		String condition = "ISBN = " + getIsbn();
		String command = SQLStatements.deleteTuple("orders", condition);
		try {
			SQLStatements.executeUpdate(BSController.getConn(), command);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
