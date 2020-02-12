package com.kvolkov.JL3;

public final class Transaction {

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
}
