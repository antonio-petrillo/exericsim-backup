/*

Since this exercise has a difficulty of > 4 it doesn't come
with any starter implementation.
This is so that you get to practice creating classes and methods
which is an important part of programming in Java.

Please remove this comment when submitting your solution.

*/
public class BankAccount {
    private boolean active;
    private int balance;

    public BankAccount() {
        active = false;
        balance = 0;
    }

    public synchronized void open() {
        active = true;
    }

    public synchronized void close() {
        active = false;
    }

    private synchronized boolean isOpen() {
        return active;
    }

    public synchronized int getBalance() throws BankAccountActionInvalidException {
        if (!isOpen()) {
            throw new BankAccountActionInvalidException("Account closed");
        }
        return balance;
    }

    public synchronized void deposit(int amount) throws BankAccountActionInvalidException {
        if (!isOpen()) {
            throw new BankAccountActionInvalidException("Account closed");
        }
        if (amount < 0) {
            throw new BankAccountActionInvalidException("Cannot deposit or withdraw negative amount");
        }
        balance += amount;
    }

    public synchronized void withdraw(int amount) throws BankAccountActionInvalidException {
        if (!isOpen()) {
            throw new BankAccountActionInvalidException("Account closed");
        }

        if (amount < 0) {
            throw new BankAccountActionInvalidException("Cannot deposit or withdraw negative amount");
        }

        if (balance == 0) {
            throw new BankAccountActionInvalidException("Cannot withdraw money from an empty account");
        }

        if (balance < amount) {
            throw new BankAccountActionInvalidException("Cannot withdraw more money than is currently in the account");
        }

        balance -= amount;
    }

    // Even with this simple exercise it's clear to me that multitreading is much
    // more simple in clojure.
}
