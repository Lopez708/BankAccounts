/**
 * SavingsAccount is a subclass of BankAccount.  This class creates a savings account object.
 * @author Juan Lopez
 *
 */
public class SavingsAccount extends BankAccount {
	
	private double aRate;
	private final double BALANCE_MINIMUM = 100.00;
	private int sCharge;
	
	/**
	 * Constructor for the class
	 * @param accountType: 1-saving, 2-checking
	 * @param aNum account Number
	 * @param bal account balance
	 * @param rt interest rate
	 */
	public SavingsAccount(int accountType, int aNum, double bal, double rt) {
		super(accountType, aNum, bal, rt);
	}
	
	/**
	 * monthylProcess charges the account for having more than 5 withdraws.  Calculates the interest on the account and adds that amount to the balance. Resets the withdraw number
	 */
	public void monthlyProcess() {
		if (this.getNumOfWithdraws() > 4) {
			sCharge = (this.getNumOfWithdraws() - 4);
			this.setBalance(this.getBalance() - sCharge);
		}
		
		double mRate = 0;
		double mInterest = 0;
		aRate = this.getRate();
		mRate = (aRate / (12 * 100));
		mInterest = this.getBalance() * mRate;
		this.setBalance(this.getBalance() + mInterest);		
		
		if (this.getBalance() < BALANCE_MINIMUM) {
			this.setStatus(false);
		}
		
		sCharge = 0;
		this.resetNumOfWithdraws();
	}
	
	/**
	 * calls deposit method of super class, sets account to active if balance is equal to or greater than $100
	 * @param amount total amount to be deposited
	 */
	public void deposit(double amount) {
		super.deposit(amount);
		if (this.getBalance() >= BALANCE_MINIMUM) {
			this.setStatus(true);
		}
	}
	
	/**
	 * calls withdraw method of super class, sets account to inactive if balance is less than $100
	 * @param amount total amount to be withdrawn
	 * @return true if money withdrawn, false if account is less than $100
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
	 * @return string representation of saving account
	 */
	public String toString() {
		return super.toString();
	}
}
