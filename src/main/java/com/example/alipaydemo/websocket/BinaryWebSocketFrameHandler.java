package com.example.alipaydemo.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.buffer.ByteBuf;

import java.util.Arrays;

public class BinaryWebSocketFrameHandler extends SimpleChannelInboundHandler<BinaryWebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BinaryWebSocketFrame msg) throws Exception {
        // 获取二进制数据
        ByteBuf content = msg.content();
        byte[] byteArray = new byte[content.readableBytes()];
        content.readBytes(byteArray);

        System.out.println("Received binary data: " + Arrays.toString(byteArray));

        // 处理并发送响应
        ByteBuf responseBuffer = ctx.alloc().buffer();
        responseBuffer.writeBytes(new byte[]{5, 6, 7, 8});
        ctx.channel().writeAndFlush(new BinaryWebSocketFrame(responseBuffer));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
