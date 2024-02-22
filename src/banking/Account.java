package banking;

public class Account {
	private String type;
	private String name;
	private double balance;
	private double accountNum;
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
	public double getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(double accountNum) {
		this.accountNum = accountNum;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	Account(){
		
	}
	Account(String type, String name, double balance, double accountNum, User owner) {
		this.type = type;
		this.name = name;
		this.balance = balance;
		this.accountNum = accountNum;
		this.owner = owner;
	}
	
	
}
