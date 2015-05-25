import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CheckingAccountTest {

	BankAccountManager Baccounts;

	@Before
	public void setUp() throws Exception {
		Baccounts = new BankAccountManager();
		Baccounts.addAccount(2,2000,500.00,0); 
		Baccounts.addAccount(2,2001,777.00,0); 
 	}
	
	@After
	public void tearDown() throws Exception {
		Baccounts = null;
	}

	@Test
	public void testNumOfAccounts() {
		assertEquals(2,Baccounts.getNumChecking());	 
	}

	 
	@Test
	public void testTotalAccountBalance() {
		assertEquals(1277.00, Baccounts.totalAccountBalance(),0.10 );
		 
	}
	@Test
	public void testCredit() {
		Baccounts.credit(2000, 50);
		assertEquals(1327.00, Baccounts.totalAccountBalance(),0.10 );
		Baccounts.credit(2001, 797.40);
		assertEquals(2124.40, Baccounts.totalAccountBalance(),0.10 );
	}
	
	@Test
	public void testDebit1() {
		Baccounts.debit(2000, 90.00);
		assertEquals(1187.00, Baccounts.totalAccountBalance(),0.10 );
		Baccounts.debit(2001,101.00);
		assertEquals(1086.00, Baccounts.totalAccountBalance(),0.10 );
		Baccounts.debit(9000,100); 
		assertEquals(1086.00, Baccounts.totalAccountBalance(),0.10 );
	}
	
	@Test
	public void testDebit2() {
		Baccounts.debit(2000, 495.00); 
		assertFalse(Baccounts.getThisaccountStatus(0));
		Baccounts.credit(2000,300.00);
		assertTrue(Baccounts.getThisaccountStatus(0));
	}
	
	@Test
	public void testStartMonthlyProcess1() {
		Baccounts.debit(2000, 495.00);
		Baccounts.startMonthlyProcess();
		assertEquals(777.00, Baccounts.totalAccountBalance(),0.10 ); 	
	}
}