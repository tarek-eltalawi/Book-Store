package model;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Publisher {
	
	private int publisher_id;
	private String name;
	private String address;
	private String telephone_no;
	
	public Publisher(int publisher_id, String name, String address,
			String telephone_no) {
		this.publisher_id = publisher_id;
		this.name = name;
		this.address = address;
		this.telephone_no = telephone_no;
	}
	
	public static Publisher findById(int publisherId, Connection conn) {
		
		String condition = "publisher_id = " + publisherId;
		String command = SQLStatements.selectFromTable("publisher", condition);
		Publisher publisher = null;
		try {
			ResultSet result = SQLStatements.executeQuery(conn, command);
			while(result.next()){
				String name = result.getString("name");
				String address = result.getString("address");
				String telephone_no = result.getString("telephone_no");
				publisher = new Publisher(publisherId, name, address, telephone_no);
			}
		} catch (SQLException e) {
			
		}
		
		return publisher;
	}
	
	public static Publisher findByName(String publisherName, Connection conn) {
		
		String condition = "name = \"" + publisherName + "\"";
		String command = SQLStatements.selectFromTable("publisher", condition);
		Publisher publisher = null;
		try {
			ResultSet result = SQLStatements.executeQuery(conn, command);
			while(result.next()){
				int publisherId = result.getInt("publisher_id");
				String name = result.getString("name");
				String address = result.getString("address");
				String telephone_no = result.getString("telephone_no");
				publisher = new Publisher(publisherId, name, address, telephone_no);
			}
		} catch (SQLException e) {
			
		}
		
		return publisher;
	}
	
	public int getPublisher_id() {
		return publisher_id;
	}

	public void setPublisher_id(int publisher_id) {
		this.publisher_id = publisher_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone_no() {
		return telephone_no;
	}

	public void setTelephone_no(String telephone_no) {
		this.telephone_no = telephone_no;
	}

}
