package com.jason.network.mode.nio;

import com.jason.network.constant.HttpConstant;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;

/**
 * Created by jason-geng on 8/15/17.
 */
public class NioBlockingHttpClient {

    private SocketChannel socketChannel;


    public static void main(String[] args) throws IOException {
        NioBlockingHttpClient client = new NioBlockingHttpClient();

        client.request();
    }

    public NioBlockingHttpClient() throws IOException {
        socketChannel = SocketChannel.open();
        socketChannel.socket().setSoTimeout(5000);
        SocketAddress remote = new InetSocketAddress(HttpConstant.HOST, HttpConstant.PORT);
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

        System.out.println(HttpConstant.REQUEST);

        pw.write(HttpConstant.REQUEST);
        pw.flush();
        String msg;
        while ((msg = br.readLine()) != null){
            System.out.println("received:");
            System.out.println(msg);
        }
    }
}
