package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Random random = new Random();

        // Create array of Accounts
        AccountType[] accounts = new AccountType[10];
        int numAccounts = 0;
        int choice;

        do {
            choice = menu(keyboard);
            System.out.println();

            if (choice == 1) {
                accounts[numAccounts++] = createAccount(keyboard);
            } else if (choice == 2) {
                doDeposit(accounts, numAccounts, keyboard);
            } else if (choice == 3) {
                doWithdraw(accounts, numAccounts, keyboard);
            } else if (choice == 4) {
                applyInterest(accounts, numAccounts, keyboard);
            } else if (choice == 5) {
                sendMoney(accounts, numAccounts, keyboard);
            } else {
                System.out.println("GoodBye!");
            }
            System.out.println();
        } while (choice != 6);
    }

    public static int menu(Scanner keyboard) {
        System.out.println("Bank Account Menu");
        System.out.println("1. Create New Account");
        System.out.println("2. Deposit Funds");
        System.out.println("3. Withdraw Funds");
        System.out.println("4. Apply Interest");
        System.out.println("5. Transfer Funds");
        System.out.println("6. Quit");

        int choice;

        do {
            System.out.print("Enter choice: ");
            choice = keyboard.nextInt();
        } while (choice < 1 || choice > 6);

        return choice;
    }

    public static int accountMenu(Scanner keyboard) {
        System.out.println("Select Account Type");
        System.out.println("1. Demand Account");
        System.out.println("2. Savings Account");
        System.out.println("3. Credit Card Account");

        int choice;
        do {
            System.out.print("Enter choice: ");
            choice = keyboard.nextInt();
        } while (choice < 1 || choice > 3);

        return choice;
    }

    public static int searchAccount(AccountType[] accounts, int count, int accountNumber) {

        for (int i = 0; i < count; i++) {
            if (accounts[i].getAccountNumber() == accountNumber) {
                return i;
            }
        }

        return -1;
    }

    public static void doDeposit(AccountType[] accounts, int count, Scanner keyboard) {
        // Get the account number
        System.out.print("\nPlease enter account number: ");
        int accountNumber = keyboard.nextInt();

        // search for account
        int index = searchAccount(accounts, count, accountNumber);

        if (index >= 0) {
            // Amount
            System.out.print("Please enter Deposit Amount: ");
            double amount = keyboard.nextDouble();

            accounts[index].deposit(amount);
        } else {
            System.out.println("No account exist with AccountNumber: " + accountNumber);
        }
    }

    public static void doWithdraw(AccountType[] accounts, int count, Scanner keyboard) {
        // Get the account number
        System.out.print("\nPlease enter account number: ");
        int accountNumber = keyboard.nextInt();

        // search for account
        int index = searchAccount(accounts, count, accountNumber);

        if (index >= 0) {
            // Amount
            System.out.print("Please enter Withdraw Amount: ");
            double amount = keyboard.nextDouble();
            if (amount < 0) {
                throw new RuntimeException("No balance in this account " + accountNumber);
            } else {
                accounts[index].withdraw(amount);
            }
        } else {
            System.out.println("No account exist with AccountNumber: " + accountNumber);
        }
    }

    public static void applyInterest(AccountType[] accounts, int count, Scanner keyboard) {
        // Get the account number
        System.out.print("\nPlease enter account number: ");
        int accountNumber = keyboard.nextInt();

        // search for account
        int index = searchAccount(accounts, count, accountNumber);

        if (index >= 0) {

            // must be instance of savings account
            if (accounts[index] instanceof SavingsAccount) {
                ((SavingsAccount) accounts[index]).applyInterest();
            }
        } else {
            System.out.println("No account exist with AccountNumber: " + accountNumber);
        }
    }

    public static AccountType createAccount(Scanner keyboard) {

        AccountType account = null;
        int choice = accountMenu(keyboard);

        int accountNumber;
        System.out.print("Enter Account Number: ");
        accountNumber = keyboard.nextInt();

        if (choice == 1) { // Demand account
            System.out.print("Enter Transaction Fee: ");
            double fee = keyboard.nextDouble();
            account = new DemandAccount(accountNumber, fee);
        } else if (choice == 2) { // Savings account
            System.out.print("Please enter Interest Rate: ");
            double ir = keyboard.nextDouble();
            account = new SavingsAccount(accountNumber, ir);
        } else if (choice == 3) {
            String creditNumber = CreditCardAccount.generateCardNumber();
            System.out.println("Be aware!!! Percent interest to Credit Card is 2.5% per month");
            System.out.println("Your Credit Card number is: " + creditNumber);
            account = new CreditCardAccount(accountNumber, 2.5, creditNumber);
        }
        return account;
    }

    public static int sendMoney(AccountType[] accounts, int count, Scanner keyboard) {
        System.out.print("\nPlease enter sender account number: ");
        int accountNumber = keyboard.nextInt();
        int index = searchAccount(accounts, count, accountNumber);
        System.out.print("\nPlease enter receiver account number: ");
        int accountNumber2 = keyboard.nextInt();
        int index2 = searchAccount(accounts, count, accountNumber2);

        if (index >= 0) {
            // Amount
            System.out.print("Please enter Deposit Amount: ");
            double amount = keyboard.nextDouble();
            if (accounts[index].balance < amount) {
                System.out.println("_______________________________________________________" +
                        "\nNo balance " + accounts[index].balance + " in this account " + accountNumber);
                return accountNumber;
            } else {
                accounts[index].withdrawFromCard(amount);
            }
            accounts[index2].deposit(amount);
//            System.out.println();
        } else {
            System.out.println("No account exist with AccountNumber: " + accountNumber);
            System.out.println("No account exist with AccountNumber: " + accountNumber2);
        }
        return accountNumber;
    }
}