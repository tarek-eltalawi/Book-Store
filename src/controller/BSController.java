package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Properties;

import view.BookStore;
import view.CustomerHomepage;
import view.Customers;
import view.Library;
import view.Log;
import view.Login;
import view.ManagerHomepage;
import view.Modify;
import view.Orders;
import view.Payment;
import view.Profile;
import view.Register;
import view.Search;
import view.SoldBooks;
import view.Update;
import view.View;

import model.Authentication;
import model.Bank;
import model.Book;
import model.BookOrder;
import model.Cart;
import model.Customer;
import model.LineItem;
import model.Manager;
import model.PlacedOrders;
import model.Publisher;


/**
 * This class demonstrates how to connect to MySQL and run some basic commands.
 * 
 * In order to use this, you have to download the Connector/J driver and add
 * its .jar file to your build path.  You can find it here:
 * 
 * http://dev.mysql.com/downloads/connector/j/
 * 
 * You will see the following exception if it's not in your class path:
 * 
 * java.sql.SQLException: No suitable driver found for jdbc:mysql://localhost:3306/
 * 
 * To add it to your class path:
 * 1. Right click on your project
 * 2. Go to Build Path -> Add External Archives...
 * 3. Select the file mysql-connector-java-5.1.24-bin.jar
 *    NOTE: If you have a different version of the .jar file, the name may be
 *    a little different.
 *    
 * The user name and password are both "root", which should be correct if you followed
 * the advice in the MySQL tutorial. If you want to use different credentials, you can
 * change them below. 
 * 
 * You will get the following exception if the credentials are wrong:
 * 
 * java.sql.SQLException: Access denied for user 'userName'@'localhost' (using password: YES)
 * 
 * You will instead get the following exception if MySQL isn't installed, isn't
 * running, or if your serverName or portNumber are wrong:
 * 
 * java.net.ConnectException: Connection refused
 */
public class BSController implements ActionListener{

	/** The name of the MySQL account to use (or empty for anonymous) */
	private final String MySQLuserName = "root";

	/** The password for the MySQL account (or empty for anonymous) */
	private final String MySQLpassword = "";

	/** The name of the computer running MySQL */
	private final String serverName = "localhost";

	/** The port of the MySQL server (default is 3306) */
	private final int portNumber = 3306;

	/** The name of the database we are testing with (this default is installed with MySQL) */
	private final String dbName = "labproject";
	
	/** The connection used to connect to database */
	private static Connection conn;
	
	/** The view object used as a frame to add pages */
	private View view;
	
	private Login loginPage;
	private Register registerPage;
	private CustomerHomepage customerHomePage;
	private ManagerHomepage managerHomepage;
	
	private BookStore storePage;
	private Profile profilePage;
	private Update updatePage;
	private Payment payPage;
	private Search searchPage;
	private Log logPage;
	private Library libraryPage;
	private Modify modifyPage;
	private SoldBooks statsPage;
	private Customers customerListPage;
	private Orders orderPage;
	
	
	private Customer customer;
	private Manager manager;
	/**
	 * Get a new database connection
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.MySQLuserName);
		connectionProps.put("password", this.MySQLpassword);

		conn = DriverManager.getConnection("jdbc:mysql://"
				+ this.serverName + ":" + this.portNumber + "/" + this.dbName,
				connectionProps);

		return conn;
	}

	/**
	 * Connect to MySQL and do some stuff.
	 * @throws InterruptedException 
	 */
	public void run() throws InterruptedException {

		// Connect to MySQL
		conn = null;
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return;
		}
		
		Authentication.setConn(conn);

		view = View.getInstance();
		loginPage = new Login();
		registerPage = new Register();
		profilePage = new Profile();
		payPage = new Payment();
		updatePage = new Update();
		storePage = new BookStore(customer);
		searchPage = new Search();
		logPage = new Log();
		customerHomePage = new CustomerHomepage();
		managerHomepage = new ManagerHomepage();
		libraryPage = new Library();
		modifyPage = new Modify(null);
		statsPage = new SoldBooks();
		customerListPage = new Customers();
		orderPage = new Orders();
		
		/** create the frame of the application */
		view.createFrame();

