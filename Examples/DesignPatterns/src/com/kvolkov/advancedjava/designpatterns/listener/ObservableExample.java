package com.kvolkov.advancedjava.designpatterns.listener;

public class ObservableExample {

    public interface OnTickListener {
        void onTick();
        void on10Ticks();
    }

    public static class Ticker {

        private OnTickListener mListener = null;
        private int nTicks = 0;

        public Ticker(OnTickListener listener) {
            mListener = listener;
        }

        public void tick() {
            // do something...
            if (nTicks == 10) {
                if (mListener != null) {
                    mListener.on10Ticks();
                }
                nTicks = 0;
            } else {
                if (mListener != null) {
                    mListener.onTick();
                    nTicks++;
                }
            }
        }

    }

    public static void start() {
        OnTickListener listener = new OnTickListener() {

            @Override
            public void onTick() {
                System.out.println("tick...");
            }

            @Override
            public void on10Ticks() {
                System.out.println("  !!! 10 ticks!");
            }
        };

        Ticker ticker = new Ticker(listener);
        while (true) {
            ticker.tick();
            System.out.println("tac...");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
