package com.company;

public interface BankTransaction {
    void deposit(double amount);
    void withdraw(double amount);
    void withdrawFromCard(double amount);
    double sendMoney(double amount);
    public void applyInterest();

}
