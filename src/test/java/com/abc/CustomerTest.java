package com.abc;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test //Test customer statement generation
    public void testApp(){

        Account checkingAccount = new CheckingAccount("Checking Account");
        Account savingsAccount = new SavingAccount("Savings Account");

        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(100.0);
        savingsAccount.deposit(4000.0);
        savingsAccount.withdraw(200.0);

        assertEquals("Statement for Henry\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $100.00\n" +
                "Total $100.00\n" +
                "\n" +
                "Savings Account\n" +
                "  deposit $4,000.00\n" +
                "  withdrawal $200.00\n" +
                "Total $3,800.00\n" +
                "\n" +
                "Total In All Accounts $3,900.00", henry.getStatement());
    }

    @Test // Test if $50 get transfered to the saving account
    public void testTransfer(){


        Account checkingAccount = new CheckingAccount("Checking Account");
        Account savingsAccount = new SavingAccount("Savings Account");

        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(100.0);
        henry.transfer(checkingAccount,savingsAccount,50.0);

        assertEquals(50.0, savingsAccount.sumTransactions(),DOUBLE_DELTA);
    }

    @Test
    public void testOneAccount(){
        Customer oscar = new Customer("Oscar").openAccount(new SavingAccount("Savings Account"));
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount(){
        Customer oscar = new Customer("Oscar")
                .openAccount(new SavingAccount("Savings Account"));
        oscar.openAccount(new CheckingAccount("Checking Account"));
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Test
    public void testThreeAcounts() {
        Customer oscar = new Customer("Oscar")
                .openAccount(new SavingAccount("Savings Account"));
        oscar.openAccount(new CheckingAccount("Checking Account"));
        oscar.openAccount(new MaxiSavingAccount("Maxi-Savings Account"));
        assertEquals(3, oscar.getNumberOfAccounts());
    }

}
