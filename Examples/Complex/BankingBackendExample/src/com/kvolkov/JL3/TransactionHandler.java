package com.kvolkov.JL3;

import java.util.*;

public class TransactionHandler {

    private static Map<Integer, Client> sClientsMap = new HashMap();
    private static boolean sEnableDebug = false;
    private static final int QUEUE_SIZE = 1000000;
    private static final int CLIENTS_SIZE = 100000;

    static {
        Client cl = null;
        Random random = new Random();
        for (int i = 0; i <= CLIENTS_SIZE; ++i) {
            cl = new Client("CL " + i, 1000);
            sClientsMap.put(cl.getUid(), cl);
        }
    }

    private List<Deque<Transaction>> mTransactionQueues = new ArrayList(4);

    private void initQueue(Deque<Transaction> trList) {
        Random random = new Random();
        for (int i = 0; i < QUEUE_SIZE; ++i) {
            trList.add(new Transaction(random.nextInt(CLIENTS_SIZE),
                                       random.nextInt(CLIENTS_SIZE),
                              random.nextFloat() + 1.f));
        }
    }

    public void start() throws InterruptedException {
        mTransactionQueues.add(new LinkedList());
        mTransactionQueues.add(new LinkedList());
        mTransactionQueues.add(new LinkedList());
        mTransactionQueues.add(new LinkedList());

        initQueue(mTransactionQueues.get(0));
        initQueue(mTransactionQueues.get(1));
        initQueue(mTransactionQueues.get(2));
        initQueue(mTransactionQueues.get(3));

        startThreads();
    }

    private static class TransactionQueueProcessThread extends Thread {
        private Deque<Transaction> mProcessQueue;
        private long mId;

        private TransactionQueueProcessThread(Deque<Transaction> queue, long id) {
            super();
            mProcessQueue = queue;
            mId = id;
        }

        @Override
        public void run() {
            Transaction transaction;
            while (!mProcessQueue.isEmpty()) {
                transaction = mProcessQueue.poll();
                applyTransaction(transaction);
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

    private void startThreads() throws InterruptedException {
//        Thread t1, t2, t3, t4;
//        t1 = new TransactionQueueProcessThread(mTransactionQueues.get(0), 0);
//        t1.start();
//
//        t2 = new TransactionQueueProcessThread(mTransactionQueues.get(1), 1);
//        t2.start();
//
//        t3 = new TransactionQueueProcessThread(mTransactionQueues.get(2), 2);
//        t3.start();
//
//        t4 = new TransactionQueueProcessThread(mTransactionQueues.get(3), 3);
//        t4.start();
//
//        t1.join();
//        t2.join();
//        t3.join();
//        t4.join();
    }


}
