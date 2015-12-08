package model;

public abstract class User {

	private String username;
	private String password;
	private String lastname;
	private String firstname;
	private String email;
	private String phonenumber;
	private String address;
	
	
	public User(String username, String password, String lastname,
			String firstname, String email, String phonenumber, String address) {
		super();
		this.username = username;
		this.password = password;
		this.lastname = lastname;
		this.firstname = firstname;
		this.email = email;
		this.phonenumber = phonenumber;
		this.address = address;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhonenumber() {
		return phonenumber;
	}


	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
