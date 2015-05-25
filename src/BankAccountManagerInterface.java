
/**
 * Interface for the BankAccount class.
 * The BankAccount class will create an arraylist to hold account information. 
 * The BankAccount class has the ability to: 
 * - Add saving/checking accounts to arraylist, 
 * - Show the total balance, number of checking and saving accounts, 
 * - Withdraw and deposit to a an account
 * - Apply monthly process on all the accounts.
 * 
 * @author khandan Monshi
 * 
 */
public interface BankAccountManagerInterface {
	
	/**
	 * Add an Account to the ArrayList. Check if the account already exists (searchAccount)
	 * @param accountType: 1-saving, 2-checking
	 * @param aNum account Number
	 * @param balance account balance
	 * @param rate interest rate
	 * @return true if account was added
	 *         false if account number already exists
	 */
	public boolean addAccount(int accountType,int aNum, double balance, double rate );
	
	/**
	 * Returns the number of saving accounts the ArrayList
	 * @return number of saving accounts
	 */
	public int getNumSaving();
	
	/**
	 * Returns the number of checking accounts the ArrayList
	 * @return number of saving accounts
	 */
	public int getNumChecking();
	
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
	public String generateAccountReport();
	
	/**
	 * Calls the monthlyProcess method for all accounts in the Bank
	 */
	public void startMonthlyProcess();
	
	/**
	 * Returns the total balance of all the accounts in the bank.
	 * @return the total balance of all the accounts
	 */
	
	public double totalAccountBalance();
	
	/**
	 * Withdraw from the balance of an account.  
	 * Make sure the accNum is valid (searchAccount) and then call the withdraw method for the account
	 * @param accNum account number of the account to withdraw from
	 * @param amount the amount of the withdraw
	 * @return true if the account was found and withdraw was made
	 *         false if the account was not found
	 */
	public boolean debit(int accNum,double amount);
	
	/**
	 * Deposit to the balance of an account.  
	 * Make sure the accNum is valid (searchAccount) and then call the deposit method for the account
	 * @param accNum account number of the account to withdraw from
	 * @param amount the amount of the withdraw
	 * @return true if the account was found and withdraw was made
	 *         false if the account was not found
	 */
	public boolean credit(int accNum,double amount);
	
	/*****************************************************/
	/**
	 * This methods is used only for testing. It returns the status of the
	 * account at the index "index"
	 * 
	 * @param index the index of the object in the array
	 * @return true if account is active
	 *         false if account is inactive
	 */
	public boolean getThisaccountStatus(int index);
	
	/**
	 * Search for the account number to see if in arraylist
	 * @param accountNum account number to search for
	 * @return index in the array list containing this account
	 *         return a -1 if the account is not found
	 */
	public int searchAccount(int accountNum);
	
	 
	

}