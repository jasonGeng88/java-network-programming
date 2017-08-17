package com.jason.network.mode.socket;

import com.jason.network.constant.HttpConstant;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jason-geng on 8/16/17.
 */
public class ThreadPoolApplication {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        for (final String host: HttpConstant.HOSTS) {

            Thread t = new Thread(new Runnable() {
                public void run() {
                    new SocketHttpClient().start(host, HttpConstant.PORT);
                }
            });

            executorService.submit(t);
            new SocketHttpClient().start(host, HttpConstant.PORT);

        }

    }
}
