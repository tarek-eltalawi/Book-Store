package model;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import controller.BSController;


public class Customer extends User{
	
	private Cart cart;
	private static String tableName = "customer";
	private static Connection conn = BSController.getConn();
	
	public Customer(String username, String password, String lastname, String firstname,
			String email, String phonenumber, String address, Cart cart) {
		super(username, password, lastname, firstname, email, phonenumber, address);
		this.cart = cart;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public static Customer finByUsername(String username){
		String condition = "username = \"" + username + "\"";
		String command = SQLStatements.selectFromTable(tableName, condition);
		Customer customer = null;
		try {
			ResultSet result = SQLStatements.executeQuery(conn, command);
			while(result.next()){
				String password = result.getString("password");
				String email = result.getString("email");
				String phonenumber = result.getString("phonenumber");
				String firstname = result.getString("firstname");
				String lastname = result.getString("lastname");
				String address = result.getString("address");
				Cart cart = Cart.findByUsername(username);
				customer = new Customer(username, password, lastname, firstname, email, phonenumber, address, cart);
				break;
			}
		} catch (SQLException e) {
			System.out.println("there are no customers named : " + username);
			e.printStackTrace();
		}
		
		
		return customer;
	}
	
	public void update(LinkedList<Object> values, LinkedList<String > columns){
		
		String condition = "username = " + "\"" + this.getUsername() + "\"";
		String command = SQLStatements.updateTable("customer", columns, values, condition);
		try {
			SQLStatements.executeUpdate(conn, command);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Customer insert(LinkedList<Object> values) {
		
		String command = SQLStatements.insertInto(tableName, values, null);
		Customer customer = null;
		
		try {
			SQLStatements.executeUpdate(conn, command);
			String username = (String) values.getFirst();
			customer = finByUsername(username);
		} catch (SQLException e) {
			System.out.println("insertion failed");
			e.printStackTrace();
		}
		
		return customer;
	}

	public static LinkedList<Customer> getAllCustomers() {
		LinkedList<Customer> customers =  new LinkedList<Customer>();
		String command = SQLStatements.selectFromTable(tableName, "");
		Customer customer = null;
		try {
			ResultSet result = SQLStatements.executeQuery(conn, command);
			while(result.next()){
				String username = result.getString("username");
				String password = result.getString("password");
				String email = result.getString("email");
				String phonenumber = result.getString("phonenumber");
				String firstname = result.getString("firstname");
				String lastname = result.getString("lastname");
				String address = result.getString("address");
				Cart cart = Cart.findByUsername(username);
				customer = new Customer(username, password, lastname, firstname, email, phonenumber, address, cart);
				customers.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return customers;
	}
	

}
