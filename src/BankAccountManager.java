import java.util.ArrayList;

/**
 * The BankAccountManager implements the BankAccountManagerInterface and runs the necessary methods to manage both checking and savings accounts.
 * @author Juan Lopez
 *
 */
public class BankAccountManager implements BankAccountManagerInterface{

	ArrayList<BankAccount> bankAccounts = new ArrayList<BankAccount>();
	private int savingsTotal;
	private int checkingTotal;
	private double totalBal;
	private String acctList;
	
	/**
	 * Add an Account to the ArrayList. Check if the account already exists (searchAccount)
	 * @param accountType: 1-saving, 2-checking
	 * @param aNum account Number
	 * @param balance account balance
	 * @param rate interest rate
	 * @return true if account was added
	 *         false if account number already exists
	 */
	public boolean addAccount(int accountType,int aNum, double balance, double rate) {
		if (this.searchAccount(aNum) == -1) {
			if (accountType == 2) {
				BankAccount newChecking = new CheckingAccount(accountType, aNum,  balance,  rate);
				bankAccounts.add(newChecking);
				return true;
			} else if (accountType == 1) {
				BankAccount newChecking = new SavingsAccount(accountType, aNum,  balance,  rate);
				bankAccounts.add(newChecking);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns the number of saving accounts the ArrayList
	 * @return number of saving accounts
	 */
	public int getNumSaving() {
		savingsTotal = 0;
		for (int x = 0; x < bankAccounts.size(); x++) {
			if (bankAccounts.get(x).getAccType() == 1) {
				savingsTotal++;
			}
		}
		return savingsTotal;
	}
	
	/**
	 * Returns the number of checking accounts the ArrayList
	 * @return number of saving accounts
	 */
	public int getNumChecking() {
		checkingTotal = 0;
		for (int x = 0; x < bankAccounts.size(); x++) {
			if (bankAccounts.get(x).getAccType() == 2) {
				checkingTotal++;
			}
		}
		return checkingTotal;
	}
	
	/**
	 *  Generates an AccountReport in the following format:
	 *  ACCOUNT REPORT
	 *  
	 *  ACC# BALANCE %RATE #WITHDRAWS
	 *  _______________________________
	 *  1001 $150.00  2    0
	 *  1000 $200.00  0    0
	 *  
	 *  Total balance: $350.00
	 *  Total number of savings Accounts: 1
	 *  Total number of checking Accounts : 1
	 *  
	 * @return string of the Account Report
	 */
	public String generateAccountReport() {
		acctList = "";
		for (int x = 0; x < bankAccounts.size(); x++) {
			acctList += bankAccounts.get(x).toString() + "\n";
		}
		return "ACCOUNT REPORT\n\n" + "ACC#  BALANCE   %RATE   #WITHDRAWS\n" + "_______________________________\n" + acctList + "\nTotal balance: $" + this.totalAccountBalance() 
				+ "\nTotal number of savings Accounts: " + this.getNumSaving() + "\nTotal number of checking Accounts: " + this.getNumChecking();
	}
		
	
	/**
	 * Calls the monthlyProcess method for all accounts in the Bank
	 */
	public void startMonthlyProcess() {
		for (int x = 0; x < bankAccounts.size(); x++) {
			bankAccounts.get(x).monthlyProcess();
		}		
	}
	
	/**
	 * Returns the total balance of all the accounts in the bank.
	 * @return the total balance of all the accounts
	 */
	
	public double totalAccountBalance() {
		totalBal = 0;
		for (int x = 0; x < bankAccounts.size(); x++) {
			totalBal += bankAccounts.get(x).getBalance();
		}
		return totalBal;
	}
	
	/**
	 * Withdraw from the balance of an account.  
	 * Make sure the accNum is valid (searchAccount) and then call the withdraw method for the account
	 * @param accNum account number of the account to withdraw from
	 * @param amount the amount of the withdraw
	 * @return true if the account was found and withdraw was made
	 *         false if the account was not found
	 */
	public boolean debit(int accNum,double amount) {
		if (this.searchAccount(accNum) != -1) {
			return bankAccounts.get(this.searchAccount(accNum)).withdraw(amount);
		}
		return false;
	}
	
	/**
	 * Deposit to the balance of an account.  
	 * Make sure the accNum is valid (searchAccount) and then call the deposit method for the account
	 * @param accNum account number of the account to deposit from
	 * @param amount the amount of the withdraw
	 * @return true if the account was found and deposit was made
	 *         false if the account was not found
	 */
	public boolean credit(int accNum,double amount) {
		if (this.searchAccount(accNum) != -1) {
			bankAccounts.get(this.searchAccount(accNum)).deposit(amount);
			return true;
		}
		return false;
	}
	
	/*****************************************************/
	/**
	 * This methods is used only for testing. It returns the status of the
	 * account at the index "index"
	 * 
	 * @param index the index of the object in the array
	 * @return true if account is active
	 *         false if account is inactive
	 */
	public boolean getThisaccountStatus(int index) {
		return bankAccounts.get(index).getStatus();
	}
	
	/**
	 * Search for the account number to see if in arraylist
	 * @param accountNum account number to search for
	 * @return index in the array list containing this account
	 *         return a -1 if the account is not found
	 */
	public int searchAccount(int accountNum) {
		if(bankAccounts != null) {
			for (int x = 0; x < bankAccounts.size(); x++) {
				if (accountNum == bankAccounts.get(x).getAccountNumber()) {
					return x;
				}
			}
		}		
		return -1;
	}
}
