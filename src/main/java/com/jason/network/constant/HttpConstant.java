package com.jason.network.constant;

/**
 * Created by jason-geng on 8/16/17.
 */
public interface HttpConstant {

    String HOST = "www.baidu.com";
    int PORT = 80;

    String REQUEST = "GET / HTTP/1.1\r\n" +
            "Host: " + HOST + "\r\n" +
            "User-Agent: curl/7.43.0\r\n" +
            "Accept: */*\r\n\r\n";
}
