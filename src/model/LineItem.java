package model;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import controller.BSController;



public class LineItem {
	
	private int itemId;
	private Book book;
	private int quantity;
	
	private static Connection conn = BSController.getConn();
	
	public LineItem(Book book, int quantity, int itemId) {
		this.book = book;
		this.quantity = quantity;
		this.itemId = itemId;
	}
	
	public static LineItem findByItemId(int itemId){
		String condition = "lineitem_id = " + itemId;
		String command = SQLStatements.selectFromTable("lineitem", condition);
		LineItem item = null;
		try {
			ResultSet result = SQLStatements.executeQuery(conn, command);
			while(result.next()){
				int quantity = result.getInt("quantity");
				int bookId = result.getInt("book_isbn");
				Book book = Book.findByISBN(bookId);
				item = new LineItem(book, quantity, itemId);
				break;
			}
		} catch (SQLException e) {
			
		}
		
		return item;
	}
	
	public void insertItem(LinkedList<Object> values){
		String command = SQLStatements.insertInto("lineitem", values, null);
		try {
			SQLStatements.executeUpdate(conn, command);
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	
	public void updateItem(LinkedList<String > columns, LinkedList<Object> values){
		// update quantity to be quantity - 1
		String condition = "lineitem_id = " + this.getItemId();
		String command = SQLStatements.updateTable("lineitem", columns, values, condition);
		try {
			SQLStatements.executeUpdate(conn, command);
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	
	public void removeItem(){
		String condition = "lineitem_id = " + this.getItemId();
		String command = SQLStatements.deleteTuple("lineitem", condition);
		try {
			SQLStatements.executeUpdate(conn, command);
		} catch (SQLException e) {}
	}
	
	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public Book getBook() {
		return book;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean removeBook() {
		int quantity = this.getQuantity();
		if(quantity > 1){
			this.setQuantity(quantity-1);
			// update the line item table with new quantity
			LinkedList<String> columns = new LinkedList<String>();
			LinkedList<Object> values = new LinkedList<Object>();
			columns.add("quantity");
			values.add(quantity-1);
			updateItem(columns, values);
			return false;
		}
		else{
			removeItem();
			return true;
		}
		
	}

	public int getPrice() {
		return book.getSelling_price()*quantity;
	}
	
}
