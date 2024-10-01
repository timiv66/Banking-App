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
	
	private String name = "User";
	private int pin = 0000;
	private String username = "username";
	private String password = "password";
	private String accNum;
	private Account account;
	
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
	
	public void insertUserToDB() {
		Connection conn = null;
	    PreparedStatement pstmt = null;
	    
	    String sql = "INSERT INTO users (full_name, username, passwrd, pin, account_num) VALUES (?, ?, ?, ?, ?)";
	     
	    try {
	       	conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			pstmt = conn.prepareStatement(sql);
		    pstmt.setString(1, getName());
		    pstmt.setString(2, getUsername());
		    pstmt.setString(3, getPassword());
		    pstmt.setDouble(4, getPin());
		    pstmt.setString(5, getAccNum());
		  
		    int rowsInserted = pstmt.executeUpdate();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean authenticateUser(String inputUsername, String inputPassword) {
		Connection conn = null;
	    Statement pstmt1 = null;
	    Statement pstmt2 = null;
	    
	    String dbUserName = null;
	    String dbPassWrd = null;
	    
	      
	    String usernamSQL = "SELECT username FROM users WHERE username = '" + inputUsername + "' AND passwrd ='" + inputPassword + "'";
		String passwordSQL = "SELECT passwrd FROM users WHERE username = '" + inputUsername + "' AND passwrd ='" + inputPassword + "'";
		
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		
			pstmt1 = conn.createStatement();
			ResultSet usernameRS = pstmt1.executeQuery(usernamSQL);
			
			pstmt2 = conn.createStatement();
			ResultSet passwordRS = pstmt2.executeQuery(passwordSQL);
		
			
			while(usernameRS.next()) {
				dbUserName = usernameRS.getString(1);
			}
			
			while(passwordRS.next()) {
				dbPassWrd = passwordRS.getString(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(inputUsername.contentEquals(dbUserName) && inputPassword.contentEquals(dbPassWrd)) {
			return true;
		}else if(!inputUsername.contentEquals(dbUserName) || !inputPassword.contentEquals(dbPassWrd)) {
			return false;
		}
		
		return true;
		
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

	}
	
}
