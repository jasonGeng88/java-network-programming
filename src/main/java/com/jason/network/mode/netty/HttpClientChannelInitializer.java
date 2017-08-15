package com.jason.network.mode.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

/**
 * Created by jason-geng on 8/16/17.
 */
public class HttpClientChannelInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline().addLast(new HttpClientHandler());
    }
}
