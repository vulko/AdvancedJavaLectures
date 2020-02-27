package com.kvolkov.advancedjava.bankingexample;

import java.io.Serializable;

public final class Transaction implements Serializable {

    private static int sUidAutoIncrement = 0;

    private final int mUID;
    private final int mFromUid;
    private final int mToUid;
    private final float mAmount;

    public Transaction(int mFromUid, int mToUid, float mAmount) {
        this.mFromUid = mFromUid;
        this.mToUid = mToUid;
        this.mAmount = mAmount;
        mUID = sUidAutoIncrement++;
    }

    public int getFromUid() {
        return mFromUid;
    }

    public int getToUid() {
        return mToUid;
    }

    public float getAmount() {
        return mAmount;
    }

    public int getUID() { return mUID; }

    @Override
    public String toString() {
        return "Transaction: FROM = " + mFromUid +
                " | TO = " + mToUid +
                " | Amount = " + mAmount +
                " | mUID=" + mUID;
    }
}
