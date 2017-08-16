package com.jason.network.mode.socket;

/**
 * Created by jason-geng on 8/16/17.
 */
public class SingleThreadApplication {

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new SocketHttpClient().start();
        }

    }
}
