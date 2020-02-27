package com.kvolkov.advancedjava.bankingexample;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class TransactionHandler {

    private static class TransactionQueueProcessThread extends Thread {
        private Queue<Transaction> mProcessQueue;
        private long mId;

        private TransactionQueueProcessThread(Queue<Transaction> queue, long id) {
            super();
            mProcessQueue = queue;
            mId = id;
        }

        @Override
        public void run() {
            Transaction transaction;
            while (!isInterrupted()) {
                transaction = mProcessQueue.peek();

                if (transaction != null) {
                    applyTransaction(transaction);
                    mProcessQueue.poll();
                } else {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        private void applyTransaction(Transaction send) {
            Client clientFrom = sClientsMap.get(send.getFromUid());
            Client clientTo = sClientsMap.get(send.getToUid());

            synchronized (clientFrom) {
                synchronized (clientTo) {
                    if (sEnableDebug) System.out.println("T(" + mId + "): start process transaction (" + send.getUID() + ")");
                    float currentFromBalance = clientFrom.getBalance();
                    currentFromBalance -= send.getAmount();
                    if (currentFromBalance >= 0.f) {
                        clientFrom.applyOutgoingTransaction(send);
                        clientTo.applyIncomingTransaction(send);
                        if (sEnableDebug) {
                            System.out.println("        " + clientFrom.getName() + " sent to " + clientTo.getName() + " " + send.getAmount() + "rur");
                            System.out.println("        " + clientTo.toString());
                        }
                    } else {
                        if (sEnableDebug) {
                            System.out.println("        " + "Sorry, " + clientFrom.getName() + " has insufficient balance.");
                        }
                    }
                    if (sEnableDebug) System.out.println("T(" + mId + "): end process transaction (" + send.getUID() + ")");
                }
            }
        }
    }

    public static List<Queue<Transaction>> mTransactionQueues = new ArrayList(4);

    private static Map<Integer, Client> sClientsMap = new HashMap();
    private static boolean sEnableDebug = true;
    private static final int QUEUE_SIZE = 1000;
    private static final int CLIENTS_SIZE = 100000;

    static {
        Client cl = null;
        Random random = new Random();
        for (int i = 0; i <= CLIENTS_SIZE; ++i) {
            cl = new Client("CL " + i, 1000);
            sClientsMap.put(cl.getUid(), cl);
        }

        mTransactionQueues.add(new ArrayBlockingQueue<Transaction>(QUEUE_SIZE));
//        mTransactionQueues.add(new LinkedList());
//        mTransactionQueues.add(new LinkedList());
//        mTransactionQueues.add(new LinkedList());
    }

//    private void initQueue(Deque<Transaction> trList) {
//        Random random = new Random();
//        for (int i = 0; i < QUEUE_SIZE; ++i) {
//            trList.add(new Transaction(random.nextInt(CLIENTS_SIZE),
//                                       random.nextInt(CLIENTS_SIZE),
//                              random.nextFloat() + 1.f));
//        }
//    }
//
    public void start() throws InterruptedException {
        startThreads();
    }

    private void startThreads() throws InterruptedException {
        Thread t1, t2, t3, t4;
        t1 = new TransactionQueueProcessThread(mTransactionQueues.get(0), 0);
        t1.start();
        t1.join();

//        t2 = new TransactionQueueProcessThread(mTransactionQueues.get(1), 1);
//        t2.start();
//
//        t3 = new TransactionQueueProcessThread(mTransactionQueues.get(2), 2);
//        t3.start();
//
//        t4 = new TransactionQueueProcessThread(mTransactionQueues.get(3), 3);
//        t4.start();
    }


}
