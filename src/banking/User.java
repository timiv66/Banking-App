package banking;
import java.util.regex.*;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;



public class User {
	
	final String DB_URL = "jdbc:mysql://localhost:3306/bankingsystem";
	final String DB_USER = "root";  // Use your MySQL username
	final String DB_PASSWORD = "Tobi4timi$";  // Use your MySQL password
	
	private int userID;
	private String name = "User";
	private int pin = 0000;
	private String username = "username";
	private String password = "password";
	private String accNum;
	private Account account;
	
	
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
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
	
	
	public String getAccNum() {
		return accNum;
	}
	
	public void setAccNum(String accNum) {
		this.accNum = accNum;
	}
	
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
	public User(Account account) {
		
		this.account = account;
	}
	
	public User() {
		
	}
	
	//Adds new user to database
	public void insertUserToDB() {
		Connection conn = null;
	    PreparedStatement userPstmt = null;
	    PreparedStatement accPstmt;
	    
	    //SQL Commands 
	    String accSQL = "INSERT INTO accounts (account_num) VALUES (?)"; 
	    String userSQL = "INSERT INTO users (full_name, username, passwrd, pin, account_num) VALUES (?, ?, ?, ?, ?)";
	    
	    try {
	    	//Connection to the database
	    	conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	    	
	    	//Inserts account into database
	    	accPstmt = conn.prepareStatement(accSQL);
		    accPstmt.setString(1, getAccNum());
			accPstmt.executeUpdate();
	    	
			//Inserts user into database
			userPstmt = conn.prepareStatement(userSQL);
			userPstmt.setString(1, getName());
			userPstmt.setString(2, getUsername());
			userPstmt.setString(3, getPassword());
			userPstmt.setDouble(4, getPin());
			userPstmt.setString(5, getAccNum());
			userPstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Checks if user is in database
	public boolean authenticateUser(String inputUsername, String inputPassword) {
		Connection conn = null;
	    Statement pstmt1 = null;
	    Statement pstmt2 = null;
	    
	    //String variables that will hold info from database
	    String dbUserName = null;
	    String dbPassWrd = null;
	    
	    //SQL commands 
	    String usernamSQL = "SELECT username FROM users WHERE username = '" + inputUsername + "' AND passwrd ='" + inputPassword + "'";
		String passwordSQL = "SELECT passwrd FROM users WHERE username = '" + inputUsername + "' AND passwrd ='" + inputPassword + "'";
		
		try {
			//Connecting to database
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		
			//Executing query for username
			pstmt1 = conn.createStatement();
			ResultSet usernameRS = pstmt1.executeQuery(usernamSQL);
			
			//Executing query for password
			pstmt2 = conn.createStatement();
			ResultSet passwordRS = pstmt2.executeQuery(passwordSQL);
		
			//Storing username query in to String variable
			while(usernameRS.next()) {
				dbUserName = usernameRS.getString(1);
			}
			
			//Storing password query in to String variable
			while(passwordRS.next()) {
				dbPassWrd = passwordRS.getString(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Checking if username and password match from the database
		if(inputUsername.contentEquals(dbUserName) && inputPassword.contentEquals(dbPassWrd)) {
			return true;
		}else if(!inputUsername.contentEquals(dbUserName) || !inputPassword.contentEquals(dbPassWrd)) {
			return false;
		}
		
		return true;
		
	}
	
	//Checks if users pin number is correct
	public boolean authenticatePin(String inputedPin) {
		Connection conn = null;
	    Statement userIdStmt = null;
	    Statement pinStmt = null;
	   
	    //The SQL command and the string variable that will hold query for userID from database;
	    String userID = null;
	    String userIDSQL = "SELECT user_ID FROM users WHERE username = '" + getUsername() + "' AND passwrd ='" + getPassword() + "'";
	    
	    try {
	    	//Connecting to database
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			//Executing query for userID
			userIdStmt = conn.createStatement();
			ResultSet userIdRS = userIdStmt.executeQuery(userIDSQL);
			
			//Storing userID query in to String variable
			while(userIdRS.next()) {
				userID = userIdRS.getString(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    //The SQL command and the string variable that will hold query for pin from database;
	    String pin = null;
	    String pinSQL = "SELECT pin FROM users WHERE pin = '" + inputedPin + "' AND user_ID = '"+ userID +"'";
	    
	    try {
	    	//Executing query for pin
	    	pinStmt = conn.createStatement();
	    	ResultSet pinRS = pinStmt.executeQuery(pinSQL);
	    	
	    	//Storing pin query in to String pin variable
	    	while(pinRS.next()) {
	    		pin = pinRS.getString(1);
	    	}
	    	
	    }catch(SQLException e) {
	    	
	    }
	    
	    //Checking if inputed pin matches pin from database
	    if(inputedPin.contentEquals(pin)) {
	    	return true;
	    }else if(!inputedPin.contentEquals(pin)) {
	    	return false;
	    }
		
		return true;
	}
	
	
	//Gets users full name from database
	public String usersFullName(String usrname, String passwrd)  {
		String userFullName = null;
		
		Connection conn = null;
	    Statement stmt = null;
	    
	    //SQL command
	    String fullNameSQL = "SELECT full_name FROM users WHERE username = '" + usrname +"' AND passwrd = '" + passwrd + "'";
	    
	    try {
	    	//Connecting to database
	    	conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		    
	    	//Executing query for full name
		    stmt = conn.createStatement();
		    ResultSet fullNameRS = stmt.executeQuery(fullNameSQL);
		    
		    //Storing full name query in to String variable
		    while(fullNameRS.next()) {
		    	userFullName = fullNameRS.getString(1);
		    }
	    }catch(SQLException e) {
	    	e.printStackTrace();
	    }
		return userFullName;
	}
	
	
	
	public static String generateRandomNumberString() {
        int length = 10;
        StringBuilder randomStringBuilder = new StringBuilder(length);
        Random rand = new Random();

        for (int i = 0; i < length; i++) {
            int randomNumber = rand.nextInt(10); 
            randomStringBuilder.append(randomNumber);
        }
        return randomStringBuilder.toString();
    }
	
	
	public static void main(String[] args) {
		User user = new User();
		String a = user.usersFullName("timiv66", "BobbyBrown77$");
		System.out.println(a);
		
	}
	
}
