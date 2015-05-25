/**
 * CheckingAccount is a subclass of BankAccount.  This class creates a checking account object.
 * @author Juan Lopez
 *
 */
public class CheckingAccount extends BankAccount {

	private double pCharge;
	private double totalPenalty = 0;
	private final double BALANCE_MINIMUM = 10.00;
	
	/**
	 * Constructor for the class
	 * @param accountType: 1-saving, 2-checking
	 * @param aNum account Number
	 * @param bal account balance
	 * @param rt interest rate
	 */
	public CheckingAccount(int accountType, int aNum, double bal, double rt) {
		super(accountType, aNum, bal, rt);
	}
	
	/**
	 * monthylProcess checks if the account is less than $5.  Sets the balance to $0 if balance is below minimum balance and resets number of withdraws
	 */
	public void monthlyProcess() {
		if (this.getBalance() < BALANCE_MINIMUM) {
			pCharge = this.getBalance();
			totalPenalty += pCharge;
			this.setBalance(0);
			this.resetNumOfWithdraws();
		}
	}
	
	/**
	 * calls deposit method of super class, sets account to active if balance is equal to or greater than $10
	 * @param amount total amount to be deposited
	 */
	public void deposit(double amount) {
		super.deposit(amount);
		if (this.getBalance() >= BALANCE_MINIMUM) {
			this.setStatus(true);
		}
	}
	
	/**
	 * calls withdraw method of super class, sets account to inactive if balance is less than $10
	 * @param amount total amount to be withdrawn
	 * @return true if money withdrawn, false if account is less than $10
	 */
	public boolean withdraw(double amount)	{
		if (this.getBalance() >= BALANCE_MINIMUM) {
			boolean isWithdraw;
			isWithdraw = super.withdraw(amount);
			if (this.getBalance() < BALANCE_MINIMUM) {
				this.setStatus(false);
			}
			return isWithdraw;
		}
		return false;
	}
	
	/**
	 * calls toString method of super class
	 * @return string representation of checking account
	 */
	public String toString() {
		return super.toString();
	}	
}