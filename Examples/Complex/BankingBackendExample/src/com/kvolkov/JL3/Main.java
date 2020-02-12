package com.kvolkov.JL3;

import java.util.*;

public class Main {


    public static void main(String[] args) throws InterruptedException {
        TransactionHandler transactionHandler = new TransactionHandler();
        long tsStart, tsEnd;

        tsStart = System.currentTimeMillis();
        try {
            transactionHandler.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tsEnd = System.currentTimeMillis();

        System.out.println("Processing took " + (tsEnd - tsStart) + "ms");
    }
}
