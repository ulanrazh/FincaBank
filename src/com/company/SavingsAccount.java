package com.company;

import com.company.exceptions.NoMoneyException;

public class SavingsAccount extends AccountType implements BankTransaction {

    private double interestRate;

    public SavingsAccount() {
        super();
    }

    public SavingsAccount(int accountNumber, double interestRate) {
        super(accountNumber);
        this.interestRate = 8;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double calcInterest() {
        return balance * (interestRate/100);
    }

    @Override
    public void applyInterest() {
        double interest = calcInterest();
        System.out.printf("Interest amount %.2f added to balance%n", interest);
        deposit(interest);
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("Amount %.2f deposited%n", amount);
            System.out.printf("Current Balance is: %.2f%n", balance);

        } else {
            System.out.println("A negative amount cannot be deposited");
        }
    }

    @Override
    public void withdrawFromCard(double amount) {
        if (amount > 0) {
            // Check sufficient balance
            if (balance == 0) {
                System.out.println("No sufficient balance in this account " + this.getAccountNumber() +
                        "\n Please deposit your account " + this.getAccountNumber());
            } else if (balance == amount) {
                System.out.printf("Amount of %.2f withdrawn from your Savings Account%n", amount);
                balance -= amount;
                System.out.printf("Current Savings Account Balance is: %.2f%n", balance +
                        "\n Transfer amount is: " + amount);
            }
        } else {
            System.out.println("Negative amount cannot be withdrawn!");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0) {
            // Check sufficient balance
            if ((amount) <= balance) {
                System.out.printf("Amount of %.2f withdrawn from Account%n", amount);
                balance -= amount;
                System.out.printf("Current Balance is: %.2f%n", balance);
            }
        } else {
            System.out.println("Negative amount cannot be withdrawn!");
        }
    }

    @Override
    public double sendMoney(double amount) {
        if (balance < amount) {
            throw new NoMoneyException("not enough balance" + balance + " => " + amount);
        } else {
            withdraw(amount);
        }
        return amount;
    }
}
