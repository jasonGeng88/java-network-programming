package com.jason.network.mode.netty;

import com.jason.network.constant.HttpConstant;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by jason-geng on 8/16/17.
 */
public class NettyHttpClient {

    public static void main(String[] args) throws InterruptedException {
        new NettyHttpClient().start();
    }

    public void start() throws InterruptedException {
        Bootstrap b = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        b.group(group)
                .channel(NioSocketChannel.class)
                .remoteAddress(HttpConstant.HOST, HttpConstant.PORT)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new HttpClientChannelInitializer());

        for (int i = 0; i < 3; i++) {
            b.connect().sync();
        }

    }

}
