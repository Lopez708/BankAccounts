import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class SavingAccountTest {

	BankAccountManager Baccounts;

	@Before
	public void setUp() throws Exception {
		Baccounts = new BankAccountManager();
		Baccounts.addAccount(1,1000,100.00,2); 
		Baccounts.addAccount(1,1001,150.00,3); 
		Baccounts.addAccount(1,1002,300,5);   
		Baccounts.addAccount(1,1003,250,5.2);   
 	}
	
	@After
	public void tearDown() throws Exception {
		Baccounts = null;
	}

	@Test
	public void testNumOfAccounts() {
		assertEquals(4,Baccounts.getNumSaving());	 
	}

	 
	@Test
	public void testTotalAccountBalance() {
		assertEquals(800.00, Baccounts.totalAccountBalance(),0.10 );
		 
	}
	@Test
	public void testCredit() {
		Baccounts.credit(1002, 100);
		assertEquals(900.00, Baccounts.totalAccountBalance(),0.10 );
		Baccounts.credit(1003, 25);
		assertEquals(925.00, Baccounts.totalAccountBalance(),0.10 );
	}
	
	@Test
	public void testDebit1() {
		Baccounts.debit(1003, 75.00);
		assertEquals(725.00, Baccounts.totalAccountBalance(),0.10 );
		Baccounts.debit(1002,100.00);
		assertEquals(625.00, Baccounts.totalAccountBalance(),0.10 );
		Baccounts.debit(8000,100); 
		assertEquals(625.00, Baccounts.totalAccountBalance(),0.10 );
	}
	
	@Test
	public void testDebit2() {
		Baccounts.debit(1002,250.00);  
		assertFalse(Baccounts.getThisaccountStatus(2));
		Baccounts.credit(1002,300.00);
		assertTrue(Baccounts.getThisaccountStatus(2));
	}
	
	@Test
	public void testStartMonthlyProcess1() {	
		Baccounts.startMonthlyProcess();
		assertEquals(802.88, Baccounts.totalAccountBalance(),0.10 ); 	
	}
	
	@Test
	public void testStartMonthlyProcess2() {	 	
		for (int i = 0; i < 8; i++ ) {
			Baccounts.debit(1003,10.00);
		}
		Baccounts.startMonthlyProcess();   
		assertEquals(718.52, Baccounts.totalAccountBalance(),0.10 );
	}	 
}