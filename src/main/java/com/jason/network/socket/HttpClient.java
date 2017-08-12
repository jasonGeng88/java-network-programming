package com.jason.network.socket;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by jason-geng on 8/13/17.
 */
public class HttpClient {

    private static final String HOST = "www.baidu.com";
    private static final int PORT = 80;

    public static void main(String[] args) {
        new HttpClient().start();

    }

    public void start() {

        // 初始化 socket
        Socket socket = new Socket();

        try {
            // 设置 socket 连接
            SocketAddress remote = new InetSocketAddress(HOST, PORT);
            socket.setSoTimeout(5000);
            socket.connect(remote);

            // 发起请求
            PrintWriter writer = getWriter(socket);
            String requestContent = getRequestContent();
            writer.write(requestContent);
            writer.flush();

            // 读取响应
            String msg;
            BufferedReader reader = getReader(socket);
            while ((msg = reader.readLine()) != null){
                System.out.println(msg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private String getRequestContent(){
        StringBuilder sb = new StringBuilder()
                .append("GET / HTTP/1.1\r\n")
                .append("Host: " + HOST + "\r\n")
                .append("User-Agent: curl/7.43.0\r\n")
                .append("Accept: */*\r\n\r\n");
        return sb.toString();
    }

    private BufferedReader getReader(Socket socket) throws IOException {
        InputStream in = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(in));
    }

    private PrintWriter getWriter(Socket socket) throws IOException {
        OutputStream out = socket.getOutputStream();
        return new PrintWriter(new OutputStreamWriter(out));
    }
}