		/** start the app by viewing the login page */
		loginPage.viewPage(this);
		view.revalidate();
		view.repaint();
		
	}
	
	private void loginCustomer(String username, String password) throws InterruptedException {
		customer = null;
		if(username.equals("")){
			System.out.println("username cannot be empty");
		}
		else if(password.equals("")){
			System.out.println("password cannot be empty");
		}
		else
			customer = Authentication.authenticateCustomerLogin(username, password);
		
		if(customer != null){
			System.out.println(customer.getEmail());
			if(customer.getCart() != null){
				customer.getCart().freeCart();
				customer.setCart(null);
			}
			customerHomePage();
		}
	}
	
	private void loginManager(String username, String password) throws InterruptedException {
		if(username.equals("")){
			System.out.println("username cannot be empty");
		}
		else if(password.equals("")){
			System.out.println("password cannot be empty");
		}
		else
			manager = Authentication.authenticateManagerLogin(username, password);
		
		if(manager != null){
			System.out.println(manager.getEmail());
			managerHomePage();
		}

	}
	private void registerCustomer(String username, String password, String email, String phonenumber, String firstname,
			String lastname, String address) {
		customer = null;
		if(username.equals("")){
			System.out.println("username cannot be empty");
		}
		else if(password.equals("")){
			System.out.println("password cannot be empty");
		}
		else if(email.equals("")){
			System.out.println("email cannot be empty");
		}
		else if(phonenumber.equals("")){
			System.out.println("phonenumber cannot be empty");
		}
		else if(firstname.equals("")){
			System.out.println("firstname cannot be empty");
		}
		else if(lastname.equals("")){
			System.out.println("lastname cannot be empty");
		}
		else if(address.equals("")){
			System.out.println("address cannot be empty");
		}
		else{
			LinkedList<Object> values = new LinkedList<Object>();
			values.add(username);
			values.add(password);
			values.add(email);
			values.add(phonenumber);
			values.add(firstname);
			values.add(lastname);
			values.add(address);
			
			customer = Authentication.authenticateCustomerRegister(values);
			if(customer != null){
				System.out.println(customer.getEmail());
				customerHomePage();
			}
			
		}
	}
	
	public void registerManager(String username, String password, String email, String phonenumber, String firstname,String lastname, String address) {
		manager = null;
		if(username.equals("")){
			System.out.println("username cannot be empty");
		}
		else if(password.equals("")){
			System.out.println("password cannot be empty");
		}
		else if(email.equals("")){
			System.out.println("email cannot be empty");
		}
		else if(phonenumber.equals("")){
			System.out.println("phonenumber cannot be empty");
		}
		else if(firstname.equals("")){
			System.out.println("firstname cannot be empty");
		}
		else if(lastname.equals("")){
			System.out.println("lastname cannot be empty");
		}
		else if(address.equals("")){
			System.out.println("address cannot be empty");
		}
		else{
			LinkedList<Object> values = new LinkedList<Object>();
			values.add(username);
			values.add(email);
			values.add(password);
			values.add(phonenumber);
			values.add(firstname);
			values.add(lastname);
			values.add(address);
			
			manager = Authentication.authenticateManagerRegister(values);
			if(manager != null){
				System.out.println(manager.getEmail());
				managerHomePage();
			}
			
		}
		
	}
	
	private void managerHomePage() {
		view.getContentPane().removeAll();
		view.revalidate();
		view.repaint();
		managerHomepage = new ManagerHomepage();
		managerHomepage.viewPage(this, manager);
		view.revalidate();
		view.repaint();
	}

	private void updateCustomer(String username, String password, String email,
			String phonenumber, String firstname, String lastname,String address) {
		LinkedList<Object> values = new LinkedList<Object>();
		LinkedList<String> columns = new LinkedList<String>();
		
		if(!username.equals("")){
			values.add(username);
			columns.add("username");
			customer.setUsername(username);
		}
		if(!password.equals("")){
			values.add(password);
			columns.add("password");
			customer.setPassword(password);
		}
		if(!email.equals("")){
			values.add(email);
			columns.add("email");
			customer.setEmail(email);
		}
		if(!phonenumber.equals("")){
			values.add(phonenumber);
			columns.add("phonenumber");
			customer.setPhonenumber(phonenumber);
		}
		if(!firstname.equals("")){
			values.add(firstname);
			columns.add("firstname");
			customer.setFirstname(firstname);
		}
		if(!lastname.equals("")){
			values.add(lastname);
			columns.add("lastname");
			customer.setLastname(lastname);
			}
		if(!address.equals("")){
			values.add(address);
			columns.add("address");
			customer.setAddress(address);
		}
		
		if(!values.isEmpty()){
			customer.update(values, columns);
			view.getContentPane().removeAll();
			view.revalidate();
			view.repaint();
			
			profilePage = new Profile();
			profilePage.viewPage(this, customer);
			view.revalidate();
			view.repaint();
		}
		
		
	}
	
	private void updateManager(String username, String password, String email,
			String phonenumber, String firstname, String lastname,String address) {
		LinkedList<Object> values = new LinkedList<Object>();
		LinkedList<String> columns = new LinkedList<String>();
		
		if(!username.equals("")){
			values.add(username);
			columns.add("username");
			manager.setUsername(username);
		}
		if(!password.equals("")){
			values.add(password);
			columns.add("password");
			manager.setPassword(password);
		}
		if(!email.equals("")){
			values.add(email);
			columns.add("email");
			manager.setEmail(email);
		}
		if(!phonenumber.equals("")){
			values.add(phonenumber);
			columns.add("phonenumber");
			manager.setPhonenumber(phonenumber);
		}
		if(!firstname.equals("")){
			values.add(firstname);
			columns.add("firstname");
			manager.setFirstname(firstname);
		}
		if(!lastname.equals("")){
			values.add(lastname);
			columns.add("lastname");
			manager.setLastname(lastname);
			}
		if(!address.equals("")){
			values.add(address);
			columns.add("address");
			manager.setAddress(address);
		}
		
		if(!values.isEmpty()){
			manager.update(values, columns);
			managerProfilePage();
		}
		
		
	}
	
	public void updateCart(Cart cart, LineItem item) {
		cart.removeBookFromCart(item);
		
		customerHomePage();
		
	}
	
	

	private void customerHomePage() {
		
		view.getContentPane().removeAll();
		view.revalidate();
		view.repaint();
		customerHomePage = new CustomerHomepage();
		customerHomePage.viewPage(this, customer);
		view.revalidate();
		view.repaint();
	}
	
	private void customerProfilePage() {
		view.getContentPane().removeAll();
		view.revalidate();
		view.repaint();
		
		profilePage = new Profile();
		profilePage.viewPage(this, customer);
		view.revalidate();
		view.repaint();
	}
	
	private void managerProfilePage() {
		view.getContentPane().removeAll();
		view.revalidate();
		view.repaint();
		
		profilePage = new Profile();
		profilePage.viewPage(this, manager);
		view.revalidate();
		view.repaint();
	}
	
	public void addBooks(Book book){
		Cart cart = customer.getCart();
		if(cart == null || cart.isEmpty()){
			// if customer have no cart yet, create new one
			cart = new Cart(new LinkedList<LineItem>(), customer.getUsername());
			customer.setCart(cart);
		}
		cart.addBookToCart(book);
		LinkedList<Book> books = Book.getAllBooks();
		view.getContentPane().removeAll();
		view.revalidate();
		view.repaint();
		
		storePage = new BookStore(customer);
		storePage.viewPage(this, books);
		view.revalidate();
		view.repaint();
	}
	
	public void validatePayement(String cardNo, String expireDate) throws ParseException, InterruptedException {
		
		boolean bankResponse = Bank.checkValid(cardNo, expireDate);
		if(bankResponse){
			
			String username = customer.getUsername();
			String paymentType = "credit card";
			String shippingStatus = "pending";
			for(LineItem item : customer.getCart().getItems()){
				int isbn = item.getBook().getISBN();
				int quantity = item.getQuantity();
				Date date = new Date();
				Timestamp dateOfPayment = new Timestamp(date.getTime());
				for(int i = 0; i < quantity; i++){
					BookOrder order = new BookOrder(username, isbn, paymentType, shippingStatus, dateOfPayment);
					order.insertNewOrder(conn);
					
			        Calendar cal = Calendar.getInstance();
			        cal.setTimeInMillis(dateOfPayment.getTime());
			        cal.add(Calendar.SECOND, 1);
			        dateOfPayment = new Timestamp(cal.getTime().getTime());
				}
			}
			customer.getCart().freeCart();
			customer.setCart(null);
		}
		
	}
	
	public LinkedList<Book> getMatchingBooks(String title, String category, String year, String publisher) {
		String condition = "";
		if(!title.equals("")){
			condition += " title = \"" + title + "\"";
		}
		if(!category.equals("")){
			if(condition.equals(""))
				condition += " category = \"" + category + "\"";
			else
				condition += " AND category = \"" + category + "\"";
		}
		if(!year.equals("")){
			if(condition.equals(""))
				condition += " publication_year = \"" + year + "\"";
			else
				condition += " AND publication_year = \"" + year + "\"";
		}
		if(!publisher.equals("")){
			try {
				int publisherId = Publisher.findByName(publisher, conn).getPublisher_id();
				if(condition.equals(""))
					condition = "Publisher_publisher_id = " + publisherId;
				else
					condition += " AND Publisher_publisher_id = " + publisherId;
			} catch (Exception e) {
				System.out.println("there are no publishers name " + publisher);
				e.printStackTrace();
			}
		}
		
		if(condition.equals(""))
			return null;
		
		return Book.findBooks(condition);
	}
	
	public LinkedList<BookOrder> getBookOrders() {
		return BookOrder.getBookOrders(customer.getUsername(), conn);
	}
	
	@SuppressWarnings("deprecation")
	public HashMap<Customer, Integer> getTopCustomers(){
		HashMap<Customer, Integer> customerMap = new HashMap<Customer, Integer>();
		java.util.Date date = new java.util.Date();
		java.util.Date previousDate = new java.util.Date(date.getYear(), date.getMonth()-1, date.getDay());
		Timestamp ts = new Timestamp(previousDate.getTime());
		
		String query = "SELECT Customer_username, count(*) FROM bookorder WHERE date_of_payment >= ? GROUP BY Customer_username ORDER BY count(*) desc LIMIT 5;";
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setTimestamp(1, ts);
			ResultSet result = stmt.executeQuery();
			while(result.next()){
				String username = result.getString("Customer_username");
				Customer customer = Customer.finByUsername(username);
				int count = result.getInt("count(*)");
				customerMap.put(customer, count);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return customerMap;
	}
	
	@SuppressWarnings("deprecation")
	public HashMap<Book, Integer> getTopSoldBooks(){
		java.util.Date date = new java.util.Date();
		java.util.Date previousDate = new java.util.Date(date.getYear(), date.getMonth()-1, date.getDay());
		Timestamp ts = new Timestamp(previousDate.getTime());
		
		String query = "SELECT Book_ISBN, count(*) FROM bookorder WHERE date_of_payment >= ? GROUP BY Book_ISBN ORDER BY count(*) desc;";
		HashMap<Book, Integer> bookMap = new HashMap<Book, Integer>();
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setTimestamp(1, ts);
			ResultSet result = stmt.executeQuery();
			while(result.next()){
				int bookId = result.getInt("Book_ISBN");
				Book book = Book.findByISBN(bookId);
				int count = result.getInt("count(*)");
				bookMap.put(book, count);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookMap;
	}
	
	public void promoteCustomer(Customer customer) {
		LinkedList<Object> values = new LinkedList<Object>();
		values.add(customer.getUsername());
		values.add(customer.getEmail());
		values.add(customer.getPassword());
		values.add(customer.getPhonenumber());
		values.add(customer.getFirstname());
		values.add(customer.getLastname());
		values.add(customer.getAddress());
		Manager.insert(values);
	}
	
	public void modifyBook(Book book) {
		
		view.getContentPane().removeAll();
		view.revalidate();
		view.repaint();
		modifyPage = new Modify(book);
		modifyPage.viewPage(this);
		view.revalidate();
		view.repaint();
		
	}
	
	public void deletePlacedOrders(PlacedOrders order) {
		order.remove();
		view.getContentPane().removeAll();
		view.revalidate();
		view.repaint();
		orderPage = new Orders();
		orderPage.viewPage(this, PlacedOrders.getAllPlacedOrders());
		view.revalidate();
		view.repaint();
	}
	
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		try {
			
			if(e.getSource() == loginPage.login_customer){
				String username = loginPage.username_Field.getText();
				String password = loginPage.password_Field.getText();
				loginCustomer(username, password);
			}
			if(e.getSource() == loginPage.login_manager){
				String username = loginPage.username_Field.getText();
				String password = loginPage.password_Field.getText();
				loginManager(username, password);
			}
			if(e.getSource() == loginPage.register){
				view.getContentPane().remove(loginPage);
				view.revalidate();
				view.repaint();
				
				registerPage = new Register();
				registerPage.viewPage(this);
				view.revalidate();
				view.repaint();
			}
			if(e.getSource() == registerPage.register_customer){
				String username = registerPage.username_Field.getText();
				String password = registerPage.password_Field.getText();
				String email = registerPage.email_Field.getText();
				String phonenumber = registerPage.phonenumber_Field.getText();
				String firstname = registerPage.firstname_Field.getText();
				String lastname = registerPage.lastname_Field.getText();
				String address = registerPage.address_Field.getText();
				registerCustomer(username, password, email, phonenumber, firstname, lastname, address);
			}
			if(e.getSource() == registerPage.register_manager){
				String username = registerPage.username_Field.getText();
				String password = registerPage.password_Field.getText();
				String email = registerPage.email_Field.getText();
				String phonenumber = registerPage.phonenumber_Field.getText();
				String firstname = registerPage.firstname_Field.getText();
				String lastname = registerPage.lastname_Field.getText();
				String address = registerPage.address_Field.getText();
				registerManager(username, password, email, phonenumber, firstname, lastname, address);
			}
			if(e.getSource() == registerPage.login){
				view.getContentPane().remove(registerPage);
				view.revalidate();
				view.repaint();
				
				loginPage = new Login();
				loginPage.viewPage(this);
				view.revalidate();
				view.repaint();
			}
			if(e.getSource() == customerHomePage.logout){
				if(customer.getCart() != null)
					customer.getCart().freeCart();
				customer.setCart(null);
				customer = null;
				view.getContentPane().removeAll();
				view.revalidate();
				view.repaint();
				
				loginPage = new Login();
				loginPage.viewPage(this);
				view.revalidate();
				view.repaint();
			}
			if(e.getSource() == managerHomepage.logout){
				manager = null;
				view.getContentPane().removeAll();
				view.revalidate();
				view.repaint();
				
				loginPage = new Login();
				loginPage.viewPage(this);
				view.revalidate();
				view.repaint();
			}
			if(e.getSource() == customerHomePage.startShopping){
				LinkedList<Book> books = Book.getAllBooks();
				view.getContentPane().removeAll();
				view.revalidate();
				view.repaint();
				
				storePage = new BookStore(customer);
				storePage.viewPage(this, books);
				view.revalidate();
				view.repaint();
			}
			if(e.getSource() == storePage.home){
				customerHomePage();
			}
			if(e.getSource() == storePage.profile){
				customerProfilePage();
			}
			if(e.getSource() == storePage.search){
				view.getContentPane().removeAll();
				view.revalidate();
				view.repaint();
				searchPage = new Search();
				searchPage.viewPage(this);
				view.revalidate();
				view.repaint();
			}
			if(e.getSource() == customerHomePage.profile){
				customerProfilePage();
			}
			if(e.getSource() == profilePage.home){
				if(customer != null)
					customerHomePage();
				else if(manager != null)
					managerHomePage();
			}
			if(e.getSource() == profilePage.edit){
				view.getContentPane().removeAll();
				view.revalidate();
				view.repaint();
				
				updatePage = new Update();
				updatePage.viewPage(this);
				view.revalidate();
				view.repaint();
			}
			if(e.getSource() == updatePage.update){
				String username = updatePage.username_Field.getText();
				String password = updatePage.password_Field.getText();
				String email = updatePage.email_Field.getText();
				String phonenumber = updatePage.phonenumber_Field.getText();
				String firstname = updatePage.firstname_Field.getText();
				String lastname = updatePage.lastname_Field.getText();
				String address = updatePage.address_Field.getText();
				if(customer != null)
					updateCustomer(username, password, email, phonenumber, firstname, lastname, address);
				else if(manager != null)
					updateManager(username, password, email, phonenumber, firstname, lastname, address);
			}
			if(e.getSource() == updatePage.home){
				if(customer != null)
					customerHomePage();
				else if(manager != null)
					managerHomePage();
			}
			if(e.getSource() == customerHomePage.checkOut){
				payPage = new Payment();
				payPage.viewPage(this);
			}
			if(e.getSource() == payPage.buyBtn){
				payPage.errorLabel.setText(" ");
				if(customer.getCart() == null || customer.getCart().isEmpty()){
					payPage.errorLabel.setText("There are no books in the cart !!");
					return;
				}
				String cardNo = payPage.cardNumField.getText();
				String expireDate = payPage.expireDateField.getText();
				if(cardNo.equals("") || expireDate.equals("")){
					payPage.errorLabel.setText("Error : empty fields !!");
					return;
				}
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); 
			    try {
			    	df.parse(expireDate);
				} catch (ParseException e2) {
					payPage.errorLabel.setText("Error : invalid date format (MM/dd/yyyy) !!");
					return;
				}
			    if(cardNo.length() != 12){
			    	payPage.errorLabel.setText("Error : card number must be 12 digits !!");
			    	return;
			    }
				payPage.getContentPane().removeAll();
				payPage.revalidate();
				payPage.repaint();
				payPage.showPayment(this);
				payPage.revalidate();
				payPage.repaint();
				validatePayement(cardNo, expireDate);
			}
			if(e.getSource() == searchPage.search){
				String title = searchPage.title_Field.getText();
				String category = searchPage.category_Field.getText();
				String year = searchPage.year_Field.getText();
				String publisher = searchPage.publisher_Field.getText();
				LinkedList<Book> books = getMatchingBooks(title, category, year, publisher);
				view.getContentPane().removeAll();
				view.revalidate();
				view.repaint();
				if(customer != null){
					storePage = new BookStore(customer);
					storePage.viewPage(this, books);
				}
				else if(manager != null){
					libraryPage = new Library();
					libraryPage.viewPage(this, books);
				}
				view.revalidate();
				view.repaint();
			}
			if(e.getSource() == customerHomePage.log){
				LinkedList<BookOrder> orders =  getBookOrders();
				view.getContentPane().removeAll();
				view.revalidate();
				view.repaint();
				
				logPage = new Log();
				if(customer != null)
					logPage.viewPage(this, orders, "customer");
				else if(manager != null)
					logPage.viewPage(this, orders, "manager");
				view.revalidate();
				view.repaint();
			}
			if(e.getSource() == logPage.home){
				if(customer != null)
					customerHomePage();
				else
					managerHomePage();
			}
			if(e.getSource() == logPage.profile){
				customerProfilePage();
			}
			if(e.getSource() == managerHomepage.addBook){
				String title = managerHomepage.title_Field.getText();
				String stock = managerHomepage.stockQuantity_Field.getText();
				String min = managerHomepage.minQuantity_Field.getText();
				String category = managerHomepage.category_Field.getText();
				String price = managerHomepage.price_Field.getText();
				String year = managerHomepage.year_Field.getText();
				String name = managerHomepage.publisher_Field.getText();
				Publisher publisher = Publisher.findByName(name, conn);
				LinkedList<Object> values = new LinkedList<Object>();
				LinkedList<String> columns = new LinkedList<String>();
				values.add(title);			columns.add("title");
				values.add(stock);			columns.add("stock_quantity");
				values.add(min);			columns.add("min_quantity");
				values.add(category);		columns.add("category");
				values.add(price);			columns.add("selling_price");
				values.add(year);			columns.add("publication_year");
				values.add(publisher.getPublisher_id());	columns.add("Publisher_publisher_id");
				Book.insertBook(values, columns);
				System.out.println(title + " inserted");
			}
			if(e.getSource() == managerHomepage.library){
				view.getContentPane().removeAll();
				view.revalidate();
				view.repaint();
				
				libraryPage = new Library();
				libraryPage.viewPage(this, Book.getAllBooks());
				view.revalidate();
				view.repaint();
			}
			if(e.getSource() == libraryPage.search){
				view.getContentPane().removeAll();
				view.revalidate();
				view.repaint();
				searchPage = new Search();
				searchPage.viewPage(this);
				view.revalidate();
				view.repaint();
			}
			if(e.getSource() == modifyPage.home){
				managerHomePage();
			}
			if(e.getSource() == modifyPage.update){
				String title = modifyPage.titleField.getText();
				String stock = modifyPage.stockQuantityField.getText();
				String min = modifyPage.minQuantityField.getText();
				String category = modifyPage.categoryField.getText();
				String price = modifyPage.priceField.getText();
				String year = modifyPage.yearField.getText();
				String name = modifyPage.publisherField.getText();
				Book book = modifyPage.getBook();
				book.updateBook(title, stock, min, category, price, year, name);
				view.getContentPane().removeAll();
				view.revalidate();
				view.repaint();
				
				libraryPage = new Library();
				libraryPage.viewPage(this, Book.getAllBooks());
				view.revalidate();
				view.repaint();
			}
			if(e.getSource() == libraryPage.home){
				managerHomePage();
			}
			if(e.getSource() == managerHomepage.log){
				java.util.Date date = new java.util.Date();
				java.util.Date previousDate = new java.util.Date(date.getYear(), date.getMonth()-3, date.getDay());
				Timestamp ts = new Timestamp(previousDate.getTime());
				LinkedList<BookOrder> orders =  BookOrder.getOrdersByDate(ts);
				view.getContentPane().removeAll();
				view.revalidate();
				view.repaint();
				
				logPage = new Log();
				if(customer != null)
					logPage.viewPage(this, orders, "customer");
				else if(manager != null)
					logPage.viewPage(this, orders, "manager");
				view.revalidate();
				view.repaint();
			}
			if(e.getSource() == logPage.topBooks){
				view.getContentPane().removeAll();
				view.revalidate();
				view.repaint();
				
				statsPage = new SoldBooks();
				statsPage.viewPage(this, getTopSoldBooks(), getTopCustomers());
				view.revalidate();
				view.repaint();
			}
			if(e.getSource() == statsPage.home){
				managerHomePage();
			}
			if(e.getSource() == managerHomepage.viewCustomer){
				view.getContentPane().removeAll();
				view.revalidate();
				view.repaint();
				customerListPage = new Customers();
				customerListPage.viewPage(this, Customer.getAllCustomers());
				view.revalidate();
				view.repaint();
			}
			if(e.getSource() == customerListPage.home){
				managerHomePage();
			}
			if(e.getSource() == managerHomepage.profile){
				managerProfilePage();
			}
			if(e.getSource() == libraryPage.profile){
				managerProfilePage();
			}
			if(e.getSource() == logPage.profile){
				if(customer != null)
					customerProfilePage();
				else if(manager != null)
					managerProfilePage();
			}
			if(e.getSource() == statsPage.profile){
				managerProfilePage();
			}
			if(e.getSource() == searchPage.home){
				if(customer != null)
					customerHomePage();
				else if(manager != null)
					managerHomePage();
			}
			if(e.getSource() == managerHomepage.viewOrders){
				view.getContentPane().removeAll();
				view.revalidate();
				view.repaint();
				orderPage = new Orders();
				orderPage.viewPage(this, PlacedOrders.getAllPlacedOrders());
				view.revalidate();
				view.repaint();
			}
			if(e.getSource() == orderPage.profile){
				managerProfilePage();
			}
			if(e.getSource() == orderPage.home){
				managerHomePage();
			}
			
		} catch (Exception e2) {}
	}
	
	public static Connection getConn() {
		return conn;
	}

	/**
	 * Connect to the DB and do some stuff
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		BSController app = new BSController();
		app.run();
	}


}