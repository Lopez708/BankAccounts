import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class BankAccountManagerTest {

	BankAccountManager accounts;
	BankAccountManager anAccount;

	@Before
	public void setUp() throws Exception {
		accounts = new BankAccountManager();
		accounts.addAccount(1,1000,200.00,2); //Saving Account
		accounts.addAccount(1,1001,150.00,3); //Saving Account
		accounts.addAccount(2,9000,400,0);    //Checking
 	}
	
	@After
	public void tearDown() throws Exception {
		accounts = null;
	}

	@Test
	public void testNumOfAccounts() {
	
		assertEquals(2,accounts.getNumSaving());
		assertEquals(1,accounts.getNumChecking());	 
	}

	 
	@Test
	public void testTotalAccountBalance() {
		assertEquals(750.00, accounts.totalAccountBalance(),0.10 );
		 
	}
	@Test
	public void testCredit() {
		boolean result = accounts.credit(1000, 50);
		if(result)
			assertEquals(800.00, accounts.totalAccountBalance(),0.10 );
		else
			fail("800 in not an invalid account number");
		result = accounts.credit(9000,100);
		if(result)
			assertEquals(900.00, accounts.totalAccountBalance(),0.10 );
		else
			fail("9000 is not an invalid account number");
		result = accounts.credit(2000, 50);
		if(result)
			fail("2000 is an invalid account number");
		else
			assertEquals(false, result);
	}
	
	@Test
	public void testDebit1() {
		boolean result = accounts.debit(1000, 50.00);
		if(result)
			assertEquals(700.00, accounts.totalAccountBalance(),0.10 );
		else
			fail("1000 is not an invalid account number");
		result = accounts.debit(9000,100.00);
		if(result)
		assertEquals(600.00, accounts.totalAccountBalance(),0.10 );
		else
			fail("9000 is not an invalid account number");
		result = accounts.debit(8000,100);   /*This account does not exist, therefore 
									          total balance should stay the same*/
		if(!result)
		assertEquals(600.00, accounts.totalAccountBalance(),0.10 );
		else
			fail("8000 is an invalid account number");
	}
	
	@Test
	public void testDebit2() {
		accounts.debit(1000,150.00);   /* account balance drops below the minimum balance 
								          account will be inactive */
		assertFalse(accounts.getThisaccountStatus(0));
		assertEquals(600.00, accounts.totalAccountBalance(),0.10 );
		
	}
	
	@Test
	public void testDebit3() {
	
		accounts.debit(9000,395.00); //makes account balance below 10 and status inactive
		assertEquals(355.00, accounts.totalAccountBalance(),0.10 );   
		assertFalse(accounts.getThisaccountStatus(2)); //inactive
		accounts.credit(9000,10.00);  
		assertTrue(accounts.getThisaccountStatus(2)); //account should be active
	}
	
	@Test
	public void testStartMonthlyProcess1() {	
		accounts.startMonthlyProcess();
		assertEquals(750.71, accounts.totalAccountBalance(),0.10 ); 	
	}
	
	@Test
	public void testStartMonthlyProcess2() {	 	
		for (int i = 0; i<6; i++ )
			accounts.debit(1000,10.00);	    //Exceeds maximum # of withdraws which is 4
		
		accounts.startMonthlyProcess();   
		assertEquals(688.61, accounts.totalAccountBalance(),0.10 );
	}	 
}