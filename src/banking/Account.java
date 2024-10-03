package banking;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class Account {
	
	
	final String DB_URL = "jdbc:mysql://localhost:3306/bankingsystem";
	final String DB_USER = "root";  // Use your MySQL username
	final String DB_PASSWORD = "Tobi4timi$";  // Use your MySQL password
	
	
	private String type = "Checking";
	private String name = "Account #1";
	private double balance = 200;
	private double withAmt;
	private double depAmt;
	private double latestTrac;
	private String accountNum = generateRandomNumberString();
	private User owner;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getWithAmt() {
		return withAmt;
	}
	public void setWithAmt(double withAmt) {
		this.withAmt = withAmt;
	}
	public double getDepAmt() {
		return depAmt;
	}
	public void setDepAmt(double depAmt) {
		this.depAmt = depAmt;
	}
	public double getLatestTrac() {
		return latestTrac;
	}
	public void setLatestTrac(double latestTrac) {
		this.latestTrac = latestTrac;
	}
	public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	//Get users account number from database
	public String usersAccNum(String fullName, String passwrd) {
		Connection conn = null;
		Statement stmt = null;
		    
		String userAccNum = null;
		    
		String accNumSQL = "SELECT account_num FROM users WHERE full_name = '"+fullName+"' AND passwrd = '" + passwrd + "'";
			
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				
			stmt = conn.createStatement();
			ResultSet accNumRS = stmt.executeQuery(accNumSQL);
			    
			while(accNumRS.next()) {
				userAccNum = accNumRS.getString(1);
			}
			    
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userAccNum;
	}
	
	//Get users account name from database
	public String userAccName(String accNum) {
		Connection conn = null;
		Statement accNameStmt = null;
		    
		String userAccName = null;
		String accNameSQL = "SELECT account_name FROM accounts WHERE account_num = '" + accNum + "'";
		
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			accNameStmt = conn.createStatement();
			ResultSet accNameRS = accNameStmt.executeQuery(accNameSQL);
			
			while(accNameRS.next()) {
				userAccName = accNameRS.getString(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return userAccName;
	}
	
	//Update an account name inside database
	public void updateUserAccName(String newAccName, String accNum) {
		Connection conn = null;
		Statement updateAccNameStmt = null;
		
		String updateAccNameSQL = "UPDATE accounts SET account_name = '" + newAccName + "' WHERE account_num = '" + accNum + "'";
		
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			updateAccNameStmt = conn.createStatement();
			updateAccNameStmt.execute(updateAccNameSQL);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	Account(){
		
	}
	
}
