package com.company;

import com.company.exceptions.NoMoneyException;

public class DemandAccount extends AccountType implements BankTransaction {
    private static double FEE = 2.5;

    public DemandAccount() {
        super();
    }

    public DemandAccount(int accountNumber, double fee) {
        super(accountNumber);
        FEE = fee;
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("Amount %.2f deposited%n", amount);

            // Apply Transaction Fee
            balance -= FEE;
            System.out.printf("Fee %.2f Applied%n", FEE);
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
                System.out.printf("Amount of %.2f withdrawn from your Demand Account%n", amount);
                balance -= amount;
                System.out.printf("Current Demand Account Balance is: %.2f%n", balance +
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
            if ((amount + FEE) <= balance) {

                System.out.printf("Amount of %.2f withdrawn from Account%n", amount);
                balance -= amount;
                balance -= FEE;
                System.out.printf("Fee of %.2f applied%n", FEE);
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
