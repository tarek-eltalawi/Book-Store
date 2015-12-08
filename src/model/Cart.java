package model;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import controller.BSController;


public class Cart {
	
	private LinkedList<LineItem> items;
	private String username;
	private static Connection conn = BSController.getConn();

	public Cart(LinkedList<LineItem> items, String username) {
		this.items = items;
		this.username = username;
	}
	
	public static Cart findByUsername(String username){
		String condition = "Customer_username = \"" + username + "\"";
		String command = SQLStatements.selectFromTable("Cart", condition);
		Cart cart = null;
		try {
			ResultSet result = SQLStatements.executeQuery(conn, command);
			LinkedList<LineItem> items = new LinkedList<LineItem>();
			while(result.next()){
				int itemId = result.getInt("LineItem_lineitem_id");
				LineItem item = LineItem.findByItemId(itemId);
				items.add(item);
			}
			cart = new Cart(items, username);
		} catch (SQLException e) {
			/**
			 * not implemented
			 * login failed
			 */
			e.printStackTrace();
		}
		
		
		return cart;
		
	}
	
	public void insertIntoCart(LineItem item){
		items.add(item);
		LinkedList<Object> values = new LinkedList<Object>();
		values.add(this.getUsername());
		values.add(item.getItemId());
		String command = SQLStatements.insertInto("cart", values, null);
		try {
			SQLStatements.executeUpdate(conn, command);
		} catch (SQLException e) {}
	}
	
	public void addBookToCart(Book book) {
		boolean updated = false;
		for(LineItem item : items){
			Book b = item.getBook();
			int quantity = item.getQuantity();
			if(book.getISBN() == b.getISBN()){
				LinkedList<String> columns = new LinkedList<String>();
				columns.add("quantity");
				LinkedList<Object> values = new LinkedList<Object>();
				values.add(quantity+1);
				item.updateItem(columns, values);
				item.setQuantity(quantity + 1);
				updated = true;
				break;
			}
		}
		if(!updated){
			/** book added for the first time in the cart
			 *  create new item with quantity = 1, add it to line item table
			 *  add new record linking that new item to current cart
			 *  */
			String command = "select * from lineitem order by lineitem_id desc limit 1";
			int newid = 0;
			try {
				ResultSet result = SQLStatements.executeQuery(conn, command);
				if(result.next())
					newid = result.getInt("lineitem_id");
			} catch (SQLException e) {}
			newid ++;
			LineItem item  = new LineItem(book, 1, newid);		
			LinkedList<Object> values = new LinkedList<Object>();
			values.add(newid);
			values.add(book.getISBN());
			values.add(1);
			item.insertItem(values);
			insertIntoCart(item);
		}
	}
	
	public boolean removeBookFromCart(LineItem item){
		try {
			boolean deleted = item.removeBook();
			if(deleted){
				items.remove(item);
				removeItemFromCart(item);
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	private void removeItemFromCart(LineItem item) {
		
		String condition = "LineItem_lineitem_id = " + item.getItemId();
		String command = SQLStatements.deleteTuple("cart", condition);
		try {
			SQLStatements.executeUpdate(conn, command);
		} catch (SQLException e) {}
		
	}
	
	public void freeCart(){
		
		for(LineItem item : getItems()){
			item.removeItem();
		}
		String condition = "Customer_username = \"" + getUsername() + "\"";
		String command = SQLStatements.deleteTuple("cart", condition);
		try {
			SQLStatements.executeUpdate(conn, command);
		} catch (SQLException e) {}
	}
	
	public int getTotalPrice(){
		int totalPrice = 0;
		for(LineItem item : items){
			totalPrice += item.getPrice();
		}
		return totalPrice;
	}

	public boolean isEmpty(){
		return items.isEmpty();
	}

	public void setItems(LinkedList<LineItem> items) {
		this.items = items;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LinkedList<LineItem> getItems() {
		return items;
	} 
}
