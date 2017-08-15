package com.jason.network.mode.netty;

import com.jason.network.constant.HttpConstant;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * Created by jason-geng on 8/16/17.
 */
public class HttpClientHandler extends SimpleChannelInboundHandler {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println(HttpConstant.REQUEST);
        ctx.writeAndFlush(Unpooled.copiedBuffer(HttpConstant.REQUEST, CharsetUtil.UTF_8));

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        ByteBuf in = (ByteBuf) o;

        while (in.isReadable()) { // (1)
            System.out.print((char)in.readByte());
            System.out.flush();
        }
    }
}
