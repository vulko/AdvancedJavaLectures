package com.kvolkov.advancedjava.bankingexample;

import com.kvolkov.advancedjava.bankingexample.Transaction;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {


    public static void main(String[] args) {
        ClientHandler clientHandler = new ClientHandler();
        new Thread(clientHandler).start();

        TransactionHandler transactionHandler = new TransactionHandler();
        try {
            transactionHandler.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean isRunning = true;

        System.out.println("APP FINISHED!");
    }
}
