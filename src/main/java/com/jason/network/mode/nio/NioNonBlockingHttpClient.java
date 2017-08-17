package com.jason.network.mode.nio;

import com.jason.network.constant.HttpConstant;
import com.jason.network.util.HttpUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by jason-geng on 8/15/17.
 */
public class NioNonBlockingHttpClient {

    private static Selector selector;
    private Charset charset = Charset.forName("utf8");

    static {
        try {
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {

        NioNonBlockingHttpClient client = new NioNonBlockingHttpClient();

        for (String host: HttpConstant.HOSTS) {

            client.request(host, HttpConstant.PORT);

        }

        client.select();

    }

    public void request(String host, int port) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.socket().setSoTimeout(5000);
        SocketAddress remote = new InetSocketAddress(host, port);
        socketChannel.connect(remote);
        socketChannel.configureBlocking(false);
        socketChannel.register(selector,
                        SelectionKey.OP_READ
                        | SelectionKey.OP_WRITE);

        System.out.println(HttpUtil.compositeRequest(host));
        socketChannel.write(charset.encode(HttpUtil.compositeRequest(host)));
    }

    public void select() throws IOException {
        while (selector.select() > 0){
            Set keys = selector.selectedKeys();

            Iterator it = keys.iterator();

            while (it.hasNext()){

                SelectionKey key = (SelectionKey)it.next();
                it.remove();

                if (key.isReadable()){
                    receive(key);
                }
            }
        }
    }

    private void receive(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer);
        buffer.flip();
        String receiveData = charset.decode(buffer).toString();

        if (!receiveData.contains("\n")) {
            key.cancel();
            channel.close();
            return;
        }

        System.out.println(receiveData);
    }
}
