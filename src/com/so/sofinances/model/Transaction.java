package com.so.sofinances.model;

/**
 * Represents a financial transaction (withdrawal or deposit) made on an account
 *
 * @author  Joseph Rossi
 * @version 1.0 4/3/2014
 */
public class Transaction implements Comparable<Transaction> {

    /**
     * The day, month, and year the transaction occurred
     */
    private TimeData timeOfTransaction;

    /**
     * The amount of currency exchanged
     */
    private double amount;

    /**
     * The name and category (food, rent, entertainment, etc) of the Transaction
     */
    private String name, category;

    /**
     * Boolean flag for whether or not the transaction is a withdrawal
     */
    private boolean isWithdrawal;

    /**
     * Creates a Transaction with data retrieved from UI
     *
     * @param timeOfTransaction the TimeData for when transaction occurred
     * @param amount    the decimal amount (either + or -) of the transaction
     * @param name  the user entered text name of the transaction
     * @param category  the selected "type" of transaction (food, rent, etc)
     * @param isWithdrawal  represents whether or not transaction is + or -
     */
    public Transaction(TimeData timeOfTransaction, double amount,
            String name, String category, boolean isWithdrawal) {
        this.timeOfTransaction = timeOfTransaction;
        this.amount = amount;
        this.setWithdrawal(isWithdrawal);

        this.name = name;
        this.category = category;
    }

    @Override
    public int compareTo(Transaction t) {
        return t.getTimeOfTransaction().compareTo(this.getTimeOfTransaction());
    }

    @Override
    public String toString() {
        return name + ", " + category + ": " + Currency.format(amount);
    }

    /**
     * @return the timeOfTransaction
     */
    public TimeData getTimeOfTransaction() {
        return timeOfTransaction;
    }

    /**
     * @param timeOfTransaction the timeOfTransaction to set
     */
    public void setTimeOfTransaction(TimeData timeOfTransaction) {
        this.timeOfTransaction = timeOfTransaction;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @return the isWithdrawal
     */
    public boolean isWithdrawal() {
        return isWithdrawal;
    }

    /**
     * @param isWithdrawal the isWithdrawal to set
     */
    public void setWithdrawal(boolean isWithdrawal) {
        this.isWithdrawal = isWithdrawal;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }
}
