package model;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;


public class SQLStatements {
	
	public static String insertInto(String tableName, LinkedList<Object> values, LinkedList<String> columns) {
		String column = "";
		if(columns != null && !columns.isEmpty()){
			column = "(";
			for(String s : columns){
				column += s + ",";
			}
			column = column.substring(0, column.length()-1);
			column += ")";
		}
		
		String command = "INSERT INTO " + tableName + column + " VALUES (";
		for(Object value : values){
			command += " \"" + value + " \",";
		}
		
		command = command.substring(0, command.length()-1);
		command += " );";
		
		return command;
	}
	
	public static String deleteTuple(String tableName, String condition){
		return "DELETE FROM " + tableName + " WHERE " + condition;
	}
	
	public static String updateTable(String tableName, LinkedList<String> columns ,
			LinkedList<Object> values, String condition){
		String command = "UPDATE " + tableName + " SET ";
		for(int i = 0; i < columns.size(); i ++){
			String column = columns.get(i);
			Object value = values.get(i);
			command += column + " = \"" + value + "\",";
		}
		command = command.substring(0, command.length()-1);
		command +=  " WHERE " + condition + " ;";
		return command;
	}
	
	public static String selectFromTable(String tableName, String condition){
		if(condition.equals(""))
			return "SELECT * FROM " + tableName;
		return "SELECT * FROM " + tableName + " WHERE " + condition;
	}
	
	public static ResultSet executeQuery(Connection conn, String command) throws SQLException {
	    Statement stmt = null;
	    stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery(command); // This will throw a SQLException if it fails
		return result;
	}
	
	/**
	 * Run a SQL command which does not return a recordset:
	 * CREATE/INSERT/UPDATE/DELETE/DROP/etc.
	 * 
	 * @throws SQLException If something goes wrong
	 */
	public static int executeUpdate(Connection conn, String command) throws SQLException {
	    Statement stmt = null;
        stmt = conn.createStatement();
        return stmt.executeUpdate(command); 	// This will throw a SQLException if it fails
	}

	
}
