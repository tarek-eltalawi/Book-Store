package model;
import java.sql.Connection;
import java.util.LinkedList;

public class Authentication {
	
	private static Connection conn;
	
	public static Customer authenticateCustomerLogin(String username, String password){
		
		Customer customer = Customer.finByUsername(username);
		if(customer == null || !password.equalsIgnoreCase(customer.getPassword()))
			return null;
		return customer;
	}
	
	public static Customer authenticateCustomerRegister(LinkedList<Object> values){
		return Customer.insert(values);
	}
	
	public static Manager authenticateManagerLogin(String username, String password){
		
		Manager manager = Manager.finByUsername(username);
		if(manager.getPassword().equalsIgnoreCase(password))
			return manager;
		return null;
	}
	
	public static Connection getConn() {
		return conn;
	}

	public static void setConn(Connection conn) {
		Authentication.conn = conn;
	}

	public static Manager authenticateManagerRegister(LinkedList<Object> values) {
		return Manager.insert(values);
	}
}
