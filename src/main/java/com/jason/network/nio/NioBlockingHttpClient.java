package com.jason.network.nio;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * Created by jason-geng on 8/15/17.
 */
public class NioBlockingHttpClient {

    private final static String HOST = "www.baidu.com";
    private final static int PORT = 80;

    private SocketChannel socketChannel;


    public static void main(String[] args) throws IOException {
        NioBlockingHttpClient client = new NioBlockingHttpClient();

        client.request();
    }

    public NioBlockingHttpClient() throws IOException {
        socketChannel = SocketChannel.open();
        socketChannel.socket().setSoTimeout(5000);
        SocketAddress remote = new InetSocketAddress(HOST, PORT);
        this.socketChannel.connect(remote);
    }

    private PrintWriter getWriter(Socket socket) throws IOException {
        OutputStream out = socket.getOutputStream();
        return new PrintWriter(out);
    }

    private BufferedReader getReader(Socket socket) throws IOException {
        InputStream in = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(in));
    }

    public void request() throws IOException {
        PrintWriter pw = getWriter(socketChannel.socket());
        BufferedReader br = getReader(socketChannel.socket());

        String requestContent = getRequestContent();

        System.out.println(requestContent);

        pw.write(requestContent);
        pw.flush();
        String msg;
        while ((msg = br.readLine()) != null){
            System.out.println("received:");
            System.out.println(msg);
        }
    }

    private String getRequestContent(){
        return "GET / HTTP/1.1\r\n" +
                "Host: " + HOST + "\r\n" +
                "User-Agent: curl/7.43.0\r\n" +
                "Accept: */*\r\n\r\n";
    }
}
