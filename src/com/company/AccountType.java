package com.company;

public class AccountType implements BankTransaction {

    private int accountNumber;
    protected double balance;

    public AccountType() {
    }

    public AccountType(int accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public void deposit(double amount) {

    }

    @Override
    public void withdrawFromCard(double amount) {

    }

    @Override
    public void withdraw(double amount) {

    }

    @Override
    public double sendMoney(double amount) {
        return 0;
    }

    @Override
    public void applyInterest() {

    }
    //
//    Scanner keyboard = new Scanner(System.in);
//
//    // Create array of Accounts
//    Account accounts [] = new Account[10];
//    int numAccounts = 0;
//
//    int choice;
//
//        do {
//        choice = menu(keyboard);
//        System.out.println();
//
//        if(choice == 1) {
//            accounts[numAccounts++] = createAccount(keyboard);
//        } else if(choice == 2) {
//            doDeposit(accounts, numAccounts, keyboard);
//        } else if(choice == 3) {
//            doWithdraw(accounts, numAccounts, keyboard);
//        } else if(choice == 4) {
//            applyInterest(accounts, numAccounts, keyboard);
//        } else {
//            System.out.println("GoodBye!");
//        }
//        System.out.println();
//    } while(choice != 5);
//}

}
