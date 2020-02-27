package com.kvolkov.advancedjava.bankingexample;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientHandler implements Runnable {

    public void start() {
        try {
            ServerSocket socket = new ServerSocket(45776);
            Socket clientSocket = socket.accept();

            System.out.println("Connection established! " + clientSocket.getInetAddress());
            ObjectInputStream inStream = new ObjectInputStream(clientSocket.getInputStream());

            while (true) {
                Object obj = inStream.readObject();

                if (obj == null) {
                    System.out.println("Stream was closed on other end...");
                    break;
                }

                if (!(obj instanceof Transaction)) {
                    System.out.println("UNEXPECTED DATA! " + obj.toString());
                    continue;
                }

                Transaction transaction = (Transaction) obj;
                if (transaction != null) {
                    System.out.println("Incoming transaction = " + transaction.toString());

                    TransactionHandler.mTransactionQueues.get(0).add(transaction);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Server finished reading incoming data");
    }

    @Override
    public void run() {
        start();
    }
}
