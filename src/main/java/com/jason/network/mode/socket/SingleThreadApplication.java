package com.jason.network.mode.socket;

import com.jason.network.constant.HttpConstant;

/**
 * Created by jason-geng on 8/16/17.
 */
public class SingleThreadApplication {

    public static void main(String[] args) {

        for (final String host: HttpConstant.HOSTS) {

            new SocketHttpClient().start(host, HttpConstant.PORT);

        }

    }
}
