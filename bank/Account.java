package bank;

public class Account extends BankAccount implements Transaction {
    private double balance;

   
    private static final String BANK_NAME = "ABC Bank";

  
    private static int accountCount = 0;

    public Account(String accountNumber, String accountHolderName, double balance) {
        super(accountNumber, accountHolderName);
        this.balance = balance;
        accountCount++;
    }

        @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Invalid or insufficient balance.");
        }
    }

    
    @Override
    public double calculateInterest() {
      
        return balance * 0.05;
    }

    
    public final String getBankName() {
        return BANK_NAME;
    }

    public double getBalance() {
        return balance;
    }

    public void displayAccountDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: " + balance);
        System.out.println("Bank Name: " + BANK_NAME);
    }
}
