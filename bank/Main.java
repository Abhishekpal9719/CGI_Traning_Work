package bank;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AccountManager accountManager = new AccountManager();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Bank Menu ---");
            System.out.println("1. Add Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Display Account Details");
            System.out.println("5. Calculate Interest");
            System.out.println("6. Get Account Count");
            System.out.println("7. Get Balance");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter Account Number: ");
                    String accNo = scanner.nextLine();
                    System.out.print("Enter Account Holder Name: ");
                    String accHolder = scanner.nextLine();
                    System.out.print("Enter Initial Balance: ");
                    double balance = scanner.nextDouble();
                    Account acc = new Account(accNo, accHolder, balance);
                    accountManager.addAccount(acc);
                    break;

                case 2:
                    System.out.print("Enter Account Number: ");
                    String depAccNo = scanner.nextLine();
                    Account depAcc = accountManager.findAccount(depAccNo);
                    if (depAcc != null) {
                        System.out.print("Enter Deposit Amount: ");
                        depAcc.deposit(scanner.nextDouble());
                    }
                    break;

                case 3:
                    System.out.print("Enter Account Number: ");
                    String witAccNo = scanner.nextLine();
                    Account witAcc = accountManager.findAccount(witAccNo);
                    if (witAcc != null) {
                        System.out.print("Enter Withdraw Amount: ");
                        witAcc.withdraw(scanner.nextDouble());
                    }
                    break;

                case 4:
                    System.out.print("Enter Account Number: ");
                    String dispAccNo = scanner.nextLine();
                    Account dispAcc = accountManager.findAccount(dispAccNo);
                    if (dispAcc != null) {
                        dispAcc.displayAccountDetails();
                    }
                    break;

                case 5:
                    System.out.print("Enter Account Number: ");
                    String intAccNo = scanner.nextLine();
                    Account intAcc = accountManager.findAccount(intAccNo);
                    if (intAcc != null) {
                        System.out.println("Calculated Interest: " + intAcc.calculateInterest());
                    }
                    break;

                case 6:
                    System.out.println("Total Accounts: " + Account.getAccountCount());
                    break;

                case 7:
                    System.out.print("Enter Account Number: ");
                    String balAccNo = scanner.nextLine();
                    Account balAcc = accountManager.findAccount(balAccNo);
                    if (balAcc != null) {
                        System.out.println("Balance: " + balAcc.getBalance());
                    }
                    break;

                case 8:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 8);

        scanner.close();
    }
}
