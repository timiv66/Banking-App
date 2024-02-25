package banking;
import java.util.regex.*;  
import java.util.Scanner;

public class User {
	private String name = "User";
	private int pin = 0000;
	private String username = "username";
	private String password = "password";
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
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public User(Account account) {
		
		this.account = account;
	}
	
	
}
