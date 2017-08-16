package com.jason.network.mode.socket;

/**
 * Created by jason-geng on 8/16/17.
 */
public class MultiThreadApplication {

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Runnable() {
                public void run() {
                    new SocketHttpClient().start();
                }
            });

            t.start();
        }
    }
}
