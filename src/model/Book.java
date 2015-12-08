package model;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import controller.BSController;


public class Book {

	private int ISBN;
	private String title;
	private int stock_quantity;
	private int min_quantity;
	private String category;
	private int selling_price;
	private int publication_year;
	private Publisher publisher;
	private static Connection conn = BSController.getConn();
	
	public Book(int iSBN, String title, int stock_quantity, int min_quantity,
			String category, int selling_price, int publication_year,
			Publisher publisher) {
		ISBN = iSBN;
		this.title = title;
		this.stock_quantity = stock_quantity;
		this.min_quantity = min_quantity;
		this.category = category;
		this.selling_price = selling_price;
		this.publication_year = publication_year;
		this.setPublisher(publisher);
	}
	
	public int getISBN() {
		return ISBN;
	}

	public String getTitle() {
		return title;
	}

	public int getStock_quantity() {
		return stock_quantity;
	}

	public int getMin_quantity() {
		return min_quantity;
	}

	public String getCategory() {
		return category;
	}

	public int getSelling_price() {
		return selling_price;
	}

	public int getPublication_year() {
		return publication_year;
	}

	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setStock_quantity(int stock_quantity) {
		this.stock_quantity = stock_quantity;
	}

	public void setMin_quantity(int min_quantity) {
		this.min_quantity = min_quantity;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setSelling_price(int selling_price) {
		this.selling_price = selling_price;
	}

	public void setPublication_year(int publication_year) {
		this.publication_year = publication_year;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	
	public static void insertBook(LinkedList<Object> values, LinkedList<String> columns){
		String command = SQLStatements.insertInto("book", values, columns);
		try {
			SQLStatements.executeUpdate(conn, command);
		} catch (SQLException e) {}
		
	}
	
	public static Book findByISBN(int ISBN) {
		String condition = "ISBN = " + ISBN;
		String command = SQLStatements.selectFromTable("Book", condition);
		
		Book book = null;
		try {
			ResultSet result = SQLStatements.executeQuery(conn, command);
			while(result.next()){
					String title = result.getString("title");
					int stockQuantity = result.getInt("stock_quantity");
					int minQuantity = result.getInt("min_quantity");
					String category = result.getString("category");
					int sellingPrice = result.getInt("selling_price");
					int publicationYear = result.getInt("publication_year");
					int publisherId = result.getInt("Publisher_publisher_id");
					Publisher publisher = Publisher.findById(publisherId, conn);
					book = new Book(ISBN, title, stockQuantity, minQuantity, category, sellingPrice, publicationYear, publisher);
			}
		} catch (SQLException e) {
			
		}
		
		return book;
	}
	
	public static LinkedList<Book> findBooks(String condition){
		LinkedList<Book> books =  new LinkedList<Book>();
		String command = SQLStatements.selectFromTable("book", condition);
		try {
			ResultSet result = SQLStatements.executeQuery(conn, command);
			while(result.next()){
				int isbn = result.getInt("ISBN");
				String title = result.getString("title");
				int stockQuantity = result.getInt("stock_quantity");
				int minQuantity = result.getInt("min_quantity");
				String category = result.getString("category");
				int sellingPrice = result.getInt("selling_price");
				int publicationYear = result.getInt("publication_year");
				int publisherId = result.getInt("Publisher_publisher_id");
				Publisher publisher = Publisher.findById(publisherId, conn);
				Book book = new Book(isbn, title, stockQuantity, minQuantity, category, sellingPrice, publicationYear, publisher);
				books.add(book);
			}
		} catch (SQLException e) {}
		return books;
	}
	
	public static Book findByTitle(String title) {
		/**
		 * not implemented
		 */
		return null;
	}
	
	public static Book findByCategory(String category) {
		/**
		 * not implemented
		 */
		return null;
	}
	
	public static Book findByAuthor(String authorName) {
		/**
		 * not implemented
		 */
		return null;
	}
	

	public static Book findByPublisher(Publisher publisher) {
		/**
		 * not implemented
		 */
		return null;
	}
	
	public static LinkedList<Book> getAllBooks(){
		String command = "SELECT * FROM book";
		LinkedList<Book> books = new LinkedList<Book>();
		try {
			ResultSet result = SQLStatements.executeQuery(conn, command);
			while(result.next()){
				int isbn = result.getInt("ISBN");
				String title = result.getString("title");
				int stockQuantity = result.getInt("stock_quantity");
				int minQuantity = result.getInt("min_quantity");
				String category = result.getString("category");
				int sellingPrice = result.getInt("selling_price");
				int publicationYear = result.getInt("publication_year");
				int publisherId = result.getInt("Publisher_publisher_id");
				Publisher publisher = Publisher.findById(publisherId, conn);
				Book book = new Book(isbn, title, stockQuantity, minQuantity, category, sellingPrice, publicationYear, publisher);
				books.add(book);
			}
		} catch (SQLException e) {}
		
		return books;
	}

	private void update(LinkedList<String> columns, LinkedList<Object> values) {
		int bookId = this.getISBN();
		String condition = "ISBN = " + bookId;
		String command = SQLStatements.updateTable("book", columns, values, condition);
		try {
			SQLStatements.executeUpdate(conn, command);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void updateBook(String title, String stock, String min,
			String category, String price, String year, String name) {
		
		LinkedList<String> columns = new LinkedList<String>();
		LinkedList<Object> values = new LinkedList<Object>();
		if(!title.equals("")){
			columns.add("title");
			values.add(title);
		}
		if(!stock.equals("")){
			columns.add("stock_quantity");
			values.add(stock);
		}
		if(!min.equals("")){
			columns.add("min_quantity");
			values.add(min);
		}
		if(!category.equals("")){
			columns.add("category");
			values.add(category);
		}
		if(!price.equals("")){
			columns.add("selling_price");
			values.add(price);
		}
		if(!year.equals("")){
			columns.add("publication_year");
			values.add(year);
		}
		if(!name.equals("")){
			String publisher_id = Publisher.findByName(name, conn).getPublisher_id() + "";
			columns.add("Publisher_publisher_id");
			values.add(publisher_id);
		}
		this.update(columns, values);
		
	}

}
