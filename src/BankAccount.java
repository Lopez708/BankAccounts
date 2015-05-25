/**
 * The BankAccount abstract class defines any type of bank account.
 * @author Juan Lopez
 *
 */
public abstract class BankAccount {

	private int accType;
	private int accNum;
	private double balance;
	private double rate;
	private boolean status;
	private int numWithdraws = 0;

	/**
	 * monthly process must be implemented by each subclass, depending on type bank account, each method will be different.
	 */
	public abstract void monthlyProcess();
	
	/**
	 * standard constructor
	 * @param accountType: 1-saving, 2-checking
	 * @param aNum account Number
	 * @param bal account balance
	 * @param rt interest rate
	 */
	public BankAccount(int accountType,int aNum, double bal, double rt) {		
		accType = accountType;
		accNum = aNum;
		balance = bal;
		rate = rt;
		status = true;
	}

	/**
	 * adds money to the account
	 * @param amount the amount to add
	 */
	public void deposit(double amount)	{
		balance = balance + amount;
		balance = Math.round(balance * 100.0) / 100.0;
	}

	/**
	 * removes money from the account
	 * @param amount  the amount to withdraw from the account
	 * @return true if there was sufficient funds to complete
	 */
	public boolean withdraw(double amount)	{
		balance = balance - amount;
		numWithdraws++;
		return true;
	}
	
	/**
	 * accessor method to the account type number
	 * @return 1-saving, 2-checking
	 */
	public int getAccType() {
		return accType;
	}
	
	/**
	 * accessor method to balance
	 * @return the balance of the account
	 */
	public double getBalance()	{
		return balance;
	}
	
	/**
	 * accessor method to account number
	 * @return the account number
	 */
	public int getAccountNumber() {
		return accNum;
	}
	
	/**
	 * accessor method to rate number
	 * @return the rate of the account
	 */
	public double getRate() {
		return rate;
	}
	
	/**
	 * accessor method to status
	 * @return true-account is active, false-account is inactive
	 */
	public boolean getStatus() {
		return status;
	}
	
	/**
	 * accessor method to amount of withdraws on account
	 * @return the number of withdraws
	 */
	public int getNumOfWithdraws() {
		return numWithdraws;
	}
	
	/**
	 * mutator method to change the balance
	 * @param newBalance the new balance for the account
	 */
	public void setBalance(double newBalance) {
		balance = newBalance;
		balance = Math.round(balance * 100.0) / 100.0;
	}
	
	/**
	 * mutator method to change the account status
	 * @param stat true - set account active, false - set account inactive
	 */
	public void setStatus(boolean stat) {
		status = stat;
	}
	
	/**
	 * mutator method to change the reset the number of withdraws
	 */
	public void resetNumOfWithdraws() {
		numWithdraws = 0;
	}
	
	/**
	 * mutator method to change the reset the number of withdraws
	 * @return returns string representation of the account
	 */
	public String toString() {
		return accNum + "   $" + balance + "        " + rate + "           " +  numWithdraws;
	}
}