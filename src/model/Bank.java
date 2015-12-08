package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Bank {
	
	public static boolean checkValid(String cardNo, String expire) throws ParseException{
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); 
		Date expireDate = df.parse(expire);
		Date currentDate = new Date();
		if(expireDate.before(currentDate))
			return false;
		return true;
	}
	
}
