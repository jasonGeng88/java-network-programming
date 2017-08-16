package com.jason.network.mode.socket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jason-geng on 8/16/17.
 */
public class ThreadPoolApplication {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Runnable() {
                public void run() {
                    new SocketHttpClient().start();
                }
            });

            executorService.submit(t);
        }

    }
}
