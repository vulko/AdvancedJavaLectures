package com.kvolkov.advancedjava.bankingexample;

import com.kvolkov.advancedjava.bankingexample.Transaction;

public class Client {

    private static int sUidAutoIncrement = -1;

    private String mName;
    private float mBalance;
    private final int mUid;

    private Client() {
        mUid = -1;
    }

    public Client(String name, float balance) {
        mUid = ++sUidAutoIncrement;
        mName = name;
        mBalance = balance;
    }

    public int getUid() {
        return mUid;
    }

    public float getBalance() {
        return mBalance;
    }

    public String getName() {
        return mName;
    }

    public void applyIncomingTransaction(Transaction incTransaction) {
        mBalance += incTransaction.getAmount();
    }

    public void applyOutgoingTransaction(Transaction outTransaction) {
        mBalance -= outTransaction.getAmount();
    }

    @Override
    public String toString() {
        return mName + " has " + mBalance + "rur";
    }
}
