package model;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import controller.BSController;

public class Manager extends User{
	
	private static Connection conn = BSController.getConn();
	
	public Manager(String username, String password, String lastname, String firstname,
			String email, String phonenumber, String address) {
		super(username, password, lastname, firstname, email, phonenumber, address);
	}
	
	public static Manager finByUsername(String username){
		String condition = "username = \"" + username + "\"";
		String command = SQLStatements.selectFromTable("manager", condition);
		Manager manager = null;
		try {
			ResultSet result = SQLStatements.executeQuery(conn, command);
			while(result.next()){
				String password = result.getString("password");
				String email = result.getString("email");
				String phonenumber = result.getString("phonenumber");
				String firstname = result.getString("firstname");
				String lastname = result.getString("lastname");
				String address = result.getString("address");
				manager = new Manager(username, password, lastname, firstname, email, phonenumber, address);
				break;
			}
		} catch (SQLException e) {}
		
		return manager;
	}

	public static Manager insert(LinkedList<Object> values) {
		String command = SQLStatements.insertInto("manager", values, null);
		Manager manager = null;
		
		try {
			SQLStatements.executeUpdate(conn, command);
			String username = (String) values.getFirst();
			manager = finByUsername(username);
		} catch (SQLException e) {}
		
		return manager;
	}

	public void update(LinkedList<Object> values, LinkedList<String> columns) {
		String condition = "username = " + "\"" + this.getUsername() + "\"";
		String command = SQLStatements.updateTable("manager", columns, values, condition);
		try {
			SQLStatements.executeUpdate(conn, command);
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	
}
