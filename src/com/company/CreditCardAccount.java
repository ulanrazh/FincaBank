package com.company;

import com.company.exceptions.NoMoneyException;

import java.util.Random;

public class CreditCardAccount extends AccountType implements BankTransaction {
    private double cardIntRate;
    private String cardNumber;

    public CreditCardAccount() {
        super();
    }

    public CreditCardAccount(int accountNumber, double cardIntRate, String cardNumber) {
        super(accountNumber);
        this.cardIntRate = 2.5;
        this.cardNumber = generateCardNumber();
    }

    public double getCardIntRate() {
        return cardIntRate;
    }

    public void setCardIntRate(double cardIntRate) {
        this.cardIntRate = cardIntRate;
    }

    public double calcInterest() {
        return balance - (balance * (cardIntRate/100));
    }
    public static String generateCardNumber() {
        Random random = new Random();
        String cardNumber = "";
        for (int i = 0; i < 4; i++) {
            cardNumber += random.nextInt(1000, 10000);
        }
        return cardNumber;
    }

    @Override
    public void withdrawFromCard(double amount) {
        if (amount > 0) {
            // Check sufficient balance
            if (balance == 0) {
                System.out.println("No sufficient balance in this account " + this.getAccountNumber() +
                        "\n Please deposit your account " + this.getAccountNumber());
            } else if (balance == amount) {
                System.out.printf("Amount of %.2f withdrawn from your Credit Card Account%n", amount);
                balance -= amount;
                System.out.printf("Current Credit Card Account Balance is: %.2f%n", balance +
                        "\n Transfer amount is: " + amount);
            }
        } else {
            System.out.println("Negative amount cannot be withdrawn!");
        }
    }

    @Override
    public void applyInterest() {
        double interest = calcInterest();
        System.out.printf("Percent Interest %.2f subtracted from balance%n", interest);
        deposit(interest);
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("Amount %.2f deposited%n", amount);
            System.out.printf("Current Credit Card Balance is: %.2f%n", balance);

        } else {
            System.out.println("A negative amount cannot be deposited");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0) {
            // Check sufficient balance
            if ((amount) <= balance) {
                System.out.printf("Amount of %.2f withdrawn from Credit Card%n", amount);
                balance -= amount;
                System.out.printf("Current Credit Card Balance is: %.2f%n", balance);
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
