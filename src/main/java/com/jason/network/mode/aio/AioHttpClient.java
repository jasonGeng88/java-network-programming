package com.jason.network.mode.aio;

import com.jason.network.constant.HttpConstant;
import com.jason.network.util.HttpUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;

/**
 * Created by jason-geng on 8/22/17.
 */
public class AioHttpClient {

    private Charset charset = Charset.forName("utf8");

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException, TimeoutException {

        AioHttpClient client = new AioHttpClient();

        for (String host: HttpConstant.HOSTS) {

            client.request(host, HttpConstant.PORT);

        }
    }

    public void request(String host, int port) throws IOException, ExecutionException, InterruptedException, TimeoutException {
        AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open();

        SocketAddress remote = new InetSocketAddress(host, port);

        Future f = socketChannel.connect(remote);

        f.get();

        String request = HttpUtil.compositeRequest(host);
        System.out.println(request);

        ByteBuffer writeBuf = charset.encode(request);
        socketChannel.write(writeBuf, null, new CompletionHandler<Integer, Object>() {

            public void completed(Integer result, Object attachment) {
                System.out.println("write");
                System.out.println(result);
            }

            public void failed(Throwable exc, Object attachment) {
                System.out.println("write failed");
                System.out.println(exc);
            }

        });



//        while (true){

//        }

        read(socketChannel);

        System.out.printf("aaaaa");
        Thread.sleep(5000);

        read(socketChannel);
        System.out.printf("bbbbb");




//        socketChannel.read(buffer);
//        buffer.flip();
//        String receiveData = charset.decode(buffer).toString();
//        System.out.println(receiveData);
    }

    private void read(AsynchronousSocketChannel socketChannel){
        final ByteBuffer readBuf = ByteBuffer.allocate(4800);
        socketChannel.read(readBuf, null, new CompletionHandler<Integer, Object>() {
            public void completed(Integer result, Object attachment) {
                System.out.println(result);
                if (result < 0){
                    System.exit(0);
                }
                readBuf.flip();
                System.out.println(charset.decode(readBuf).toString());
                readBuf.clear();
            }

            public void failed(Throwable exc, Object attachment) {
                System.out.println("read failed");
                System.out.println(exc);
            }
        });
    }

}
