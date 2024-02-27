package banking;
import java.util.Random;

public class Account {
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
